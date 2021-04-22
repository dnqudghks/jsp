package com.increpas.cafe.dispatch;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.increpas.cafe.controller.CafeController;

/**
 * 이 클래스는 확장자가 .cafe로 요청이 오는 경우
 * Dispatch 시킬 서블릿 클래스이다.
 * @author  우병환
 * @since 	2021.04.21
 * @version v.0.91
 *
 *@see
 *			v.0.91 작업내용
 *				2021.04.21 - 클래스 작성
 *									init() 구현
 *									service() 구현			
 */
@WebServlet("*.cafe")
public class CafeDispatch extends HttpServlet {
	//등록된 결과를 기억할 변수
	public HashMap<String, CafeController> map;
	public void init(ServletConfig config) throws ServletException{
		// 할일
		// 		최초로 이 서블릿이 시작되면
		//		준비된 properties 파일을 읽어서
		// 		이것을 이용해서 맵을 만들어서 놓는다.
		//		즉, 요청이 오면 사용할 클래스가 무엇인지 등록한다.
		
		// 우리의 경우 직접 Properties 에 정의된 내용을 읽어서 
		// Map으로 만들어야하는데
		// Properties라는 클래스를 이용해서 작업한다.
		
		Properties prop = new Properties();
		FileInputStream fin = null;
		try {
			// 파일 경로 변수
			String path =  this.getClass().getResource("").getPath();
		//	System.out.println("#### path" + path);
			// 파일스트림을 만들어서
		fin = new FileInputStream(path+ "../resources/Cafe.properties");
		// 파일의 내용을 Properties에 기억시키고
		prop.load(fin);
		// 이때 Properties 는 앱의 한 종류이므로 이미 맵이 만들어졌다.
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fin.close();
			}catch(Exception e) {
			e.printStackTrace();	
			}
		}
		/*
		 	문제는 위에서 만든 prop에 있는 클래스는
		 	문자열로 된 클래스의 이름이다.
		 	따라서 실행이 불가능한 클래스라는 말이다.
		 	그래서 이것을 실행가능한 클래스로 다시 만들어서 넣어줘야 한다.
		 */
		
		// prop에 있는 내용을 하나씩 꺼내서 
		// 실행가능한 클래스로 만들어서 
		// 이것을 다시 전역변수 map에 넣어주면 된다.
		
		// prop에 있는 키값만 알아내서
		// 만들어진 실행가능한 클래스를 등록할 맵을 만들어 놓고
		map = new HashMap<String, CafeController>();
		
		Enumeration en = prop.keys();
		while(en.hasMoreElements()) {
			//하나씩 키값을 꺼내고
			String key = (String) en.nextElement();
			// Key(요청내용) 에 해당하는 클래스 이름을 꺼내서
			String strClass = prop.getProperty(key);
			
			// 문자열로된 클래스를  실행가능한 클래스로 만들어주고
			try {
				//문자열로된 클래스를 실제 클래스로 변환
				Class tmp = Class.forName(strClass);
				// 강제로 new 시켜서 
				Object o = tmp.newInstance();
				map.put(key, (CafeController) o);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 요청이 올때마다 실행되는 함수 구현
	public void service(HttpServletRequest req, HttpServletResponse resp)
										throws ServletException, IOException {
		// 이 함수는 요청이 오면 실행되는 함수이다.
		// 따라서 이 디스패치 서블릿의 목적에 의해서
		// 요청을 분석하고 
		// 1. 전체 요청을 알아내고
		String full = req.getRequestURI();
		// 2. 도메인을 찾아내고
		String domain = req.getContextPath();
		// 3. 실제 요청내용만 분석한다.
		/*
		 	full = "http://localhost/cafe/main.cls"
		 	domain = "http://localhost/cafe"
		 */
		String real = full.substring(domain.length());
		
		// 요청내용을 알아냈으니
		// 실행해야할 컨트롤러를 선택해서 
		// 앞에서 map에 등록된 것을 이용해서 알아낸다.
		CafeController controller = map.get(real);
		
		// 뷰 처리방식 변수 선언
		Boolean bool = false;
		
		// 변수를 요청객체에 넣어서 CafeController 를 호출한다.
		// 이때 요청 객체는 사용자가 요청할때 만들어진 요청객체를
		// CafeController 에 입력하면서 함수를 호출하게 된다.
		// 다시말해서 하나의 객체를 계속 사용하게 된다.
		req.setAttribute("isRedirect", bool);
		/*
		 	이제 CafeController 에서 isRedirect 의 속성값을 변경하지 않으면
		 	계속 데이터는 false로 유지될 것 이고
		 	이때는 forward 방식으로 뷰릴 부르고
		 	
		 	만약 exec 함수내에서 이 속성값을 변경하게 되면
		 	다른 방식으로
		 		redirect, ajax 처리
		 	도 이곳에서 처리해주면 된다.
		 	
		 	isRedirect 속성값 설명 ]
		 		false : forward 방식
		 		true  : redirect 방식
		 		null  : 비동기 통신 처리
		 */
		
		// 실행하고
		String view = controller.exec(req, resp);
		// 뷰를 호출한다.
		/*
		 	뷰는 컨트롤러에서 생산한 데이터를 이용해야 하므로
		 	같은 요청이 유지되어야 사용할 수 있다.
		 	HTTP 방식은 요청이 바뀌면 그전의 모든 데이터가 사라진다.
		 	요청이 유지되는 상태로 뷰를 부르는 방식이 
		 		forward 이다.
		 	M	- 생산된 데이터 자체를 표현하는 용어
		 	V	- 생산된 데이터를 이용해서 응답문서를 만드는 업무
		 	C	- 데이터 생산을 담당하는 업무를 담당(실제 필요한 기능을 수행)
		 */
		
		/*
		 	참고 ]
		 		우리의 경우 구현된 함수가 반환해주는 문자열은
		 		보여줘야할 파일의 이름만 반환해주도록 했으므로
		 		앞에 공통적으로 붙여야할 경로와 확장자는
		 		함수가 반환해주는 문자열의 덧붙여서 처리를 해야한다.
		 		이때
		 			앞에 붙이는 경로는 prefix라는 변수로 처리하고
		 			뒤에 붙는 확장자는 surfix라는 변수로 처리하기로 하자.
		 */
		
		// 요청객체에 isRedirect 속성값을 변수에 담고 
		bool = (Boolean)req.getAttribute("isRedirect");
		// 변수의 내용을 살펴서 처리한다.
		if(bool == null) {
			//비동기 통신처리
			PrintWriter pw = resp.getWriter();
			pw.print(view);
		}else if (bool) {
			//리다이렉트 처리하는 경우
			resp.sendRedirect(view);
		}else {
		//forward 방식 처리
		String prefix = "/WEB-INF/views/cafe/";
		String surrffix = ".jsp";
		
		RequestDispatcher rd = req.getRequestDispatcher(prefix + view + surrffix);
		rd.forward(req, resp);
		
		}
	}
	
}
