package com.javaex.jdbc;

import java.sql.*;

/* 자신의 매니저보다 채용일(hire_date)이 빠른 사원의 사번(employee_id), 성(last_name)과 채용일(hire_date)을 조회하세요 */
public class SelectHr {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " SELECT E.employee_id, E.last_name, E.hire_date "
					+ "      FROM employees E, employees M "
					+ "      WHERE E.manager_id = M.employee_id AND E.hire_date < M.hire_date";
					
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 4. 결과 처리
			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String lastName = rs.getString("last_name");
				String hireDate = rs.getString("hire_date");
				
				System.out.println(employeeId + ". " + lastName + "	" + hireDate.substring(0, 10));
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
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
