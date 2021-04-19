<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cls Main</title>
<link rel="stylesheet" type="text/css" href="/cafe1/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cafe1/css/user.css">
<script type="text/javascript" src="/cafe1/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/cafe1/js/w3color.js"></script>
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
		</div>
	</div>
</body>
</html>