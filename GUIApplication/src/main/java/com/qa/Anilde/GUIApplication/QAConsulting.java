package com.qa.Anilde.GUIApplication;

public class QAConsulting 
{
	private static QAConsulting qa;
	private static int a;
	private int b =2;
	
	static
	{
		a = 25;
	}

	public int getB() 
	{
		return b;
	}

	public void setB(int b) 
	{
		this.b = b;
	}
	public int calculate()
	{
		return 25*b;
	}
	public static QAConsulting createCompany()
	{
		if(qa == null)
		qa = new QAConsulting();
		return qa;
	}

}
