package com.qa.StringManipulation.Strings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class StringManipulation 
{
	public int stringChange(String[] sArray)
	{
		System.out.println("\n=========== String Manipulation =================");
		int output = 0;
		ArrayList<Integer> numbers =  new ArrayList<Integer>();
		for (String string : sArray) 
		{
			if(string.length() <= 2)
			{
				try
				{
					int num = Integer.parseInt(string);
					numbers.add(num);
					System.out.println("Number Added:"+num);
				}
				catch (Exception e)
				{
					continue;
				}
			}
		}
		for (Integer integer : numbers) 
		{
			integer *=10;
			System.out.println("Number *10:"+integer);
			output += integer;
		}
		System.out.println("Result: "+output);
		return output;
	}
	public void stringChangeStream(String[] sArray)
	{
		System.out.println("\n=========== String Manipulation Stream =================");
		Arrays.stream(sArray)
		.filter((word) -> word.length() <= 2)
		.filter((word) -> 
		{
			try
			{
				Integer.parseInt(word);
				return true;
			}
			catch (Exception e)
			{
				return false;
			}
		})
		.map((num) -> Integer.parseInt(num))
		.map((num) -> num*10)
		.reduce((current, total) -> total+current)
		.ifPresent(System.out::print);
	}
	
	
	public void stringAStream(String[] sArray)
	{
		System.out.println("\n=========== String Manipulation Stream Fom ABCD to biggest number =================");
		Arrays.stream(sArray)
		.filter((word) -> !word.startsWith("D"))
		.map((word) -> 
		{
			int number = Integer.parseInt(word.substring(1, word.length()));
			int result = 0;
			if(word.startsWith("A"))
				result = number*2;
			else if(word.startsWith("B"))
				result = number+100;
			else
				result = number-100;
			
			 return result;
		})
		.filter((num) -> num%5 != 0)
		.reduce((current,  total) -> 
		{
			if(current > total)
				total = current;
			
			return total;
		}).ifPresent(System.out::print);
	}
	
	public void stringIntegersStream(int[] sArray)
	{
		System.out.println("\n=========== String Manipulation Stream =================");
		Arrays.stream(sArray);
	}
	private static String output = "";
	public String dNumber(long number)
    {
		Arrays.stream(firstSelection).reduce((current, result) -> 
		{
			 int n = java.util.Arrays.asList(firstSelection).indexOf(current);
			 System.out.println("\nIndex: " +n);
			 if(n < 20)
			 {
				 System.out.print("\nSingleNum: " +number);
				 if(number == n)
				 {
					 output = (n == 0 ? "" : " ")+ firstSelection[n];
					 System.out.println("Output: " +output);
				 }
			 }
			 else
			 {
				 int secondN = n-19;
			     if(secondN == -19)
			     return null;
			     
			     long checkNum = (number/10)*10;
			     int actualNum = secondN*10;
				 long remainder = number%10;
				 System.out.print("\nNum: " +number);
				 System.out.print("\nCheck Num: " +checkNum);
				 System.out.print("\nActual Num: " +actualNum);
				 System.out.print("\nRemainder Num: " +remainder+"\n");
				 if(checkNum == actualNum)
				 {
					 output = " "+firstSelection[n]  + dNumber(remainder);
				 }
			 }
			 return result;
		}).ifPresent(word -> System.out.println("\nFinal Word: " +word));;
		System.out.println("Result "+output);
		
	    return output;
    }
	private final String[] firstSelection =
	{
	    "",
	    "one",
	    "two",
	    "three",
	    "four",
	    "five",
	    "six",
	    "seven",
	    "eight",
	    "nine",
	    "ten",
	    "eleven",
	    "twelve",
	    "thirteen",
	    "fourteen",
	    "fifteen",
	    "sixteen",
	    "seventeen",
	    "eighteen",
	    "nineteen",
	    "",
	    "twenty",
	    "thirty",
	    "fourty",
	    "fifty",
	    "sixty",
	    "seventy",
	    "eighty",
	    "ninety"
	};
}
