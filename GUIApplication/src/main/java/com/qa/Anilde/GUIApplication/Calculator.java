package com.qa.Anilde.GUIApplication;

import java.util.ArrayList;

public class Calculator
{
	static String steps = "";
	static int count = 0;
	ArrayList<Character> operators = new ArrayList<Character>()
	{
		{
			add('/');
			add('*');
			add('+');
			add('-');
		}
	};
	ArrayList<Double> numbers = new ArrayList<Double>();
	ArrayList<Character> usedOperators = new ArrayList<Character>();

	public Calculator(String c)
	{
		calculation = c;
	}

	String calculation;

	public void getNumbersAndOperators()
	{
		String tempNumber = "";
		for (int i = 0; i < calculation.length(); i++)
		{
			char current = calculation.charAt(i);

			if (current == '(')
			{
				int index = calculation.indexOf(current);
				int index2 = calculation.indexOf(')');
				String inner = calculation.substring(index + 1, index2);
				Calculator innerCheck = new Calculator(inner);

				calculation = calculation.substring(0, index) + innerCheck.calculateUpgrade()
						+ calculation.substring(index2 + 1, calculation.length());

				steps += "\nStep " + count + ": " + calculation;
				count++;
				i = index - 1;

			} else if (operators.contains(current) && tempNumber != "")
			{
				numbers.add(Double.parseDouble(tempNumber));
				usedOperators.add(current);
				tempNumber = "";
			} else

			{
				tempNumber += current;
			}
		}
		numbers.add(Double.parseDouble(tempNumber));
	}

	public void steps()
	{
		System.out.println("===== STEPS ====");
		System.out.println(steps);
	}

	public double calculateUpgrade()
	{
		if (count == 0)
		{
			steps += "\nCalculation" + ": " + calculation;
			count++;
		}
		getNumbersAndOperators();
		double result = 0;
		for (int i = 0; i < operators.size(); i++)
		{
			while (usedOperators.contains(operators.get(i)))
			{
				int index = usedOperators.indexOf(operators.get(i));
				double num1 = numbers.get(index);
				double num2 = numbers.get(index + 1);

				result = operationType(operators.get(i), num1, num2);
				numbers.remove(index);
				numbers.remove(index);
				numbers.add(index, result);
				usedOperators.remove(index);
			}

		}
		return result;
	}

	public double operationType(char c, double n1, double n2)
	{
		double result = 0;
		switch (c)
		{
			case '*':
				result = n1 * n2;

				break;
			case '/':
				result = n1 / n2;
				break;
			case '+':
				result = n1 + n2;
				break;
			case '-':
				result = n1 - n2;
				break;
		}
		addSteps(c, n1, n2, result);
		return result;
	}

	public void addSteps(char c, double n1, double n2, double result)
	{
		steps += "\nStep " + count + ": " + n1 + " " + c + " " + n2 + " = " + result;
		count++;
	}
}
