package practice.belong.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MultiThreadCopyFile {
	public static void main(String[] args) {
		new MultiThread().multiThread("d:\\JavaFile", "e:\\w");
	}
}

class MultiThread implements Runnable{
	private String pathS;
	private String pathD;
	private static MultiThread m;
	private BufferedFileByteStream1 bfbs;
	@SuppressWarnings("static-access")
	public void multiThread(String pathS,String pathD){
		File fileS = new File(pathS);//源文件目录
		File fileD = new File(pathD,fileS.getName());//与源文件同名的目的根目录
		File [] fs = fileS.listFiles();
		Thread[] t = new Thread[fs.length];//多少个文件就开多少个线程
		if(fileS.isDirectory()){//复制根文件
			fileD.mkdir();
		}
		for(int i = 0;i<fs.length;i++){
			m = new MultiThread();
			t[i] = new Thread(m);
			m.pathS = fs[i].getAbsolutePath();//源文件路径
			File tempfileD = new File(fileD,fs[i].getName());	//在根下进行子目录复制		
			m.pathD = tempfileD.getAbsolutePath();//目的文件路径
			//System.out.println(m.pathS+"\n"+m.pathD);
			t[i].start();	
			try {
				t[i].sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		File fileS = new File(m.pathS);
		bfbs = new BufferedFileByteStream1();
		//System.out.println(m.pathS);
		//System.out.println(m.pathD);
		if(fileS.isDirectory()){
			multiThread(m.pathS,m.pathD);//递归拷贝目录
			System.out.println(Thread.currentThread().getName()+":"+m.pathS);
		} else {
			bfbs.copy(m.pathS, m.pathD);
			System.out.println(Thread.currentThread().getName()+":"+m.pathS);
		}
	}
}
class BufferedFileByteStream1 {
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

