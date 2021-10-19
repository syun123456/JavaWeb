<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 匯入JSTL核心標籤庫 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 定義變數score值為81 --%>
<c:set var="score" value="81"/>

<c:choose>
	<c:when test="${score>=90}">成績:A</c:when>
	<c:when test="${score>=80}">成績:B</c:when>
	<c:when test="${score>=70}">成績:C</c:when>
	<c:when test="${score>=60}">成績:D</c:when>
	<c:when test="${score<60}">成績:E</c:when>
</c:choose>

</body>
</html>