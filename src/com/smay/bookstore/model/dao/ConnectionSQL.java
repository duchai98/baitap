package com.smay.bookstore.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSQL {
	private String hostName;
	private String sqlInstanceName;
    private String userName;
    private String password;
    private Connection connection;
    private String database;
    private String port;
    
     
    public ConnectionSQL(String hostName, String sqlInstanceName, String port, String database, String userName, String password) {
		
    	this.hostName = hostName;
		this.sqlInstanceName = sqlInstanceName;
		this.port = port;
		this.database = database;
		this.userName = userName;
		this.password = password;
	}

	protected Connection connect() throws SQLException {
		
        if (connection == null || connection.isClosed()) {
        
        	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        	
        	String connectionURL = "jdbc:sqlserver://" + hostName + ":" + port + ";instance=" + sqlInstanceName + ";databaseName=" + database;
        
            connection = DriverManager.getConnection(connectionURL, userName, password);
        }
        
        return connection;
    }
	
	protected void disconnect() throws SQLException {
		
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
	
	public Connection connection() {
		
		return connection;
	}
}
