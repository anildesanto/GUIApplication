package com.qa.Anilde.GUIApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
public class CalculatorWindow 
{
	public void createWindow()
	{
		Frame f =  new Frame("Calculator");
		f.setLayout(new FlowLayout());

		f.setBackground(Color.GRAY);
		f.setVisible(true);
		Button sumBtn, divBtn, timesBtn, minusBtn;
		sumBtn = new Button("+");
		minusBtn = new Button("-");
		timesBtn = new Button("*");
		divBtn = new Button("/");
		
		//===============================================
		Label lbNr1, lbNr2, lbResults;
		lbNr1 = new Label("Number 1:");
		lbNr2 = new Label("Number 2:");
		lbResults = new Label("Results:");
		TextField firstNumberField, secondNumberField, resultsField;
		firstNumberField = new TextField(15);
		secondNumberField = new TextField(15);
		resultsField =  new TextField(30);
		
		OperationEvent calculate = new OperationEvent(f,firstNumberField,secondNumberField, resultsField);
		sumBtn.addActionListener(calculate);
		minusBtn.addActionListener(calculate);
		timesBtn.addActionListener(calculate);
		divBtn.addActionListener(calculate);
		f.add(lbNr1);
		f.add(firstNumberField);
		f.add(lbNr2);
		f.add(secondNumberField);
		f.add(sumBtn);
		f.add(minusBtn);
		f.add(timesBtn);
		f.add(divBtn);
		f.add(lbResults);
		f.add(resultsField);
		f.setSize(400, 400);
	}

	Button sumBtn, divBtn, timesBtn, minusBtn;

	
	public void upgradedCalculator()
	{
		Frame f =  new Frame("Calculator");
		//f.setLayout(new FlowLayout());
		sumBtn = new Button("+");
		minusBtn = new Button("-");
		timesBtn = new Button("*");
		divBtn = new Button("/");
		f.setBackground(Color.GRAY);
		f.setVisible(true);
	
		TextField inputField =  new TextField(30);
		Panel inputPanel = new Panel();
		Panel gridPanel = new Panel();
		inputPanel.add(inputField);
		gridPanel.setLayout(new GridLayout(4,4));
		
		
		addButtons(12,gridPanel);
		setButtons(gridPanel,   inputField);
		f.add(inputPanel, BorderLayout.NORTH);
		f.add(gridPanel, BorderLayout.CENTER);
		f.setSize(400, 400);
	}
	public void setButtons(Panel gridPanel, final TextField field)
	{
		for (Component c : gridPanel.getComponents()) 
		{
			final Button btn = (Button)c;
			if(btn.getLabel().toLowerCase().equals( "=") || btn.getLabel().toLowerCase().equals( "c"))
			{
				btn.addActionListener(new UpgradedCalculator(field));
			}
			else
			{
				btn.addActionListener(
						new ActionListener()
						{
							public void actionPerformed(ActionEvent arg) 
							{
								String output = field.getText()+btn.getLabel();
								field.setText(output);
							}
						}
						);
			}
		}
	}
	public void addButtons(int number,Panel gridPanel)
	{
		Component[] components = {sumBtn, minusBtn,timesBtn,divBtn};
	
		for (int i = 0,a = 0; i <= number; i++) 
		{
			
			if(i != 3 && i != 6 && i != 9 && i != 12)
			{
				Button btn = null;
				if(i == 10)
				{
					btn = new Button("=");
				}
				else if(i == 11)
				{
					btn = new Button("C");
				}
				else
				{
					btn = new Button(Integer.toString(i));
				}
				gridPanel.add(btn);
			}
			else
			{
				gridPanel.add(components[a]);
				a++;
				if(i != number)
				{
					Button btn = new Button(Integer.toString(i));
					gridPanel.add(btn);
				}
			}
		}
	}
}
