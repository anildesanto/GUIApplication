package com.qa.BankingApplication.Banking;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BankingEventHandler implements ActionListener
{
	Button used;
	Statement stmt ;
	TextField name, address, accno;
	TextField amount, balance;
	Button deposit;

	public BankingEventHandler(Statement statement, TextField accno,TextField name,TextField address)
	{
		stmt = statement;
		this.name = name;
		this.address = address;
		this.accno = accno;
	}
	public BankingEventHandler(TextField accno,TextField amount, TextField balance,Statement statement)
	{
		stmt = statement;
		this.accno = accno;
		this.amount = amount;
		this.balance = balance;
	}
	public BankingEventHandler(TextField accno,TextField name,TextField amount,Statement statement, Button deposit)
	{
		stmt = statement;
		this.name = name;
		this.amount = amount;
		this.accno = accno;
		this.deposit = deposit;
	}
	public BankingEventHandler(TextField accno,TextField name,TextField amount,Statement statement, Button deposit,TextField balance )
	{
		stmt = statement;
		this.name = name;
		this.amount = amount;
		this.balance = balance;
		this.accno = accno;
		this.deposit = deposit;
	}
	public BankingEventHandler()
	{
	}
	public void createAccount() throws SQLException
	{
		ResultSet rs = stmt.executeQuery("Select * from accounts");
		int number = 0;
		while (rs.next())
		{
			number++;
	    }
		number++;
		stmt.executeUpdate("insert into accounts values("+number+",'"+name.getText()+"','"+address.getText()+"')");
		accno.setEditable(true);
		accno.setText(Integer.toString(number));
		accno.setEditable(false);
		//stmt.executeUpdate("insert into school values(345,'LOL',89)");
		stmt.close();
	}
	public String balance() throws SQLException
	{
		//stmt.executeUpdate("insert into deposits values("+number+",'"+amount.getText()+"','sys.date')");
		ResultSet checkDeposits = stmt.executeQuery("Select * from deposits where accno = "+accno.getText()+"");
		int depositsAmount = 0;
		int withdrawalsAmount = 0;
		while (checkDeposits.next())
		{
			depositsAmount += checkDeposits.getInt(2);
		}
		checkDeposits.close();
		
		ResultSet checkWithdrawls = stmt.executeQuery("Select * from withdrawals where accno = "+accno.getText()+"");
		while (checkWithdrawls.next())
		{
			withdrawalsAmount += checkDeposits.getInt(2);
		}
		checkWithdrawls.close();
		return "£"+(depositsAmount-withdrawalsAmount);
	}
	public void lookFor() throws SQLException
	{
		ResultSet rs = stmt.executeQuery("Select * from accounts");
		int number = 0;
		int nr = Integer.parseInt(accno.getText());
		String n = "";
		String a = "";
		while (rs.next())
		{
			System.out.println(rs.getInt(1));
			if(rs.getInt(1) == nr)
			{
				number = nr;	
				n = rs.getString(2);
				a = rs.getString(3);
			}
	    }
		rs.close();
		if(number > 0)
		{
			amount.setEditable(true);
			amount.setBackground(Color.WHITE);
			accno.setText(Integer.toString(number));
			deposit.setEnabled(true);
			name.setText(n);
//			address.setText(a);
			System.out.println("Balance: "+balance()+ " "+balance);
			balance.setEditable(true);
			balance.setText(balance());
			balance.setEditable(false);
			//stmt.executeUpdate("insert into school values(345,'LOL',89)");
		}
		else
		{
			amount.setEditable(false);
			amount.setBackground(Color.CYAN);
			deposit.setEnabled(false);
			
			String errorMessage = "Account not found!";
			JOptionPane.showMessageDialog(used.getParent(), errorMessage);
		}
		//stmt.close();
	}
	
	public void deposit() throws SQLException
	{
		stmt.executeUpdate("insert into deposits values("+accno.getText()+",'"+amount.getText()+"','2008-11-11')");
		balance.setEditable(true);
		balance.setText(balance());
		balance.setEditable(false);
	}
	public void actionPerformed(ActionEvent arg) 
	{
		Button used  = (Button)arg.getSource();
		String btnName = used.getLabel().toLowerCase();
		try 
		{
			if(btnName.equals("create"))
			{
				createAccount();
			}
			else if(btnName.equals("search"))
			{
				lookFor();
			}
			else if(btnName.equals("deposit"))
			{
				deposit();
			}
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(used.getParent(), "Invalid Account Number");
			System.out.println(e.getMessage());
		}
		System.out.println("\n"+used.getLabel()+" button clicked");
	}
}
