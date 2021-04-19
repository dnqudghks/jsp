package com.increpas.coffee.member;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.increpas.coffee.dao.ClsMemberDao;

@WebServlet("/member/loginProc.cls")
public class ClsLoginProc extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		//할일
		// 로그인 한 회원인지 확인해준다.
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("SID");
		if(obj != null) {
			//이미 로그인 한 상태이다.
			// 따라서 메인화면으로 넘긴다.
			try {
				resp.sendRedirect("/cafe/main.cls");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//1. 파라미터 받고
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");
		//2. 데이터베이스 작업하고
		ClsMemberDao mDao = new ClsMemberDao();
		int cnt = mDao.getLoginCnt(sid, spw);
		//3. 결과값에 따라 처리하고
		String view = "/cafe/main.cls"; // 로그인 성공 뷰
		if(cnt == 1) {
			//로그인에 성공한 경우
			// 로그인에 성공한 경우는 세션에 아이디를 SID라는 키값으로 기록해두기로한다.
			session.setAttribute("SID", sid);
			
		} else {
			// 로그인에 실패한 경우
			view = "/cafe/member/login.cls";
		}
		//4. 뷰를 부르고
		/*
		 	이 경우 로그인에 성공하던지
		 	실패하던지 두경우 모두 요청이 살아있으면 곤란한 경우이다.
		 	따라서 두 경우 모두 리다이렉트 방식으로 뷰를 불러야한다.
		 	리다이렉트 처리는 응답객체에서 처리해서
		 	새로운 요청을 만들어 준다.
		 */
		try {
			resp.sendRedirect(view);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}



