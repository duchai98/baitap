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
	    <c:if test="${book != null}">
	   		<c:set var="action" value="/Bookstore/update"/>
	   		<c:set var="title" value="Edit Book"/>
	   		<input type="hidden" name="id" value="<c:out value="${book.id}"/>">
	    </c:if>
	    <c:if test="${book == null}">
	   		<c:set var="action" value="/Bookstore/insert"/>
	   		<c:set var="title" value="Add new Book"/>
	    </c:if>
	    <form action="<c:out value='${action}'/>" method="post">
	    	
	    	<h2><c:out value="${title}"/></h2>
	    	
	    	<c:if test="${book != null}">
	   			<input type="hidden" name="id" value="<c:out value="${book.id}"/>">
	    	</c:if>
			
			<table border="1" cellpadding="5">	         
			    <tr>
			        <th>Title: </th>
			        <td>
			            <input type="text" name="title" size="45" value="<c:out value='${book.title}'/>"/>
			        </td>
			    </tr>
			    <tr>
			        <th>Author: </th>
			        <td>
			            <input type="text" name="author" size="45" value="<c:out value='${book.author}'/>"/>
			        </td>
			    </tr>
			    <tr>
			        <th>Price: </th>
			        <td>
			            <input type="text" name="price" size="5" value="<c:out value='${book.price}'/>"/>
			        </td>
			    </tr>
			    <tr>
			        <td colspan="2" align="center">
			            <input type="submit" value="Save" />
			        </td>
			    </tr>
			</table>    
	    </form>
	    <a href="/Bookstore/Logout">Logout</a>
    </div>
</body>
</html>