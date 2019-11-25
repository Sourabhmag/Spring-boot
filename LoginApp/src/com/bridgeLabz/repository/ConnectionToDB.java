package com.bridgeLabz.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sourabh Magdum
 * @Purpose - Used to connect database
 *  Date - 11/11/2019
 */
public class ConnectionToDB {
	public static Connection DBConnection() 
	{
		String url = "jdbc:mysql://localhost:3306/Login";
		String username = "root";
		String password = "password";
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			if(connection!=null)
			{
				return connection;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return connection;
	}
}
