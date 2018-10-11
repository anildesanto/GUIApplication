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
	TextField name, address, accno;
	TextField amount, balance;
	Button deposit;
	boolean isDeposit;

	public BankingEventHandler(TextField accno,TextField name,TextField address)
	{
		this.name = name;
		this.address = address;
		this.accno = accno;
	}
	public BankingEventHandler(TextField accno)
	{
		this.accno = accno;
	}
	public BankingEventHandler(TextField accno, TextField name,TextField address,TextField balance,TextField amount)
	{
		this.name = name;
		this.amount = amount;
		this.balance = balance;
		this.accno = accno;
		this.address = address;
	}
	public BankingEventHandler(TextField accno,TextField amount,TextField balance, boolean isDeposit)
	{
		this.amount = amount;
		this.balance = balance;
		this.accno = accno;
		this.isDeposit = isDeposit;
	}
	public void actionPerformed(ActionEvent arg) 
	{
		Button used  = (Button)arg.getSource();
		String btnName = used.getLabel().toLowerCase();
		try 
		{
			AccountManager accManager = new AccountManager();
			if(btnName.equals("create"))
			{
				int accNumber = accManager.createAccount(name.getText(), address.getText());
				accno.setText(Integer.toString(accNumber));
			}
			else if(btnName.equals("search"))
			{
				int nr = Integer.parseInt(accno.getText().trim());
				if(accManager.lookFor(nr))
				{
					name.setText(accManager.getName());
					balance.setEditable(true);
					balance.setText("£"+accManager.getBalance());
					balance.setEditable(false);
					amount.setEditable(true);
					amount.setBackground(Color.WHITE);
					address.setText(accManager.getAddress());
				}
				else
				{
					amount.setEditable(true);
					amount.setBackground(Color.CYAN);
				}
			}
			else if(btnName.equals("deposit") || btnName.equals("withdrawal"))
			{
				int nr = Integer.parseInt(accno.getText().trim());
				int a = Integer.parseInt(amount.getText().trim());
				balance.setText(accManager.tansaction(nr,a, isDeposit));
				amount.setText("");
				used.setEnabled(false);
			}
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(used.getParent(), e.getMessage());
			e.printStackTrace();
		}
		System.out.println("\n"+used.getLabel()+" button clicked");
	}
}
