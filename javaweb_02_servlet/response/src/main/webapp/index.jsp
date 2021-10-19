<html>
<body>
<h2>Hello World!</h2>

<%--${pageContext.request.contextPath}表示網站目前路徑 --%>
<form action="${pageContext.request.contextPath}/login" method="get">
	帳號：<input type="text" name="username"><br>
	密碼：<input type="password" name="password"><br>
	<input type="submit">
</form>

</body>
</html>
