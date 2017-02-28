package thread.belong.Test;

/**
 * ȡ���
 * 
 * @author belong
 *
 */
public class ATM {
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public ATM(User user, int balance) {
		super();
		this.user = user;
		this.balance = balance;
	}
	private User user;
	private int balance;
	
	@SuppressWarnings("static-access")
	public synchronized void draw(int drawmoney) {
		try {
			if (user.isFlag() == false) {
				wait();
			} else {
				System.out.print(Thread.currentThread().getName() + "ȡǮ��" + drawmoney);
				balance -= drawmoney;
				System.out.println("�˻���" + balance);				
				user.setFlag(false);
				try {
					Thread.currentThread().sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	public synchronized void deposite(int depositemoney) {
		try {
			if (user.isFlag() == true) {//��Ǯ����ȥ
				wait();
			} else {//ûǮ���Դ�
				System.out.print(Thread.currentThread().getName() + "��Ǯ��" + depositemoney);
				balance += depositemoney;
				System.out.println("�˻���" + balance);				
				user.setFlag(true);;
				try {
					Thread.currentThread().sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
