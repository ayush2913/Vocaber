package com.vocaber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;

public class DemoSynonymnGenerator {
	
	String url;
	String charset;
	
	DemoSynonymnGenerator(){
		url="http://words.bighugelabs.com/api/2/b692964a96b3e4d894852125fd8ea079/kind/";
		charset="UTF-8";
	}

	public void getSynonymns() throws IOException{
		

		System.setProperty("java.net.useSystemProxies", "true");		
		String url = "http://words.bighugelabs.com/api/2/b692964a96b3e4d894852125fd8ea079/kind/";
		 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");


		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);
		
		final String authUser = "069.3715";
		final String authPassword = "varanasi";
		Authenticator.setDefault(
		   new Authenticator() {
		      public PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(
		               authUser, authPassword.toCharArray());
		      }
		   }
		);
		System.setProperty("http.proxyHost", "10.1.1.18");
		System.setProperty("http.proxyPort", "80");
		System.setProperty("http.proxyUser", authUser);
		System.setProperty("http.proxyPassword", authPassword); 
		System.out.println("\nSending 'GET' request to URL : " + url);
		
		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);
 
		if(responseCode!=404){
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine+"\n");
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
		}
	}
}
