package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectTest {
	public static void main(String[] args) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC ����̹� (Oracle) �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection ������
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

			// 3. SQL�� �غ� / ���ε� / ����
			String query = " SELECT B.book_id, B.title, B.pubs, B.pub_date, B.author_id, A.author_name, A.author_desc " +
							" FROM book B, author A " +
							" WHERE B.author_id = A.author_id ";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.���ó��
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pub_date = rs.getString("pub_date");
				int author_id = rs.getInt("author_id");
				String author_name = rs.getString("author_name");
				String author_desc = rs.getString("author_desc");
				System.out.println(bookId + "	" + title + "	" + pubs + "	" + pub_date.substring(0, 10) + "	" + author_id + "	" + author_name + "	" + author_desc);
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
