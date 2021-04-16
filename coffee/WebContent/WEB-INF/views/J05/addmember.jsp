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
	#avtfr {
		display: none;
	}
	.avtimg{
		width: 100px;
		height: 100px;
	}
	.avtbox{
		width:102px;
		height: 117px;
		diplay: none;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		//form 태그 action 속성부여
		// 1. code 값 받아오고
		var scode = '${param.code}';
		
		if(scode == 'taglib'){
			$('#frm').attr('action', '/cafe/j05/memberinfo.cls');
		}	else {
			$('#frm').attr('action', '/cafe/J05/memberinfo2.cls');
		}
		// 제출버튼 이벤트 처리
		$('#sbtn').click(function(){
			// 할일
			// 1. 입력된 내용 읽어오고
			var sid = $('#id').val();
			var sname = $('#name').val();
			var sgen = $('#gen :checked').val();
			var savt = $('#avt :selected').val();
			
			// 2. 입력안된 내용있으면 돌려보내고
			if(!(sid && sname && sgen && savt)){
				$('#id01').css('display', 'block');
			}
			// 3. 제출하고
			$('#frm').submit();
		});
		
		// 모달창 닫기 이벤트 처리
		$('.md1').click(function(){
			$('#id01').css('display', 'none');
		});
		
		$(document.frm.gen).change(function(){
			var sgen = $(this).val();
			
			if(sgen == 'M'){
				$('#avt').html('');
				
				// 기본 태그 채우고
				$('#avt').append('<option value=""># 아바타 선택</option>');
				$('#avt').append('<option value="1">avatar1</option>');
				$('#avt').append('<option value="2">avatar2</option>');
				$('#avt').append('<option value="3">avatar3</option>');
			} else if(sgen == 'F'){
				$('#avt').html('');
				
				$('#avt').append('<option value=""># 아바타 선택</option>');
				$('#avt').append('<option value="4">avatar4</option>');
				$('#avt').append('<option value="5">avatar5</option>');
				$('#avt').append('<option value="6">avatar6</option>');
			}
			
			$('#avtfr').stop().slideDown(300);
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw650 w3-center">
		<h1 class="w3-blue w3-padding w3-card-4 w3-round-large">회원정보입력</h1>
		
		<form method="post" action="memberInfo2.jsp" name="frm" id="frm">
		<input type="hidden" name="code" value="${param.code}">
			<div class="w3-col w3-margin-top w3-card-4 w3-padding w3-round-large">
				<div class="w3-col pdt10 w3-margin-top">
					<label for="name" class="w3-col m3 w3-right-align w3-text-grey">회원이름 : </label>
					<input type="text" name="name" id="name" class="w3-col m8 w3-input w3-border mgl20">
				</div>
				<div class="w3-col w3-margin-top">
					<label for="id" class="w3-col m3 w3-right-align w3-text-grey">아이디 : </label>
					<input type="text" name="id" id="id" class="w3-col m8 w3-input w3-border mgl20">
				</div>
				<div class="w3-col w3-margin-top">
					<label class="w3-col m3 w3-right-align w3-text-grey">회원성별 : </label>
					<div class="w3-col m8 mgl20" id="gen">
						<div class="w3-half w3-center">
							<input type="radio" name="gen" class="w3-radio" value="M">
							<span>남 자</span>
						</div>
						<div class="w3-half w3-center">
							<input type="radio" name="gen" class="w3-radio" value="F">
							<span>여 자</span>
						</div>
					</div>
				</div>
				
				<div class="w3-col w3-margin-top w3-margin-bottom">
					<div id="avtfr">
						<label class="w3-col m3 w3-right-align w3-text-grey">아 바 타 : </label>
						
						
						<!-- 
						<select class="w3-col m8 w3-text-grey w3-select w3-border mgl20" name="avt" id="avt">
							<option value=""># 아바타 선택</option>
						</select>
						 -->
						<div class="w3-col avtbox" id="mavt">
					<c:forEach var = "idx" begin="1" end="3">
							<div class="inblock avtbox">
								<img src="/img/avatar/img_avatar1.png" class="w3-col avtimg">
								<input type="radio" name="avt" value="${idx}">
							</div>
							</c:forEach>
						</div>
						<div class="w3-col avtbox" id="favt">
					<c:forEach var = "idx" begin="1" end="3">
							<div class="inblock avtbox">
								<img src="/img/avatar/img_avatar1.png" class="w3-col avtimg">
								<input type="radio" name="avt" value="${idx}">
							</div>
							</c:forEach>
						
						</div>
					</div>
				</div>
				
			</div>
		</form>
		
		<div class="w3-col w3-margin-top w3-card-4 showFr">
			<div class="w3-half w3-padding w3-red w3-hover-orange" id="rbtn">reset</div>
			<div class="w3-half w3-padding w3-green w3-hover-lime" id="sbtn">submit</div>
		</div>
		
		<!-- message modal -->
		<div id="id01" class="w3-modal">
			<div class="w3-modal-content mxw600 w3-card-4">
			    <header class="w3-container w3-teal"> 
			      <span class="w3-button w3-display-topright md1">&times;</span>
			      <h2>입력 오류</h2>
			    </header>
			    <div class="w3-container w3-margin">
			      <h4 class="w3-text-red w3-center w3-margin">필수 입력 사항을 확인하세요.</h4>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>