package com.abc.learning.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {

		System.out.println("Inside DB Connection.java");

		// MySql Config
		String mySqlDriver = "com.mysql.jdbc.Driver";
		String mySqlJdbcUrl = "jdbc:mysql://localhost:3306/learning?useSSL=false";
		String mySqlUsername = "root";
		String mySqlPassword = "root";
		// MySql Config

		// MsSql Config
		String msSqlDbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String msSqlJdbcUrl = "jdbc:sqlserver://43.252.195.78:1433;DatabaseName=test_db;encrypt=true;trustServerCertificate=true";
		String msSqlUserName = "sa";
		String msSqlPassword = "Cyber@987";
		// MsSql Config

		Connection con = null;

		try {
			// MySql Config
			System.out.println("Inside DB Connection.java");
			Class.forName(mySqlDriver);
			con = DriverManager.getConnection(mySqlJdbcUrl, mySqlUsername, mySqlPassword);
			System.out.println("Connection established successfully");
			// MySql Config

			// MsSql Config
//			System.out.println("Inside DB Connection.java");
//			Class.forName(msSqlDbDriver);
//			con =  DriverManager.getConnection(msSqlJdbcUrl, msSqlUserName, msSqlPassword);
//			System.out.println("Connection established successfully");
			// MsSql Config

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection not established");
		}

		return con;

	}

}
