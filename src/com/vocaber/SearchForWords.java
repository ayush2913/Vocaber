package com.vocaber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchForWords {

	String parsedString;
	ArrayList<ArrayList<String>> selectedWords;
	Connection c;
	Statement stmt;
	String allSuggestions;

	SearchForWords(String str) {
		allSuggestions="";
		parsedString = str;
		selectedWords = new ArrayList<ArrayList<String>>();
	}

	public void searchInDB() throws SQLException {

		ArrayList<String> selected = new ArrayList<String>();
		Scanner scn = new Scanner(parsedString);
		scn.useDelimiter("/|\\s");
		while (scn.hasNext()) {
			String word = scn.next();
			String pos = scn.next();

			selected = new ArrayList<String>();
			if (pos.equalsIgnoreCase("NN") == true) {
				selected.add(word);
				selected.add("NOUN");
			} else if (pos.equalsIgnoreCase("VBG") == true
					|| pos.equalsIgnoreCase("VBN") == true
					|| pos.equalsIgnoreCase("VB")==true) {
				selected.add(word);
				selected.add("VERB");
			} else if (pos.equalsIgnoreCase("JJ") == true) {
				selected.add(word);
				selected.add("ADJECTIVE");
			} else if (pos.equalsIgnoreCase("RB") == true) {
				selected.add(word);
				selected.add("ADVERB");
			}

			if (selected.isEmpty() == false) {
				selectedWords.add(selected);
			}
		}
		if (selectedWords.isEmpty() == false) {
			allSuggestions=getSuggestions();
		}
	}

	public String getSuggestions() throws SQLException {

		String str="";
		System.out.println(selectedWords);

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Vocaber.db");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ":" + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened Vocaber database successfully");

		for (int i = 0; i < selectedWords.size(); i++) {
			try {

				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(String.format(
						"SELECT * FROM WORDS where WORD='%s';", selectedWords.get(i).get(0)));
				String pos=selectedWords.get(i).get(1);
				String posScore=String.format("%s_SCORE", pos);

				while (rs.next()) {

					int score = rs.getInt("SCORE");
					String suggestions=rs.getString(pos);
					String suggestionScores=rs.getString(posScore);
					
					System.out.println(selectedWords.get(i).get(0)+":"+score+":"+suggestions+":"+suggestionScores);
					str=String.format("%s\n%s: %s",str,selectedWords.get(i).get(0),suggestions);
				}
				rs.close();
				stmt.close();
				//c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		c.close();
		return str;
	}
}
