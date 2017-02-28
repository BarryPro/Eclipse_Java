package socket.belong.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * �������ˣ���������
 * @author Belong
 */
public class MyServer extends Thread{
	private String ip;
	private Integer port;
	public MyServer(String ip,Integer port) {
		this.ip = ip;
		this.port = port;
	}
	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		System.out.println(this.port+"������������...");
		BufferedReader reader = null;
		ServerSocket server = null;
		Socket client = null;
		try {
			//Thread.sleep(10);
			server = new ServerSocket(this.port);//ͨ���˿ڽ��������û�������			
			client = server.accept();//�����û���Ϣ			
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));			
			boolean flag = true;//���ڿ����Ƿ�����			
			while(flag){				
				String str = reader.readLine();//�ѷ��������û�����������Ϣ���ж�����
				if(str.toLowerCase().equals("q")){//������Сдq�ſ��Խ��������벿�ֲ��ִ�Сд��q�ͽ�����
					flag = false;
					System.out.println("�Ҳ�����������ˣ��ݰ�");
					
				} else {
					System.out.println(this.ip+"client��"+str);//������û������������������
				}
			}
			Runtime.getRuntime().exec("cmd.exe /c Start");
			new TestQQ().show();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {					
				client.close();
				server.close();
				reader.close();//�ȿ��ĺ��
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
}

