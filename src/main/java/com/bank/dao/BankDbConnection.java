package com.bank.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BankDbConnection {

	//This makes a new connection to the database BankDB
	
	ClassLoader classLoader = getClass().getClassLoader();
	InputStream stream;
	Properties props = new Properties();
	
	public BankDbConnection() {
		stream = classLoader.getResourceAsStream("connection.properties");
		try(props.load(stream)) {
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getDbConnection() throws SQLException {
		final String URL = "";
		final String USERNAME = "";
		final String PASSWORD = "";
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
	
}
