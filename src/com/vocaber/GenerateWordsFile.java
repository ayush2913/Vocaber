package com.vocaber;

import java.io.*;
import java.util.*;

public class GenerateWordsFile {
	
	String inputFileName;
	File inputFile;
	private Scanner scn;
	private Formatter fmt;
	
	GenerateWordsFile(String fileName){
		inputFileName = fileName;
	}
	
	public void openFile(){
		
		inputFile = new File(inputFileName);
		
		try {
			scn = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			fmt = new Formatter(new File("/media/DriveE/Ubuntu/words.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile(){
		
		String lastWord="";
		while(scn.hasNext()){
			
			String word = scn.next();
			if(word.equalsIgnoreCase(lastWord)==false){
				System.out.println(word);
				if(word.length()>4)
				fmt.format("%s\n", word);
				lastWord = word;
			}
		}
		
		scn.close();
		fmt.close();
	}

}
