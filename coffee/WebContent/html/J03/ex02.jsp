<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/cafe/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cafe/css/user.css">
<script type="text/javascript" src="/cafe/js/w3color.js"></script>

<style>
	.dar {
		display: inline-block;
	}
</style>
</head>
<body>
	<!-- 구구단을 출력하세요 -->
	<div calss="w3-content mxw600 w3-center w3-margin-top">
	<%
	for(int i = 1 ; i < 10; i++ ){
		%>
		<div class="inblock w600 w3-border w3-card-4">
			<h1 clss="w3-col w3-border-bottom" style="margin: 0px;"><%= i %>단</h1>
			<div class="w3-col w3-padding">
		<% 
		for(int r = 1; r < 10; r++){
			%>			
			<h3 class="w3-blue"><%= i %> x <%=r %> = <%=i * r %></h3>
			
			<%
		}
		%>
		</div>
			</div>
	<%
	}
	%>
		</div>
	</div>
</body>
</html>