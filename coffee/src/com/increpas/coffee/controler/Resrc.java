package com.increpas.coffee.controler;


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
		 */
		
		url = url.substring(url.indexOf("/", 1));
		
		// 요청 루트 경로
		// <== /css || /js || /img
		String spath = url.substring(0, url.indexOf("/", 1));
		
		url = url.substring(url.indexOf(spath + "/"));
		
		String view = "/WEB-INF/resources" + url;
		
		//응답을 해준다.
		RequestDispatcher rd = req.getRequestDispatcher(view);
		try {
			rd.forward(req, resp);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
