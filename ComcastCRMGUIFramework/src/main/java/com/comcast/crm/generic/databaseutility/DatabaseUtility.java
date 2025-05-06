package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	Connection conn;
	
	
	public void getDbconnection(String url,String username,String password) throws SQLException
	{
		
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public void getDbConnection()
	{
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/projects","root","root");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void closeDbconnection() throws SQLException{
		try {
			conn.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public ResultSet executeSelectQuery(String query) throws SQLException
	{
		ResultSet result=null;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeQuery(query);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public int executeNonSelectQuery(String query)
	{
		int result=0;
		try {
			Statement stat = conn.createStatement();
			result = stat.executeUpdate(query);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}

}
