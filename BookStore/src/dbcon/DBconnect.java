package dbcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//�̱��� ������ ���� ���
public class DBconnect {
	
//	private static DBconnect singleton = null;
	private static Connection conn;
	
	private DBconnect() {}
	
	public static Connection getInstance() {
		//����
		String user = "madang";
		//��й�ȣ
		String password = "madang";
		//���� url
		String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
		try {
			if(conn !=null && !conn.isClosed()) {
				return conn;
			}
			//����̹��ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("ojdbc.jar �� �����ϴ�.(����̹��� �������� �ʽ��ϴ�.)");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("url,user,password �� �����ͺ��̽��� �����ִ��� Ȯ���ϼ���.");
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
//	public static DBconnect getInstance() {
//		if(singleton == null) {
//			singleton = new DBconnect();
//		}
//		
//		return singleton;
//	}
	
	
	
}
