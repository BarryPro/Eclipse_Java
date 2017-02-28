package jdbc.belong.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDAO{
	private Connection conn = null;
	private boolean flag = false;
	private PreparedStatement pstmt= null;
	public RegisterDAO(){
		conn = RegisterConnection.getConnection();
	}
	public boolean dao(){
		String sql = "insert into user(name,password) values(?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "ÓÚ³ÉÁú");
			pstmt.setString(2, "belong");
			System.out.println(pstmt.executeUpdate()>0?"success":"failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RegisterConnection.closeConnection();
		}
		
		return flag;
	}
}
