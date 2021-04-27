package com.increpas.cafe1.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/main.cafe1")
public class MainCtrl extends HttpServlet {
	public void sercive(HttpServletRequest req, HttpServletResponse resp)
							throws ServletException, IOException {
		String view = "/WEB-INF/views/main.cafe1";
		
		//뷰를 부른다.
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req,  resp);
	}
}
