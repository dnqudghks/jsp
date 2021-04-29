package com.increpas.cafe.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;
import com.increpas.cafe.vo.*;

public class ReBoardWrite implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 세션에서 아이디 꺼내오고
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			// 뷰 전환방식 변경하고
			req.setAttribute("isRedirect", true);
			// 뷰 반환하고
			return "/cafe/member/login.cafe";
		}
		
		// 뷰 정하고
		String view = "reboard/reBoardWrite";
		// 데이터 베이스에서 데이터 가져오고
		ReBoardDao rDao = new ReBoardDao();
		MemberVO mVO = rDao.getAvtMno(sid);
		// 뷰에 데이터 전달하고
		req.setAttribute("MNO", mVO.getMno());
		req.setAttribute("AVATAR", mVO.getAvatar());
		
		return view;
	}

}