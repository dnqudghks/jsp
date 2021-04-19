package com.inrepas.cafe1.controller.member;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/member/login.cafe1")
public class LoginForm extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession session = req.getSession();
		Object o = session.getAttribute("SID");
		if(o !=null) {
			
			try {
				resp.sendRedirect("/cafe1/main.cafe1");
			}catch(Exception e) {
				e.printStackTrace();
			}
			return;
		}
		String view = "/WEB-INF/views/member/login.cafe1";
		
		RequestDispatcher rd = req.getRequestDispatcher(view);
		
		try {
			rd.forward(req,  resp);
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
