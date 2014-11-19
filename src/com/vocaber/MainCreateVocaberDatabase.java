package com.vocaber;

import java.util.ArrayList;

public class MainCreateVocaberDatabase {
	
	public static void main(String args[]){
		
		String inputFilePath="/media/DriveE/Ubuntu/words.txt";
		ArrayList<String> alchemyApiList = new ArrayList<String>();
		
		
		alchemyApiList.add("5e75bf2c1c3f6fb0b2fa68c280b0ac139078fc90");
		alchemyApiList.add("9f69bde5a286b62436ca41825289ce16b095c3c7");
		alchemyApiList.add("32873d7ebd57d3cbf824061741af7af5481724fc");	
		alchemyApiList.add("17f5d19b93a243f2550f581be5c0b74b4a05940e");
		alchemyApiList.add("ab076a07a58500bfd109bb5ef49e62be6082cabf");
		alchemyApiList.add("786c0a822c7585f4be1ec60ef53326de5093756e");
		alchemyApiList.add("bf7818c89cb9ead54be17067e96613bfd8d79aae");
		alchemyApiList.add("bf8f5b9cfc04c2bce3a1d678379b4e688bc3403c");
		alchemyApiList.add("1f519440c718c6d165aa2ca7663b6d407de1a24b");
		alchemyApiList.add("11287c6c118f2f6bbf44a7e68b8a91afb0418758");
		alchemyApiList.add("291a67f1d2808220d468e3d9bd9a2f6864cd1289");
		
		String bigHugeLabApi="b692964a96b3e4d894852125fd8ea079";
		
		CreateVocaberDatabase Ob = new CreateVocaberDatabase(inputFilePath,bigHugeLabApi,alchemyApiList);
		Ob.getWords();
	}

}
