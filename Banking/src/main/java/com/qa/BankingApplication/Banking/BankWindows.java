package com.qa.BankingApplication.Banking;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BankWindows 
{
	Statement statement;
	Panel registeredStudents = new Panel();
	public BankWindows (Statement statement)
	{
		this.statement = statement;
	}
	public void mainWindow() throws HeadlessException, SQLException
	{
		Frame f =  new Frame("Register");
		Button createAccount = new Button("Create Account");
		Button depositAccount = new Button("Deposit Account");
		Button withdrawalAccount = new Button("Withdarwal Account");
		createAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg) 
			{
				createAccountWindow();
			}
		}
		);
		depositAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg) 
			{
				depositAccountWindow();
			}
		}
		);
		f.setBackground(Color.GRAY);
		f.setVisible(true);
		
		Panel title = new Panel();
		Label titleLabel =  new Label("BANKING");
		title.add(titleLabel);
		title.setBackground(Color.yellow);
		
		Panel mainPanel = new Panel();
		mainPanel.setLayout(new GridLayout(4,2));
		mainPanel.add(createAccount);
		mainPanel.add(depositAccount);
		mainPanel.add(withdrawalAccount);
		f.add(mainPanel,BorderLayout.CENTER);
		
		f.add(title,BorderLayout.NORTH);
		f.setSize(500, 400);
	}
	public void createAccountWindow()
	{
		Frame createUser =  new Frame("Create Account");
		createUser.setBackground(Color.GRAY);
		createUser.setVisible(true);
		
		Panel title = new Panel();
		Label titleLabel =  new Label("Create Account");
		title.add(titleLabel);
		title.setBackground(Color.yellow);
		
		Panel create = new Panel();
		create.setLayout(new GridLayout(3,2));
		Label name = new Label("Name");
		Label address = new Label("Adress");
		Label accountNr = new Label("Account Number");
		TextField nameTxt = new TextField(15);
		TextField addressTxt = new TextField(15);
		TextField accountTxt = new TextField(15);
		accountTxt.setBackground(Color.CYAN);
		accountTxt.setEditable(false);
		
		create.add(name);
		create.add(nameTxt);
		create.add(address);
		create.add(addressTxt);
		create.add(accountNr);
		create.add(accountTxt);
		

		createUser.add(title,BorderLayout.NORTH);
		createUser.add(create,BorderLayout.CENTER);
		Button saveAccount = new Button("Create");
		saveAccount.addActionListener(new BankingEventHandler (statement, accountTxt, nameTxt,addressTxt));
		createUser.add(saveAccount,BorderLayout.SOUTH);
		createUser.setSize(300, 180);
	}
	public void depositAccountWindow()
	{
		Frame createUser =  new Frame("Create Account");
		createUser.setBackground(Color.GRAY);
		createUser.setVisible(true);
		
		Panel title = new Panel();
		Label titleLabel =  new Label("Deposit");
		title.add(titleLabel);
		title.setBackground(Color.yellow);
		
		Panel create = new Panel();
		create.setLayout(new GridLayout(4,2));
		Label name = new Label("Name");
		Label accountNr = new Label("Account Number");
		Label balance = new Label("Balance");
		Label depositAmount = new Label("Deposit Amount");
		TextField nameTxt = new TextField(15);
		TextField balanceTxt = new TextField(15);
		TextField accountTxt = new TextField(15);
		TextField depositAmountTxt = new TextField(15);
		
		nameTxt.setBackground(Color.CYAN);
		balanceTxt.setBackground(Color.CYAN);
		nameTxt.setEditable(false);
		balanceTxt.setEditable(false);
		depositAmountTxt.setBackground(Color.CYAN);
		depositAmountTxt.setEditable(false);
		
		Panel searchPanel = new Panel();
		
		create.add(accountNr);
		searchPanel.add(accountTxt);
		Button lookForAccount = new Button("Search");

		Button saveAccount = new Button("Deposit");
		lookForAccount.addActionListener(new BankingEventHandler(accountTxt, nameTxt,depositAmountTxt,statement, saveAccount,balanceTxt));
		saveAccount.addActionListener(new BankingEventHandler( accountTxt,depositAmountTxt, balanceTxt,statement));
		searchPanel.add(lookForAccount);
		create.add(searchPanel);
		create.add(name);
		create.add(nameTxt);
	
		create.add(balance);
		create.add(balanceTxt);
		
		create.add(depositAmount);
		create.add(depositAmountTxt);
		
		createUser.add(title,BorderLayout.NORTH);
		createUser.add(create,BorderLayout.CENTER);
		
//		/saveAccount.addActionListener();
		saveAccount.setEnabled(false);;
		createUser.add(saveAccount,BorderLayout.SOUTH);
		createUser.setSize(500, 200);
	}
}
