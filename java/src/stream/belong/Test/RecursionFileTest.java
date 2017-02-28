package stream.belong.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * �ݹ�copy�ļ�
 * 
 * @author Belong
 *
 */
public class RecursionFileTest {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		RecursionFile rf = new RecursionFile();
		rf.setFilecount("d:\\Javafile");
		rf.funpool("D:\\Javafile", "e:\\w");
//		Timer timer = new Timer();
//		rf.setTimer(timer);
//		timer.schedule(rf, 0, 10);
		long end = System.currentTimeMillis();
		System.out.println("һ����ʱ:" + (end - start) + "ms");// ����copy ����Ҫ��ʱ��
	}
}
/**
 * copy�ļ��� �� �ļ�
 * 
 * @author belong
 *
 */
class RecursionFile extends TimerTask {// �ݹ�copy�ļ�
	private int filecount;// Դ�ļ��е��ļ�����
	private int currentfile = 0;// ��ǰ�����ļ��ĵĸ���
	private Timer timer;// ��ʱ����

	@Override
	public void run() {
		if (++currentfile <= filecount) {
			System.out.println("�ļ�����������" + (currentfile * 100 / filecount) + "%");// ����copy�˶����ļ�
		} else {
			timer.cancel();
			System.out.println("�ļ�������ϣ�");
		}
	}

	public void setFilecount(String path1) {// ���ļ��ĸ���������������
		this.filecount = new File(path1).listFiles().length;
	}

	public void setTimer(Timer timer) {// Ϊtimer��ֵ
		this.timer = timer;
	}
	public int getFilecount(){
		return filecount;
	}
	/**
	 * @param path1 Դ·��
	 * @param path2 Ŀ��·��
	 */
	public void funpool(String path1, String path2) {
		File file1 = new File(path1);// Դ�ļ�Ŀ¼
		File file2 = new File(path2, file1.getName());// �õ���Դ�ļ���ͬ�����ֵ��ļ���ֻ���̷���ͬ��
		if (file2.exists()) {// ���ж�Ŀ���ļ�����û�У��ͣ�Ҫ��Դ�ļ��и��Ƶ��ļ��������ļ���
			System.out.println("Ҫcopy�ļ��Ѵ���");
			return;// �����ֱ�ӽ����ݹ鷽��
		}
		if (file1.isDirectory()) {// �ж�Դ�ļ��Ƿ���Ŀ¼��true ����Ŀ���ļ��н���һ��ͬ�����ļ��У�
			File tempFile = new File(path2, file1.getName());
			tempFile.mkdirs();// �����ļ��У������ļ��У���Դ�ļ��ĸ�Ŀ¼������Ŀ���ļ���
			path2 = tempFile.getAbsolutePath();// ��Ŀ���ļ��ľ���Ŀ¼����Ϊ���������ݹ麯��
		}
		File f1[] = file1.listFiles();// �õ�Դ�ļ��ļ��� ���ض������飨���뵽Դ�ļ�����Ŀ¼��
		for (int i = 0; i < f1.length; i++) {// ѭ�����������е��ļ�����
			if (f1[i].isDirectory()) {// �ж�Դ�ļ��Ƿ���Ŀ¼��true �������ļ����µ��ļ��У�
				funpool(f1[i].getAbsolutePath(), path2);// �ݹ�
			} else {// �������Ŀ¼ �����ļ��� ���Խ��и����ļ���
				new BufferedFileByteStream().copy(f1[i].getAbsolutePath(), (path2 + "\\" + f1[i].getName()));// �����ļ�
				if(++currentfile<=filecount){
					
				} else {
					break;
				}
			}
		}
		ExecutorService service = Executors.newCachedThreadPool();//�����̳߳�
		List<Future<String>> result = new ArrayList<Future<String>>();// ���ڴ��Future<String>�ķ���ֵ
		Future<String> f = service.submit(new MyCall(path2));// ����Callable��ڵ�Funture<string>����
		result.add(f);// �ѽ�����뼯����list
		service.shutdown();
		try {
			service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);			
			System.out.println("���..."+(currentfile*100/filecount) + "%");
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


	/**
	 * @param path1Դ�ļ�
	 * @param path2Ŀ���ļ�
	 */
	public void copyDirectory(String path1, String path2) {
		File file1 = new File(path1);// Դ�ļ�Ŀ¼
		File file2 = new File(path2, file1.getName());// �õ���Դ�ļ���ͬ�����ֵ��ļ���ֻ���̷���ͬ��
		if (file2.exists()) {// ���ж�Ŀ���ļ�����û�У��ͣ�Ҫ��Դ�ļ��и��Ƶ��ļ��������ļ���
			System.out.println("Ҫcopy�ļ��Ѵ���");
			return;// �����ֱ�ӽ����ݹ鷽��
		}
		if (file1.isDirectory()) {// �ж�Դ�ļ��Ƿ���Ŀ¼��true ����Ŀ���ļ��н���һ��ͬ�����ļ��У�
			File tempFile = new File(path2, file1.getName());
			tempFile.mkdirs();// �����ļ��У������ļ��У���Դ�ļ��ĸ�Ŀ¼������Ŀ���ļ���
			path2 = tempFile.getAbsolutePath();// ��Ŀ���ļ��ľ���Ŀ¼����Ϊ���������ݹ麯��
			// System.out.println(tempFile.getAbsolutePath());// ��ʾԴĿ¼���ļ�
		}
		File f1[] = file1.listFiles();// �õ�Դ�ļ��ļ��� ���ض������飨���뵽Դ�ļ�����Ŀ¼��
		for (int i = 0; i < f1.length; i++) {// ѭ�����������е��ļ�����
			if (f1[i].isDirectory()) {// �ж�Դ�ļ��Ƿ���Ŀ¼��true ��
										// ��Դ�ļ�·����Ŀ���ļ�·������������ݹ鷽����
				copyDirectory(f1[i].getAbsolutePath(), path2);// �ݹ�
			} else {// �������Ŀ¼ �����ļ��� ���Խ��и����ļ���
				new BufferedFileByteStream().copy(f1[i].getAbsolutePath(), (path2 + "\\" + f1[i].getName()));// �����ļ�
			}
		}
	}
}	

/**
 * ��ʾ�ص����ļ���
 * @author belong
 *
 */
class MyCall implements Callable<String> {// �ص��߳�
	private String name;

	public MyCall(String name) {// �ļ�������
		this.name = name;
	}

	@Override
	public String call() throws Exception {// �ص����������̺߳�
		// TODO Auto-generated method stub
		System.out.println("���ڽ���copy->" + name + "�ļ�" + Thread.currentThread().getName());
		return name + "�ļ�";
	}

}
/**
 * �����ļ�copy
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
