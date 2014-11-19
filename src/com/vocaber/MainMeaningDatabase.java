package com.vocaber;

public class MainMeaningDatabase {
	
	public static void main(String args[]){
		
		MeaningDatabase Ob = new MeaningDatabase();
		Ob.openCreateDatabase();
		//Ob.createTable();
		//Ob.enterValuesinTable("/home/prabal/Downloads/TEMP-E20140819.tsv");
		Ob.getMeaning();
	}

}
