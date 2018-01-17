package com.javaex.jdbc;

import java.sql.*;

public class AuthorInsertTest {
	public static void main(String[] args) {

		// 0. import java.sql.*; ctrl + shift + o
		Connection conn = null;
		PreparedStatement pstmt = null;	//쿼리문 관련

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO author VALUES(seq_author_id.nextval, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "하혜민");
			pstmt.setString(2, "비트학생");
			
			int count = pstmt.executeUpdate();
			
			// 4.결과처리 
			System.out.println(count + "건 저장완료");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			
			// 5. 자원정리
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);

			}

		}

	}
}
