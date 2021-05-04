package com.bank.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getDatabaseConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://appian-210419.cidxzyed6tz4.us-east-2.rds.amazonaws.com:5432/bank";
		
		//TODO: Consider using a better username/password ad using system environment variables to hide this
		String username = "postgres";
		String password = "password";
		
		return DriverManager.getConnection(url, username, password);
	}
}
