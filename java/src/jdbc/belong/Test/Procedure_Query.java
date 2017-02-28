package jdbc.belong.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
/**
 * �洢�����еĲ�ѯ��callablestatement �� ���� prepare��statement ��һ����
 * @author belong
 *
 */
public class Procedure_Query {
	public static void main(String[] args) {
		Connection conn = null;//����
		CallableStatement cs = null;//���ø�ֵ���
		ResultSet rs = null;//�������
		String ip = "jdbc:mysql://192.168.1.109/bz?useUnicode=true&characterEncoding=utf-8";//ip������
		String user = "root";//MySQL�û���
		String password = "root";//����
		try {
			//1.
			Class.forName("com.mysql.jdbc.Driver");
			//2.
			conn = DriverManager.getConnection(ip, user, password);
			//3.
			String sql = "call p_4(?,?)";
			cs = conn.prepareCall(sql);//׼���������
			cs.setString(1, "t%");//�������
			cs.registerOutParameter(2, java.sql.Types.INTEGER);//�����ݿ��ж��������
			//4.�ж��Ƿ�ִ�гɹ�
			boolean flag = cs.execute();
			while(flag){
				System.out.println("������"+cs.getInt(2)+"��");//�õ�������
				rs = cs.getResultSet();//�õ������
				while(rs.next()){
					System.out.println(rs.getString("id")+rs.getString("name")+rs.getString("scord"));
				}
				flag = cs.getMoreResults();//��cs��û�и���������Ϊfalse
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				//5.
				if(rs!=null){
					rs.close();
				}
				if(cs!=null){
					cs.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}
