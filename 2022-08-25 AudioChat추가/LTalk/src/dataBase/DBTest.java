package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBTest {
	public static void main(String[] args) {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 성공");
			
			String url = "jdbc:oracle:thin:@localhost:1521";// jdbc:oracle:thin:호스트명:포트버호
			String user = "L";
			String pass = "lee";
			
			Connection conn = DriverManager.getConnection(url,user,pass);
			System.out.println("Connection 객체 생성 성공");
			Statement stmt = conn.createStatement();
			System.out.println("Statement 객체 생성 성공");
			
			String sql = "SELECT * FROM MEMBER";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				System.out.println("id: "+rs.getString(1));
				System.out.println("password: "+rs.getString(2));
				System.out.println("name: "+rs.getString(3));
				System.out.println("email: "+rs.getString(4));
				System.out.println("authority: "+rs.getInt(5));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
