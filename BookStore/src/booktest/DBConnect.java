package booktest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 싱글톤 디자인 패턴
public class DBConnect {
//	private static DBConnect singleton = null;
	private static Connection conn = null;
	
	private DBConnect() {
		
	}
	
	public static Connection getInstance() {
		// 계정
		String user = "madang";
		// 비밀번호
		String password = "madang";
		// url
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			if (conn != null && !conn.isClosed()) {
				return conn;
			}
			// 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc.jar가 없습니다. (드라이버가 존재하지 않습니다.)");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("url, user, password 및 DB가 켜져있는지 확인하세요.");
			e.printStackTrace();
		}
			
		return conn;
	}
	
//	public static DBConnect getInstance() {
//		if (singleton == null) {
//			singleton = new DBConnect();
//		}
//		
//		return singleton;
//	}
}
