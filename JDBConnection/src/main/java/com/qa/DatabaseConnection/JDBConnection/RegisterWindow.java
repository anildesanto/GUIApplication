package com.qa.DatabaseConnection.JDBConnection;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterWindow 
{
	Statement statement;
	Frame f =  new Frame("Register");
	Panel registeredStudents = new Panel();
	public RegisterWindow (Statement statement)
	{
		this.statement = statement;
	}
	public void registerWindow() throws HeadlessException, SQLException
	{
		
		//f.setLayout(new FlowLayout());
		Button register = new Button("Save");
		
		f.setBackground(Color.GRAY);
		f.setVisible(true);
		TextField regField =  new TextField(10);
		TextField nameField =  new TextField(10);
		TextField marksField =  new TextField(10);
		Label regLabel =  new Label("Regno");
		Label nameLabel =  new Label("Name");
		Label marksLabel =  new Label("Marks");
		Panel title = new Panel();
		Label titleLabel =  new Label("REGISTER ON OUR DATABASE");
		title.add(titleLabel);
		title.setBackground(Color.yellow);
		Panel registerPanel = new Panel();
		registerPanel.setLayout(new GridLayout(4,2));
		registerPanel.add(regLabel);
		registerPanel.add(regField);
		registerPanel.add(nameLabel);
		registerPanel.add(nameField);
		registerPanel.add(marksLabel);
		registerPanel.add(marksField);
		f.add(registerPanel,BorderLayout.CENTER);
		
		Panel savePanel = new Panel();
		register.addActionListener(new SchoolEventHandler(statement,regField,nameField,marksField));
		savePanel.add(register);
		registerPanel.add(savePanel);
		f.add(title,BorderLayout.NORTH);
		addStudentsPanel();
		f.setSize(500, 400);
	}
	public void updateStudentsPanel() throws SQLException
	{
		f.remove(registeredStudents);
		f.add(retrieve(),BorderLayout.SOUTH);
	}
	public void addStudentsPanel() throws SQLException
	{
		f.add(retrieve(),BorderLayout.SOUTH);
	}
	public Panel retrieve() throws SQLException
	{
		ResultSet rs = statement.executeQuery("Select * from school");
		String users = "";
		while (rs.next()) 
		{
			int regno = rs.getInt("regno");
			String name = rs.getString("name");
			int marks = rs.getInt("marks");

			users += "\n"+regno+" "+name+" "+marks;
			Label studentLabel =  new Label(users);
			registeredStudents.add(studentLabel);
			users = "";
		}
		registeredStudents.setLayout(new GridLayout(10,1));
		rs.close();
		return registeredStudents;
	}
}
