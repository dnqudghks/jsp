<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<%-- 
	<div class="w3-content mxw600 w3-center">
		<h1 class="w3-padding w3-blue">여기는 요청 페이지</h1>
		<div class="w3-col w3-padding w3-card-4">
			<h3>아이디 : <%=request.getParameter("id") %></h3>
			<h3>비밀번호 : <%=request.getParameter("pw") %></h3>
		</div>
	</div>
--%>
	<div class="w3-content mxw600 w3-center">
		<h1 class="w3-padding w3-blue">여기는 요청 페이지</h1>
		<div class="w3-col w3-padding w3-card-4">
			<h3>아이디 : ${param.id}</h3>
			<h3>비밀번호 : ${param.pw}</h3>
		</div>
	</div>
	<script type="text/javascript">
		alert('nick : ${pram.nick}');
	</script>
</body>
</html>