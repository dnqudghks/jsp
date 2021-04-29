package com.increpas.cafe.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;

public class ReBoardDel implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 뷰 설정하고
		req.setAttribute("isRedirect", true);
		
		// 세션 검사하고
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) return "/cafe/member/login.cafe";
		
		String view = "/cafe/reBoard/reBoardList.cafe";
		
		// 데이터 받고
		String sno = req.getParameter("rno");
		int rno = Integer.parseInt(sno);
		ReBoardDao rDao = new ReBoardDao();
		rDao.delReBoard(rno);
		
		return view;
	}

}