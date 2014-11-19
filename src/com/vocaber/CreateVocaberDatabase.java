package com.vocaber;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateVocaberDatabase {
	
	String inputDataFile;
	Scanner scn;
	String word;
	ArrayList<String> adjective;
	ArrayList<String> noun;
	ArrayList<String> verb;
	ArrayList<String> adverb;
	ArrayList<Integer> adjectiveScore;
	ArrayList<Integer> nounScore;
	ArrayList<Integer> verbScore;
	ArrayList<Integer> adverbScore;
	int wordScore;
	String bigHugeLabApi;
	int apiCounter;
	boolean flag;
	int nullValueCounter;
	ArrayList<String> apiList;
	
	CreateVocaberDatabase(String fileName,String bigHuge,ArrayList<String> list){
		
		inputDataFile = fileName;
		scn = null;
		adjective = new ArrayList<String>();
		noun = new ArrayList<String>();
		verb = new ArrayList<String>();
		adverb = new ArrayList<String>();
		adjectiveScore = new ArrayList<Integer>();
		nounScore = new ArrayList<Integer>();
		verbScore = new ArrayList<Integer>();
		adverbScore = new ArrayList<Integer>();
		wordScore=0;
		bigHugeLabApi=bigHuge;
		apiCounter=0;
		nullValueCounter=0;
		flag=false;
		apiList=list;
	}
	
	public void getWords(){
		
		try {
			scn = new Scanner(new File(inputDataFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while(scn.hasNext()){
			word = scn.next();
			try {
				getSynonyms(word);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void getSynonyms(String word) throws IOException{
		
		System.setProperty("java.net.useSystemProxies", "true");		
		String url = String.format("http://words.bighugelabs.com/api/2/%s/%s/", bigHugeLabApi,word); 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
		
		final String authUser = "069.3706";
		final String authPassword = "prwiitbhu";
		
		Authenticator.setDefault(
		   new Authenticator() {
		      public PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication(
		               authUser, authPassword.toCharArray());
		      }
		   }
		);
		System.setProperty("http.proxyHost", "10.1.1.16");
		System.setProperty("http.proxyPort", "80");
		System.setProperty("http.proxyUser", authUser);
		System.setProperty("http.proxyPassword", authPassword); 
		System.out.println(word);
		
		int responseCode = con.getResponseCode();
		//System.out.println("Response Code : " + responseCode);
 
		if(responseCode!=404){
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		adjective = new ArrayList<String>();
		noun = new ArrayList<String>();
		verb = new ArrayList<String>();
		adverb = new ArrayList<String>();
		wordScore=0;
		adjectiveScore = new ArrayList<Integer>();
		nounScore = new ArrayList<Integer>();
		verbScore = new ArrayList<Integer>();
		adverbScore = new ArrayList<Integer>();
 
		while ((inputLine = in.readLine()) != null) {
			
			getSynonymValues(inputLine);
		}
		in.close();
		}
		
		getScoreValues();
	}

	public void getSynonymValues(String line) throws IOException{
		
		if(line.contains("adjective")==true && line.contains("syn")==true){
			String word = getWord(line);
			if(checkProperWord(word)==true)
				adjective.add(word);
		}
		
		if(line.contains("verb")==true && line.contains("syn")==true){
			String word = getWord(line);
			if(checkProperWord(word)==true)
				verb.add(word);
		}
		
		if(line.contains("adverb")==true && line.contains("syn")==true){
			String word = getWord(line);
			if(checkProperWord(word)==true)
				adverb.add(word);
		}
		
		if(line.contains("noun")==true && line.contains("syn")==true){
			String word = getWord(line);
			if(checkProperWord(word)==true)
				noun.add(word);
		}
	}
	
	public boolean checkProperWord(String word){
		
		for(int i=0;i<word.length();i++){
			if((int)word.charAt(i)>=65 && (int)word.charAt(i)<=90){
				return false;
			}
		}
		return true;
	}
	
	public String getWord(String line) throws IOException{
		
		Scanner s = new Scanner(line);
		s.useDelimiter("syn");
		
		while(s.hasNext()){
			s.next();
			String word = s.next().substring(1);
			wordScore=(int)(getWordScore(word)*10);
				return word;
		}
		
		return "";
	}
	
	public void getScoreValues() throws IOException{
		
		for(int i=0;i<adjective.size();i++){
			adjectiveScore.add((int)(getWordScore(adjective.get(i))*10));
		}
		
		for(int i=0;i<verb.size();i++){
			verbScore.add((int)(getWordScore(verb.get(i))*10));
		}
		for(int i=0;i<noun.size();i++){
			nounScore.add((int)(getWordScore(noun.get(i))*10));
		}
		for(int i=0;i<adverb.size();i++){
			adverbScore.add((int)(getWordScore(adverb.get(i))*10));
		}
		
		
		
		sortArray(adjective,adjectiveScore);
		
		sortArray(verb,verbScore);
		
		sortArray(noun,nounScore);
		
		sortArray(adverb,adverbScore);
		
		System.out.println(word);
		System.out.println(wordScore);
		System.out.println(adjective);
		System.out.println(adjectiveScore);
		System.out.println(verb);
		System.out.println(verbScore);
		System.out.println(noun);
		System.out.println(nounScore);
		System.out.println(adverb);
		System.out.println(adverbScore);
		
		int sumOfScores=0;
		for(int i=0;i<adjectiveScore.size();i++){
		sumOfScores+=adjectiveScore.get(i);
		}
		for(int i=0;i<adverbScore.size();i++){
		sumOfScores+=adverbScore.get(i);
		}
		for(int i=0;i<nounScore.size();i++){
		sumOfScores+=nounScore.get(i);
		}
		for(int i=0;i<verbScore.size();i++){
		sumOfScores+=verbScore.get(i);
		}

		if(sumOfScores==0)
			nullValueCounter++;
		else
			nullValueCounter=0;

		if(nullValueCounter>=10){
		//api key can be changed here
		try(PrintWriter out=new PrintWriter(new BufferedWriter(new FileWriter("databaseFile.txt",true)))){
			out.println("api key changed");
			apiCounter++;
			nullValueCounter=0;
			if(apiCounter>10){
				System.exit(0);
			}
		}catch(IOException e){
			System.out.println("error in appending the database file");
		}
		
		}
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("databaseFile.txt", true)))) {
		    out.println(String.format("%s\t%d\t%s;%s\t%s;%s\t%s;%s\t%s;%s",word,wordScore,adjective,adjectiveScore,adverb,adverbScore,noun,nounScore,verb,verbScore));
		}catch (IOException e) {
		    System.out.println("error in appending the database file");
		}
		
		adjective = new ArrayList<String>();
		noun = new ArrayList<String>();
		verb = new ArrayList<String>();
		adverb = new ArrayList<String>();
		wordScore=0;
		adjectiveScore = new ArrayList<Integer>();
		nounScore = new ArrayList<Integer>();
		verbScore = new ArrayList<Integer>();
		adverbScore = new ArrayList<Integer>();
	}
	
	public void sortArray(ArrayList<String>words,ArrayList<Integer>scores){
		
		int min=0;
		for(int i=0;i<scores.size()-1;i++){
			
			min =i;
			for(int j=i+1;j<scores.size();j++){
				
				if(scores.get(min)>scores.get(j))
					min=j;
			}
			
			int temp= scores.get(i);
			scores.set(i, scores.get(min));
			scores.set(min, temp);
			
			String tem = words.get(i);
			words.set(i, words.get(min));
			words.set(min, tem);
		}
		
	}
	
	public double getWordScore(String word) throws IOException{
		
		word.replaceAll(" ","%20");
		System.setProperty("java.net.useSystemProxies", "true");
		//String url = "http://www.stands4.com/services/v2/defs.php?uid=3554&tokenid=Jlp1QuXhxasXzE6P&word=voyage";
		String url = String.format("http://access.alchemyapi.com/calls/text/TextGetTextSentiment?apikey=%s&sentiment=1&showSourceText=1&text=%s",apiList.get(apiCounter),word );
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");


		final String authUser = "069.3706";
		final String authPassword = "prwiitbhu";
		Authenticator.setDefault(new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(authUser, authPassword
						.toCharArray());
			}
		});
		System.setProperty("http.proxyHost", "10.1.1.16");
		System.setProperty("http.proxyPort", "80");
		System.setProperty("http.proxyUser", authUser);
		System.setProperty("http.proxyPassword", authPassword);
		//System.out.println("\nSending 'GET' request to URL : " + url);

		int responseCode = con.getResponseCode();
		//System.out.println("Response Code : " + responseCode);

		if (responseCode != 404) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;

			double score =0.0;
			while ((inputLine = in.readLine()) != null) {

				//String key = "noun";
				if(inputLine.contains("score")==true){
				score = getScoreForWord(inputLine);
				return score;
				}
			}
			in.close();
		}
		return 0.0;
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
