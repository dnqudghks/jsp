package com.increpas.cafe.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;

import com.increpas.cafe.sql.MemberSQL;
import com.increpas.coffee.db.CafeDBCP;
import com.increpas.coffee.vo.MemberVO;
/**
 *  이 클래스는 회원관련 데이터베이스 작업을 
 *  서버가 처음 기동될때 미리 확보한 커넥션(DBCP : DataVase Connection Pool)을 이용해서 데이터베이스작업을 
 *  전담해서 처리할 클래스
 * @author class02
 * @since 2021.04.21
 * @version v.1.0
 *
 */

public class MemberDao {
	// 커넥션 풀을 관할 변수
	CafeDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	MemberSQL mSQL;
	
	public MemberDao() {
		db = new CafeDBCP();
		mSQL = new MemberSQL();
	}
	
	// 로그인 회원 정보 조회 전담 처리함수
	public MemberVO getLoginInfo(String sid){
		//반환값 변수 준비하고
		MemberVO mVO = new MemberVO();
		// 데이터베이스 작업 처리
		//할일
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 꺼내오고
		String sql = mSQL.getSQL(mSQL.SEL_ID_INFO);
		System.out.println("########## sql : " + sql);
		// 3. PreparedStatement 꺼내오고
		pstmt = db.getPSTMT(con, sql);
		try{
			// 4. 질의명령 완성하고
			pstmt.setString(1, sid);
			// 5. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6. 결과에서 데이터꺼내고
			// ==> 이 질의명령의 결과는 한행만 만들어 지므로 한행 내려서 처리한다.
			rs.next();
			
			int mno = rs.getInt("mno");
			String id = rs.getString("id");
			String name = rs.getString("name");
			String mail = rs.getString("mail");
			String tel = rs.getString("tel");
			String gen = rs.getString("gen");
			int ano = rs.getInt("ano");
			String avatar = rs.getString("avatar");
			Date jDate = rs.getDate("joindate");
			Time jTime = rs.getTime("joindate");
			
			// 7. VO 데이터 담고
			mVO.setMno(mno);
			mVO.setId(id);
			mVO.setName(name);
			mVO.setMail(mail);
			mVO.setTel(tel);
			mVO.setGen(gen.equals("M") ? "남자" : "여자");
			mVO.setAno(ano);
			mVO.setAvatar(avatar);
			mVO.setjDate(jDate);
			mVO.setjTime(jTime);
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		
		// 8. VO 내보내주고
		
		return mVO;
		
	}
	
	// 로그인 처리 데이터 베이스 작업 잔담 처리함수
	public int getLoginCnt(String sid, String spw) {
		int cnt = 0;
		
		// 1. Connection꺼내오고
		con = db.getCon();
		
		// 2. SQL 질의명령꺼내오고
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN);
		
		// 3. 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try{
			// 4. 질의명령을 완성하고
			pstmt.setString(1, sid);
			pstmt.setString(2, spw);
			// 5. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6. 데이터 추출하고
			// 집계함수를 사용하는 질의 명령이므로 단일행, 단일값으로 결과가 발생한다.
			rs.next();
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		db.close(rs);
		db.close(pstmt);
		db.close(con);
		}
		
		//결과값 반환하고
		return cnt;
	}
	
	//회원가입 데이터 베이스 전담 처리함수
	public int addMember(MemberVO mVO) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.ADD_MEMB);
		pstmt = db.getPSTMT(con,  sql);
		try {
			pstmt.setString(1, mVO.getId());
			pstmt.setString(2,  mVO.getPw());
			pstmt.setString(3, mVO.getName());
			pstmt.setString(4, mVO.getMail());
			pstmt.setString(5, mVO.getTel());
			pstmt.setString(6, mVO.getGen());
			pstmt.setInt(7, mVO.getAno());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
			
		return cnt;
	}
	

}
