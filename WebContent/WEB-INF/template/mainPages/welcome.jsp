<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="height: 1000px; background-color: skyblue;">
		<div>this is WEB-INF welcome page</div>
		<h1>Welcome [ ${sessionScope.user.username} ] to WarmHouse Background management system</h1>
	</div>
	
</body>
</html>