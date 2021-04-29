package com.increpas.cafe.controller.reBoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;
import com.increpas.cafe.vo.*;

public class ReBoardReplyProc implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 뷰부르는 방식 설정하고
		req.setAttribute("isRedirect", true);
		
		// 세션 검사하고
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/cafe/member/login.cafe";
		}
		
		String view = "/cafe/reBoard/reBoardList.cafe"; // 처리에 성공한 경우 뷰
		
		// 파라미터 받고
		String sno = req.getParameter("upno");
		int upno = Integer.parseInt(sno);
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		
		BoardVO rVO = new BoardVO();
		rVO.setId(sid);
		rVO.setBno(upno);
		rVO.setTitle(title);
		rVO.setBody(body);
		
		// 데이터가 준비되었으니 데이터베이스 작업하고 결과받는다.
		ReBoardDao rDao = new ReBoardDao();
		int cnt = rDao.addReply(rVO);
		
		// 결과값에 따른 처리
		if(cnt != 1) {
			// 원칙은 처리해야 되지만..
			// 이 예제는 그냥 리스트페이지로 돌려보내기로 하자.
		}
		
		return view;
	}

}
