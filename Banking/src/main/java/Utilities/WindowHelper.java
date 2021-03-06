package Utilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Stream;

import com.qa.BankingApplication.Banking.AccountManager;

public class WindowHelper 
{
	private static AccountManager accManager;
	public static void addToContainer(Component[] cList, Container cc)
	{
		Arrays.stream(cList).forEach((component) -> cc.add(component));
	}
	public static void setFontDetails(Label titleLabel)
	{
		titleLabel.setFont(new Font ("TimesRoman", Font.BOLD, 15));
		titleLabel.setText(titleLabel.getText().toUpperCase());
	}
	public static void addCloseOption(Frame f, boolean isMain)
	{
		 f.addWindowListener(new WindowAdapter()
		{
			 public void windowClosing(WindowEvent e) 
			 {
	               // Dispose the window after the close button is clicked.
				 accManager = AccountManager.getGetAccountManager();
				 try 
				 {
					 if(isMain)
						 accManager.close();
					 else
						 f.setTitle("closed");
				 } 
				 catch(Exception ex)
				 {
					 ex.printStackTrace();
				 }
				 finally
				 {
					 e.getWindow().dispose();
					 //System.exit(0);
				 }
				
			 }
		});
	}
}
