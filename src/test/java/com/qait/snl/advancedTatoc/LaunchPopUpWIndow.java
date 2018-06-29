package com.qait.snl.advancedTatoc;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class LaunchPopUpWIndow 
{
	WebDriver driver;
	JavascriptExecutor js;
	public LaunchPopUpWIndow(WebDriver driver,JavascriptExecutor js)
	{
		this.driver = driver;
		this.js = js;

	}
	
	public void launchWindowandHandle()
	{
		String parentWindowHandler = driver.getWindowHandle(); // Store your parent window
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles(); // get all window handles
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext())
		{
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler); // switch to popup window
		String token = (String)js.executeScript("return document.querySelector('body > pre').innerHTML.substring(10,42)");
		driver.switchTo().window(parentWindowHandler); 

	}


}
