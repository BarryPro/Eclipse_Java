package thread.belong.Test;

/**
 * 取款机
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
				System.out.print(Thread.currentThread().getName() + "取钱：" + drawmoney);
				balance -= drawmoney;
				System.out.println("账户余额：" + balance);				
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
			if (user.isFlag() == true) {//有钱可以去
				wait();
			} else {//没钱可以存
				System.out.print(Thread.currentThread().getName() + "存钱：" + depositemoney);
				balance += depositemoney;
				System.out.println("账户余额：" + balance);				
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
