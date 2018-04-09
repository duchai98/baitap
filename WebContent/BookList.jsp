<%@page import="com.smay.bookstore.model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<%
			if (!((String)session.getAttribute("login")=="true")) {
				
				response.sendRedirect("/Bookstore/LoginForm.jsp");
			} else {
				
				User user = (User)session.getAttribute("user");
				
				%><span>hello: </span><%= user.getName() %><% 
			}
		%>
        <h1>Books Management</h1>
        <h2>
            <a href="/Bookstore/new">Add New Book</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/Bookstore/list">List All Books</a>
             
        </h2>
    </center>
    <div align="center">
    	<h2>List Of Book</h2>
        <table border="1" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td>
                    	<a href="/Bookstore/edit?id=<c:out value='${book.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/Bookstore/delete?id=<c:out value='${book.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/Bookstore/logout">Logout</a>
    </div>
</body>
</html>