package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Delete {//删除
	public static void main(String[] args) {
		String ip = "jdbc:mysql://192.168.1.104/bz?useUnicode=true&characterEncoding=utf-8";//ip地址（防止乱码）
		String user = "root";//MySQL用户名
		String password = "root";//MySQL用户密码
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//1.把驱动放到内存当中
			Class.forName("com.mysql.jdbc.Driver");
			//2.链接数据库
			conn = DriverManager.getConnection(ip,user,password);
			//3.赋值
			String sql = "delete from data_1 where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, 2);
			//4.判断是否执行成功
			System.out.println(ps.executeUpdate()>0?"执行成功":"失败");//使用executeUpdate方法来删增
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null){
					ps.close();
				}
				//5.关闭链接
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
