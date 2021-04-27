package com.increpas.cafe.controller.member;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;
import com.increpas.cafe.vo.*;

public class MyInfoEdit implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "member/myInfoEdit";
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			// 이 경우는 로그인을 하지 않았거나
			// 시간이 초과되서 세션이 만료된 경우...
			// 따라서 로그인페이지로 보내자.
			req.setAttribute("isRedirect", true);
			return "/cafe/member/login.cafe";
		}
		
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getLoginInfo(sid);
		ArrayList<MemberVO> list = mDao.getAvtList(mVO.getGen().equals("남자") ? "M" : "F");
		req.setAttribute("DATA", mVO);
		req.setAttribute("LIST", list);
		return view;
	}

}