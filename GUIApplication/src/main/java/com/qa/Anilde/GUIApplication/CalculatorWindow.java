package com.qa.Anilde.GUIApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Utilities.WindowHelper;
public class CalculatorWindow 
{
	Label steps = new Label();
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
		WindowHelper.addCloseOption(f);
		//f.setLayout(new FlowLayout());
		Panel title = new Panel();
		Button titleLabel =  new Button("Steps");
		titleLabel.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				resultsSteps();
				
			}
			
		});
		//WindowHelper.setFontDetails(titleLabel);
		//title.setLayout(new GridLayout(2,1));
		TextField inputField =  new TextField(30);
		Panel inputPanel = new Panel();
		Panel gridPanel = new Panel();
		
		title.add(inputPanel);
		title.add(titleLabel);
		title.setBackground(Color.CYAN);
		
		sumBtn = new Button("+");
		minusBtn = new Button("-");
		timesBtn = new Button("*");
		divBtn = new Button("/");
		f.setBackground(Color.GRAY);
		f.setVisible(true);
	
		
		inputPanel.add(inputField);
		gridPanel.setLayout(new GridLayout(5,4));
		
		addButtons(13,gridPanel);
		setButtons(gridPanel,   inputField, steps);
		
		f.add(title,BorderLayout.NORTH);
		//f.add(inputPanel, BorderLayout.NORTH);
		f.add(gridPanel, BorderLayout.CENTER);
		
		f.setSize(400, 400);
	}
	public Label resultsSteps()
	{
		Frame f =  new Frame("Steps");
		WindowHelper.addCloseOption(f);
		//f.setLayout(new FlowLayout());
		Panel title = new Panel();
		Button titleLabel =  new Button("Steps");
		//title.setLayout(new GridLayout(2,1));
		title.add(titleLabel);
		title.setBackground(Color.CYAN);
		
		f.setBackground(Color.GRAY);
		f.setVisible(true);
		
		Panel inputPanel = new Panel();
		Panel gridPanel = new Panel();
		gridPanel.setLayout(new GridLayout(5,4));
		gridPanel.add(steps);
		title.add(inputPanel);
		f.add(title,BorderLayout.NORTH);
		//f.add(inputPanel, BorderLayout.NORTH);
		f.add(gridPanel, BorderLayout.CENTER);
		
		f.setSize(200, 200);
		return steps;
	}
	public void addToPanel(String step)
	{
		TextField t = new TextField(10);
	}
	public void setButtons(Panel gridPanel, final TextField field, Label steps)
	{
		for (Component c : gridPanel.getComponents()) 
		{
			final Button btn = (Button)c;
			if(btn.getLabel().toLowerCase().equals( "=") || btn.getLabel().toLowerCase().equals( "c"))
			{
				btn.addActionListener(new UpgradedCalculator(field, steps));
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
			
			if(i != 3 && i != 6 && i != 9 && i != 13)
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
				else if(i == 12)
				{
					btn = new Button("(");
				}
				else if(i == 13)
				{
					btn = new Button(")");
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
