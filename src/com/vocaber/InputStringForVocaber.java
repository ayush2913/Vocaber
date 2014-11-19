package com.vocaber;

import java.io.IOException;
import java.util.Scanner;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;
 
public class InputStringForVocaber {
	
	String inputString;
	String outputString;
	InputStringForVocaber(String str){
		inputString=str;
		outputString="";
	}
	public void enterString() throws IOException,
            ClassNotFoundException {
 
        MaxentTagger tagger = new MaxentTagger(
                "taggers/bidirectional-distsim-wsj-0-18.tagger");
 
        outputString = tagger.tagString(inputString);
    }
}
