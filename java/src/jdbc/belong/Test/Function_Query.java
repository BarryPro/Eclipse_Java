package jdbc.belong.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 * ���������ݿ��ѯ
 * @author belong
 *
 */
public class Function_Query {
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cs = null;
		
		String ip = "jdbc:mysql://192.168.1.109/bz?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String password = "root";
		try {
			//1.��������
			Class.forName("com.mysql.jdbc.Driver");
			//2.�������ݿ�
			conn = DriverManager.getConnection(ip, user, password);
			//3.��ֵ
			String sql = "{?=call f_4 (?)}";//�ȴ洢���̶��˸�{}
			cs = conn.prepareCall(sql);
			//������������ͺ����ݿ��ﶨ�������������һ����
			cs.registerOutParameter(1, java.sql.Types.INTEGER);//������Ĵ洢���̺ͺ���һ��Ҫд��仰
			cs.setString(2, "t%");
			//4.�ж��Ƿ�ִ�гɹ�
			System.out.println(cs.execute()==false?"ִ�гɹ�":"ʧ��");
			System.out.println("����ֵ�ǣ�"+cs.getInt(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//5.�ر����ݿ�����
			try {
				if(cs!=null){
					cs.close();
				}
				if(conn!= null){
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}
