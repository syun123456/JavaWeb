<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>目前在線人數：<span><%=this.getServletConfig().getServletContext().getAttribute("OnlineCount") %></span></h2>
</body>
</html>
