package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete {//ɾ��
	public static void main(String[] args) {
		String ip = "jdbc:mysql://192.168.1.104/bz?useUnicode=true&characterEncoding=utf-8";//ip��ַ����ֹ���룩
		String user = "root";//MySQL�û���
		String password = "root";//MySQL�û�����
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.�������ŵ��ڴ浱��
			Class.forName("com.mysql.jdbc.Driver");
			//2.�������ݿ�
			conn = DriverManager.getConnection(ip,user,password);
			//3.��ֵ
			String sql = "delete from data_1 where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			//4.�ж��Ƿ�ִ�гɹ�
			System.out.println(ps.executeUpdate()>0?"ִ�гɹ�":"ʧ��");//ʹ��executeUpdate������ɾ��
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
				//5.�ر�����
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
