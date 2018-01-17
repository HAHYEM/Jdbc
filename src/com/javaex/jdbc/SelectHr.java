package com.javaex.jdbc;

import java.sql.*;

/* �ڽ��� �Ŵ������� ä����(hire_date)�� ���� ����� ���(employee_id), ��(last_name)�� ä����(hire_date)�� ��ȸ�ϼ��� */
public class SelectHr {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			// 3. SQL�� �غ� / ���ε� / ����
			String query = " SELECT E.employee_id, E.last_name, E.hire_date "
					+ "      FROM employees E, employees M "
					+ "      WHERE E.manager_id = M.employee_id AND E.hire_date < M.hire_date";
					
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			// 4. ��� ó��
			while (rs.next()) {
				int employeeId = rs.getInt("employee_id");
				String lastName = rs.getString("last_name");
				String hireDate = rs.getString("hire_date");
				
				System.out.println(employeeId + ". " + lastName + "	" + hireDate.substring(0, 10));
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
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
