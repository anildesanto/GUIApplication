package com.qa.Anilde.GUIApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputMap;

public class GUIPractice implements ActionListener
{
	TextField inputField;
	Container f = new Frame("GUI Practice");
	public void createWindow()
	{
		Frame f = new Frame("GUI Practice");
		Button continua = new Button("Profile");
		f.setLayout(new FlowLayout());
		f.add(continua, BorderLayout.NORTH);
		Button lol = new Button("Lol");
		f.add(lol, BorderLayout.CENTER);
		f.setSize(400, 400);
		f.setVisible(true);
	}
	public void createOther()
	{
		Button profile = new Button("Profile");
		Button log = new Button("Log In");
		Button contact = new Button("Contact");
		//===============================================
		Button saveEmail = new Button("Save Email");
		Button save = new Button("Save");
		Button test3 = new Button("test3");
		
		
		Panel panel = new Panel();
		TextField text = new TextField(30);
		Label label = new Label("Description:");

		profile.addActionListener(new GUIPractice(text));
		contact.addActionListener(new GUIPractice(text));
		log.addActionListener(new GUIPractice(text));
		saveEmail.addActionListener(new GUIPractice(text));
		//panel.setLayout(mgr);
		panel.setBackground(Color.GRAY);
		panel.add(label);
		panel.add(text);
		
		Panel header = new Panel();
		header.setBackground(Color.BLACK);
		//header.setSize(300,300);
		//header.setLayout(new FlowLayout());
		//header.add(profile,BorderLayout.WEST );
		header.add(contact);
		header.add(log);
		header.add(saveEmail);
		header.add(save);
		header.add(test3);
		
		Panel register = new Panel();
		TextField email = new TextField(30);
		Label emailLabel = new Label("Email:");
		register.add(email);
		register.add(emailLabel);
	
		//f.setLayout(new FlowLayout());
		//f.setLayout(new BorderLayout());
		f.setSize(400, 400);
		f.setBackground(Color.BLUE);
		
	
		
		
		
		
		f.add(header, BorderLayout.NORTH);
		f.add(panel, BorderLayout.WEST);
		f.add(register, BorderLayout.EAST);
		f.setVisible(true);
	}
	public GUIPractice(TextField text)
	{
		inputField = text;
	}
	public GUIPractice()
	{
	}
	public Component lookForComponent()
	{
		Component[] lol =  f.getComponents();
		for (Component component : lol) 
		{
			if(component instanceof TextField)
			{
				TextField chosenTextField = (TextField) component;
				System.out.println("Input field Details: "+chosenTextField);
				return component;
			}
		}
		return null;
	}
	public void textEntered()
	{
		System.out.println("Email stored: "+inputField.getText());
		inputField.setText("");
	}
	public void actionPerformed(ActionEvent arg) 
	{
		Button used = (Button) arg.getSource();
		
		if(used.getLabel().toLowerCase().equals("profile"))
			inputField.setText("This is my Profile");
		else if(used.getLabel().toLowerCase().equals("contact"))
			inputField.setText("Contact me on 966879626");
		else if(used.getLabel().toLowerCase().equals("save email"))
			textEntered();
		else
			inputField.setText("You are now logged in");
		System.out.println("\n"+used.getLabel()+" button clicked");
	
	}
}
