package com.belong.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Con_Oracle {	
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	
	private static String driverName = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";
	private static String user = "C##BELONG"; // 用户
	private static String password="belong";  //密码  
	
	public static Connection getConnection(){
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url,user,password);  // 取得链接
			
			String sql = "select * from test_user";
			User user = new User();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				user.setU_id(rs.getInt("U_ID"));
				user.setU_name(rs.getString("U_NAME"));
				user.setU_pwd(rs.getString("U_PWD"));
				user.setU_tel(rs.getString("U_TEL"));
				System.out.println("姓名："+user.getU_name()+"密码："+user.getU_pwd()+"电话："+user.getU_tel());
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return conn;
	}
	
	public static void main(String[] args) {
		getConnection();
	}
}
