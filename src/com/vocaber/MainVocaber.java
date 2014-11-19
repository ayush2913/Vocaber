package com.vocaber;

import java.io.IOException;
import java.sql.SQLException;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class MainVocaber {
	
	String inputString;
	String parsedString;
	String suggestions;
	MainVocaber(String str){
		inputString=str;
		parsedString="";
		suggestions="";
	}
	public void getparsedString() throws ClassNotFoundException, IOException, SQLException{
		
		MaxentTagger tagger = new MaxentTagger(
                "taggers/bidirectional-distsim-wsj-0-18.tagger");
 
		parsedString = tagger.tagString(inputString);
		SearchForWords search = new SearchForWords(parsedString);
		search.searchInDB();
		suggestions=search.allSuggestions;
	}

}
