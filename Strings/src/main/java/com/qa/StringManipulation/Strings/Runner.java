package com.qa.StringManipulation.Strings;

public class Runner {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		StringManipulation sM = new StringManipulation();
		String[] sArray = {"2","24","err","dsd","288"};
		System.out.println(sM.stringChange(sArray));
		sM.stringChangeStream(sArray);
		String[] sArray2 = {"A123","B111","C453","C3331","D3456","D2245","C111","A1","B22"};
		sM.stringAStream(sArray2);
		System.out.println("Number To Word: "+sM.dNumber(99l));
		
	}

}
