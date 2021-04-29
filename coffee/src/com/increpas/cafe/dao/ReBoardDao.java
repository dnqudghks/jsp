package com.increpas.cafe.dao;

import java.sql.*;
import java.util.*;
import javax.sql.*;
import java.sql.Date;
import java.sql.Time;

import com.increpas.coffee.db.*;
import com.increpas.cafe.vo.*;
import com.increpas.cafe.sql.*;

/**
 * 이 클래스는 댓글게시판 데이터베이스 작업 전담 처리 클래스
 * @author	전은석
 * @since	2021.04.28
 * @version	v.1.0
 * @see
 * 			작업이력
 * 				2021.04.28 - 전은석
 * 					: 클래스 작성, 리스트조회 함수 작성
 *
 */
public class ReBoardDao {
	CafeDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	ReBoardSQL rSQL;
	MemberSQL mSQL;
	
	public ReBoardDao() {
		db = new CafeDBCP();
		rSQL = new ReBoardSQL();
		mSQL = new MemberSQL();
	}
	
	// 게시글 조회 전담 처리함수
	public ArrayList<BoardVO> getReBoardList() {
		// 할일
		// 0. 반환값 변수 만들고
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = rSQL.getSQL(rSQL.SEL_ALL_LIST);
		// 3. 스테이트먼트 준비하고
		stmt = db.getSTMT(con);
		try {
			// 4. 질의명령 보내고 결과받고
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				// 5. 결과에서 데이터추출하고 vo 에 담고
				BoardVO vo = new BoardVO();
				vo.setRno(rs.getInt("rno"));
				vo.setMno(rs.getInt("mno"));
				vo.setId(rs.getString("id"));
				vo.setAvatar(rs.getString("avatar"));
				vo.setTitle(rs.getString("title"));
				vo.setBody(rs.getString("body").replaceAll("\r\n", "<br>"));
				vo.setBno(rs.getInt("upno"));
				vo.setStep(rs.getInt("step"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setWtime(rs.getTime("wdate"));
				vo.setSdate();
				// 6. vo를 list에 담고
				list.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		// 7. list 반환하고
		return list;
	}
	
	// 아바타저장이름, 회원번호 조회 전담 처리함수
	public MemberVO getAvtMno(String id) {
		// 할일
		// 1. 반환값 변수만들고
		MemberVO mVO = new MemberVO();
		// 2. 커넥션 꺼내오고
		con = db.getCon();
		// 3. 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_AVTMNO);
		// 4. 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 5. 질의명령 완성하고
			pstmt.setString(1, id);
			// 6. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 7. 결과에서 데이터 꺼내서 vo에 담고
			rs.next();
			mVO.setMno(rs.getInt("mno"));
			mVO.setAvatar(rs.getString("avatar"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 8. vo 반환하고
		return mVO;
	}
	
	// 게시글 등록 데이터베이스작업 전담 처리함수
	public int addReBoard(BoardVO rVO) {
		// 반환값 변수
		int cnt = 0 ;
		// 할일
		// 1. 커넥션 꺼내오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = rSQL.getSQL(rSQL.ADD_REBOARD_MNO);
		// 3. 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성
			pstmt.setInt(1, rVO.getMno());
			pstmt.setString(2, rVO.getTitle());
			pstmt.setString(3, rVO.getBody());
			// 5. 질의명령 보내고 결과 받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 6. 결과 반환해주고
		return cnt;
	}
	
	// 댓글 등록 전담 처리함수
	public int addReply(BoardVO rVO) {
		int cnt = 0 ;
		// 할일
		// 1. 커넥션 꺼내오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = rSQL.getSQL(rSQL.ADD_REPLY);
		// 3. 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setString(1, rVO.getId());
			pstmt.setString(2, rVO.getTitle());
			pstmt.setString(3, rVO.getBody());
			pstmt.setInt(4, rVO.getBno());
			// 5. 질의명령 보내고 결과받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 6. 결과 반환하고
		return cnt;
	}
	
	// 게시글 삭제 전담 처리함수
	public int delReBoard(int rno) {
		int cnt = 0;
		// 커넥션 꺼내오고
		con = db.getCon();
		// 질의명령 가져오고
		String sql = rSQL.getSQL(rSQL.DEL_REBOARD);
		// 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 질의명령 완성하고
			pstmt.setInt(1, rno);
			// 질의명령 보내고 결과 받고
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		// 결과값 반환하고
		return cnt;
	}
	
	// 게시글 정보조회 전담 처리함수
	public void getBoardRno(BoardVO rVO) {
		// 할일
		// 1. 커넥션 꺼내오고
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = rSQL.getSQL(rSQL.SEL_RNO_INFO);
		// 3. 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setString(1, rVO.getId());
			pstmt.setInt(2, rVO.getRno());
			// 5. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6. 결과에서 데이터꺼내고 vo에 담고
			rs.next(); //커서내리고
			rVO.setRno(rs.getInt("rno"));
			rVO.setUpno(rs.getInt("upno"));
			rVO.setMno(rs.getInt("mno"));
			rVO.setAvatar(rs.getString("avatar"));
			rVO.setTitle(rs.getString("title"));
			rVO.setUptitle(rs.getString("uptitle"));
			rVO.setBody(rs.getString("body"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 7. 종료
		return;
	}
	
}
