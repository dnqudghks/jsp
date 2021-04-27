package com.increpas.cafe.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.increpas.cafe.db.CafeDBCP;
import com.increpas.cafe.sql.*;
import com.increpas.cafe.vo.*;

public class GuestBoardDao {
	CafeDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	MemberSQL mSQL;
	GuestBoardSQL gSQL;
	
	public GuestBoardDao() {
		db = new CafeDBCP();
		mSQL = new MemberSQL();
		gSQL = new GuestBoardSQL();
	}
	
	//로그인 회원 정보 조회 전달 처리함수
	public MemberVO getMemberData(String id) {
		MemberVO mVO = new MemberVO();
		
		// connection 꺼내오고
		con = db.getCon();
		// 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_AVTMNO);
		// 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try{
			// 질의명령 완성하고
			pstmt.setString(1, id);
			// 질의보내고 결과받고
			rs = pstmt.executeQuery();
			// 데이터 꺼내서 VO에 답고
			rs.next();
			mVO.setMno(rs.getInt("mno"));
			mVO.setAvatar(rs.getString("avatar"));
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		
		// 반환해주고
		return mVO;
	}
	
	//방명록 리스트 조회 전담 처리함수
	public ArrayList<BoardVO> getGBoardList(){
		//반환값 변수 만들고
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		//컨넥션 꺼내오고
		con = db.getCon();
		//질의명령 가져오고
		String sql = gSQL.getSQL(gSQL.SEL_ALL_LIST);
		//스테이트먼트 준비하고
		stmt = db.getSTMT(con);
		try{
			//질의명령 보내고 결과 받고
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				BoardVO gVO = new BoardVO();
				gVO.setGno(rs.getInt("gno"));
				gVO.setMno(rs.getInt("mno"));
				gVO.setId(rs.getString("id"));
				gVO.setBody(rs.getString("body"));
				gVO.setAvatar(rs.getString("avatar"));
				gVO.setSdate(rs.getDate("wdate"));
				//vo가완성되면 list에 담고
				list.add(gVO);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		
		//반환값 반환하고
		return list;
	}
	
	//작성글 카운트 조회 전담 처리함수
	public int getWriteCount(String id) {
		int cnt = 0;
		con = db.getCon();
		String sql = gSQL.getSQL(gSQL.SEL_MY_CNT);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt=rs.getInt("cnt");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
}

















