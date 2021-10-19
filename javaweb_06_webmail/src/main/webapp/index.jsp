<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>註冊</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/RegisterServlet.do" method="post">
		帳號:<input type="text" name="username"><br/>
		密碼:<input type="password" name="password"><br/>
		信箱:<input type="text" name="email"><br/>
		<input type="submit" value="註冊">
	</form>


</body>
</html>
