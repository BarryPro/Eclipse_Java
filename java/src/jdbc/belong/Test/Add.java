package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Add {
	public static void main(String[] args) {
		String ip = "jdbc:mysql://192.168.1.104/bz?useUnicode=true&characterEncoding=utf-8";//ip地址（防止乱码）
		String user = "root";//MySQL用户名
		String password = "root";//MySQL用户密码
		Connection conn = null;//链接数据库
		PreparedStatement ps = null;//准备状态语句
		try {
			//1.把驱动放到内存中 
			Class.forName("com.mysql.jdbc.Driver");
			//2.链接驱动
			conn = DriverManager.getConnection(ip,user,password);
			//3.赋值参数
			String sql = "insert into data_1(name,scord) values(?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "alice");//把状态语句中的值（参数）赋值前面的数字是代表第几个参数
			ps.setInt(2, 95);
			//4.判断是否链接成功
			System.out.println(ps.executeUpdate()>0?"执行成功":"失败");//大于0表示执行成功
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {	
				if(ps!=null){
					ps.close();
				}				
				//5.关闭链接
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
