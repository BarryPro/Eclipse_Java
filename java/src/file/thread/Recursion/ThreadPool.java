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
 * �̳߳�
 * @author belong
 */
public class ThreadPool {
	public static void main(String[] args) {
		new Pool("d:\\JavaFile", "e:\\w").multiThread();
	}
}
//�̳߳���
class Pool{
	private String pathS;
	private String pathD;
	/**
	 * @param pathS Դ·��
	 * @param pathD Ŀ��
	 */
	public Pool(String pathS, String pathD){
		this.pathS = pathS;
		this.pathD = pathD;
	}
	
	public static ExecutorService es = Executors.newCachedThreadPool();	//�����߳�
	public static List<Future<String>> result = new ArrayList<Future<String>>();//����������Future<String>

	public void multiThread(){
		Future<String> re = es.submit(new MyCall(pathS,pathD));//�������������ʵ�ֶ��̣߳�
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
class MyCall implements Callable<String>{//ֻ�ǻص�������ʵ�ֶ��߳�
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
			String [] spath = fileS.list();//�õ�Դ�ļ���·�������ַ�����ʾ
			for(String path:spath){
				File tempfileS = new File(fileS,path);//�м���ʱ����Դ�ļ�
				File tempfileD = new File(fileD,path);//�м���ʱ����Ŀ���ļ�
				System.out.println(tempfileS.getAbsolutePath());
				new MultiThread(tempfileS.getAbsolutePath(),tempfileD.getAbsolutePath());//����Ŀ¼�ڸ�				
			}//���౾���γɵݹ�
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