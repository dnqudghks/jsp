package com.increpas.coffee.member;

import javax.servlet.*;
import javax.servlet.http.*;

import com.increpas.coffee.dao.ClsMemberDao;
import com.increpas.coffee.vo.*;

import javax.servlet.annotation.*;
import java.io.*;

@WebServlet("/member/joinProc.cls")
public class JoinProc extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
											throws ServletException, IOException{
		
		//할일
		//파라미터 읽어보고
		String sname = req.getParameter("name");
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		String smail = req.getParameter("mail");
		String stel = req.getParameter("tel");
		String savt = req.getParameter("avt");
		String sgen = req.getParameter("gen");
		int ano = Integer.parseInt(savt) + 10;
		//데이터 하나로 합치고
		MemberVO mVO = new MemberVO();
		
		//데이터베이스 작업하고
		mVO.setName(sname);
		mVO.setId(sid);
		mVO.setPw(spw);
		mVO.setMail(smail);
		mVO.setTel(stel);
		mVO.setAno(ano);
		mVO.setGen(sgen);
		//데이터베이스 작업하고
		ClsMemberDao mDao = new ClsMemberDao();
		int cnt = mDao.addMemb(mVO);
		//결과 받아서 처리하고
		String view = "/cafe/member/join.cls";
		if(cnt == 1) {
			// 이 경우는 회원가입에 성공한 경우이므로
			// 로그인처리를 해주고 
			HttpSession session = req.getSession();
			session.setAttribute("SID", sid);
			// 메인페이지로 보낸다.
			view = "/cafe/main.cls";
			
		}
		//뷰 부르고 ..
		//성공 또는 실패 모두 리다이렉트로 처리해야 되므로
		resp.sendRedirect(view);
	}
}
