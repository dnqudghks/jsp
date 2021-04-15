<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
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
	
</script>
</head>
<body>
	<%
		ArrayList<String> list;
	
		public void setList() {
			list.add("w3-red");
			list.add("w3-pink");
			list.add("w3-purple");
			list.add("w3-deep-purple");
			list.add("w3-blue");
			list.add("w3-cyan");
			list.add("w3-aqua");
			list.add("w3-green");
			list.add("w3-light-green");
			list.add("w3-lime");
			list.add("w3-yellow");
			list.add("w3-amber");
			list.add("w3-orange");
			list.add("w3-deep-orange");
			list.add("w3-black");
			list.add("w3-dark-grey");
			list.add("w3-grey");
			list.add("w3-light-grey");
			list.add("w3-blue-grey");
			list.add("w3-brown");
			list.add("w3-pale-red");
			list.add("w3-sand");
			list.add("w3-pale-yellow");
			list.add("w3-khaki");
			list.add("w3-pale-blue");
			list.add("w3-light-blue");
	%>

	<!-- 구구단 출력 -->
	<div class="w3-content mxw600 w3-center">
		<c:forEach var="i" begin="1" end="9">
			<h1 clas="w3-col w3-deep-orangew3-border-bottom" style="margin: 0px;">단</h1>
			<div class="w3-col w3-padding">
				<c:forEach var="r" begin="r" end="9">
				</c:forEach>
		</c:forEach>
	</div>
	</div>
	<!-- 구구단 5단 -->
	<div class="w3-content mxw600 w3-center">
		<div class="inbock w250 w3-border w3-padding">
			<h1 class="w3-col w3-deep-orange w3-border-bottom"
				style="margin: 0px;">5단</h1>
			<div class="w3-col w3-padding">
				<c:forEach var="i" begin="1" end="9">
					<h3>5 x ${i} = ${i * 5}</h3>
				</c:forEach>
			</div>
		</div>
</body>
</html>