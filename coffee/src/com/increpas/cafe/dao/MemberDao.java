package com.increpas.cafe.dao;

import java.util.*;
import java.sql.*;
import java.sql.Date;
import javax.sql.DataSource;
import javax.swing.DebugGraphics;

import com.increpas.coffee.db.*;
import com.increpas.cafe.sql.*;
import com.increpas.cafe.vo.*;

/**
 * 이 클래스는 회원관련 데이터베이스 작업을 
 * 서버가 처음 기동될때 미리확보한 커넥션(DBCP : DataBase Connection Pool)을 이용해서 데이터베이스 작업을 
 * 전담해서 처리할 클래스
 * @author	전은석
 * @since	2021.04.21
 * @version	v.1.0
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
	public MemberVO getLoginInfo(String sid) {
		// 반환값 변수 준비하고
		MemberVO mVO = new MemberVO();
		// 데이터베이스 작업 처리
		// 할일
		// 1. 커넥션 얻어오고
		con = db.getCon();
		// 2. 질의명령 꺼내오고
		String sql = mSQL.getSQL(mSQL.SEL_ID_INFO);
		// 3. PreparedStatement 꺼내오고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setString(1, sid);
			// 5. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6. 결과에서 데이터꺼내고
			// ==> 이 질의명령의 결과는 한행만 만들어 지므로 한행 내려서 처리하면 된다.
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
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 8. VO 내보내주고
		return mVO;
	}
	
	// 로그인 처리 데이터베이스작업 전담 처리함수
	public int getLogin(String sid, String spw) {
		// 반환값 변수
		int cnt = 0 ;
		// 할일
		// 1. 커넥션 꺼내오고
		con = db.getCon();
		// 2. 질의명령 꺼내오고
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN);
		// 3. 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 4. 질의명령 완성하고
			pstmt.setString(1, sid);
			pstmt.setString(2, spw);
			// 5. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
			// 6. 데이터 추출하고
			// 집계함수를 사용하는 질의 명령이므로 단일행, 단일값으로 결과가 발생한다.
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// 결과값 반환하고
		return cnt;
	}
	
	// 회원가입 데이터베이스 전담 처리함수
	public int addMember(MemberVO mVO) {
		int cnt = 0 ;
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
			pstmt.setInt(7, mVO.getAno());
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 내 정보조회 데이터베이스 전담 처리함수
	public MemberVO getMyInfo(String sid) {
		MemberVO mVO = new MemberVO();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_ID_INFO);
		pstmt = db.getPSTMT(con, sql);
		
		try {
			pstmt.setString(1, sid);
			
			rs = pstmt.executeQuery();
			rs.next();
			mVO.setMno(rs.getInt("mno"));
			mVO.setName(rs.getString("name"));
			mVO.setId(rs.getString("id"));
			mVO.setMail(rs.getString("mail"));
			mVO.setTel(rs.getString("tel"));
			mVO.setGen(rs.getString("gen").equals("M") ? "남자" : "여자");
			mVO.setAvatar(rs.getString("avatar"));
			mVO.setjDate(rs.getDate("joindate"));
			mVO.setjTime(rs.getTime("joindate"));
			mVO.setAno(rs.getInt("ano"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return mVO;
	}
	
	// 회원리스트 조회 데이터베이스 작업 전담 처리함수
	public ArrayList<MemberVO> getMembList(){
		// 반환값 변수 만들고
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		// 할일
		// 커넥션 꺼내오고
		con = db.getCon();
		// 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_MEMB_LIST);
		// 스테이트먼트 준비하고
		stmt = db.getSTMT(con);
		try {
			// 질의명령 보내고 결과 받고
			rs = stmt.executeQuery(sql);
			// 결과에서 데이터꺼내고 VO 에 담고
			while(rs.next()) {
				MemberVO mVO = new MemberVO(); //  한명당 한개씩 데이터 담을 그릇 만들고...
				// 데이터 꺼내서 VO에 담고
				mVO.setMno(rs.getInt("mno"));
				mVO.setName(rs.getString("name"));
				mVO.setGen(rs.getString("gen").equals("M") ? "남자" : "여자");
				// 가입일의 경우는 날짜와 시간을 따로 꺼내야 되기때문에
				// 꺼내서 처리하고..
				mVO.setjDate(rs.getDate("joindate"));
				mVO.setjTime(rs.getTime("joindate"));
				// 리스트에 보여주는 데이터는 날짜만 보여주기로 하자.
				// 시간이 셋팅이 되면 sdate 변수도 초기화가 되므로
				// sdate를 날짜까지만 남기고 처리해준다.
				String str = mVO.getSdate();
				String data = str.substring(0, str.indexOf(' '));
				mVO.setSdate(data);
				
				// 한명분 데이터가 완성이 됬으므로 리스트에 추가해준다.
				// VO를 list에 담고
				list.add(mVO);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		// list 반환하고
		return list;
	}
	
	// 회원 정보조회 데이터베이스 작업 전담 처리함수
	public MemberVO getMembInfo(int mno) {
		// 할일
		// 반환값 변수 
		MemberVO mVO = new MemberVO();
		// 커넥션 얻어오고
		con = db.getCon();
		// 질의명령 가져오고
		String sql = mSQL.getSQL(mSQL.SEL_NO_INFO);
		// 스테이트먼트 준비하고
		pstmt = db.getPSTMT(con, sql);
		try {
			// 질의명령 완성하고
			pstmt.setInt(1, mno);
			// 질의명령 보내고 결과 받고
			rs = pstmt.executeQuery();
			// 결과에서 데이터 꺼내서 VO 담고
			rs.next(); // 커서 내리고...
			mVO.setMno(rs.getInt("mno"));
			mVO.setId(rs.getString("id"));
			mVO.setName(rs.getString("name"));
			mVO.setMail(rs.getString("mail"));
			mVO.setTel(rs.getString("tel"));
			mVO.setGen(rs.getString("gen").equals("M") ? "남성" : "여성");
			mVO.setAvatar(rs.getString("avatar"));
			mVO.setjDate(rs.getDate("joindate"));
			mVO.setjTime(rs.getTime("joindate"));
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		// VO 반환하고
		return mVO;
	}
	
	//성별 아파타 리스트 조회 전담 처리함수
	public ArrayList<MemberVO> getAvtList(String gen){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_AVT_GEN);
		pstmt = db.getPSTMT(con, sql);
		try {
			pstmt.setString(1, gen);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				
				mVO.setAno(rs.getInt("ano"));
				mVO.setAvatar(rs.getString("avatar"));
				
				list.add(mVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
	
	// 내정보수정 데이터베이스 작업 전담 처리함수
	public int editMyInfo(HashMap<String, Object> map) {
		int cnt = 0 ;
		
		// connection
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.EDIT_MYINFO);
		Set<String> set = (Set<String>) map.keySet();
		ArrayList<String> list = new ArrayList<String>(set);
		Collections.sort(list);
		sql = sql.replaceAll("###", getParam(list));
		
		pstmt = db.getPSTMT(con, sql);
		try {
			for(int i = 0 ; i < list.size(); i++ ) {
				String key = list.get(i);
				if(key.substring(1).equals("avt")) {
					/*
					 	맵에는 키값들이 정렬을 위해서
					 	만약 비밀번호와 아바타를 변경한 경우
					 		"1pw", pw
					 		"2avt", avt
					 	
					 	만약 아바타 번호를 꺼내는 경우에는 정수로 변환해줘야 할 것이고
					 	나머지 경우는 문자열로 변한해주면 된다.
					 */
					pstmt.setInt(i+1, (int) map.get(key));
				} else {
					pstmt.setString(i+1, (String) map.get(key));
				}
			}
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	public String getParam(ArrayList<String> list) {
		String sql = "";
		for(int i = 0 ; i < list.size(); i++ ) {
			if(i != 0) {
				sql = sql + ", ";
			} 
			sql = sql + list.get(i).substring(1) + " = ? ";
		}
		
		/*
		 * 	MemberSQL에서 반환해준 질의명령 문자열은
		 * 		UPDATE
		 * 			member
		 * 		SET
		 * 			###
		 * 		WHERE
		 * 			pw = ?
		 * 만약 비밀번호만 수정 햇다면
		 * 		UPDATE
		 * 			member
		 * 		SET
		 * 			pw = ?
		 * 		WHERE
		 * 			id = ?
		 * 
		 *	만약 비밀번호와 메일만 수정한 경우는 
		 *	우리가 필요한 문자열은
		 *		UPDATE
		 * 			member
		 * 		SET
		 *			pw = ?
		 *			mail = ?
		 * 		WHERE
		 * 			pw = ?		
		 *	
		 *	따라서 이 함수는 ### 대신 전달된 데이터에 
		 *	따라서 변경되는 부분만 만들어서 반환해주는 함수이다.
		 */
		return sql;
	}
}
