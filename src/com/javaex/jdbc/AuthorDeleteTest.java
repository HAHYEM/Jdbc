package com.javaex.jdbc;

import java.sql.*;

public class AuthorDeleteTest {
	public static void main(String[] args) {

		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL�� �غ� / ���ε� / ����
			String query = "delete from author where author_id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 1);

			int count = pstmt.executeUpdate();

			// 4.���ó��
			System.out.println(count + "�� �����Ϸ�");

		} catch (ClassNotFoundException e) {
			System.out.println("error: ����̹� �ε� ���� - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. �ڿ�����
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
