<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>

<%--
	Object user_session = request.getSession().getAttribute("USER_SESSION");
	if(user_session == null){
		response.sendRedirect("/javaweb_06_filter/login.jsp");
	}
--%>

<h1>首頁</h1>

<p><a href="/javaweb_06_filter/servlet/logout">登出</a></p>

</body>
</html>