<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<% 
java.util.ArrayList<String> people = new java.util.ArrayList<>();
people.add(0, "A0");
people.add(1, "B1");
people.add(2, "C2");
people.add(3, "D3");
people.add(4, "E4");
request.setAttribute("list", people);%>


<c:forEach var="people" items="${list}">
	<c:out value="${people}"></c:out>
</c:forEach>

<hr>

<c:forEach var="people" items="${list}" begin="1" end="3" step="2">
	<c:out value="${people}"></c:out>
</c:forEach>

</body>
</html>