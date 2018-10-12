package com.qa.BankingApplication.Banking;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.util.Arrays;
public class BankingInputHandler implements TextListener
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

	public void updateField()
	{
		if(!used.getText().equals(""))
		{
			toUpdate.setText("");
			toUpdate.setEditable(true);
			toUpdate.setBackground(Color.WHITE);
		}
		else
		{
			toUpdate.setText("");
			KeyListener[] list = toUpdate.getKeyListeners();
			Arrays.stream(list).forEach(action -> action.keyReleased(null));
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
	@Override
	public void textValueChanged(TextEvent e) 
	{
		used = (TextField) e.getSource();
		if(updateField)
			updateField();
		if(updateButton)
			updateButton();
	}

}
