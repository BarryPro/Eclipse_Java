package thread.belong.pool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile implements Runnable {
	private File fileS;
	private File fileD;
	public CopyFile(File fileS,File fileD){
		this.fileS = fileS;
		this.fileD = fileD;
	}

	@Override
	public void run() {//copyÎÄ¼þ
		// TODO Auto-generated method stub
		InputStream fis = null;
		OutputStream fos = null;
		try {
			fis = new FileInputStream(fileS);
			fos = new FileOutputStream(fileD);
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
				//System.out.println(Thread.currentThread().getName()+fileS.getAbsolutePath()+"½áÊø¿½±´");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} 
	}

}
