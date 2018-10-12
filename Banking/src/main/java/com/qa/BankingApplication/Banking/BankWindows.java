package com.qa.BankingApplication.Banking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import Utilities.WindowHelper;
import javax.swing.event.DocumentListener.*;
public class BankWindows 
{
	Panel registeredStudents = new Panel();
	Frame createUser = new Frame("Create Account");
	Frame depositsFrame =  new Frame("Deposit");
	Frame withDrawalFrame =  new Frame();
	Frame transaction =  new Frame("closed");
	public void mainWindow() throws HeadlessException, SQLException
	{
		Frame f =  new Frame("Register");
		WindowHelper.addCloseOption(f,true);
		Button createAccount = new Button("Create Account");
		Button depositAccount = new Button("Deposit Account");
		Button withdrawalAccount = new Button("Withdrawal Account");
		createAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg) 
			{
				if(createUser != null && !createUser.isActive())
				createAccountWindow();
			}
		}
		);
		depositAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg) 
			{
				if(transaction.getTitle().equals("closed"))
				{
					transactionAccountWindow("Deposit", true);
					transaction.setTitle("Deposit");
				}
			}
		}
		);
		withdrawalAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg) 
			{
				if(transaction.getTitle().equals("closed"))
				{
					transactionAccountWindow("Withdrawal", false);
					transaction.setTitle("Withdrawal");
				}
			}
		}
		);
		f.setBackground(Color.GRAY);
		f.setVisible(true);
		
		Panel title = new Panel();
		Label titleLabel =  new Label("BANKING");
		WindowHelper.setFontDetails(titleLabel);
		title.add(titleLabel);
		title.setBackground(Color.yellow);
		
		Panel deleteAccount = new Panel();
		TextField accountNrTxt = new TextField(15);
		Label textLabel = new Label("Account Numberr");
		Button deleteButton = new Button("Delete");
		deleteButton.addActionListener(new ActionListener()
						{
								@Override
								public void actionPerformed(ActionEvent e)
								{
										String message = "";
										Button used = (Button) e.getSource();
										AccountManager accManager = AccountManager.getGetAccountManager();
										try
										{
												int nr = Integer.parseInt(accountNrTxt.getText());
												message = "Account Number: "+nr+" was deleted";
												if(!accManager.deleteAccount(nr))
												{
														message =  "Account Number does not exist";
												}
										}
										catch (NumberFormatException | SQLException e1)
										{
												message =  "Insert a Valid Number";
												e1.printStackTrace();
										}
										finally 
										{
												JOptionPane.showMessageDialog(used.getParent(),message);
												accountNrTxt.setText("");
										}
								}
				
						});
		
		deleteAccount.add(textLabel);
		deleteAccount.add(accountNrTxt);
		deleteAccount.add(deleteButton);
		
		Panel mainPanel = new Panel();
		mainPanel.setLayout(new GridLayout(4,2));
		Component[] mainPanelComponentsList = {createAccount,depositAccount,withdrawalAccount};
		WindowHelper.addToContainer(mainPanelComponentsList, mainPanel);
		f.add(title,BorderLayout.NORTH);
		f.add(mainPanel,BorderLayout.CENTER);
		f.add(deleteAccount,BorderLayout.SOUTH);
		
		f.setSize(500, 300);
	}
	public void createAccountWindow()
	{
		WindowHelper.addCloseOption(createUser, false);
		createUser.setBackground(Color.GRAY);
		createUser.setVisible(true);
		
		Panel title = new Panel();
		Label titleLabel =  new Label("Create Account");
		WindowHelper.setFontDetails(titleLabel);
		title.add(titleLabel);
		title.setBackground(Color.yellow);
		
		Panel create = new Panel();
		create.setLayout(new GridLayout(3,2));
		Label name = new Label("Name");
		Label address = new Label("Adress");
		Label accountNr = new Label("Account Number");
		TextField nameTxt = new TextField(15);
		final TextField addressTxt = new TextField(15);
		TextField accountTxt = new TextField(15);
		accountTxt.setBackground(Color.CYAN);
		addressTxt.setBackground(Color.CYAN);
		addressTxt.setEditable(false);
		accountTxt.setEditable(false);
		
		Component[] createComponentsList = {name,nameTxt,address,addressTxt,accountNr,accountTxt};
		WindowHelper.addToContainer(createComponentsList, create);
		

		createUser.add(title,BorderLayout.NORTH);
		createUser.add(create,BorderLayout.CENTER);
		Button saveAccount = new Button("Create");
		saveAccount.setEnabled(false);
		saveAccount.addActionListener(new BankingEventHandler (accountTxt,nameTxt,addressTxt));
		nameTxt.addTextListener((new BankingInputHandler(addressTxt)));
		addressTxt.addTextListener(new BankingInputHandler(saveAccount));
		createUser.add(saveAccount,BorderLayout.SOUTH);
		createUser.setSize(300, 180);
	}
	
	public void transactionAccountWindow(String operationType, boolean isDeposit)
	{
		transaction =  new Frame(operationType + " Cash");
		
		WindowHelper.addCloseOption(transaction, false);
		transaction.setBackground(Color.GRAY);
		transaction.setVisible(true);
		
		Panel title = new Panel();
		Label titleLabel =  new Label(operationType);
		WindowHelper.setFontDetails(titleLabel);
		title.add(titleLabel);
		
		title.setBackground(Color.yellow);
		
		Panel create = new Panel();
		create.setLayout(new GridLayout(5,2));
		Label name = new Label("Name");
		Label accountNr = new Label("Account Number");
		Label balance = new Label("Balance");
		Label depositAmount = new Label(operationType+" Amount");
		Label address = new Label("Address");
		TextField nameTxt = new TextField(15);
		TextField balanceTxt = new TextField(15);
		TextField accountTxt = new TextField(15);
		TextField depositAmountTxt = new TextField(15);
		TextField addressTxt = new TextField(15);
		

		addressTxt.setBackground(Color.CYAN);
		nameTxt.setBackground(Color.CYAN);
		balanceTxt.setBackground(Color.CYAN);
		depositAmountTxt.setBackground(Color.CYAN);
		addressTxt.setEditable(false);;
		nameTxt.setEditable(false);
		balanceTxt.setEditable(false);
		depositAmountTxt.setEditable(false);
		


		BankingEventHandler buttonEvents = new BankingEventHandler(accountTxt,nameTxt,addressTxt,balanceTxt,depositAmountTxt);
		
		Panel searchPanel = new Panel();
		searchPanel.add(accountTxt);
		Button lookForAccount = new Button("Search");

		Button depositMoney = new Button(operationType);
		nameTxt.addTextListener((new BankingInputHandler(depositAmountTxt)));
		lookForAccount.addActionListener(buttonEvents);
		depositMoney.addActionListener(buttonEvents);
		searchPanel.add(lookForAccount);
		depositAmountTxt.addTextListener(new BankingInputHandler(depositMoney));
		Component[] createComponentsList = {accountNr,searchPanel,name,nameTxt,address,addressTxt,balance,balanceTxt,depositAmount,depositAmountTxt};
		WindowHelper.addToContainer(createComponentsList, create);
		transaction.add(title,BorderLayout.NORTH);
		transaction.add(create,BorderLayout.CENTER);
		
		depositMoney.setEnabled(false);;
		transaction.add(depositMoney,BorderLayout.SOUTH);
		transaction.setSize(500, 250);
	}
}
