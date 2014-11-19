package com.vocaber;

import java.io.FileNotFoundException;

public class MainVocaberDatabase{
	
	public static void main(String args[]) throws FileNotFoundException{
	
		VocaberDatabase Ob = new VocaberDatabase("databaseFile.txt");
		Ob.openDatabaseFile();
		//Ob.createTable();
		//Ob.enterValuesInTable();
		//Ob.printTable();
		Ob.selectValueFromTable("eager");
	}
}