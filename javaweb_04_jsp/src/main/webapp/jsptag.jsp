<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- jsp:include --%>
<%-- http://localhost:8080/jsptag.jsp?name=syun$age=18 --%>
<jsp:forward page="/jsptag2.jsp">
	<jsp:param name="name" value="syun"/>
	<jsp:param name="age" value="18"/>
</jsp:forward>

	
	


</body>
</html>