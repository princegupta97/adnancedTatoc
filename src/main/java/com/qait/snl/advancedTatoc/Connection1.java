package com.qait.snl.advancedTatoc;
import java.sql.*;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
public class Connection1 
{	  
	 public static String SCHEMA_NAME="${tatoc}";
	 int id;
	 WebDriver driver;
	 JavascriptExecutor js;
	
	 public Connection1(WebDriver driver,JavascriptExecutor js) 
	 {
		 this.driver = driver;
		 this.js = js;

	}
	 public  void main1(String name)
	 {  
		try
		{  
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con = DriverManager.getConnection("jdbc:mysql://10.0.1.86:3306/tatoc","tatocuser","tatoc01");  


				Statement stmt=con.createStatement();
				
				ResultSet rs=stmt.executeQuery("select id from identity where symbol= '"+name+"'"); 

				if(rs.next())  
				id = rs.getInt(1);  
				ResultSet password = stmt.executeQuery("select name,passkey from credentials where id = '"+id+"'");
				String name1 ="";
				String passkey="";
				if(password.next())
				{
					name1 = password.getString(1);
					passkey = password.getString(2);				
					System.out.println(name1+"  " + passkey);
				}
				
				js.executeScript("return document.getElementById('name').value= '"+name1+"'");
				js.executeScript("return document.getElementById('passkey').value= '"+passkey+"'");
				js.executeScript("return document.getElementById('submit').click()");

				con.close();  
	}
		catch(Exception e){ System.out.println(e);}  
	}  
/*	 public static void main(String args[])
	 {
		 Connection1 ob = new Connection1("bravo");
		 ob.main1();
	 }
*/}  


