package com.qait.snl.advancedTatoc;
import java.io.File;
import java.util.Scanner;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FileHandling 
{
	 WebDriver driver;
	 JavascriptExecutor js;
	public FileHandling(WebDriver driver, JavascriptExecutor js) 
	{
		this.driver = driver;
		 this.js = js;
		// TODO Auto-generated constructor stub
	}
	
	  public void main() throws Exception
	  {
	   int c=0;
	   String str="";
		  File file = new File("/home/princegupta/Downloads/file_handle_test.dat");
	    Scanner sc = new Scanner(file);
	 
	    while (sc.hasNextLine()) 
	    {
	    	c++;
	    	
	    	if(c==3)
	    	{	
	    		str = sc.nextLine().substring(11);
	    		System.out.println(str);
	    	}
	    	else
	    	{
	    		sc.nextLine();
	    	}
	    	
	    }
	    
	    js.executeScript("return document.querySelector('#signature').value='"+str+"'");
	    
	    	
	  }
	
}


