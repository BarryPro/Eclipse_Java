package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * ���ݿ��ѯ
 * @author belong
 *
 */
public class Query {
	public static void main(String[] args) {
		Connection conn = null;//����
		PreparedStatement ps = null;//��ֵ���
		ResultSet rs = null;//�������
		String ip = "jdbc:mysql://192.168.1.164/register?useUnicode=true&characterEncoding=utf-8";//ip������
		String user = "root";//MySQL�û���
		String password = "root";//����
		try {
			//1.�������ŵ��ڴ���
			Class.forName("org.gjt.mm.mysql.Driver");
			//2.�������ݿ�
			conn = DriverManager.getConnection(ip, user, password);
			//3.��ֵ
			String sql = "select * from user where name like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,"b%");
			rs = ps.executeQuery();//�������Ľ�����ŵ�re��
			//���������
			while(rs.next()){
				System.out.println(rs.getString("id"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//5.�ر�����
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
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
