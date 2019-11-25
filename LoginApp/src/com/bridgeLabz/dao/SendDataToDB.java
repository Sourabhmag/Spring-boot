package com.bridgeLabz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bridgeLabz.model.Registration;
import com.bridgeLabz.repository.ConnectionToDB;

/**
 * @author Sourabh Magdum
 * @Purpose - Sends data to database
 * Date - 11/11/2019
 */
public class SendDataToDB {

	
	/**
	 * @Purpose - used to insert data into database
	 * @param register
	 * @return - returns true after successful insertion of data into database
	 * @throws Exception
	 */
	public static boolean inseretIntoDB(Registration register) throws Exception {
		String query = "INSERT INTO userData VALUES (?,?,?,?)";

		Connection connection = ConnectionToDB.DBConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(query);

		preparedStatement.setString(1, register.getName());
		preparedStatement.setString(2, register.getPhoneNo());
		preparedStatement.setString(3, register.getEmail());
		preparedStatement.setString(4, register.getPassword());

		try {
			int value = preparedStatement.executeUpdate();
			if (value > 0) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("username already exist,please enter another username");
		}
		return false;
	}

	/**
	 * @Purpose - Used to reset password of user
	 * @param email
	 * @param password
	 * @return - Returns true after successful password reset
	 * @throws SQLException
	 */
	public static boolean resetPassword(String email, String password) throws SQLException {
		
		String query1 = "UPDATE `Login`.`userData` SET `password` = ? WHERE (`email` = ?)";
		Connection connection = ConnectionToDB.DBConnection();
		if(connection==null)
			System.out.println("connection is null");
		PreparedStatement preparedStatement = connection.prepareStatement(query1);
		
		preparedStatement.setString(1,password);
		preparedStatement.setString(2,email);
		
		int value = preparedStatement.executeUpdate();
		if(value>0)
			return true;
		else
			return false;
	}
}
