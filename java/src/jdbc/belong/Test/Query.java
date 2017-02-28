package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * 数据库查询
 * @author belong
 *
 */
public class Query {
	public static void main(String[] args) {
		Connection conn = null;//链接
		PreparedStatement ps = null;//赋值语句
		ResultSet rs = null;//结果集合
		String ip = "jdbc:mysql://192.168.1.164/register?useUnicode=true&characterEncoding=utf-8";//ip防乱码
		String user = "root";//MySQL用户名
		String password = "root";//密码
		try {
			//1.把驱动放到内存中
			Class.forName("org.gjt.mm.mysql.Driver");
			//2.连接数据库
			conn = DriverManager.getConnection(ip, user, password);
			//3.赋值
			String sql = "select * from user where name like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,"b%");
			rs = ps.executeQuery();//把搜索的结果集放到re中
			//遍历结果集
			while(rs.next()){
				System.out.println(rs.getString("id"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//5.关闭链接
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
