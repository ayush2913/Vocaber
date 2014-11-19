package com.vocaber;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class MeaningDatabase {
	
	Connection c;
	Statement stmt;
	
	MeaningDatabase(){
		
		c = null;
		stmt = null;
		
	}
	
	public void openCreateDatabase(){
		
		
		
		try{
			
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:wordMeanings.db");
		}catch(Exception e){
			
			System.err.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened Database Successfully");
		
	}
	
	public void createTable(){
		
		try{
			stmt = c.createStatement();
			
			String sql = "CREATE	TABLE	MEANINGS"+
						"(WORD	TEXT	PRIMARY KEY 	NOT NULL,"+
						"POS	TEXT 					NOT NULL,"+
						"CONTEXT	TEXT				NOT NULL)";
			
			stmt.executeUpdate(sql);
			stmt.close();
			
		}catch(Exception e){
			
			System.err.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
		}
		
		System.out.println("TABLE CREATED SUCCESSFULLY");
		
		
	}
	
	public void enterValuesinTable(String inputFileName){
		
		File inputDataFile = new File(inputFileName);
		Scanner scn = null;
		
		try {
			scn = new Scanner(inputDataFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		scn.useDelimiter("\t|\n");
		
		try{
			
			stmt = c.createStatement();
			c.setAutoCommit(false);
			String lastWord="";
			String fullContext="";
			while(scn.hasNext()){
				
			scn.next();
			
				String word= scn.next();
				String POS = scn.next();
				
				boolean flag = false;
				for(int i=0;i<word.length();i++){
					if((int)word.charAt(i)<97 || (int)word.charAt(i)>122)
						flag=true;
				}
				if(flag==true)
					continue;
				String context = "$ "+POS+" "+scn.next();
				
				if(word.equalsIgnoreCase(lastWord)==true){
					fullContext = fullContext+"; "+context;
					String sql = String.format("UPDATE MEANINGS set CONTEXT= '%s' where WORD='%s'", fullContext,word);
					stmt.executeUpdate(sql);
					lastWord = word;
					continue;
				}
				else{
					fullContext="";
				}
				
				String sql = String.format("INSERT INTO MEANINGS (WORD,POS,CONTEXT) "
							+"VALUES ('%s','%s','%s');",word,POS,context);
				
				System.out.println(word+"-->"+POS+"----->"+context);
				lastWord = word;
				stmt.executeUpdate(sql);
			}
			
			
			stmt.close();
			c.commit();
			
			System.out.println("VALUES ENTERED SUCCESSFULLY");
			
		}catch(Exception e){
			
			System.err.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
		}
		
	}
	
	public void getMeaning(){
		
		try{
		stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT aac FROM MEANINGS;");
		
		while(rs.next()){
			
			String word = rs.getString("WORD");
			String POS = rs.getString("POS");
			String context = rs.getString("CONTEXT");
			
			System.out.println();
			System.out.println();
			System.out.println(word);
			System.out.println(POS);
			System.out.println(context);
			System.out.println();
			System.out.println("QUERY SEARCHED SUCCESSFULLY");
		}
		
		rs.close();
		stmt.close();
		c.close();
		
		}catch(Exception e){
			
			System.err.println(e.getClass().getName()+":"+e.getMessage());
			System.exit(0);
			
		}
	}

}
