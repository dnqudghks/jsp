package com.increpas.coffee.controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class MainController extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
											throws ServletException, IOException {
		// 사용할 뷰 정의하고
		String view = "/WEB-INF/views/main.jsp";
		
		// 디스패치 꺼내오고
		RequestDispatcher rd = req.getRequestDispatcher(view);
		try {
			// 뷰를 부르고...
			rd.forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
