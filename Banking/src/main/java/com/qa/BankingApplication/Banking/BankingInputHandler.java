package com.qa.BankingApplication.Banking;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BankingInputHandler implements KeyListener
{
	TextField toUpdate;
	Button button;
	boolean updateButton;
	boolean updateField;
	TextField used;
	public BankingInputHandler(TextField toUpdate, Button button)
	{
		this.toUpdate = toUpdate;
		this.button = button;
		setBools();
	}
	public BankingInputHandler(Button button)
	{
		this.button = button;
		setBools();
	}
	public BankingInputHandler(TextField toUpdate)
	{
		this.toUpdate = toUpdate;
		setBools();
	}
	public void setBools()
	{
		if(toUpdate != null)
			updateField = true;
		if(button != null)
			updateButton = true;
	}
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	public void updateField()
	{
		if(!used.getText().equals(""))
		{
			toUpdate.setEditable(true);
			toUpdate.setBackground(Color.WHITE);
		}
		else
		{
			toUpdate.setText("");
			toUpdate.setBackground(Color.CYAN);
			toUpdate.setEditable(false);
		}
	}
	public void updateButton()
	{
		if(!used.getText().equals(""))
		{
			button.setEnabled(true);
		}
		else
		{
			button.setEnabled(false);
		}
	}
	public void keyReleased(KeyEvent e) 
	{
		used = (TextField) e.getSource();
		if(updateField)
			updateField();
		if(updateButton)
			updateButton();
		
	}

	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

}
