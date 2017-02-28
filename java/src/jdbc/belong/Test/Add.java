package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add {
	public static void main(String[] args) {
		String ip = "jdbc:mysql://192.168.1.104/bz?useUnicode=true&characterEncoding=utf-8";//ip��ַ����ֹ���룩
		String user = "root";//MySQL�û���
		String password = "root";//MySQL�û�����
		Connection conn = null;//�������ݿ�
		PreparedStatement ps = null;//׼��״̬���
		try {
			//1.�������ŵ��ڴ��� 
			Class.forName("com.mysql.jdbc.Driver");
			//2.��������
			conn = DriverManager.getConnection(ip,user,password);
			//3.��ֵ����
			String sql = "insert into data_1(name,scord) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "alice");//��״̬����е�ֵ����������ֵǰ��������Ǵ���ڼ�������
			ps.setInt(2, 95);
			//4.�ж��Ƿ����ӳɹ�
			System.out.println(ps.executeUpdate()>0?"ִ�гɹ�":"ʧ��");//����0��ʾִ�гɹ�
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {	
				if(ps!=null){
					ps.close();
				}				
				//5.�ر�����
				if(ps!=null){
					conn.close();
				}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
