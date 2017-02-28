package others.belong.application;

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

public class A517 {
	//һ���̳߳أ�����߷��߳�
	public static ExecutorService es=Executors.newCachedThreadPool();
	//����������Future<String>
	public static List<Future<String>> result=new ArrayList<Future<String>>();
	public static void main(String[] args) {
		A517 a=new A517();
		//���̳߳���߷�4���ļ�����copy������myCall
		a.poolCallable("d:\\JavaFile", "e:\\w");
		//a.poolCallable("f:\\qq", "h:\\w2");
		//a.poolCallable("f:\\qwer", "h:\\w3");
		//a.poolCallable("f:\\qwer", "h:\\w4");
	//�ر�
		//a.shutdown();
	}
	//path1Դ�ļ���path2Ҫ�����ĵ�ַ
	public void poolCallable(String path1,String path2){
		//����MyCallִ���߳�
		File  fiel=new File(path1);
		File [] b=fiel.listFiles();
		for(int i=0;i<b.length;i++){
			System.out.println(b[i].getAbsolutePath());
			String a=b[i].getAbsolutePath();
			Future<String> re=es.submit(new MyCall(a,path2));
			System.out.println("bb");
			result.add(re);//���
		}
		es.shutdown();
		try {
			es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			for(Future<String>i:result){
				System.out.println(i.get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Future<String> re=es.submit(new MyCall(path1,path2));
		//result.add(re);//���
	}
	public void shutdown(){
		//�ر�
		es.shutdown();
		try {
			es.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
			for(Future<String>i:result){
				System.out.println(i.get());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
//Mycall�࣬�����߳�ִ��
class MyCall implements Callable<String>{
	private String a;
	private String b;
	
	public MyCall(String a, String b) {
		super();
		this.a = a;
		this.b = b;
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		//�ڲ��࣬����copy
		class Copy{
			@SuppressWarnings("unused")
			public void copy(String path1,String path2){
				
				File file=new File(path1);
				File file1=new File(path2,file.getName());
				
				if(file.isDirectory()){//����Ŀ¼
					File b=new File(path2,file.getName());
					b.mkdir();
					
					
					path2=b.getAbsolutePath();
					
				}
				if(file.listFiles()==null){
					InputStream fis=null;
					OutputStream fos=null;
					try {
						String qw=path2+"\\"+file.getName();
						System.out.println(qw);
						fis=new FileInputStream(file.getAbsolutePath());
						fos=new FileOutputStream(qw);
						int n=-1;
						byte buffer[]=new byte[1024];
						while((n=fis.read(buffer))!=-1){
							fos.write(buffer, 0, n);
				
						}
						File mu=new File(qw);
						//d1=d1+mu.length();//�Ѿ����Ƶĳ���
						
					} catch (Exception e) {
						
						e.printStackTrace();
					}finally{
						try {
							
							fos.flush();//ˢ��
							fos.close();
							fis.close();
						} catch (Exception e2) {
							
							e2.printStackTrace();
						}
					}
				}else{
					File [] a=file.listFiles();//������ļ�
					for(int i=0;i<a.length;i++){
						
						if(a[i].isDirectory()){
							//��Ŀ¼����ѭ��
							copy(a[i].getAbsolutePath(),path2);
						}else{//����Ŀ¼����
							
							InputStream fis=null;
							OutputStream fos=null;
							try {
								String qw=path2+"\\"+a[i].getName();
								System.out.println(qw);
								fis=new FileInputStream(a[i].getAbsolutePath());
								fos=new FileOutputStream(qw);
								int n=-1;
								byte buffer[]=new byte[1024];
								while((n=fis.read(buffer))!=-1){
									fos.write(buffer, 0, n);
						
								}
								File mu=new File(qw);
								//d1=d1+mu.length();//�Ѿ����Ƶĳ���
								
							} catch (Exception e) {
								
								e.printStackTrace();
							}finally{
								try {
									
									fos.flush();//ˢ��
									fos.close();
									fis.close();
								} catch (Exception e2) {
									
									e2.printStackTrace();
								}
							}
						}
					}
				}
				
				
				
			}
		}
		//�����ڲ��࣬����copy
		Copy c=new Copy();
		c.copy(a, b);
		System.out.println(Thread.currentThread().getName());
		return "ok";
	}
	
}

