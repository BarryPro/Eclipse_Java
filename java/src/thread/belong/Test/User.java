package thread.belong.Test;
/**
 * �����˻�
 * @author Belong
 *
 */
public class User {
	/**
	 * ������
	 * @param no �����˺�
	 * @param password �˺�����
	 */
	public User(String no, String password) {
		super();
		this.no = no;
		this.password = password;
		
	}
	private boolean flag; // flag=false ��ʾû�д����Դ� true ��ʾ�д�� ����ȡ
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
