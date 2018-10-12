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
		AccountManager accManager = AccountManager.getGetAccountManager();

		public BankingEventHandler(TextField accno, TextField name, TextField address)
		{
				this.name = name;
				this.address = address;
				this.accno = accno;
		}

		public BankingEventHandler(TextField accno, TextField name, TextField address, TextField balance, TextField amount)
		{
				this.name = name;
				this.amount = amount;
				this.balance = balance;
				this.accno = accno;
				this.address = address;
		}

		public void actionPerformed(ActionEvent arg)
		{
				
				Button used = (Button) arg.getSource();
				String btnName = used.getLabel().toLowerCase();
				String message = "I don't know";
				try
				{
						if (btnName.equals("create"))
						{
								int accNumber = accManager.createAccount(name.getText(), address.getText());
								accno.setText(Integer.toString(accNumber));
								used.setEnabled(false);
						}
						else if (btnName.equals("search"))
						{
								int nr = Integer.parseInt(accno.getText().trim());
								boolean found = accManager.lookFor(nr);
								name.setText(accManager.getName());
								balance.setText(found ? accManager.getBalance() : "");
								amount.setBackground(Color.WHITE);
								address.setText(accManager.getAddress());
								if (!found)
								{
										JOptionPane.showMessageDialog(used.getParent(), "Invalid Account Number");
								}
						}
						else if (btnName.equals("deposit") || btnName.equals("withdrawal"))
						{
								transaction((btnName.equals("deposit") ? true : false), used);
						}
				}
				catch (Exception e)
				{
						JOptionPane.showMessageDialog(used.getParent(), e.getMessage());
						e.printStackTrace();
				}
				System.out.println("\n" + used.getLabel() + " button clicked");
		}

		public void transaction(boolean isDeposit, Button used) throws SQLException
		{
				int nr = Integer.parseInt(accno.getText().trim());
				float a = Float.parseFloat(amount.getText().trim());

				String message = accManager.tansaction(nr, a, isDeposit, used);
				if (message.equals("invalid"))
						JOptionPane.showMessageDialog(used.getParent(), "Cannot withdrawal more than " + "£" + balance.getText());
				else
						balance.setText(message);
				amount.setText("");
		}
}
