package socket.belong.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端（先启动）
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
		System.out.println(this.port+"服务器已启动...");
		BufferedReader reader = null;
		ServerSocket server = null;
		Socket client = null;
		try {
			//Thread.sleep(10);
			server = new ServerSocket(this.port);//通过端口接收来自用户的内容			
			client = server.accept();//接收用户信息			
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));			
			boolean flag = true;//用于控制是否聊天			
			while(flag){				
				String str = reader.readLine();//把服务器从用户接收来的信息按行读出来
				if(str.toLowerCase().equals("q")){//必须是小写q才可以结束（输入部分部分大小写是q就结束）
					flag = false;
					System.out.println("我不想和你聊天了，拜拜");
					
				} else {
					System.out.println(this.ip+"client："+str);//输出从用户里接收来的聊天内容
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
				reader.close();//先开的后闭
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
}

