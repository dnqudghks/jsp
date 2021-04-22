package com.increpas.cafe.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.increpas.cafe.controller.CafeController;

public class Logout implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		//이 요청의 경우는 처리 후 리다이렉트로 뷰가 전환이되어야 하기 때문에
		req.setAttribute("isRedirect", true);
		
		// 로그아웃 처리
		// ==> session의 SID 라는 키값의 데이터를 지워준다.
		// 1. 따라서 세션부터 꺼내온다.
		HttpSession session = req.getSession();
		// 2. 세션에 기록된 데이터를 지운다.
		session.removeAttribute("SID");
		
		//로그아웃처리가 됏으니 뷰를 새롭게 처리 요청한다.
		return "/cafe/main.cafe";
	}

}
