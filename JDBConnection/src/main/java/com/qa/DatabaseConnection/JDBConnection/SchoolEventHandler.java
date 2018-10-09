package com.qa.DatabaseConnection.JDBConnection;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SchoolEventHandler implements ActionListener
{
	Button used;
	Statement stmt;
	TextField regno, marks, name;

	public SchoolEventHandler(Statement statement, TextField regno, TextField name, TextField marks)
	{
		stmt = statement;
		this.regno = regno;
		this.name = name;
		this.marks = marks;
	}
	public void saveStudent() throws SQLException
	{
		stmt.executeUpdate("insert into school values("+regno.getText()+",'"+name.getText()+"',"+marks.getText()+")");
		//stmt.executeUpdate("insert into school values(345,'LOL',89)");
		stmt.close();
	}
	
	public void actionPerformed(ActionEvent arg) 
	{
		used = (Button) arg.getSource();
		try 
		{
			saveStudent();
		} 
		catch (SQLException e) 
		{
			String errorMessage = "Enter Valid Details!";
			JOptionPane.showMessageDialog(null, errorMessage);
			System.out.println(errorMessage + "\n"+ e.getMessage());
		}
		System.out.println("\n"+used.getLabel()+" button clicked");
	}
}
