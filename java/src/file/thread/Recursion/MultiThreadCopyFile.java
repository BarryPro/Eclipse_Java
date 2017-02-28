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
 * �����
 * @author belong
 *
 */
class MultiThread extends Thread {
	private String pathS;//Դ�ļ���
	private String pathD;//Ŀ���ļ���
	public MultiThread(String pathS, String pathD) {
		this.pathD = pathD;
		this.pathS = pathS;
		this.start();//ֱ����run�������ݹ鸴��
	}

	@Override
	public void run() {//���и���
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
				new MultiThread(tempfileS.getAbsolutePath(),tempfileD.getAbsolutePath());//����Ŀ¼�ڸ�				
			}//���౾���γɵݹ�
		} else {
			System.out.println(Thread.currentThread().getName() + pathS + "��ʼ����");
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
				System.out.println(Thread.currentThread().getName()+s.getAbsolutePath()+"��������");
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
