package customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFunction {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 스트림 닫는 메소드 선언 - 1
	public void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) rs.close();
			if (pstmt != null && !pstmt.isClosed()) pstmt.close();
			if (conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 스트림 닫는 메소드 선언 - 2
	public void closeAll(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null && !pstmt.isClosed()) pstmt.close();
			if (conn != null && !conn.isClosed()) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// custID로 한 명의 고객 조회
	public CustomerVO selectCustomer(int CUSTID) {
		String SQL = "SELECT * FROM CUSTOMER WHERE CUSTID = ?";
		CustomerVO cvo = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, CUSTID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cvo = new CustomerVO();

				cvo.setBookID(rs.getInt("CUSTID"));
				cvo.setName(rs.getString("NAME"));
				cvo.setAddress(rs.getString("ADDRESS"));
				cvo.setPhone(rs.getString("PHONE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return cvo;
	}
	
	// 전체 고객 조회 (customer 테이블 전체 조회)
	public ArrayList<CustomerVO> selectCustomer() {
		String SQL = "SELECT * FROM CUSTOMER";
		ArrayList<CustomerVO> clist = null;
		try {
			conn = DBConnect.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			clist = new ArrayList<CustomerVO>();
			while(rs.next()) {
				CustomerVO cvo = new CustomerVO();

				cvo.setBookID(rs.getInt("CUSTID"));
				cvo.setName(rs.getString("NAME"));
				cvo.setAddress(rs.getString("ADDRESS"));
				cvo.setPhone(rs.getString("PHONE"));
				
				clist.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return clist;
	}
	
	// insert
	public int insertCustomer(int custID, String name, String address, String phone) {
		String SQL = "INSERT INTO CUSTOMER (CUSTID, NAME, ADDRESS, PHONE) "
				+ "VALUES (?, ?, ?, ?)";
		try {
			// DBConnect로부터 conn 객체 받아오기
			conn = DBConnect.getConnection();
			// DB 쿼리 실행을 위한 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(SQL);
						
			// ? 에 값 넣기
			pstmt.setInt(1, custID);
			pstmt.setString(2, name);
			pstmt.setString(3, address);
			pstmt.setString(4, phone);
			
			// 쿼리 실행 및 결과값 리턴
			return pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
		
		return -1;
	}
	
	// update
	public int updateCustomer(int custID, String name, String address, String phone) {
		// 실행 쿼리
		String SQL = "UPDATE CUSTOMER "
					+ "SET NAME = ?, "
					+ "	   ADDRESS = ?, "
					+ "    PHONE = ? "
					+ "WHERE CUSTID = ?";
	
		try {
			// DBConnect로부터 conn 객체 받아오기
			conn = DBConnect.getConnection();
			// DB 쿼리 실행을 위한 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(SQL);
			
			// ? 에 값 넣기
			
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, phone);
			pstmt.setInt(4, custID);
			
			// 쿼리 실행 및 결과값 저장
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
		
		return -1;
	}
	
	// delete
	public int deleteCustomer(int custID) {
		String SQL = "DELETE FROM CUSTOMER "
				+ "WHERE CUSTID = ?";
		try {					
			// DBConnect로부터 conn 객체 받아오기
			conn = DBConnect.getConnection();

			// DB 쿼리 실행을 위한 PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(SQL);
			
			// ? 에 값 넣기
			pstmt.setInt(1, custID);
			
			// 쿼리 실행 및 결과값 저장
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
		
		return -1;
	}
}
