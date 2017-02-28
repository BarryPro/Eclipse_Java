package file.thread.Recursion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Thread3 extends Thread {
	private String str;//源地址
	private String dstr;//目的地址
	public Thread3(String str, String dstr){
		this.str = str;
		this.dstr = dstr;
		this.start();//启动线程
	}
	@SuppressWarnings("unused")
	@Override
	public void run(){
		File s = new File(str);
		File ds = new File(dstr);
		if(s.isDirectory()){
			if(!ds.exists()){
				ds.mkdir();
			}
			String [] spath = s.list();
			for(String sptr:spath){
				File es = new File(s,sptr);
				File des = new File(ds,sptr);
				Thread3 t = new Thread3(es.getAbsolutePath(),des.getAbsolutePath());
			}
		}else{
			System.out.println(Thread.currentThread().getName()+s.getAbsolutePath()+"开始拷贝");
			copy(s,ds);
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
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				fos.flush();
				fos.close();
				fis.close();
				System.out.println(Thread.currentThread().getName()+s.getAbsolutePath()+"结束拷贝");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
}
