package com.increpas.cafe.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cafe.controller.CafeController;

public class JoinProc implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 할일
		
		// 1. 파라미터 받고
		String sname = req.getParameter("name");
		String sid = req.getParameter("id");
		String semail = req.getParameter("email");
		String stel = req.getParameter("tel");
		String sgen = req.getParameter("gen");
		// 2. vo에 담고
		
		//3. db 처리하고
		
		// 4. 결과처리하고
		
		// 5. 뷰 부르고
		
		return null;
	}

}
