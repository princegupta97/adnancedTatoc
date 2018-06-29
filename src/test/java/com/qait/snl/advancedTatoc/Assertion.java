package com.qait.snl.advancedTatoc;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Assertion 
{
	WebDriver driver;
	JavascriptExecutor js;
	
	Assertion(WebDriver driver,JavascriptExecutor js)
	{
		this.driver = driver;
		this.js = js;
	}
	public void putAssertiontoCheckErrorPageIsNotThere()
	{
		Assert.assertNotEquals("http://10.0.1.86/tatoc/error", (String) js.executeScript("return window.top.location.href.toString()"));
	}
	public void putAssertionNewtoCheckErrorPage()
	{
		Assert.assertEquals("http://10.0.1.86/tatoc/error", (String) js.executeScript("return window.top.location.href.toString()"));
	}
	
	public void checkThatTitleIsDisplayingOrNot()
	{
		String title = js.executeScript("return document.title;").toString();
		Assert.assertEquals(driver.getTitle(), title);

	}


}
