package com.increpas.cafe.controller.reBoard;

import java.util.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cafe.controller.*;
import com.increpas.cafe.dao.*;
import com.increpas.cafe.vo.*;

public class ReBoardList implements CafeController {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) {
		String view = "reboard/reBoardList";
		//할일
		// 1. 데이터 베이스 조회한 결과
		ReBoardDao rDao = new ReBoardDao();
		ArrayList<BoardVO> list = rDao.getReBoardList();
		// 2. 결과를 요청객체에 심고
		req.setAttribute("LIST", list);
		// 3. 뷰를 부른다.
		
		return view;
	}

}
