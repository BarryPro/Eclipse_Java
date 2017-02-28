package practice.belong.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyTest {
	public static void main(String[] args) {
		new Copy("d:\\Javafile", "e:\\file11").copyPoolThread();
	}
}

class Copy implements Runnable {
	private static String pathS;
	private static String pathD;
	private static int filecount;
	private static int currentfile;

	public Copy(String pathS, String pathD) {
		Copy.pathD = pathD;
		Copy.pathS = pathS;
	}

	public void copyPoolThread() {
		File fileS = new File(pathS);
		File[] fs = fileS.listFiles();
		filecount = fs.length;
		ThreadGroup tg = new ThreadGroup("T");
		Thread[] threadcopy = new Thread[filecount];
		for (int i = 0; i < fs.length; i++) {
			threadcopy[i] = new Thread(tg, this);
			threadcopy[i].start();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			currentfile++;
		}
	}

	@Override
	public void run() {
		copyDirectory(pathS, pathD, currentfile);
		System.out.println(pathD + "\n" + pathS);
		System.out.println(Thread.currentThread().getName());
	}

	public void copyDirectory(String path1, String path2, int currentfile) {
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
		if (f1[currentfile].isDirectory()) {// �ж�Դ�ļ��Ƿ���Ŀ¼��true ��
			// ��Դ�ļ�·����Ŀ���ļ�·������������ݹ鷽����
			copyDirectory(f1[currentfile].getAbsolutePath(), path2, currentfile);// �ݹ�
		} else {// �������Ŀ¼ �����ļ��� ���Խ��и����ļ���
			new BufferedFileByteStream().copy(f1[currentfile].getAbsolutePath(), (path2 + "\\" + f1[currentfile].getName()));// �����ļ�
		}
	}
}

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