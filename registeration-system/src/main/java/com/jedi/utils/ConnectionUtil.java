package com.jedi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	String url,username,password;
	
	public ConnectionUtil(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public Connection getConnection() {
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
