package com.qa.BankingApplication.Banking;

import java.awt.Button;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class AccountManager
{
		private Connection conn;
		private Statement stmt;
		DecimalFormat df = new DecimalFormat("###,###,###.##");
		private static AccountManager accManager;
		static
		{
				try
				{
						accManager = new AccountManager();
				}
				catch (SQLException | ClassNotFoundException e)
				{
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
		}

		public static AccountManager getGetAccountManager()
		{
				return accManager;
		}

		private AccountManager() throws SQLException, ClassNotFoundException
		{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/bank", "root", "password");
				stmt = conn.createStatement();
		}

		public String getName()
		{
				return name;
		}

		public String getAddress()
		{
				return address;
		}

		public String getBalance() throws SQLException
		{
				return "£" + df.format(retrieveBalance(accno));
		}

		private int accno = 0;
		private String name = "";
		private String address = "";
		private float balance = 0;

		public int createAccount(String name, String address) throws SQLException
		{

				int number = 1;
				ResultSet closedAccountsCheck = stmt.executeQuery("Select * from closedAccounts");
				if(closedAccountsCheck.next())
				{
						number = closedAccountsCheck.getInt(1);
						stmt.executeUpdate("delete from closedaccounts where accno =" + number);
				}
				else
				{
    				// select max from accno
    				ResultSet rs = stmt.executeQuery("Select max(accno) from accounts");
    				if (rs.next())
    						number += rs.getInt(1);
    				rs.close();
				}
				stmt.executeUpdate("insert into accounts values(" + number + ",'" + name + "','" + address + "')");
				return number;
		}

		public boolean deleteAccount(int nr) throws SQLException
		{
				// select max from accno
				ResultSet rs = stmt.executeQuery("Select * from accounts where accno = " + nr);
				if (rs.next())
				{
						stmt.executeUpdate("delete from withdrawals where accno =" + nr);
						stmt.executeUpdate("delete from deposits where accno =" + nr);
						stmt.executeUpdate("delete from accounts where accno =" + nr);
						stmt.executeUpdate("insert into closedaccounts values(" + nr+")");
						rs.close();
						return true;
				}
				else
				{
						rs.close();
						return false;
				}
		}

		public float retrieveBalance(int accno) throws SQLException
		{
				ResultSet checkDeposits = stmt.executeQuery("Select sum(amount) from deposits where accno = " + accno + "");
				float money = 0.0f;
				// select sum amounts
				if (checkDeposits.next())
						money = checkDeposits.getFloat(1);
				checkDeposits.close();
				// select sum amount
				ResultSet checkWithdrawls = stmt
								.executeQuery("Select sum(amount) from withdrawals where accno = " + accno + "");
				if (checkWithdrawls.next())
						money -= checkWithdrawls.getFloat(1);
				checkWithdrawls.close();
				return (money);
		}

		public void close() throws SQLException
		{
				stmt.close();
				conn.close();
		}

		public boolean lookFor(int nr) throws SQLException
		{
				ResultSet rs = stmt.executeQuery("Select * from accounts where accno =" + nr);
				if (rs.next())
				{
						accno = rs.getInt(1);
						name = rs.getString(2);
						address = rs.getString(3);
						balance = retrieveBalance(nr);
						rs.close();
						return true;
				}
				else
				{
						accno = 0;
						name = "";
						address = "";
						balance = 0;
						rs.close();
						return false;
				}
		}
		public String tansaction(int accno, float amount, boolean deposit, Button used) throws SQLException
		{
				if (deposit)
						stmt.executeUpdate("insert into deposits values(" + accno + ",'" + amount + "',CURDATE())");
				else
				{
						if (amount <= retrieveBalance(accno))
								stmt.executeUpdate("insert into withdrawals values(" + accno + ",'" + amount + "',CURDATE())");
						else
								return "invalid";
				}
				return "£" + df.format(retrieveBalance(accno));
		}
}
