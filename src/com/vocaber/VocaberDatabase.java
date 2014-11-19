package com.vocaber;

import java.sql.*;
import java.util.*;
import java.io.*;

public class VocaberDatabase{

	Scanner scn;
	String inputDatabaseFileName;
	Connection c;
	Statement stmt;
	VocaberDatabase(String inputFileName){
	
	scn=null;
	inputDatabaseFileName=inputFileName;
	c=null;
	stmt=null;
	}

	public void openDatabaseFile(){
	
		try{
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:Vocaber.db");
		}catch(Exception e){
			System.out.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened Vocaber database successfully");
	}
	
	public void createTable(){
		
		try{
			
			stmt = c.createStatement();
			String sql="CREATE TABLE WORDS"+
						"(WORD	TEXT	PRIMARY KEY	NOT NULL,"+
						"SCORE	INT	NOT NULL,"+
						"ADJECTIVE	TEXT	,"+
						"ADJECTIVE_SCORE	TEXT	,"+
						"ADVERB	TEXT	,"+
						"ADVERB_SCORE	TEXT	,"+
						"NOUN	TEXT	,"+
						"NOUN_SCORE	TEXT	,"+
						"VERB	TEXT	,"+
						"VERB_SCORE	TEXT	)";
			stmt.execute(sql);
			stmt.close();
		}catch(Exception e){
			System.err.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
	
	public void enterValuesInTable() throws FileNotFoundException{
		
		scn = new Scanner(new File(inputDatabaseFileName));
		scn.useDelimiter("\t|;|\n");
		try{
			
			stmt=c.createStatement();
			c.setAutoCommit(false);
			while(scn.hasNext()){
				String word = scn.next();
				int wordScore=scn.nextInt();
				String adjective=scn.next();
				String adjectiveScore=scn.next();
				String adverb=scn.next();
				String adverbScore=scn.next();
				String noun=scn.next();
				String nounScore=scn.next();
				String verb=scn.next();
				String verbScore=scn.next();
				String sql=String.format("INSERT INTO WORDS (WORD,SCORE,ADJECTIVE,ADJECTIVE_SCORE,ADVERB,ADVERB_SCORE,NOUN,NOUN_SCORE,VERB,VERB_SCORE)"+
				"VALUES ('%s',%d,'%s','%s','%s','%s','%s','%s','%s','%s');",word,wordScore,adjective,adjectiveScore,adverb,adverbScore,noun,nounScore,verb,verbScore);
				try{
				stmt.execute(sql);
				}catch(Exception e){
					
				}
				System.out.println(word);
			}
			stmt.close();
			c.commit();
			c.close();
			
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
		
	}
	
	public void printTable(){
		
		try{
			
			stmt=c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM WORDS;");
			
			while(rs.next()){
				
				String word = rs.getString("WORD");
				int wordScore=rs.getInt("SCORE");
				String adjective=rs.getString("ADJECTIVE");
				String adverb = rs.getString("ADVERB");
				String noun=rs.getString("NOUN");
				String verb=rs.getString("VERB");
				
				System.out.println(word+":"+wordScore+":"+adjective+":"+adverb+":"+noun+":"+verb);
			}
			rs.close();
			stmt.close();c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void selectValueFromTable(String searchWord){
		
		try{
			
			stmt=c.createStatement();
			ResultSet rs=stmt.executeQuery(String.format("SELECT * FROM WORDS where WORD='%s';",searchWord));
			
			while(rs.next()){
				
				int score=rs.getInt("SCORE");
				String adjective=rs.getString("ADJECTIVE");
				String adverb=rs.getString("ADVERB");
				String noun=rs.getString("NOUN");
				String verb=rs.getString("VERB");
				
				System.out.println(score+":"+adjective+":"+adverb+":"+noun+":"+verb);
				
			}
			rs.close();
			stmt.close();
			c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}