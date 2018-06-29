package com.qait.snl.advancedTatoc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class HttpClass 
{
	String token;
	public String getHttpMethod(String sessionId) throws IOException
	{
		String url = "http://10.0.1.86/tatoc/advanced/rest/service/token/"+ sessionId;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		JSONObject jsonObject = new JSONObject(response.toString());
		token = jsonObject.getString("token");
		System.out.println(token);
		return token;

	}
	
	public void postHttpClass(String sessionId) throws IOException
	{
		String url1 = "http://10.0.1.86/tatoc/advanced/rest/service/register";
		URL obj1 = new URL(url1);
		HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();

		con1.setRequestMethod("POST");

		String urlParameters = "id="+sessionId+"& signature="+token+"&allow_access=1";
		
		// Send post request
		con1.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con1.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode1 = con1.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url1);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode1);

		BufferedReader in1 = new BufferedReader(
		        new InputStreamReader(con1.getInputStream()));
		String inputLine1;
		StringBuffer response1 = new StringBuffer();

		while ((inputLine1 = in1.readLine()) != null) {
			response1.append(inputLine1);
		}
		in1.close();
		
		//print result
		System.out.println(response1.toString());

	}

}
