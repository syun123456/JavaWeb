<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<title>FileUpload</title>
</head>
<body>
<h2>FileUpload</h2>

<%-- 通過表單上傳文件 
	 get:上傳的檔案有大小限制
	 post:上傳的檔案沒有大小限制
--%>
<form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
	上傳用戶:<input type="text" name="username"><br/>
	<p><input type="file" name="file1"></p>
	<p><input type="file" name="file2"></p>
	<p><input type="submit">  <iuput type="reset"></p>
</form>

</body>
</html>
