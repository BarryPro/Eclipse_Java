package jdbc.belong.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 * 函数的数据库查询
 * @author belong
 *
 */
public class Function_Query {
	public static void main(String[] args) {
		Connection conn = null;
		CallableStatement cs = null;
		
		String ip = "jdbc:mysql://192.168.1.109/bz?useUnicode=true&characterEncoding=utf-8";
		String user = "root";
		String password = "root";
		try {
			//1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.连接数据库
			conn = DriverManager.getConnection(ip, user, password);
			//3.赋值
			String sql = "{?=call f_4 (?)}";//比存储过程多了个{}
			cs = conn.prepareCall(sql);
			//声明输出的类型和数据库里定义的数据类型是一样的
			cs.registerOutParameter(1, java.sql.Types.INTEGER);//带输出的存储过程和函数一定要写这句话
			cs.setString(2, "t%");
			//4.判读是否执行成功
			System.out.println(cs.execute()==false?"执行成功":"失败");
			System.out.println("返回值是："+cs.getInt(1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//5.关闭数据库链接
			try {
				if(cs!=null){
					cs.close();
				}
				if(conn!= null){
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}
