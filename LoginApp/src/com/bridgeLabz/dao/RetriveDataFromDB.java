package com.bridgeLabz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bridgeLabz.model.Login;
import com.bridgeLabz.model.Registration;
import com.bridgeLabz.repository.ConnectionToDB;

/**
 * @author Sourabh Magdum
 * @Purpose - Read data from database and return data in the form of list
 * Date - 11/11/2019
 */
public class RetriveDataFromDB {
	public static List<Registration>  getData()
	{
		String query = "select * from userData";
		Connection connection = ConnectionToDB.DBConnection();
		try 
		{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultset = preparedStatement.executeQuery();
			List<Registration> list = new ArrayList<Registration>();
				
				while(resultset.next())
				{
					Registration retrivedData = new Registration();
					retrivedData.setName(resultset.getString("name"));
					retrivedData.setPhoneNo(resultset.getString("phoneNo"));
					retrivedData.setEmail(resultset.getString("email"));
					retrivedData.setPassword(resultset.getString("password"));
					list.add(retrivedData);
				}
				return list;
				
		}
		catch (SQLException e) 
		{
				e.printStackTrace();
		}
		
		return null;
	}
}
