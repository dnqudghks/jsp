package com.increpas.cafe.controller.reBoard;

import javax.servlet.http.*;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;
import com.increpas.cafe.vo.*;

public class ReBoardReply implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 뷰 설정하고
		String view = "reboard/reBoardReply";
		// 세션 검사하고
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			// 뷰 방식 설정
			req.setAttribute("isRedirect", true);
			return "/cafe/member/login.cafe";
		}
		
		// 파라미터 꺼내고
		String tno = req.getParameter("upno");
		String title = req.getParameter("title");
		
		// 내 아바타 가져오고...
		ReBoardDao rDao = new ReBoardDao();
		String avatar = rDao.getAvtMno(sid).getAvatar();
		
		// 데이터 전달 하고
		req.setAttribute("UPNO", tno);
		req.setAttribute("AVATAR", avatar);
		req.setAttribute("UPTITLE", title);
		
		return view;
	}

}