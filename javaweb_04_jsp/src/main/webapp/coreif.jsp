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

<h4>JSTL if測試</h4>

<hr>
<%-- EL表達式參數獲取 ${param.參數名} --%>
<form action="coreif.jsp" method="get">
	<input type="text" name="username" value="${param.username}">
	<input type="submit" value="登入">
</form>

<%-- 判斷username是否為admin --%>
<c:if test="${param.username=='admin'}" var="isAdmin">
	<c:out value="Welcome Admin!" />
</c:if>

<c:out value="${isAdmin}"></c:out>

</body>
</html>