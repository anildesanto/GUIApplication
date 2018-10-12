package com.qa.Anilde.GUIApplication;

import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UpgradedCalculator implements ActionListener
{
	TextField resultBox;
	Label steps;
	Button used;
	public UpgradedCalculator (TextField field,Label steps)
	{
		resultBox = field;
		this.steps = steps;
	}

	public void actionPerformed(ActionEvent arg) 
	{
		used = (Button) arg.getSource();
		if(used.getLabel().toLowerCase().equals("c"))
		{
			resultBox.setText(" ");
			System.out.println("Everything cleared");
		}
		try
		{
			if(used.getLabel().toLowerCase().equals("="))
			{
    			//calculate();
    			 Calculator ei = new Calculator(resultBox.getText());
    			 resultBox.setText(""+ei.calculateUpgrade());
    			    System.out.println("\nResult: "+ei.calculateUpgrade());
    			    steps.setText(ei.steps);
    			    ei.steps();
    			    ei.steps = "";
			}
		}
		catch(Exception e)
		{
			String errorMessage = "Enter Valid Numbers!";
			JOptionPane.showMessageDialog(null, errorMessage);
			System.out.println(errorMessage);
		}
		System.out.println("\n"+used.getLabel()+" button clicked");
	}
	

}
