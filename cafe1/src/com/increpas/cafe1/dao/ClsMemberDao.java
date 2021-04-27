package com.increpas.cafe1.dao;

import java.sql.*;
import com.increpas.cafe1.db.*;
import com.increpas.cafe1.sql.*;

public class ClsMemberDao {
	
	ClsJDBC db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	MemberSQL mSQL;
	
	public ClsMemberDao() {
		db = new ClsJDBC();
		mSQL = new MemberSQL();
	}
	
	public int getLoginCnt(String id, String pw) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN);
		
		pstmt = db.getPSTMT(con, sql);
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	public int getIdCnt(String id) {
		int cnt = 1;
		
		con = db.getCon();
		
		String sql = mSQL.getSQL(mSQL.SEL_ID_CNT);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	public int addMemb(MemberVO mVO) {
		int cnt = 0;
		
		con = db.getCon();
		
		String sql = mSQL.getSQL(mSQL.ADD_MEMB);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, mVO.getId());
			pstmt.setString(2, mVO.getPw());
			pstmt.setString(3, mVO.getName());
			pstmt.setString(4, mVO.getMail());
			pstmt.setString(5, mVO.getTel());
			pstmt.setString(6, mVO.getGen());
			pstmt.setString(7, mVO.getAno());
			
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
}
