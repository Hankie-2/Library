package com.example.applibrary.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection databaseLink;

	public static Connection getConnection() {

		if (databaseLink == null) {
			String databaseName = "library";
			String databaseUser = "root";
			String databasePassword = "9Jfu5vew_";
			String url = "jdbc:mysql://localhost/" + databaseName;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
				System.out.println("Connection Established!");
			} catch (Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}

		return databaseLink;
	}

	

}
