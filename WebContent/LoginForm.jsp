<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Login to Bookstore</h1>
		<form action="/Bookstore/login" method="post">
			<%
				if (session.getAttribute("login") == "false") {
					%><span style="color: red">Wrong username or password</span><br><%
				}
			%>
			<label for="username">Username: </label>
			<input type="text" name="username"><br>
			<label for="password">Password: </label>
			<input type="password" name="password"><br>
			<input type="submit" value="Login">
		</form>
	</center>
</body>
</html>