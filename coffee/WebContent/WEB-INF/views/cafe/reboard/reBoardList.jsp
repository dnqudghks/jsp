<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/cafe/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cafe/css/user.css">
<script type="text/javascript" src="/cafe/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/cafe/js/w3color.js"></script>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		// 각각의 태그에 이벤트를 적용시키는 방법<-- 해당태그를 선택해서 처리하는 방법
		/*
		$('#hbtn').click(function(){
			$(location).attr('href', '/cafe/main.cafe');
		});
		*/
		
		// 여러태그를 동시에 이벤트를 처리하는 방법
		$('.w3-button').click(function(){
			// 이렇게 선택하면 클래스에 w3-button 이 추가되어있는 태그는 모두 선택하고
			// 선택된 태그들에 클릭이벤트를 등록하게 된다.
			
			//할일 
			// 어떤태그가 클릭되어있는지 먼저 알아낸다.
			var tid = $(this).attr('id');
			var url = '';
			switch(tid){
			case 'hbtn':
				url = '/cafe/main.cafe';
				break;
			case 'rbtn':
				url = '/cafe/member/login.cafe';
				break;
			case 'jbtn':
				url = '/cafe/member/join.cafe';
				break;
			case 'wbtn':
				url = '/cafe/reboard/reBoardWrite.cafe';//원글쓰기 버튼
				break;
			}
			$(location).attr('href', url);
		});
	});
</script>
</head>
<body>
	<form method="POST" action="/cafe/reBoard/reBoardReply.cafe" id="frm" name="frm">
		<input type="hidden" name="rno" id="rno">
	</form>
	<div class ="w3-content mxw750 margin-top">
		<header class=" w3-col w3-card-4 mgb20">
		<h1 class="w3-green w3-center w3-padding mgb0">방명록 작성</h1>
		<nav class="w3-bar w3-pink ">
			<div class="w3-col w150 w3-left w3-button w3-small w3-amber" id="hbtn">home</div>
	<c:if test="${not empty SID}">
		<c:if test ="${CNT == 0}">
			<div class="w3-col w150 w3-left w3-button w3-small w3-lime w3-right" id="wbtn">${CNT}글작성</div>
		</c:if>
			<div class="w3-col w150 w3-left w3-button w3-small w3-light-green w3-right" id="outbtn">logout</div>
	</c:if>
	<c:if test="${empty SID}">
			<div class="w3-col w150 w3-left w3-button w3-small w3-deep-orange w3-right" id="rbtn">login</div>
			<div class="w3-col w150 w3-left w3-button w3-small w3-yellow w3-right" id="jbtn">join</div>
	</c:if>
		</nav>
		</header>
<c:forEach var="data" items="${LIST}">
	<div class="W3-col" style="padding-left: ${data.step * 70}px">
			<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
			<div class="w3-col w120 box120 w3-center pdAll10 w3-border-right mgb0">
				<img src="/cafe/img/avatar/${data.avatar}" class="inblock avtBox100 w3-border w3-border-grey">
					<span class="w3-col  mgt10 ft10"><b>${data.id}</b></span>
			</div>
			<div class="w3-rest w3-padding w3-display-container">
			<div class="w3-col w3-border-bottom w3-border-gray">
					<div class="w3-col w3-border-bottom">
						<span class="mgt10 ft10"><b>${data.title}</b></span>
						<span class="w3-right mgb10 ft10">${data.sdate}</span>
						</div>
					<div class="w3-col w3-margin-top">
						<span class="w3-col w3-padding ft12">${data.body}</span>
					</div>
				</div>
				<div class="w3-col mgt10">
					<span class="w3-button  w3-blue w3-small w3-left rebtn" id="r${data.rno}">댓글달기</span>	<!-- r100001 -->
	<c:if test="${SID eq data.id}">
					<span class="w3-button  w3-green w3-smaill w3-right mgl10 rebtn" id = "e${data.rno}">수정하기</span>
					<span class="w3-button  w3-red w3-small w3-right rebtn" id="d${data.rno}">삭제하기</span>
	</c:if>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
		</div>
<!--  
	<div class="w3-row w3-margin-top">
		<div class="w3-col m2 w3-button w3-green" id="hbtn">Home</div>
		<div class="w3-col m2 w3-button w3-blue w3-right" id="wbtn">write</div>
	</div>
-->		
</body>
</html>