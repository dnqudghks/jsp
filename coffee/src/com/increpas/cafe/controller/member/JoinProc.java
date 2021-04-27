package com.increpas.cafe.controller.member;

import javax.servlet.http.*;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;
import com.increpas.cafe.vo.*;

public class JoinProc implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 할일
		// 1. 파라미터 받고
		String sname = req.getParameter("name");
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		String smail = req.getParameter("mail");
		String stel = req.getParameter("tel");
		String sgen = req.getParameter("gen");
		String savt = req.getParameter("avt");
		int ano = Integer.parseInt(savt) + 10;
		// 2. vo에 담고
		MemberVO mVO = new MemberVO();
		mVO.setName(sname);
		mVO.setId(sid);
		mVO.setPw(spw);
		mVO.setMail(smail);
		mVO.setTel(stel);
		mVO.setGen(sgen);
		mVO.setAno(ano);
		// 3. db 처리하고
		MemberDao mDao = new MemberDao();
		int cnt = mDao.addMember(mVO);
		// 4. 결과 처리하고
		String view = "/cafe/main.cafe";
		if(cnt == 1) {
			req.getSession().setAttribute("SID", sid);
			req.setAttribute("isRedirect", true);
		} else {
			view = "redirectPage";
			req.setAttribute("JOINMSG", "회원 가입에 실패했습니다.");
		}
		
		// 5. 뷰 부르고
		return view;
	}

}