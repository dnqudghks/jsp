package com.increpas.cafe.controller.gboard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cafe.controller.CafeController;
import com.increpas.cafe.dao.GuestBoardDao;

public class GuestWriteProc implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 뷰 부르는 방식 셋팅
		req.setAttribute("isRedirect", true);
		
		//세션이 종료된 경우 또는 로그인 하지않고 직접 요청하는 경우
		// 로그인 페이지로 돌려보낸다.
		String sid =(String) req.getSession().getAttribute("SID");
		if(sid==null) {
			//이 경우는 로그인 안되어있는 경우 로그인페이지로 돌려보낸다.
			return "cafe/member/login.cafe"; 
		}
		
		String view = "/cafe/gboard/gBoardList.cafe"; //작업이 성공했을 경우
		
		//할일
		//1. 데이터 받고
		String sno = req.getParameter("mno");
		int mno = Integer.parseInt(sno);
		String body = req.getParameter("body");
		//2. 받은 데이터로 데이터베이스 처리하고
		GuestBoardDao gDao = new GuestBoardDao();
		int cnt = gDao.addGBoard(mno, body);
		//3. 결과 받아서 처리해주고
		if(cnt !=1) {
			view = "cafe/gboard/gBoardWrite.cafe";
		}
		
		//4. 뷰 부르고
		return view;
	}

}
