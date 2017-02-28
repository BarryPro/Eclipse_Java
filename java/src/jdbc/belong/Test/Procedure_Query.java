package jdbc.belong.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
/**
 * 存储过程中的查询（callablestatement 与 语句的 prepare的statement 不一样）
 * @author belong
 *
 */
public class Procedure_Query {
	public static void main(String[] args) {
		Connection conn = null;//链接
		CallableStatement cs = null;//调用赋值语句
		ResultSet rs = null;//结果集合
		String ip = "jdbc:mysql://192.168.1.109/bz?useUnicode=true&characterEncoding=utf-8";//ip防乱码
		String user = "root";//MySQL用户名
		String password = "root";//密码
		try {
			//1.
			Class.forName("com.mysql.jdbc.Driver");
			//2.
			conn = DriverManager.getConnection(ip, user, password);
			//3.
			String sql = "call p_4(?,?)";
			cs = conn.prepareCall(sql);//准备调用语句
			cs.setString(1, "t%");//输入参数
			cs.registerOutParameter(2, java.sql.Types.INTEGER);//在数据库中定义的类型
			//4.判断是否执行成功
			boolean flag = cs.execute();
			while(flag){
				System.out.println("笔数："+cs.getInt(2)+"笔");//得到输出结果
				rs = cs.getResultSet();//得到结果集
				while(rs.next()){
					System.out.println(rs.getString("id")+rs.getString("name")+rs.getString("scord"));
				}
				flag = cs.getMoreResults();//当cs中没有更多结果集是为false
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
