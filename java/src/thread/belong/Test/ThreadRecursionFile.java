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
		System.out.println("һ����ʱ:" + (end - start) + "ms");// ����copy ����Ҫ��ʱ��
	}
}
class Recursion{
	public void funpool(String path1, String path2,Recursion r) {
		ExecutorService service = Executors.newCachedThreadPool();// �����̳߳�
		List<Future<String>> result = new ArrayList<Future<String>>();// ���ڴ��Future<String>�ķ���ֵ
		Future<String> f = service.submit(new MyCall2(path1, path2,r));// ����Callable��ڵ�Funture<string>����
		result.add(f);// �ѽ�����뼯����list
		service.shutdown();
		try {
//			 for (Future<String> file : result) {
//			 System.out.println("���ڽ���copy->��" + file.get());
//			 }
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

			//System.out.println("���..." + (currentfile * 100 / filecount) + "%");
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


class MyCall2 implements Callable<String> {// �ص��߳�
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
	public MyCall2(String path1, String path2,Recursion r) {// �ļ�������
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
	public String call() throws Exception {// �ص����������̺߳�		
		// TODO Auto-generated method stub
		File file1 = new File(path1);// Դ�ļ�Ŀ¼
		File file2 = new File(path2, file1.getName());// �õ���Դ�ļ���ͬ�����ֵ��ļ���ֻ���̷���ͬ��
		if (file2.exists()) {// ���ж�Ŀ���ļ�����û�У��ͣ�Ҫ��Դ�ļ��и��Ƶ��ļ��������ļ���
			return "Ҫcopy�ļ��Ѵ���";// �����ֱ�ӽ����ݹ鷽��
		}
		if (file1.isDirectory()) {// �ж�Դ�ļ��Ƿ���Ŀ¼��true ����Ŀ���ļ��н���һ��ͬ�����ļ��У�
			File tempFile = new File(path2, file1.getName());
			tempFile.mkdirs();// �����ļ��У������ļ��У���Դ�ļ��ĸ�Ŀ¼������Ŀ���ļ���
			path2 = tempFile.getAbsolutePath();// ��Ŀ���ļ��ľ���Ŀ¼����Ϊ���������ݹ麯��
			 System.out.println(tempFile.getAbsolutePath());// ��ʾԴĿ¼���ļ�
		}
//		System.out.println("ok");
		File f1[] = file1.listFiles();// �õ�Դ�ļ��ļ��� ���ض������飨���뵽Դ�ļ�����Ŀ¼��
		for (int i = 0; i < f1.length; i++) {// ѭ�����������е��ļ�����
			
			if (f1[i].isDirectory()) {// �ж�Դ�ļ��Ƿ���Ŀ¼��true �������ļ����µ��ļ��У�
				r.funpool(f1[i].getAbsolutePath(), path2,r);// �ݹ�
			} else {// �������Ŀ¼ �����ļ��� ���Խ��и����ļ���
				new BufferedFileByteStream().copy(f1[i].getAbsolutePath(), (path2 + "\\" + f1[i].getName()));// �����ļ�
			}
		}
		
		return (path1 + "\n" + path2);
	}
}

/**
 * �����ļ�copy
 * 
 * @author belong
 *
 */
class BufferedFileByteStream {
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
				 System.out.println(new String(buffer));
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
