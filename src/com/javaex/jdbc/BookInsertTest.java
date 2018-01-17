package com.javaex.jdbc;

import java.sql.*;

public class BookInsertTest {
	public static void main(String[] args) {
		// 0. import java.sql.*; ctrl + shift + o
		Connection conn = null;
		PreparedStatement pstmt = null; // ������ ����

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// �߿� 3. SQL�� �غ� / ���ε� / ����
			String query = "INSERT INTO book VALUES(seq_book_id.nextval, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "�츮���� �ϱ׷��� ����");
			pstmt.setString(2, "�ٸ�");
			pstmt.setString(3, "1998-02-22");
			pstmt.setInt(4, 1);
			
			
			int count = pstmt.executeUpdate();

			// �߿� 4.���ó��
			System.out.println(count + "�� ����Ϸ�");

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
