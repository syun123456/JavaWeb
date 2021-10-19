<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- JspServlet 內之九大物件 --%>
<%
	pageContext.setAttribute("name1", "NO.1"); // 保存的資料只在同一個頁面中有效
	request.setAttribute("name2", "NO.2");     // 保存的資料只在一次請求中有效，請求轉發亦有效
	session.setAttribute("name3", "NO.3");     // 保存的資料只在一次會話中有效(打開瀏覽器至關閉瀏覽器)
	application.setAttribute("name4", "NO.4"); // 保存的資料在伺服器中有效(打開伺服器至關閉伺服器)
%>

<%
	String name1 = (String) pageContext.findAttribute("name1");
	String name2 = (String) pageContext.findAttribute("name2");
	String name3 = (String) pageContext.findAttribute("name3");
	String name4 = (String) pageContext.findAttribute("name4");
	String name5 = (String) pageContext.findAttribute("name5");
%>

<h1>取得值</h1>
<h2>${name1}</h2>
<h2>${name2}</h2>
<h2>${name3}</h2>
<h2>${name4}</h2>
<h2>${name5}</h2>
<h2><%=name5 %></h2>

</body>
</html>