package com.smay.bookstore.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smay.bookstore.model.bean.User;
import com.smay.bookstore.model.dao.UserDAO;


public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
     
	public void init() {
		String hostName = getServletContext().getInitParameter("hostName");
		String sqlInstanceName = getServletContext().getInitParameter("sqlInstanceName");
		String database = getServletContext().getInitParameter("database");
		String port = getServletContext().getInitParameter("port");
		String userName = getServletContext().getInitParameter("userName");
		String password = getServletContext().getInitParameter("password");
		
		userDAO = new UserDAO(hostName, sqlInstanceName, port, database, userName, password);	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			try {
				
				checkLogin(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession(true);
		User user = new User(username, password);
		
		if (userDAO.checkLogin(user)) {
			
			session.setAttribute("user", user);
			session.setAttribute("login", "true");
			request.getRequestDispatcher("/").forward(request, response);
		}
		else {
			
			session.setAttribute("login", "false");
			response.sendRedirect("/Bookstore/LoginForm.jsp");
		}
	}
}
