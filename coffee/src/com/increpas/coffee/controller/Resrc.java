package com.increpas.coffee.controller;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet({"/css/*", "/js/*", "/img/*"})
public class Resrc extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse resp) {
		String url = req.getRequestURI();
		// ==>요청파일 경로 추출한다.
		/*
		 	/css/w3.css 또는 /js/jquery-3.6.0.min.js
		 	
		 	w3.css 파일을 요청하는 경우
		 	 url - /cafe/css/w3.css
		 	 
		 	url에서 /cafe 이후 부분을 추출해서 문서의 경로를 만들어주면 된다.
		 	
		 	 
		 */
		
		url = url.substring(url.indexOf("/", 1));
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/resources" + url);
		try {
			rd.forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
