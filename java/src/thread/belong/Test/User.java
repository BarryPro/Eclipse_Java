package thread.belong.Test;
/**
 * 银行账户
 * @author Belong
 *
 */
public class User {
	/**
	 * 构造器
	 * @param no 银行账号
	 * @param password 账号密码
	 */
	public User(String no, String password) {
		super();
		this.no = no;
		this.password = password;
		
	}
	private boolean flag; // flag=false 表示没有存款，可以存 true 表示有存款 可以取
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	private String no; 
	private String password;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
