package com.qa.BankingApplication.Banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC 
{
	public static void main (String args[]) 
	{
		try
		{
			BankWindows regWindow = new BankWindows();
			regWindow.mainWindow();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
