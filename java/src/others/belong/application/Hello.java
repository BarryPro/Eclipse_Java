package others.belong.application;

import java.io.IOException;

public class Hello {
	public static void main(String[] args) {
		try {
			/*�ļ�·�������ִ���ļ�����
			C://Windows//notepad.exeָc���µ�notepad.exe�ļ���
			cmd /c Start �̶���ʽ��ʼ.exe�ļ�*/
			Runtime.getRuntime().exec("cmd /c Start G://�����ȫ//���//conewpaizhao//conewpaizhao.exe ");			
			} catch (IOException e) {
			e.printStackTrace();
			}	
	}	
}
