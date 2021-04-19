package com.increpas.coffee.controller;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/login.cls2")
public class LoginStart extends HttpServlet {
public void service(HttpServletRequest req, HttpServletResponse resp) {
		HashMap<String, String> map = new HashMap<String, String>();
		String view = "/WEB-INF/views/member/login_ajax.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(view);
		try {
			rd.forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
				
	}
}		
