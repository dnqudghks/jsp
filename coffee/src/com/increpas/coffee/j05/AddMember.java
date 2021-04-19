package com.increpas.coffee.j05;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class AddMember extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) 
						throws ServletException, IOException {
		String view = "/WEB-INF/views/j05/addMember.jsp";
		
		RequestDispatcher rd = req.getRequestDispatcher(view);
		try {
			rd.forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}