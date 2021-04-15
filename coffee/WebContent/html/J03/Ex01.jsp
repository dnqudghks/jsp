<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/cafe/css/w3.css">
<link rel="stylesheet" type="text/css" href="/cafe/css/user.css">
</head>
<body>
	
	<%
		int no = 5;
		
	%>
		<div class="w3-content mxw600 w3-center">
			<div class="inbock w250 w3-border w3-padding">
				<h1 class = "w3-col w3-deep-orange w3-border-bottom">5단</h1>
	<%
		for(int i = 1 ; i < 10 ; i++){
			
	%>
		<h1 class="w3-blue">5 x <%=i %> = <%=no*i %></h1>
	<%		
		}		
	%>
			</div>
		</div>
	
	
	
</body>
</html>