package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Update {
	public static void main(String[] args) {
		String ip = "jdbc:mysql://192.168.1.104/bz?useUnicode=true&characterEncoding=utf-8";//ip地址（防止乱码）
		String user = "root";//MySQL用户名
		String password = "root";//MySQL用户密码
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.
			Class.forName("com.mysql.jdbc.Driver");
			//2.
			conn = DriverManager.getConnection(ip, user,password);
			//3.
			String sql = "update data_1 set name = ? ,scord = ? where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(3, 5);
			ps.setString(1, "len");
			ps.setLong(2, 67);
			//4.
			System.out.println(ps.executeUpdate()>0?"执行成功":"失败");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//5.
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
