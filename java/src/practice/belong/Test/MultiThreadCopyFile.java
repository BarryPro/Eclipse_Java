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
		File fileS = new File(pathS);//Դ�ļ�Ŀ¼
		File fileD = new File(pathD,fileS.getName());//��Դ�ļ�ͬ����Ŀ�ĸ�Ŀ¼
		File [] fs = fileS.listFiles();
		Thread[] t = new Thread[fs.length];//���ٸ��ļ��Ϳ����ٸ��߳�
		if(fileS.isDirectory()){//���Ƹ��ļ�
			fileD.mkdir();
		}
		for(int i = 0;i<fs.length;i++){
			m = new MultiThread();
			t[i] = new Thread(m);
			m.pathS = fs[i].getAbsolutePath();//Դ�ļ�·��
			File tempfileD = new File(fileD,fs[i].getName());	//�ڸ��½�����Ŀ¼����		
			m.pathD = tempfileD.getAbsolutePath();//Ŀ���ļ�·��
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
			multiThread(m.pathS,m.pathD);//�ݹ鿽��Ŀ¼
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
		BufferedInputStream bis = null;// ���ܹ����Դ��ڱ������������
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(path);// �����ļ���Ŀ¼
			fos = new FileOutputStream(path2);// д���ļ���Ŀ¼
			bis = new BufferedInputStream(fis, 1024 * 1024 * 10);// ���ٻ������Ĵ�С
			bos = new BufferedOutputStream(fos);
			int n = -1;// Ĭ��ֵ
			byte[] buffer = new byte[1024 * 1024 * 10];// �����ֽ�����һ�ζ�ȡ���ֽ���
			while ((n = fis.read(buffer)) != -1) {// read �ǰ��ֽ��������
				// System.out.println(new String(buffer));
				fos.write(buffer, 0, n);// ��buffer�е�����д���ڴ���
			}
		} catch (Exception e) {
		} finally {
			try {
				bos.flush();// ���ļ����ڴ���д������γ��ļ�
				bos.close();
				bis.close();
				fos.flush();// ���ļ����ڴ���д������γ��ļ�
				fos.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

