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
<style type="text/css"></style>
<script type="text/javascript">
	$(document).ready(function(){
		$('.w3-button').click(function(){
			// 어떤버튼이 클릭되었는지 확인하고
			var bid = $(this).attr('id');
			var url = '/cafe/main.cafe';
			switch(bid){
			case 'rbtn':
				document.frm.reset();
				return;
			case 'ebtn':
				
				return;
			}
			$(location).attr('href', url);
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw750 w3-margin-top">
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-green w3-center w3-padding mg0">게시글 수정</h1>
		</header>
		
		<section class="w3-col">
			<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
				<div class="w3-col w120 w3-center pdAll10">
					<img src="/cafe/img/avatar/${DATA.avatar}" class="inblock w3-circle avtBox100 border3px w3-card-2">
					<span class="w3-col mgt10 ft10"><b>${SID}</b></span>
				</div>
				<div class="w3-rest w3-padding w3-border-left h100per">
					<form method="POST" action="/cafe/reBoard/reBoardEditProc.cafe" name="frm" id="frm" class="w3-col">
						<input type="hidden" name="rno" id="rno" value="${DATA.rno}">
	<c:if test="${DATA.upno ne 0}">
						<input type="hidden" name="upno" id="upno" value="${DATA.upno}">
						<div class="w3-col w3-margin-bottom">
							<span class="w3-col m2 mgt10 w3-text-grey ft14"><label><b>원글제목</b></label></span>
							<div class="w3-col m10 pdl10">
								<input type="text" class="w3-input w3-text-blue w3-border-0 ft14" value="${DATA.uptitle}" disabled>
							</div>
						</div>
	</c:if>
						<div class="w3-col">
							<span class="w3-col m2 mgt10 w3-text-grey ft14"><label for="title"><b>글제목</b></label></span>
							<div class="w3-col m10 pdl10">
								<input type="text" name="title" id="title"  class="w3-input w3-border w3-border-grey" 
									placeholder="글제목을 입력하세요!" value="${DATA.title}">
							</div>
						</div>
						<div class="w3-col w3-margin-top">
							<span class="w3-col m2 mgt10 w3-text-grey ft14"><label for="body"><b>글내용</b></label></span>
							<div class="w3-col m10 pdl10">
								<textarea rows="7" class="w3-input w3-border w3-border-grey noresize"
									name="body" id="body" placeholder="본문 내용을 입력하세요!">${DATA.body}</textarea>
							</div>
						</div>
					</form>
				</div>
			</div>
		</section>

		<footer class="w3-col w3-card-4">
			<div class="w3-third w3-left w3-button w3-small w3-green" id="hbtn">home</div>
			<div class="w3-third w3-left w3-button w3-small w3-lime" id="rbtn">reset</div>
			<div class="w3-third w3-left w3-button w3-small w3-blue" id="ebtn">글수정</div>
		</footer>
	</div>
</body>
</html>