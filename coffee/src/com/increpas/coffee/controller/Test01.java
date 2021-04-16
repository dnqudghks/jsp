package com.increpas.coffee.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Test01")
public class Test01 extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		String view = "/WEB-INF/views/Test01.jsp";
		
		RequestDispatcher rd = req.getRequestDispatcher(view);
		try {
				rd.forward(req, resp);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
