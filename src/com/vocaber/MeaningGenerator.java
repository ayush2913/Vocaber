package com.vocaber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Scanner;

public class MeaningGenerator {

	String url;
	String charset;

	MeaningGenerator() {
		url = "http://words.bighugelabs.com/api/2/b692964a96b3e4d894852125fd8ea079/kidl/";
		charset = "UTF-8";
	}

	public void getMeaning() throws IOException {

		System.setProperty("java.net.useSystemProxies", "true");
		//String url = "http://www.stands4.com/services/v2/defs.php?uid=3554&tokenid=Jlp1QuXhxasXzE6P&word=voyage";
		String url="http://access.alchemyapi.com/calls/text/TextGetTextSentiment?apikey=ab076a07a58500bfd109bb5ef49e62be6082cabf&sentiment=1&showSourceText=1&text=vacate";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		final String authUser = "069.3715";
		final String authPassword = "varanasi";
		Authenticator.setDefault(new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(authUser, authPassword
						.toCharArray());
			}
		});
		System.setProperty("http.proxyHost", "10.1.1.18");
		System.setProperty("http.proxyPort", "80");
		System.setProperty("http.proxyUser", authUser);
		System.setProperty("http.proxyPassword", authPassword);
		System.out.println("\nSending 'GET' request to URL : " + url);

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		if (responseCode != 404) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;

			double score =0.0;
			while ((inputLine = in.readLine()) != null) {

				//String key = "noun";
				if(inputLine.contains("score")==true){
				score = getScoreForWord(inputLine);
				System.out.println(score);
				break;
				}
			}
			in.close();
		}
	}

	public double getScoreForWord(String line) {

		double score=0.0;
		if (line.contains("score") == true) {
			Scanner s = new Scanner(line);
			s.useDelimiter("<|\\s|>");

			while (s.hasNext()) {

				String word = s.next();
				
				if (word.equalsIgnoreCase("score") == true) {
					score = s.nextDouble();
					return score;
				}

			}

		}
		return 0.0;
	}
}
