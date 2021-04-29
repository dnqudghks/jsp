package com.increpas.cafe.controller.reBoard;

import javax.servlet.http.*;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;
import com.increpas.cafe.vo.*;

public class ReBoardWriteProc implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 이 컨트롤러는 처리 결과들 모두 뷰를 부를때 리다이렉트로 불러야 한다.
		req.setAttribute("isRedirect", true);
		
		// 세션 확인하고
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/cafe/member/login.cafe";
		}
		
		// 성공 뷰 정하고
		String view = "/cafe/reBoard/reBoardList.cafe";
		
		// 데이터 받고
		String sno = req.getParameter("mno");
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		// vo 에 데이터 담고
		BoardVO rVO = new BoardVO();
		rVO.setMno(Integer.parseInt(sno));
		rVO.setTitle(title);
		rVO.setBody(body);
		
		// 데이터베이스 작업하고
		ReBoardDao rDao = new ReBoardDao();
		
		int cnt = rDao.addReBoard(rVO);
		
		if(cnt != 1) {
			view = "/cafe/reBoard/reBoardWrite.cafe";
		}
		
		return view;
	}

}