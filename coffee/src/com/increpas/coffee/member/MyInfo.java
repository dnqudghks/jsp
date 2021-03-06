package com.increpas.coffee.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.increpas.coffee.dao.ClsMembDao;
import com.increpas.coffee.vo.MemberVO;

@WebServlet("/member/myInfo.cls")
public class MyInfo extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
										throws ServletException, IOException{
		//할일
		// 다른 요청처리에서는 먼저 파라미터를 받아서 처리를 해야했지만
		// 이 경우는 회원의 아이디가 필요하고
		// 필요한 아이디를 우리는 세션에서 꺼내서 처리하기로 했다.
		
		// 세션꺼내오고
		HttpSession session = req.getSession();
		// 세션에 등록된 아이디 꺼내오고
		String sid = (String) session.getAttribute("SID");
		
		if(sid == null) {
			// 이 경우는 아직 로그인 안한 상태이므로 로그인인 페이지로 보낸다.
			resp.sendRedirect("/cafe/member/login.cls");
			
			// 이 함수의 실행을 종료한다.
			return;
		}
		
		// 이 경우는 로그인한 상태이므로 아이디를 이용해서 데이터베이스에서 조회한다.
		ClsMembDao mDao = new ClsMembDao();
		
		MemberVO mVO = mDao.getLoginInfo(sid);
		
		/*
		 	이 요청의 경우는
		 	데이터가 만들어지면 요청은 남겨놓고
		 	뷰를 교체해서 보여주는 방식의 요청처리가 이루어질 것이고
		 	이 방법을 우리는 forward 방식이라 했따.
		 	
		 	이때 요청이 남겨졌다는 것은 request 객체가 유지가 된다는 것이고
		 	뷰(jsp)에 데이터를 표현을 하려면
		 	이 요처ㅇ객체를 사용해서 요청객체에 속성을 추가하는 방법으로
		 	데이터를 전달하면 된다.
		 	
		 	방법 ]
		 	 	req.serAttribute('키값", 데이터);
		 	 	
		 	 	==>
		 	 	뷰에서 사용하는 방법 ]
		 	 		${키값}
		 	 	
		 	 	참고 ]
		 	 		이때 데이터가 자바 객체(vo)인 경우는
		 	 		실제 사용하는 데이터들이 모두 멤버변수에 기억되어있을 것이고
		 	 		그 변수의 내용을 꺼내서 사용하는 방법은
		 	 		*****
		 	 		getXXX()의 형식으로 원래는 함수를 호출해서 사용해야 하지만
		 	 		JSP 에서는 jsp문서에서 변수의 데이터를 읽는 방법을
		 	 			${키값.변수이름}
		 	 		의 형식으로 사용하도록 처리해 놓았다.
		 	 		이때 변수에 직접 접근해서 처리하는것이 아니고
		 	 		JSP에서 내부적으로
		 	 		  	getXXXX()
		 	 		함수를 호출해서 처리하는 방식으로 처리해 놓았다.
		 */
		
		// 데이터 심고
		req.setAttribute("DATA", mVO);
		
		//뷰를 부르고
		String view = "/WEB-INF/views/member/memberinfo.jsp";
		RequestDispatcher rd  = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
}
