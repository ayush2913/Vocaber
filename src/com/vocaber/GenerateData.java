package com.vocaber;

import java.io.*;
import java.util.*;

public class GenerateData {

	String fileName;
	File data;
	private Scanner scn;
	private Formatter fmt;
	ArrayList<String> words = new ArrayList<String>();

	GenerateData(String name) {
		fileName = name;
		scn = null;
	}

	public void readFile() {

		data = new File(fileName);
		if (data.exists() == false)
			System.out.println("The entered file does not exist");
	}

	public void openFile() {

		try {
			scn = new Scanner(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			fmt = new Formatter(new File("/media/DriveE/Ubuntu/data.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void generateDataFile() {

		while (scn.hasNext()) {

			String word = scn.next();

			if (word.length() > 0) {
				word = absoluteWord(word);
				if (word.length() > 0) {

					words.add(word);
				}
			}
		}
		sortData();
		removeRedundancy();
		for (int i = 0; i < words.size(); i++) {
			fmt.format("%s\n", words.get(i));
			System.out.println(words.get(i));
		}

		scn.close();
		fmt.close();
	}

	public String absoluteWord(String word) {

		String temp = "";
		word.trim();
		for (int i = 0; i < word.length(); i++) {

			char c = word.charAt(i);
			if (((int) c >= 65 && (int) c <= 90)
					|| ((int) c >= 97 && (int) c <= 122)) {
				temp = temp + c;
			}
		}
		if (temp.length() > 0) {
			char start = temp.charAt(0);
			if ((int) start >= 65 && (int) start <= 90)
				temp = "";
		}
		return temp;

	}

	public void sortData() {

		int smallest = 0;

		for (int i = 0; i < words.size(); i++) {

			smallest = i;

			for (int j = i + 1; j < words.size(); j++) {

				if (words.get(j).compareTo(words.get(smallest)) < 0)
					smallest = j;
			}

			System.out.println(words.get(smallest));
			String temp1 = words.get(smallest);
			String temp2 = words.get(i);
			words.set(smallest, temp2);
			words.set(i, temp1);

		}
	}

	public void removeRedundancy() {

		for (int i = 0; i < words.size() - 1; i++) {
			if (words.get(i).equalsIgnoreCase(words.get(i + 1)) == true) {

				words.remove(i + 1);
			}
		}
	}

}
