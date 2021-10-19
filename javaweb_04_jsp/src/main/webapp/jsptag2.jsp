<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>jsptag2</h1>

<%-- 取出參數 --%>
name: <%=request.getParameter("name") %>
age:  <%=request.getParameter("age") %>


</body>
</html>