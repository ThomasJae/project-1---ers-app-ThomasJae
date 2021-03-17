package com.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton utility for creating and retrieving database connection
 */
public class ConnectionUtil {
	private static ConnectionUtil cu = null;
	private static Properties prop;
	
	/**
	 * This method should read in the "database.properties" file and load
	 * the values into the Properties variable
	 */
	public ConnectionUtil() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("database.properties");
		prop = new Properties();
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if(cu == null)
			cu = new ConnectionUtil();
		return cu;
	}
	
	/**
	 * This method should create and return a Connection object
	 * @return a Connection to the database
	 */
	public Connection getConnection() {
		// Hint: use the Properties variable to setup your Connection object
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection((String)prop.getProperty("url"),
					(String)prop.getProperty("usr"), (String)prop.getProperty("pswd"));
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
