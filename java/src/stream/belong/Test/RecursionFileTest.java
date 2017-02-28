package stream.belong.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 递归copy文件
 * 
 * @author Belong
 *
 */
public class RecursionFileTest {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		RecursionFile rf = new RecursionFile();
		rf.setFilecount("d:\\Javafile");
		rf.funpool("D:\\Javafile", "e:\\w");
//		Timer timer = new Timer();
//		rf.setTimer(timer);
//		timer.schedule(rf, 0, 10);
		long end = System.currentTimeMillis();
		System.out.println("一共用时:" + (end - start) + "ms");// 看看copy 所需要的时间
	}
}
/**
 * copy文件夹 和 文件
 * 
 * @author belong
 *
 */
class RecursionFile extends TimerTask {// 递归copy文件
	private int filecount;// 源文件中的文件个数
	private int currentfile = 0;// 当前复制文件的的个数
	private Timer timer;// 计时器类

	@Override
	public void run() {
		if (++currentfile <= filecount) {
			System.out.println("文件拷贝进度是" + (currentfile * 100 / filecount) + "%");// 计算copy了多少文件
		} else {
			timer.cancel();
			System.out.println("文件拷贝完毕！");
		}
	}

	public void setFilecount(String path1) {// 把文件的个数给进度条计数
		this.filecount = new File(path1).listFiles().length;
	}

	public void setTimer(Timer timer) {// 为timer赋值
		this.timer = timer;
	}
	public int getFilecount(){
		return filecount;
	}
	/**
	 * @param path1 源路径
	 * @param path2 目的路径
	 */
	public void funpool(String path1, String path2) {
		File file1 = new File(path1);// 源文件目录
		File file2 = new File(path2, file1.getName());// 得到与源文件相同的名字的文件（只是盘符不同）
		if (file2.exists()) {// （判断目的文件中有没有）和（要从源文件中复制的文件重名的文件）
			System.out.println("要copy文件已存在");
			return;// 如果有直接结束递归方法
		}
		if (file1.isDirectory()) {// 判段源文件是否是目录（true ：在目的文件中建立一个同名的文件夹）
			File tempFile = new File(path2, file1.getName());
			tempFile.mkdirs();// 建立文件夹（复制文件夹）把源文件的根目录拷贝到目的文件中
			path2 = tempFile.getAbsolutePath();// 把目的文件的绝对目录在做为参数传给递归函数
		}
		File f1[] = file1.listFiles();// 得到源文件的集合 返回对象数组（进入到源文件的子目录）
		for (int i = 0; i < f1.length; i++) {// 循环遍历集合中的文件对象
			if (f1[i].isDirectory()) {// 判断源文件是否是目录（true ：搜索文件夹下的文件夹）
				funpool(f1[i].getAbsolutePath(), path2);// 递归
			} else {// 如果不是目录 就是文件了 可以进行复制文件了
				new BufferedFileByteStream().copy(f1[i].getAbsolutePath(), (path2 + "\\" + f1[i].getName()));// 复制文件
				if(++currentfile<=filecount){
					
				} else {
					break;
				}
			}
		}
		ExecutorService service = Executors.newCachedThreadPool();//建立线程池
		List<Future<String>> result = new ArrayList<Future<String>>();// 用于存放Future<String>的返回值
		Future<String> f = service.submit(new MyCall(path2));// 返回Callable借口的Funture<string>类型
		result.add(f);// 把结果放入集合中list
		service.shutdown();
		try {
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);			
			System.out.println("完成..."+(currentfile*100/filecount) + "%");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		service.shutdown();
		try {
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @param path1源文件
	 * @param path2目的文件
	 */
	public void copyDirectory(String path1, String path2) {
		File file1 = new File(path1);// 源文件目录
		File file2 = new File(path2, file1.getName());// 得到与源文件相同的名字的文件（只是盘符不同）
		if (file2.exists()) {// （判断目的文件中有没有）和（要从源文件中复制的文件重名的文件）
			System.out.println("要copy文件已存在");
			return;// 如果有直接结束递归方法
		}
		if (file1.isDirectory()) {// 判段源文件是否是目录（true ：在目的文件中建立一个同名的文件夹）
			File tempFile = new File(path2, file1.getName());
			tempFile.mkdirs();// 建立文件夹（复制文件夹）把源文件的根目录拷贝到目的文件中
			path2 = tempFile.getAbsolutePath();// 把目的文件的绝对目录在做为参数传给递归函数
			// System.out.println(tempFile.getAbsolutePath());// 显示源目录的文件
		}
		File f1[] = file1.listFiles();// 得到源文件的集合 返回对象数组（进入到源文件的子目录）
		for (int i = 0; i < f1.length; i++) {// 循环遍历集合中的文件对象
			if (f1[i].isDirectory()) {// 判断源文件是否是目录（true ：
										// 把源文件路径和目的文件路径做参数传输递归方法）
				copyDirectory(f1[i].getAbsolutePath(), path2);// 递归
			} else {// 如果不是目录 就是文件了 可以进行复制文件了
				new BufferedFileByteStream().copy(f1[i].getAbsolutePath(), (path2 + "\\" + f1[i].getName()));// 复制文件
			}
		}
	}
}	

/**
 * 显示回调的文件名
 * @author belong
 *
 */
class MyCall implements Callable<String> {// 回调线程
	private String name;

	public MyCall(String name) {// 文件的名字
		this.name = name;
	}

	@Override
	public String call() throws Exception {// 回调方法返回线程号
		// TODO Auto-generated method stub
		System.out.println("正在进行copy->" + name + "文件" + Thread.currentThread().getName());
		return name + "文件";
	}

}
/**
 * 进行文件copy
 * @author belong
 *
 */
 class BufferedFileByteStream {
	public void copy(String path, String path2) {
		InputStream fis = null;// InputStream is FileInputStream parents
		OutputStream fos = null;
		BufferedInputStream bis = null;// 不能够独自存在必须借助其他流
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(path);// 读入文件的目录
			fos = new FileOutputStream(path2);// 写入文件的目录
			bis = new BufferedInputStream(fis, 1024 * 1024 * 10);// 开辟缓冲区的大小
			bos = new BufferedOutputStream(fos);
			int n = -1;// 默认值
			byte[] buffer = new byte[1024 * 1024 * 10];// 缓冲字节数组一次读取的字节数
			while ((n = fis.read(buffer)) != -1) {// read 是按字节数组读的
				// System.out.println(new String(buffer));
				fos.write(buffer, 0, n);// 把buffer中的内容写到内存中
			}
		} catch (Exception e) {
		} finally {
			try {
				bos.flush();// 把文件从内存中写入磁盘形成文件
				bos.close();
				bis.close();
				fos.flush();// 把文件从内存中写入磁盘形成文件
				fos.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
