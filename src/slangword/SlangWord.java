package slangword;

import java.util.*;

public class SlangWord {
	private String slang;
	private ArrayList<String> definitions;
	
	public SlangWord() {
		this.slang = "";
		this.definitions = new ArrayList<String>();
	}
	
	public SlangWord(String record) {
		/* 
		 * # Concept
		 * The record of slang word has this structure:
		 * <Slangword>`<Definition1>|[space]<Definition2>|... 
		 * 
		 * # Reference 
		 * Split string => https://stackoverflow.com/questions/3481828/how-to-split-a-string-in-java
		 * String[] to ArrayList => https://stackoverflow.com/questions/7347856/how-to-convert-a-string-into-an-arraylist
		 * Find substring => 
		*/
		String[] parts = record.split("`");
		this.slang = parts[0];
		this.definitions = new ArrayList<>(Arrays.asList(parts[1].split("| ")));
	}
	
	public SlangWord(String slang, List<String> definitions) {
		this.slang = slang;
		this.definitions = new ArrayList<>(definitions);
	}
	
	public String getSlang() {
		return this.slang;
	}
	
	public void setSlang(String slang) {
		this.slang = slang;
	}
	
	public ArrayList<String> getDefinitions() {
		return new ArrayList<String>(this.definitions);
	}
	
	public void setDefinitions(ArrayList<String> definitions) {
		this.definitions = new ArrayList<>(definitions);
	}
	
	public boolean isMatchDefinition(String matchString) {
		for (String definition : this.definitions) {
			if (definition.contains(matchString)) {
				return true;
			}
		}
		return false;
	}
	public String toString() {
		String result = this.slang + "=>";
		for (String definition : definitions) {
			result += "- ";
			result += definition;
			result += '\n';
		}
		return result;
	}
}