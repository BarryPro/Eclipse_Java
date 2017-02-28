package thread.belong.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadRecursionFile {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Recursion r = new Recursion();
		r.funpool("d:\\Javafile", "e:\\w",r);
		long end = System.currentTimeMillis();
		System.out.println("一共用时:" + (end - start) + "ms");// 看看copy 所需要的时间
	}
}
class Recursion{
	public void funpool(String path1, String path2,Recursion r) {
		ExecutorService service = Executors.newCachedThreadPool();// 建立线程池
		List<Future<String>> result = new ArrayList<Future<String>>();// 用于存放Future<String>的返回值
		Future<String> f = service.submit(new MyCall2(path1, path2,r));// 返回Callable借口的Funture<string>类型
		result.add(f);// 把结果放入集合中list
		service.shutdown();
		try {
//			 for (Future<String> file : result) {
//			 System.out.println("正在进行copy->：" + file.get());
//			 }
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

			//System.out.println("完成..." + (currentfile * 100 / filecount) + "%");
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
}


class MyCall2 implements Callable<String> {// 回调线程
	public String getPath1() {
		return path1;
	}

	public void setPath1(String path1) {
		this.path1 = path1;
	}

	public String getPath2() {
		return path2;
	}

	public void setPath2(String path2) {
		this.path2 = path2;
	}
	private Recursion r;
	private String path1;
	private String path2;
	public MyCall2(String path1, String path2,Recursion r) {// 文件的名字
		this.path1 = path2;
		this.path2 = path2;
		this.r = r;
	}

	public Recursion getR() {
		return r;
	}

	public void setR(Recursion r) {
		this.r = r;
	}

	@Override
	public String call() throws Exception {// 回调方法返回线程号		
		// TODO Auto-generated method stub
		File file1 = new File(path1);// 源文件目录
		File file2 = new File(path2, file1.getName());// 得到与源文件相同的名字的文件（只是盘符不同）
		if (file2.exists()) {// （判断目的文件中有没有）和（要从源文件中复制的文件重名的文件）
			return "要copy文件已存在";// 如果有直接结束递归方法
		}
		if (file1.isDirectory()) {// 判段源文件是否是目录（true ：在目的文件中建立一个同名的文件夹）
			File tempFile = new File(path2, file1.getName());
			tempFile.mkdirs();// 建立文件夹（复制文件夹）把源文件的根目录拷贝到目的文件中
			path2 = tempFile.getAbsolutePath();// 把目的文件的绝对目录在做为参数传给递归函数
			 System.out.println(tempFile.getAbsolutePath());// 显示源目录的文件
		}
//		System.out.println("ok");
		File f1[] = file1.listFiles();// 得到源文件的集合 返回对象数组（进入到源文件的子目录）
		for (int i = 0; i < f1.length; i++) {// 循环遍历集合中的文件对象
			
			if (f1[i].isDirectory()) {// 判断源文件是否是目录（true ：搜索文件夹下的文件夹）
				r.funpool(f1[i].getAbsolutePath(), path2,r);// 递归
			} else {// 如果不是目录 就是文件了 可以进行复制文件了
				new BufferedFileByteStream().copy(f1[i].getAbsolutePath(), (path2 + "\\" + f1[i].getName()));// 复制文件
			}
		}
		
		return (path1 + "\n" + path2);
	}
}

/**
 * 进行文件copy
 * 
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
				 System.out.println(new String(buffer));
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
