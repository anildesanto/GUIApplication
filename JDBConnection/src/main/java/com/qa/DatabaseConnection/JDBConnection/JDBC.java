package com.qa.DatabaseConnection.JDBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC 
{
	public static void main (String args[]) 
	{
		Connection conn;
		Statement stmt;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/practice","root","password");
			stmt = conn.createStatement();
			
			RegisterWindow regWindow = new RegisterWindow(stmt);
			regWindow.registerWindow();
			
			//conn.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		
	}
}
