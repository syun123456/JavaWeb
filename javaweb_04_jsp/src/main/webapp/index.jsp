<html>
<body>
<h2>Hello World!</h2>

<%= new java.util.Date() %>
<hr>

<% 
	int sum = 0;
	for(int i = 1; i <= 100; i++){
		sum = sum + i;
	}
	out.println("<h2> sum = " + sum + "</h2>");
%>
<hr>

<% 
	int x = 10;
	out.println(x);
%>
<p>JSP檔案</p>
<%
	int y = 20;
	out.println(x + y);
%>
<hr>

<% 
	for(int i = 0; i < 5; i++){
		
%>
	<h2>Hello World <%=i %></h2>
<%
	}
%>
<hr>

<%--%!表示代碼寫在全域裡，而非JSPService方法裡--%>
<%! 
	static{
	System.out.println("Loading Servlet");
	}
	
	private int globalvar = 10;
	
	public void test(){
		System.out.println("進入test方法");
	}
%>
</body>
</html>
