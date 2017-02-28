package thread.belong.Test;
/**
 * ȡǮ��ȫ����
 * @author belong
 * �߳�ͬ�ű����Ƕ��������ͬһ�����ݽ��в���
 * �����ͬһ�����ݾ���user ��ͬһ���û���
 */
public class SynchronizedThread {
	public static void main(String[] args) {
		User user = new User("1234567", "00000");
		ATM atm = new ATM(user,0);
		new Draw("��",user,atm).start();
		new Draw("��",user,atm).start();
		new Deposite("��",user,atm).start();
		new Deposite("��",user,atm).start();
	}
}

class Draw extends Thread {//ȡǮ���߳�
	@SuppressWarnings("unused")
	private User user ;
	private ATM atm;
	public Draw(String name ,User user,ATM atm){
		super(name);
		this.user = user;
		this.atm = atm;		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub		
		for (int i = 0; i < 100; i++) {				
			atm.draw(1000);
		}		
	}
}

class Deposite extends Thread{//��Ǯ���߳�
	@SuppressWarnings("unused")
	private User user ;
	private ATM atm;
	public Deposite(String name,User user,ATM atm){
		super(name);
		this.user = user;
		this.atm = atm;
	}
	@Override	
	public void run() {
		// TODO Auto-generated method stub		
		for (int i = 0; i < 100; i++) {
			atm.deposite(1000);
		}
	}
}