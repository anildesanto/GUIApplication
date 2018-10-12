package com.qa.Anilde.GUIApplication;

public class Runner
{

	public static void main(String[] args)
	{
		//
		// // TODO Auto-generated method stub
		// QAConsulting qa1 = QAConsulting.createCompany();
		// System.out.println(qa1.calculate());
		//
		//
		//
		// QAConsulting qa2 = QAConsulting.createCompany();
		// qa2.setB(4);
		// System.out.println(qa2.calculate());
		//
		// System.out.println(qa1.calculate());

		// GUIPractice g = new GUIPractice();
		// g.createOther();
		//
		// CalculatorWindow calculator = new CalculatorWindow();
		// calculator.upgradedCalculator();
		prime(100);
	}

	public static void prime(int inp)
	{
		int i = 1;
		int loopTimes = 0;
		for (; i <= inp; i += 2)
		{
			boolean isPrime = true;
			for (int j = 3; j < Math.sqrt(inp); j += 2)
			{
				loopTimes++;
				if (i <= j)
				{
					continue;
				}
				if (i % j == 0)
				{
					isPrime = false;
					break;
				}
			}
			System.out.println("LoopTimes: " + loopTimes);
			System.out.print(isPrime ? ("Prime: " + i + "\n") : "");
		}
	}

}
