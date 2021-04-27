package com.increpas.cafe1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClsJDBC {

	public ClsJDBC() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			System.out.println("###드라이버 로딩 실패");
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "hellow";
		String pw = "hellow";
		try {
			con = DriverManager.getConnection(url, id, pw);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public Statement getSTMT(Connection con) {
		Statement stmt = null;
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;

	}

	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public void close(Object o) {
		try {
			if (o instanceof Connection) {
				((Connection) o).close();
			} else if (o instanceof Statement) {
				((Statement) o).close();

			} else if (o instanceof PreparedStatement) {
				((Statement) o).close();
			} else if (o instanceof ResultSet) {
				((Statement) o).close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
