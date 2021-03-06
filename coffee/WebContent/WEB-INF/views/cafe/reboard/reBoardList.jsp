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
	.rebtn {
		padding: 3px 10px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		/*
		// 각각의 태그에 이벤트를 적용시키는 방법(<== 해당 태그를 선택해서 처리하는 방법)
		$('#hbtn').click(function(){
			$(location).attr('href', '/cafe/main.cafe');
		});
		*/
		
		// 여러태그를 동시에 이벤트를 처리하는 방법
		$('.w150.w3-button').click(function(){
			// 이렇게 선택하면 클래스에 w3-button 이 추가되어있는 태그는 모두 선택하고
			// 선택된 태그들에 클릭이벤트를 등록하게 된다.
			
			// 할일
			// 어떤태그가 클릭되었는지 먼저 알아낸다.
			var tid = $(this).attr('id');
			var url = '';
			switch(tid){
			case 'hbtn':
				url = '/cafe/main.cafe';
				break;
			case 'lbtn':
				url = '/cafe/member/login.cafe';
				break;
			case 'jbtn':
				url = '/cafe/member/join.cafe';
				break;
			case 'outbtn':
				url = '/cafe/member/logout.cafe';
				break;
			case 'wbtn':
				url = '/cafe/reBoard/reBoardWrite.cafe'; // 원글 쓰기 버튼 이벤트 처리
				break;
			}
			$(location).attr('href', url);
		});
		
		$('.rebtn').click(function(){
			// 어떤 버튼이 클릭되었는지 알아낸다.
			// 먼저 아이디속성값 읽어오고
			var tid = $(this).attr('id');
			var pre = tid.substring(0, 1); 	// 앞글자 추출
			var tno = tid.substring(1); 	// 글번호 추출
			var row = $(this).parent().attr('id').substring(3);
			
			if(pre == 'r'){
				// 댓글달기 버튼 클릭한 경우
				
				// input 태그에 채울 데이터 준비한다.
				var tmp1 = '#t' + tno;
				var tmp2 = '#a' + tno;
				var title = $(tmp1).html();
				// 입력태그에 입력해준다.
				$('#upno').val(tno);
				$('#nowPage').val((row == '${PAGE.endCont}') ? 'L${PAGE.nowPage}' : '${PAGE.nowPage}');
				$('#title').val(title);
				
				$('#frm').submit();
				return;
			} else if(pre == 'e'){
				$('#rno').val(tno);
				$('#nowPage').val('${PAGE.nowPage}');
				
				$('#frm').attr('action', '/cafe/reBoard/reBoardEdit.cafe');
				$('#frm').submit();
			} else if(pre == 'd'){
				$('#rno').val(tno);
				
				$('#frm').attr('action', '/cafe/reBoard/reBoardDel.cafe');
				$('#frm').submit();
			}
		});
		
		// 페이지 버튼 이벤트 처리
		$('.pbtn').click(function(){
			var spage = $(this).html();
			
			switch(spage){
			case 'pre':
				$('#nowPage').val('${PAGE.startPage - 1}');
				break;
			case 'next':
				$('#nowPage').val('${PAGE.endPage + 1}');
				break;
			default:
				$('#nowPage').val(spage);
			}
			$('#frm').attr('action', '/cafe/reBoard/reBoardList.cafe');
			$('#frm').submit();
		});
		
	});
</script>
</head>
<body>
<c:if test="${not empty param.msg}">
	<script type="text/javascript">
		alert('수정작업에 실패했습니다!');
	</script>
</c:if>
	<form method="POST" action="/cafe/reBoard/reBoardReply.cafe" id="frm" name="frm">
		<input type="hidden" name="nowPage" id="nowPage">
		<input type="hidden" name="rno" id="rno">
		<input type="hidden" name="upno" id="upno">
		<input type="hidden" name="title" id="title">
	</form>
	<div class="w3-content mxw750 w3-margin-top">
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-green w3-center w3-padding mg0">댓글 게시판</h1>
			<nav class="w3-bar w3-pale-yellow">
				<div class="w3-col w150 w3-left w3-button w3-small w3-amber" id="hbtn">home</div>
<c:if test="${not empty SID}">
				<div class="w3-col w150 w3-left w3-button w3-small w3-lime w3-right" id="wbtn">글작성</div>
				<div class="w3-col w150 w3-left w3-button w3-small w3-light-green w3-right" id="outbtn">logout</div>
</c:if>
<c:if test="${empty SID}">
				<div class="w3-col w150 w3-left w3-button w3-small w3-deep-orange w3-right" id="lbtn">login</div>
				<div class="w3-col w150 w3-left w3-button w3-small w3-yellow w3-right" id="jbtn">join</div>
</c:if>
		</nav>
		</header>
		
<c:forEach var="data" items="${LIST}">
		<div class="w3-col" style="padding-left: ${(data.step <= 2) ? data.step * 70 : 140}px">
			<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
				<div class="w3-col w120 w3-center pdAll10">
					<img src="/cafe/img/avatar/${data.avatar}" class="inblock w3-circle avtBox100 border3px w3-card-2" id="a${data.rno}">
					<span class="w3-col mgt10 ft10"><b>${data.id}</b></span>
				</div>
				<div class="w3-rest w3-padding h100per">
					<div class="w3-col w3-border-bottom w3-border-grey">
						<div class="w3-col w3-border-bottom">
							<span class="mgt10 ft10"><b id="t${data.bno}">${data.title}</b></span>
							<span class="w3-right mgb10 ft10">${data.sdate}</span>
						</div>
						<div class="w3-col w3-margin-top">
							<span class="w3-col w3-padding ft12 bdminH">${data.body}</span>
						</div>
					</div>
					<div class="w3-col mgt10" id="row${data.rno}">
						<span class="w3-button w3-blue w3-small w3-left w3-round-medium rebtn" id="r${data.bno}">댓글달기</span><!-- r100001 -->
		<c:if test="${SID eq data.id}">
						<span class="w3-button w3-green w3-small w3-right w3-round-medium mgl10 rebtn" id="e${data.bno}">수정하기</span>
						<span class="w3-button w3-red w3-small w3-right w3-round-medium rebtn" id="d${data.bno}">삭제하기</span>
		</c:if>			
					</div>
				</div>
			</div>
		</div>
</c:forEach>

		<div class="w3-center w3-margin-bottom">
			<div class="w3-bar w3-border w3-round w3-margin-top">
	<c:if test="${PAGE.startPage == 1}">
				<span class="w3-bar-item w3-grey">pre</span>
	</c:if>
	<c:if test="${PAGE.startPage != 1}">
				<span class="w3-bar-item w3-button w3-hover-aqua pbtn">pre</span>
	</c:if>
<c:forEach var="page" begin="${PAGE.startPage}" end="${PAGE.endPage}">
	<c:if test="${PAGE.nowPage == page}">
				<span class="w3-bar-item w3-button w3-hover-aqua w3-blue pbtn">${page}</span>
	</c:if>
	<c:if test="${PAGE.nowPage != page}">
				<span class="w3-bar-item w3-button w3-hover-aqua pbtn">${page}</span>
	</c:if>
</c:forEach>
	<c:if test="${PAGE.endPage == PAGE.totalPage}">
				<span class="w3-bar-item w3-grey">next</span>
	</c:if>
	<c:if test="${PAGE.endPage != PAGE.totalPage}">
				<span class="w3-bar-item w3-button w3-hover-aqua pbtn">next</span>
	</c:if>
			</div>
		</div>
	</div>
</body>
</html>