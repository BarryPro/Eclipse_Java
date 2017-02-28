package thread.belong.Test;
/**
 * 取钱安全测试
 * @author belong
 * 线程同信必须是多个方法对同一个数据进行操作
 * 这里的同一个数据就是user （同一个用户）
 */
public class SynchronizedThread {
	public static void main(String[] args) {
		User user = new User("1234567", "00000");
		ATM atm = new ATM(user,0);
		new Draw("丙",user,atm).start();
		new Draw("丁",user,atm).start();
		new Deposite("甲",user,atm).start();
		new Deposite("乙",user,atm).start();
	}
}

class Draw extends Thread {//取钱多线程
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

class Deposite extends Thread{//存钱多线程
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