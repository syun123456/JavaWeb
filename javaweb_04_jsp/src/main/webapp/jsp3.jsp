<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 將三個網頁併成一個 --%>
	<%@include file="common/header.jsp" %>
	<h1>內容</h1>
	<%@include file="common/footer.jsp" %>
	<hr>
	
	<%-- 為三個不同的網頁，一起呈現 --%>
	<jsp:include page="/common/header.jsp" />
	<h1>內容</h1>
	<jsp:include page="/common/footer.jsp" />
</body>
</html>