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
		System.out.println("1��Ӻ��� \n2��ʾ����\n3ɾ������\n4����\n�����˳�");
		System.out.println("������Ӻ��ѣ�");
		while(cin.hasNext()){
			String change = cin.next(); 
			switch(change){
			case "1" : 
				System.out.println("������ IP ��192.168.137.1���� port ��9501��");
				String ip = cin.next();
				Integer port = cin.nextInt();
				qq.add(ip, port);
				System.out.println("��ѡ����������");
				break;
			case "2" : 
				System.out.println("�����б����£�");
				qq.printFriends();
				System.out.println("��ѡ����������");
				break;
			case "3" : 
				System.out.println("ɾ��ָ������");
				String ip1 = cin.next();
				qq.delete(ip1);
				System.out.println("��ѡ����������");
				break;
			case "4" : 
				System.out.println("������...��");
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
				System.out.println("�ѳɹ��˳�����");
				System.exit(0);
			}			
		}
	}
}
