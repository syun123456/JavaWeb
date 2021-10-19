<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>

<div style="text-align:center">
	<form action="${pageContext.request.contextPath}/login" method="post">
		帳號：<input type="text" name="username"><br>
		密碼：<input type="password" name="password"><br>
		愛好：
		<input type="checkbox" name="hobby" value="唱歌">唱歌
		<input type="checkbox" name="hobby" value="運動">運動
		<input type="checkbox" name="hobby" value="電影">電影
		<input type="checkbox" name="hobby" value="睡覺">睡覺
		
		<br>
		<input type="submit">
	</form>
</div>

</body>
</html>