package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * 
 * �����ݿ���д��
 */
public class RegisterConnection {
	private static Connection conn;
	public static Connection getConnection(){
		try {
			Class.forName("org.gjt.mm.mysql.Driver");//�����������ڴ���
			conn = DriverManager.getConnection("jdbc:mysql://192.168.1.164/register?useUnicode=true&characterEncoding=utf-8",
				"root",
				"root");//�������ݿ�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	public static void closeConnection(){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}
}
