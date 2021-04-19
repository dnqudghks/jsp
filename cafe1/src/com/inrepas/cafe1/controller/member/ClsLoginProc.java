package com.inrepas.cafe1.controller.member;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/loginProc.cafe1")
public class ClsLoginProc extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();
		Object obj = session.getAttribute("SID");
		if (obj != null) {
			resp.sendRedirect("/cafe/main.cafe1");
			try {

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String sid = req.getParameter("id");
		String spw = req.getParameter("pw");

		ClsMemberDao mDao = new ClsMemberDao();
		int cnt = mDao.getLoginCnt(sid, spw);
		String view = "/cafe1/main.cafe1";
		if (cnt == 1) {
			session.setAttribute("SID", sid);
		} else {
			view = "/cafe/member/login.cafe1";
		}
		try {
			resp.sendRedirect(view);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}