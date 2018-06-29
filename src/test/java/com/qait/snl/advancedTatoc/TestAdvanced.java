package com.qait.snl.advancedTatoc;

import java.util.ArrayList;


import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TestAdvanced 
{
	WebDriver driver;
	JavascriptExecutor js;
	Assertion assertion;
	Connection1 connection1;
	LaunchPopUpWIndow launch;
	HttpClass http;
	String sessionId;
	FileHandling fileHandling;
	public TestAdvanced() 
	{
		System.setProperty("webdriver.chrome.driver","/home/princegupta/Downloads/chromedriver");
		driver = new ChromeDriver();
		
		js = (JavascriptExecutor) driver;
		assertion = new Assertion(driver, js);
		connection1 = new Connection1(driver,js);
		launch = new LaunchPopUpWIndow(driver, js);
		http = new HttpClass();
		fileHandling = new FileHandling(driver,js);
	}
	
	@Test
	public void advancedCourse()
	{
		driver.get("http://10.0.1.86/tatoc");
		js.executeScript("return document.querySelector(\'.page > a:nth-child(6)\').click()");
		assertion.putAssertiontoCheckErrorPageIsNotThere();
	}

	
	@Test(dependsOnMethods = {"advancedCourse"})
	public void hoverMenu()
	{
		assertion.checkThatTitleIsDisplayingOrNot();
		js.executeScript("return document.querySelector('body > div > div.page > div.menutop.m2 > span:nth-child(5)').click()");
		assertion.putAssertiontoCheckErrorPageIsNotThere();
	}
	

	@Test(dependsOnMethods = {"hoverMenu"})
	public void proceedWithoutEnteringCredentials() throws InterruptedException
	{
		Thread.sleep(1000);
		js.executeScript("return document.getElementById('submit').click()");
		assertion.putAssertionNewtoCheckErrorPage();
	}
	@Test(dependsOnMethods = {"hoverMenu"})
	public void queryGate() throws InterruptedException
	{
		driver.get("http://10.0.1.86/tatoc/advanced/query/gate");
		Thread.sleep(1200);
		String name = (String) js.executeScript("return document.getElementById('symboldisplay').textContent");
		System.out.println(name);
		connection1.main1(name);
		assertion.putAssertiontoCheckErrorPageIsNotThere();
		
	}
	
	@Test(dependsOnMethods = {"queryGate"})
	public void clickOnProceedWithoutPostHttpRequest()
	{
		js.executeScript("return document.querySelector('body > div > div.page > a').click()");
		assertion.putAssertionNewtoCheckErrorPage();
	}
	
	@Test(dependsOnMethods = {"queryGate"})
	public void restApiGenerateToken() throws IOException, InterruptedException
	{
		driver.get("http://10.0.1.86/tatoc/advanced/rest");
		sessionId = (String)js.executeScript("return document.getElementById(\"session_id\").innerHTML.substring(12);");
		String url = "http://10.0.1.86/tatoc/advanced/rest/service/token/"+ sessionId;
		String token = http.getHttpMethod(sessionId);
		Assert.assertNotNull(token);
	}
	
	@Test(dependsOnMethods = {"restApiGenerateToken"})
	public void restApiPostMethod() throws IOException
	{
		http.postHttpClass(sessionId);
		js.executeScript("return document.querySelector('body > div > div.page > a').click()");
		assertion.putAssertiontoCheckErrorPageIsNotThere();
	}
	
	@Test(dependsOnMethods = {"restApiPostMethod"})
	public void DownlaodFileandEnterSignatue() throws Exception
	{

		js.executeScript("return document.querySelector('body > div > div.page > a').click();");
		Thread.sleep(1000);
		fileHandling.main();
		js.executeScript("return document.querySelector('body > div > div.page > form > input.submit').click();");
		assertion.putAssertiontoCheckErrorPageIsNotThere();

	}

	@Test(dependsOnMethods = {"DownlaodFileandEnterSignatue"})
	public void ProceedWithoutEnteringSignature() throws Exception
	{
		driver.get("http://10.0.1.86/tatoc/advanced/file/handle");
		js.executeScript("return document.querySelector('body > div > div.page > a').click();");
		js.executeScript("return document.querySelector('body > div > div.page > form > input.submit').click();");
		assertion.putAssertionNewtoCheckErrorPage();

	}
	
	@Test(dependsOnMethods = {"DownlaodFileandEnterSignatue"})
	public void ProceedWithoutDownloadingFile() throws Exception
	{
		driver.get("http://10.0.1.86/tatoc/advanced/file/handle");		
		js.executeScript("return document.querySelector('body > div > div.page > form > input.submit').click();");
		assertion.putAssertionNewtoCheckErrorPage();

	}
	

	@Test(dependsOnMethods = {"DownlaodFileandEnterSignatue"})
	public void ProceedAfter30Seconds() throws Exception
	{
		driver.get("http://10.0.1.86/tatoc/advanced/file/handle");		
		js.executeScript("return document.querySelector('body > div > div.page > a').click();");
		Thread.sleep(32000);
		fileHandling.main();
		assertion.putAssertionNewtoCheckErrorPage();

	}

	
	
   // code for REstApi
		
//		js.executeScript("window.open('"+url+"')");
		
/*		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
		String token = (String)js.executeScript("return document.querySelector('body > pre').innerHTML.substring(10,42)");
		System.out.println(token);
		driver.switchTo().window(tabs.get(0));*/
	/*	RestAssured.baseURI ="http://10.0.1.86/tatoc/advanced/rest/service";
		RequestSpecification request = RestAssured.given();
	 
		JSONObject requestParams = new JSONObject();
		requestParams.put("id", sessionId); // Cast
		requestParams.put("signature", token);
		requestParams.put("allow_access",1);
		request.body(requestParams.toString());
		Response response1 =   request.post("/register");
		int statusCode = response1.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
		//Assert.assertEquals(statusCode, "201");
		//String successCode = response1.jsonPath().get("SuccessCode");
		//Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");
		js.executeScript("return document.querySelector('body > div > div.page > a').click()");*/
	
		
		
	}


