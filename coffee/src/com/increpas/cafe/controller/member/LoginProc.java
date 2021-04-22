package com.increpas.cafe.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.MemberDao;

public class LoginProc implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		//할일
		// 0. 뷰 부르는 방식 설정하고
		
		// 1. 파라미터 받고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		
		// 2. 데이터베이스 작업, dao 함수 호출하고
		MemberDao mDao = new MemberDao();
		int cnt = mDao.getLoginCnt(sid, spw);
		// 3. 결과에 따라 처리하고
		String view = "/cafe/main.cafe";		
		if(cnt == 1) {
			//로그인처리하고
			req.getSession().setAttribute("SID", sid);
			//뷰 부르는 방식 설정하고
			req.setAttribute("isRedirect", true);
		}else {
			/*
			파라미터로 바로 뷰를 다시 부르는 방식
			view = "/cafe/member/login.cafe?MSG=fail";
			*/
			
			//forward 방식으로 리다이렉트용 페이지를 부르는 방식
			view = "redirectPage";
			// 요청객체에 데이터를 심어준다.
			req.setAttribute("LOGINMSG", "fail");
		}
		
		// 4. 뷰 부르고
		return view;
	}

}
