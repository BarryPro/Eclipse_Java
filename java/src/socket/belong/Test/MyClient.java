package socket.belong.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
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
		System.out.println(this.port+"客户以就绪...");
		Socket client = null;
		PrintWriter writer = null;
		Scanner sc = null;
		try {
			client = new Socket(this.ip,this.port);//用户把自己的IP给服务器（和服务器共用同一个端口）
			writer = new PrintWriter(client.getOutputStream(),true);//用户把内容写出去
			sc = new Scanner(System.in);			
			while(sc.hasNext()){
				String str = sc.nextLine();//按行写聊天内容
				writer.println(str);//输出聊天内容
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
