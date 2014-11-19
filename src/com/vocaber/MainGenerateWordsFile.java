package com.vocaber;

public class MainGenerateWordsFile {
	
	public static void main(String args[]){
		
		GenerateWordsFile Ob = new GenerateWordsFile("/media/DriveE/Ubuntu/data.txt");
		Ob.openFile();
		Ob.readFile();
	}

}
