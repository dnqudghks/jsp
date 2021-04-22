<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cls Main</title>
<link rel="stylesheet" type="text/css" href="/cafe/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cafe/css/user.css">
<script type="text/javascript" src="/cafe/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/cafe/js/w3color.js"></script>
<style type="text/css"></style>
<script type="text/javascript">
	$(document).ready(function(){
		// 태그라이브러리 테스트 버튼 이벤트 처리
		$('#libbtn1').click(function(){
			// 할일
			// 1. 넘겨야할 데이터 form 태그에 추가해주고
			$('#frm').append('<input type="hidden" name="code" value="taglib">');
			// 2. 보내질 주소 셋팅하고
			$('#frm').attr('action', '/cafe/j05/addMember.cls');
			// 3. 폼태그 전송하고
			$('#frm').submit();
		});
		// jQuery 처리 테스트 버튼 이벤트 처리
		$('#libbtn2').click(function(){
			// 할일
			// 1. 넘겨야할 데이터 form 태그에 추가해주고
			$('#frm').append('<input type="hidden" name="code" value="jquery">');
			// 2. 보내질 주소 셋팅하고
			$('#frm').attr('action', '/cafe/j05/addMember.cls');
			// 3. 폼태그 전송하고
			$('#frm').submit();
		});
		
		// login 버튼 클릭 이벤트 처리
		$('#login').click(function(){
			// 폼을 전송하는 방법
			$('#frm').attr('action', '/cafe/member/login.cls');
			$('#frm').submit();
		});
		/* 로그아웃버튼 이벤트 처리*/
		$('#logout').click(function(){
			$(location).attr('href', 'http://localhost/cafe/member/logout.cls');
		});
		/* 회원가입버튼 이벤트 처리*/
		$('#join').click(function(){
			$(location).attr('href', 'http://localhost/cafe/member/join.cls');
		});
		
		$('#myInfo').click(function(){
			$(document.frm).attr('action', '/cafe/member/myInfo.cls');
			$(document.frm).submit();
		});
		
		$('.cafeMBtn').click(function(){
			// 어떤 버튼이 클릭되었는지 확인하고
			var tid = $(this).attr('id');
			var url = '';
			switch(tid){
			case 'cafeLogin' :
				url = '/cafe/member/login.cafe';
				break;
			
			case 'cafeJoin' :
				url = '/cafe/member/join.cafe';
				break;
			case 'cafeLogout' :
				url = '/cafe/member/logout.cafe';
				break;
			}
			$(location).attr('href', url);
		});
	});
</script>
</head>
<body>
	<!-- 데이터 전송용 form tag -->
	<form method="POST" id="frm" name="frm">
	</form>
	
	<div class="w3-content mxw700 w3-center w3-margin-top">
		<h1 class="w3-margin-top w3-blue w3-card-4 w3-padding">CLS Project</h1>
		<div class="w3-margin-top">
			<!-- 수업 예제 링크 버튼 추가 장소 -->
			<div class="w3-col w3-padding w3-border-bottom">
				<h4 class="w3-col s4 w3-text-grey">Tag Library Example</h4>
				<div class="w3-col s8">
					<div class="w3-col w3-lime w3-margin-bottom w3-button" id="libbtn1">
						<h4>Tag Library 처리</h4>
					</div>
					<div class="w3-col w3-lime w3-margin-bottom w3-button" id="libbtn2">
						<h4>jQuery 처리</h4>
					</div>
				</div>
			</div>
			
			<!-- 회원관련 처리 버튼 -->
			<div class="w3-col w3-padding w3-border-bottom">
				<h4 class="w3-col s4 w3-text-grey">회원 관련 처리</h4>
				<div class="w3-col s8">
				<%--
					EL 객체중 
						requestScope, sessionScope 에 추가된 속성은 
						별도로 소속을 밝혀주지 않아도 된다.
						
						파라미터의 경우는 스코프를 분명히 지정해줘야 해당 데이터를 꺼내올 수 있었다.
							${param.파라미터키값}
							
						하지만 requesScope와 sessionScope는 소속을 밝혀주지 않아도 꺼내올 수 있다.
							${속성이름}
				--%>
			<c:if test="${empty SID}">
			<%-- 
				이 경우는 세션에 SID 라는 키값으로 데이터가 입력되지 않은 상태이므로
				아직 로그인 안한 상태이다.
			--%>
					<div class="w3-col w3-red w3-margin-bottom w3-button" id="login">
						<h4>로그인</h4>
					</div>
			</c:if>		
			<c:if test="${not empty SID}">
			<%-- 이 경우는 세션에 SID 라는 키값으로 데이터가 입려되어있는 상태이다.
				 따라서 로그인 처리가 되었다는 말이 된다.
			 --%>
			 <div class="w3-col w3-red w3-margin-bottom w3-button" id="myInfo">
						<h4>내 정보 보기</h4>
			</div>
					<div class="w3-col w3-pink w3-margin-bottom w3-button" id="logout">
						<h4>로그아웃</h4>
					</div>
					<script type="text/javascript">
						alert('아이디 : ${SID}');
					</script>
			</c:if>		
			<c:if test="${empty SID}">
					<div class="w3-col w3-purple w3-margin-bottom w3-button" id="join">
						<h4>회원가입</h4>
					</div>
			</c:if>		
				</div>
			</div>
			<!--  수업 예제 링크 버튼 추가 장소 -->
			<div class="w3-col w3-padding w3-border-bottom">
				<h4 class="w3-col s4 w3-text-grey">Dispatch Controller Request</h4>
				<div class="w3-col s8">
			<c:if test="${empty SID }">
					<div class="w3-col w3-green w3-margin-bottom w3-button cafeMBtn" id="cafeLogin">
						<h4>Cafe Login</h4>
					</div>
					<div class="w3-col w3-blue w3-margin-bottom w3-button cafeMBtn" id="cafeJoin">
						<h4>Cafe Join</h4>
					</div>
			</c:if>
			<c:if test="${not empty SID }">
					<div class="w3-col w3-purple w3-margin-bottom w3-button " id="cafeMyInfo">
						<h4>Cafe 내정보보기</h4>
					</div>
					<div class="w3-col w3-deep-purple w3-margin-bottom w3-button cafeMBtn" id="cafeLogout">
						<h4>Cafe 로그아웃</h4>
					</div>
			</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>