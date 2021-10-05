package com.bank.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BankDbConnection {

	//This makes a new connection to the database BankDB using the URL, user, pass from connection.properties
	
	ClassLoader classLoader = getClass().getClassLoader();
	InputStream stream;
	Properties props = new Properties();
	
	public BankDbConnection() {
		stream = classLoader.getResourceAsStream("connection.properties");
		try {
			props.load(stream);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getDbConnection() throws SQLException {
		final String URL = props.getProperty("url");
		final String USERNAME = props.getProperty("username");
		final String PASSWORD = props.getProperty("password");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
}
