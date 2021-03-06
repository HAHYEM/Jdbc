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
			
			//중요 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO author VALUES(seq_author_id.nextval, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, "이문열");
			pstmt.setString(2, "경북 영양");
			pstmt.setString(1, "박경리");
			pstmt.setString(2, "경상남도 통영");
			pstmt.setString(1, "유시민");
			pstmt.setString(2, "17대 국회의원");
			pstmt.setString(1, "기안84");
			pstmt.setString(2, "기안동에서 산 84년생");
			pstmt.setString(1, "강풀");
			pstmt.setString(2, "온라인 만화가 1세대");
			pstmt.setString(1, "김영하");
			pstmt.setString(2, "알쓸신잡");
		
			int count = pstmt.executeUpdate();
			
			//중요 4.결과처리 
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
