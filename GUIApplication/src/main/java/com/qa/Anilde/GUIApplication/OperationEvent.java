package com.qa.Anilde.GUIApplication;

import java.awt.Button;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class OperationEvent implements ActionListener
{
	TextField firstNumber, secondNumber, resultBox;
	Frame f;
	Button used;
	public OperationEvent(Frame f,TextField firstNumber,TextField secondNumber, TextField resultBox)
	{
		this.f = f;
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.resultBox = resultBox;
	}
	

	public void reset()
	{
		firstNumber.setText("");
		secondNumber.setText("");
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
	public void calculate() throws Exception
	{
		String label = used.getLabel().toLowerCase();
		
		int firstNum = Integer.parseInt(firstNumber.getText());
		int secondNum = Integer.parseInt(secondNumber.getText());
		System.out.println(firstNum);
		System.out.println(secondNum);
		if(label.equals("+"))
			addition(firstNum,secondNum);
		else if(label.equals("/"))
			division(firstNum,secondNum);
		else if(label.equals("-"))
			subtraction(firstNum,secondNum);
		else
			multiplication(firstNum,secondNum);
	}
	public void actionPerformed(ActionEvent arg) 
	{
		used = (Button) arg.getSource();
		try
		{
			calculate();
		}
		catch(Exception e)
		{
			String errorMessage = "Enter Valid Numbers!";
			JOptionPane.showMessageDialog(f, errorMessage);
			System.out.println(errorMessage);
		}
		System.out.println("\n"+used.getLabel()+" button clicked");
	}
}
