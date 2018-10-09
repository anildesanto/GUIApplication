package com.qa.Anilde.GUIApplication;

import java.awt.Button;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class UpgradedCalculator implements ActionListener
{
	ArrayList<Integer> numbers = new ArrayList<Integer>();
	ArrayList<Character> operations = new ArrayList<Character>();
	TextField resultBox;
	Button used;
	public UpgradedCalculator (TextField field)
	{
		resultBox = field;
	}
	public void addition (int stNum, int ndNum)
	{
		resultBox.setText(Integer.toString(stNum + ndNum));
	}
	public void subtraction(int stNum, int ndNum)
	{
		resultBox.setText(Integer.toString(stNum - ndNum));
	}
	public void division(int stNum, int ndNum)
	{
		resultBox.setText(Integer.toString(stNum / ndNum));
	}
	public void multiplication(int stNum, int ndNum)
	{
		resultBox.setText(Integer.toString(stNum * ndNum));
	}
	
	public void calculate()
	{
		//resultBox.setText(resultBox.getText()+used.getLabel());
		System.out.println(resultBox.getText());
		String end = resultBox.getText();
		String currentNum = "";
		String previous = "";
		//System.out.println(num);
		for (int i = 0; i < end.length(); i++) 
		{
			if(end.charAt(i) != '+' && end.charAt(i) != '/'
					&& end.charAt(i) != '*' && end.charAt(i) != '-')
			{
				currentNum += end.charAt(i);
			}
			else
			{
				numbers.add(Integer.parseInt(currentNum));
			}
		}
		numbers.add(Integer.parseInt(currentNum));
		System.out.println( "numbers size: "+numbers.size());
		for (Integer number : numbers) 
		{
			System.out.println(number);
		}
		for (int i = 0; i < end.length(); i++) 
		{
			if(end.charAt(i) == '+' || end.charAt(i) == '/'
					|| end.charAt(i) == '*' || end.charAt(i) == '-')
			{
				
				operations.add(end.charAt(i));
				System.out.println(i);
			}
		}
		for (int i = 0; i < operations.size(); i++) 
		{
			if(operations.get(i) == '+')
			{
				System.out.println("ooog");
				int result = numbers.get(0)+numbers.get(1);
				resultBox.setText(Integer.toString(result));
				numbers.remove(numbers.get(0));
				numbers.remove(numbers.get(1));
				numbers.set(0, result);
			}
			
		}

	}
	@Override
	public void actionPerformed(ActionEvent arg) 
	{
		used = (Button) arg.getSource();
		if(used.getLabel().toLowerCase().equals("c"))
		{
			resultBox.setText(" ");
			numbers.clear();
			operations.clear();
			System.out.println("Everything cleared");
		}
		
		try
		{
			calculate();
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
