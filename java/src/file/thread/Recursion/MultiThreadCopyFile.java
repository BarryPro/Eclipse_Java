package file.thread.Recursion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MultiThreadCopyFile {
	public static void main(String[] args) {
		new MultiThread("d:\\JavaFile", "e:\\w");
	}
}
/**
 * 更简洁
 * @author belong
 *
 */
class MultiThread extends Thread {
	private String pathS;//源文件名
	private String pathD;//目的文件名
	public MultiThread(String pathS, String pathD) {
		this.pathD = pathD;
		this.pathS = pathS;
		this.start();//直接用run（）来递归复制
	}

	@Override
	public void run() {//进行复制
		File fileS = new File(pathS);
		File fileD = new File(pathD);
		if (fileS.isDirectory()) {
			if (!fileD.exists()) {
				fileD.mkdir();
			}
			String [] spath = fileS.list();//得到源文件的路径名的字符串表示
			for(String path:spath){
				File tempfileS = new File(fileS,path);//中间临时变量源文件
				File tempfileD = new File(fileD,path);//中间临时变量目的文件
				new MultiThread(tempfileS.getAbsolutePath(),tempfileD.getAbsolutePath());//把子目录在赋				
			}//给类本身形成递归
		} else {
			System.out.println(Thread.currentThread().getName() + pathS + "开始拷贝");
			copy(fileS, fileD);
		}

	}

	public void copy(File s,File d){
		InputStream fis = null;
		OutputStream fos = null;
		try {
			fis = new FileInputStream(s);
			fos = new FileOutputStream(d);
			int n = -1;
			byte [] buffer = new byte[1024*1024];
			while((n = fis.read(buffer)) != -1){
				fos.write(buffer, 0, n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.flush();
				fos.close();
				fis.close();
				System.out.println(Thread.currentThread().getName()+s.getAbsolutePath()+"结束拷贝");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
