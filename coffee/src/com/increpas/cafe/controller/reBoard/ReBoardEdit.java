package com.increpas.cafe.controller.reBoard;

import javax.servlet.http.*;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;
import com.increpas.cafe.vo.*;

public class ReBoardEdit implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		// 세션 조회하고
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/cafe/member/login.cafe";
		}
 		
		// 파라미터 받고
		String sno = req.getParameter("rno");
		int rno = Integer.parseInt(sno);
		
		// vo 에 데이터 담고
		BoardVO rVO = new BoardVO();
		rVO.setId(sid);
		rVO.setRno(rno);
		
		// 데이터베이스 조회하고
		ReBoardDao rDao = new ReBoardDao();
		rDao.getBoardRno(rVO);
		
		// 변경된 vo 뷰에 전달하고
		req.setAttribute("DATA", rVO);
		
		String view = "reboard/reBoardEdit";
		return view;
	}

}