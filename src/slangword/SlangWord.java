package slangword;

import java.util.*;

public class SlangWord {
	private String slang;
	private List<String> definitions;
	
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
		 * Regular expresstion Split => https://javarevisited.blogspot.com/2016/02/2-ways-to-split-string-with-dot-in-java-using-regular-expression.html#axzz7OhxNaC52
		*/
		String[] parts = record.split("`");
		this.slang = parts[0];
		if (parts[1].contains("|")) {
			this.definitions = new ArrayList<String>(Arrays.asList(parts[1].toLowerCase().split("\\|")));
		} else {
			this.definitions = new ArrayList<>();
			definitions.add(parts[1].toLowerCase());
		}		
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
		StringBuilder sb = new StringBuilder();
		sb.append(this.slang);
		sb.append("`");
		for (int i = 0; i < this.definitions.size(); i++) {
			sb.append(this.definitions.get(i));
			if (i != this.definitions.size() - 1) {
				sb.append("|");
			}
		}
		return sb.toString();
	}
}