package module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {
	
	public static Member login(String id, String password) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Member member = null;
		
		try {
			//드라이버 로딩
			System.out.println("LoginStart");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521";
			String user = "L";
			String pass = "lee";
			
			conn = DriverManager.getConnection(url, user, pass);
			
			stmt = conn.createStatement();
			String sql = "SELECT * FROM MEMBER WHERE ID = '"+id+"' AND PASSWORD = '"+password+"'";
			System.out.println("쿼리 : "+sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				member = new Member(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5));				
			}else{
				System.out.println("[Login] id 와 password가 일치하는 사용자가 존재 하지 않습니다.");
				rs.close();
				stmt.close();
				conn.close();
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return member;
	}
	
}
