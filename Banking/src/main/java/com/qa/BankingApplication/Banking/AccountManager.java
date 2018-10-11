package com.qa.BankingApplication.Banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AccountManager 
{
	private Connection conn;
	private Statement stmt;
	public AccountManager() throws SQLException 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			System.out.println(e.getMessage());
		}
		conn = DriverManager.getConnection("jdbc:mysql://localhost/bank","root","password");
		stmt = conn.createStatement();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getAddress() 
	{
		return address;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}
	public int getBalance() 
	{
		return balance;
	}
	public void setBalance(int balance) 
	{
		this.balance = balance;
	}

	private int accno = 0;
	private String name = "";
	private String address = "";
	private int balance = 0;
	public int createAccount(String name, String address) throws SQLException 
	{
		ResultSet rs = stmt.executeQuery("Select * from accounts");
		int number = 0;
		while (rs.next())
		{
			number++;
	    }
		number++;
		stmt.executeUpdate("insert into accounts values("+number+",'"+name+"','"+address+"')");
		
		return number;
	}
	public int retrieveBalance(int accno) throws SQLException
	{
		ResultSet checkDeposits = stmt.executeQuery("Select * from deposits where accno = "+accno+"");
		int depositsAmount = 0;
		int withdrawalsAmount = 0;
		while (checkDeposits.next())
		{
			depositsAmount += checkDeposits.getInt(2);
		}
		checkDeposits.close();
		
		ResultSet checkWithdrawls = stmt.executeQuery("Select * from withdrawals where accno = "+accno+"");
		while (checkWithdrawls.next())
		{
			withdrawalsAmount += checkWithdrawls.getInt(2);
		}
		checkWithdrawls.close();
		return (depositsAmount-withdrawalsAmount);
	}
	public void close() throws SQLException
	{
		stmt.close();
		conn.close();
	}
	
	public boolean lookFor(int nr) throws SQLException
	{
		ResultSet rs = stmt.executeQuery("Select * from accounts");
		while (rs.next())
		{
			System.out.println(rs.getInt(1));
			if(rs.getInt(1) == nr)
			{
				accno = rs.getInt(1);
				name = rs.getString(2);
				address = rs.getString(3);
				AccountManager accManager = new AccountManager();
				balance = accManager.retrieveBalance(nr);
				System.out.println(accno+name+address+balance);
				break;
			}
	    }
		rs.close();
		if(accno > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public String tansaction(int accno, int amount, boolean deposit) throws SQLException
	{
		if(deposit)
			stmt.executeUpdate("insert into deposits values("+accno+",'"+amount+"','2008-11-11')");
		else
		{
			if(amount <= retrieveBalance(accno))
				stmt.executeUpdate("insert into withdrawals values("+accno+",'"+amount+"','2008-11-11')");
			else
			{
				return "Cannot withrawal more than Balance";
			}
		}
		return "£"+retrieveBalance(accno);
	}
}
