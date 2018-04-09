package com.smay.bookstore.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.smay.bookstore.model.bean.User;

public class UserDAO {

	private ConnectionSQL connection;    
    
    public UserDAO(String hostName, String sqlInstanceName, String port, String database, String userName, String password) {
    	
		connection = new ConnectionSQL(hostName, sqlInstanceName, port, database, userName, password);
	}
    
    public boolean checkLogin(User user) throws SQLException {
    	
    	String sql = "SELECT * FROM t_login";
    	
    	connection.connect();
        
        Statement statement = connection.connection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        
        while (resultSet.next()) {
        	
        	if (resultSet.getString("login_username").equals(user.getUserName())&&resultSet.getString("login_password").equals(user.getPassword())) {
        		
        		user.setName((String)resultSet.getString("login_name"));
        		
        		return true;
        	}
        }
        return false;
    }
}
