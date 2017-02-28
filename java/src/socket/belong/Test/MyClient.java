package socket.belong.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * �ͻ���
 * @author belong
 */
public class MyClient extends Thread{
	private String ip;
	private Integer port;
	public MyClient(String ip,Integer port) {		
		this.ip = ip;
		this.port = port;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(this.port+"�ͻ��Ծ���...");
		Socket client = null;
		PrintWriter writer = null;
		Scanner sc = null;
		try {
			client = new Socket(this.ip,this.port);//�û����Լ���IP�����������ͷ���������ͬһ���˿ڣ�
			writer = new PrintWriter(client.getOutputStream(),true);//�û�������д��ȥ
			sc = new Scanner(System.in);			
			while(sc.hasNext()){
				String str = sc.nextLine();//����д��������
				writer.println(str);//�����������
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {	
			sc.close();
			writer.close();
			try {				
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
