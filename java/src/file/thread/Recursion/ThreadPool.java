package file.thread.Recursion;

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
/**
 * 线程池
 * @author belong
 */
public class ThreadPool {
	public static void main(String[] args) {
		new Pool("d:\\JavaFile", "e:\\w").multiThread();
	}
}
//线程池类
class Pool{
	private String pathS;
	private String pathD;
	/**
	 * @param pathS 源路径
	 * @param pathD 目的
	 */
	public Pool(String pathS, String pathD){
		this.pathS = pathS;
		this.pathD = pathD;
	}
	
	public static ExecutorService es = Executors.newCachedThreadPool();	//建立线程
	public static List<Future<String>> result = new ArrayList<Future<String>>();//集合用来存Future<String>

	public void multiThread(){
		Future<String> re = es.submit(new MyCall(pathS,pathD));//把任务分配任务（实现多线程）
		result.add(re);
		es.shutdown();
		try {
			es.awaitTermination(Long.MAX_VALUE,TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
class MyCall implements Callable<String>{//只是回调不用它实现多线程
	private String pathS;
	private String pathD;
	public MyCall(String pathS,String pathD){
		this.pathS = pathS;
		this.pathD = pathD;
	}
	@Override
	public String call() throws Exception {
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
				System.out.println(tempfileS.getAbsolutePath());
				new MultiThread(tempfileS.getAbsolutePath(),tempfileD.getAbsolutePath());//把子目录在赋				
			}//给类本身形成递归
		} else {
			copy(fileS, fileD);
		}
		return "ok";
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}