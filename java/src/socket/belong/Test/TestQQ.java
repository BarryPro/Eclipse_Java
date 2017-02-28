package socket.belong.Test;

import java.util.Scanner;

public class TestQQ extends Thread{
	public static void main(String[] args) {
		new TestQQ().show();
	}
	@SuppressWarnings("resource")
	public synchronized void show(){
		notifyAll();
		Scanner cin = new Scanner(System.in); 
		QQ qq = new QQ();
		System.out.println("1添加好友 \n2显示好友\n3删除好友\n4聊天\n其他退出");
		System.out.println("请先添加好友！");
		while(cin.hasNext()){
			String change = cin.next(); 
			switch(change){
			case "1" : 
				System.out.println("请输入 IP （192.168.137.1）和 port （9501）");
				String ip = cin.next();
				Integer port = cin.nextInt();
				qq.add(ip, port);
				System.out.println("请选择其他功能");
				break;
			case "2" : 
				System.out.println("好友列表如下：");
				qq.printFriends();
				System.out.println("请选择其他功能");
				break;
			case "3" : 
				System.out.println("删除指定好友");
				String ip1 = cin.next();
				qq.delete(ip1);
				System.out.println("请选择其他功能");
				break;
			case "4" : 
				System.out.println("聊天中...：");
				qq.launch();
				qq.chat();
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default : 
				System.out.println("已成功退出程序！");
				System.exit(0);
			}			
		}
	}
}
