package com.increpas.coffee.member;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.coffee.dao.ClsMemberDao;
/**
 * 이 클래스는 회원가입시 아이디 체크 요청을 처리할 서블릿 클래스
 * @author 우병환
 * @since 	2021.04.20
 * @version v.0.95
 *
 */

// 응답문서를 만들때 자바객체의 내용을 json 데이터를 만들때는 외부라이브러리를 사용해서 처리한다.
// 그때 사용하는 라이브러리는 gson, jackson,... 
// 이 예제에서는 응답문서를 실제로 어떻게 만들어서 처리하는지 공부하는 목적으로
// 직접 작성해보기로 하자.

@WebServlet("/member/idcheck.cls")
public class IdCheck extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse resp)
												throws ServletException, IOException{
		//할일
		//파라미터 꺼내고
		String sid = req.getParameter("id");
		// 데이터 베이스 조회결과 밥고
		ClsMemberDao mDao = new ClsMemberDao();
		int cnt = mDao.getIdCnt(sid);
		
		String result = "NO";
		if(cnt == 0) {
			//이 경우는 아이디를 사용하는 사람이 없는 경우
			result = "OK";
		}
		
		// 응답문서 만들어준다.
		// 응답문서는 json 문서를 만들어야 하는데
		// 이때 응답문서를 만들어주는 기능이 이미 구현되어있다.
		
		//문서 작성기 꺼내고
		PrintWriter pw = resp.getWriter();
		
		//문서작성한다.
		pw.println("{");
		pw.println("\"result\": \"" + result + "\"");
		pw.println("}");
	}
}
