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
	label {
		font-size: 14pt;
	}
	
	#avtimg {
		display: inline-block;
		width: 100px;
		height: auto;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#hbtn').click(function(){
			// addMember.jsp 페이지로 돌려보낸다.
			$(location).attr('href', '/cafe/main.cls');
		});
		
	});
</script>
</head>
<body>
<%--
	회원아이디가 
		euns 이면 농장주
		han, chae 이면 종업원
		나머지는 방문객으로 표현되도록 한다.
		
	회원의 성별은
		남자, 여자로 표현한다.
 --%>
	<div class="w3-content mxw650 w3-center">
		<h1 class="w3-green w3-padding w3-card-4 w3-round-large">내 정보</h1>
		
		<div id="frm">
			<div class="w3-col w3-margin-top w3-card-4 w3-padding w3-round-large">
				<div class="w3-col pdt10 w3-margin-top">
					<label class="w3-col m3 w3-right-align w3-text-grey">회원번호 : </label>
					<span id="mno" class="w3-col m8 mgl20 pdl20">${DATA.mno}</span>
				</div>
				<div class="w3-col pdt10 w3-margin-top">
					<label class="w3-col m3 w3-right-align w3-text-grey">회원이름 : </label>
					<span id="name" class="w3-col m8 mgl20 pdl20">${DATA.name}</span>
				</div>
				<div class="w3-col w3-margin-top">
					<label class="w3-col m3 w3-right-align w3-text-grey">아이디 : </label>
					<span id="id" class="w3-col m8 mgl20 pdl20">${DATA.id}</span>
				</div>
				<div class="w3-col pdt10 w3-margin-top">
					<label class="w3-col m3 w3-right-align w3-text-grey">메일주소 : </label>
					<span id="mail" class="w3-col m8 mgl20 pdl20">${DATA.mail}</span>
				</div>
				<div class="w3-col pdt10 w3-margin-top">
					<label class="w3-col m3 w3-right-align w3-text-grey">전화번호 : </label>
					<span id="tel" class="w3-col m8 mgl20 pdl20">${DATA.tel}</span>
				</div>
				<div class="w3-col w3-margin-top">
					<label class="w3-col m3 w3-right-align w3-text-grey">회원성별 : </label>
					<span id="gen" class="w3-col m8 mgl20 pdl20">${DATA.gen}</span>
				</div>
				<div class="w3-col pdt10 w3-margin-top">
					<label class="w3-col m3 w3-right-align w3-text-grey">가 입 일 : </label>
					<span id="sdate" class="w3-col m8 mgl20 pdl20">${DATA.sdate}</span>
				</div>
					
				<div class="w3-col w3-margin-top w3-margin-bottom">
					<label class="w3-col m3 w3-right-align w3-text-grey">아바타 : </label>
					<div class="w3-col m8 mgl10 pdl10">
						<img src="/cafe/img/avatar/${DATA.avatar}" 
								class="w3-border w3-border-grey w3-card-2" width=200px id="avtimg${DATA.ano}">
					</div>
				</div>
			</div>
		</div>
		<div class="w3-col w3-margin-top w3-card-4 showFr">
			<div class="w3-col w3-padding w3-green w3-hover-lime " id="hbtn">home</div>
		</div>
	</div>
</body>
</html>