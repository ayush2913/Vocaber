package com.vocaber;

public class MainGenerateData {
	
	public static void main(String args[]){
		
		GenerateData Ob = new GenerateData("/media/DriveE/Ubuntu/dataset");
		
		Ob.readFile();
		Ob.openFile();
		Ob.generateDataFile();
	}

}
