package com.increpas.cafe.dao;

import java.sql.*;
import java.util.*;
import javax.sql.*;

import java.sql.Date;
import java.sql.Time;
import com.increpas.cafe.db.*;
import com.increpas.cafe.vo.*;
import com.increpas.cafe.sql.*;

/**
 * 이클래스는 댓글게시판 데이터베이스를  작업전담 처리클래스
 * @author 우병환
 * @since 2021.04.28
 * @version v.1.0
 * @see	
 * 			작업이련
 * 				2021.04.28 - 전은석
 * 					:	클래스 작성, 리스트조회 함수 작성
 * 
 */


public class ReBoardDao {
	CafeDBCP db;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	ReBoardSQL rSQL;
	
	public ReBoardDao() {
		db = new CafeDBCP();
		rSQL = new ReBoardSQL();
	}
	
	//게시글 조회 전담 처리 함수
	public ArrayList<BoardVO> getReBoardList() {
		//할일
		//0. 반환값 변수 만들기
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		// 1. 커넥션
		con = db.getCon();
		// 2. 질의명령 가져오고
		String sql = rSQL.getSQL(rSQL.SEL_ALL_LIST);
		//3. 스테이트먼트 준비
		stmt = db.getSTMT(con);
		try{
			//4. 질의명령 보내고
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				//5. 결과에서 데이터추출하고
				BoardVO vo = new BoardVO();
				vo.setRno(rs.getInt("rno"));
				vo.setMno(rs.getInt("mno"));
				vo.setId(rs.getString("id"));
				vo.setAvatar(rs.getString("avatar"));
				vo.setTitle(rs.getString("title"));
				vo.setBody(rs.getString("body").replaceAll("\r\n",  "<br>"));
				vo.setBno(rs.getInt("upno"));
				vo.setStep(rs.getInt("step"));
				vo.setWdate(rs.getDate("wdate"));
				vo.setWtime(rs.getTime("wdate"));
				vo.setSdate();
				//6. vo를 list에 담고
				list.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		
		//7. list 반환해주고
		return list;
	}
	
}
