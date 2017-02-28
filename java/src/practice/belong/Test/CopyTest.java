package practice.belong.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyTest {
	public static void main(String[] args) {
		new Copy("d:\\Javafile", "e:\\file11").copyPoolThread();
	}
}

class Copy implements Runnable {
	private static String pathS;
	private static String pathD;
	private static int filecount;
	private static int currentfile;

	public Copy(String pathS, String pathD) {
		Copy.pathD = pathD;
		Copy.pathS = pathS;
	}

	public void copyPoolThread() {
		File fileS = new File(pathS);
		File[] fs = fileS.listFiles();
		filecount = fs.length;
		ThreadGroup tg = new ThreadGroup("T");
		Thread[] threadcopy = new Thread[filecount];
		for (int i = 0; i < fs.length; i++) {
			threadcopy[i] = new Thread(tg, this);
			threadcopy[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentfile++;
		}
	}

	@Override
	public void run() {
		copyDirectory(pathS, pathD, currentfile);
		System.out.println(pathD + "\n" + pathS);
		System.out.println(Thread.currentThread().getName());
	}

	public void copyDirectory(String path1, String path2, int currentfile) {
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
		if (f1[currentfile].isDirectory()) {// 判断源文件是否是目录（true ：
			// 把源文件路径和目的文件路径做参数传输递归方法）
			copyDirectory(f1[currentfile].getAbsolutePath(), path2, currentfile);// 递归
		} else {// 如果不是目录 就是文件了 可以进行复制文件了
			new BufferedFileByteStream().copy(f1[currentfile].getAbsolutePath(), (path2 + "\\" + f1[currentfile].getName()));// 复制文件
		}
	}
}

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