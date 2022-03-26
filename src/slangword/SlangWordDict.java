package slangword;

import java.util.*;

public class SlangWordDict {
	/*
	 * Tree Map data structure for fast traverse and order
	 * ?WHY TREE MAP: Because it maintain ORDER.
	 * 
	 * Reference: 
	 * TreeMap definition and methods: https://www.javatpoint.com/java-treemap
	 * */
	private TreeMap<String, ArrayList<String>> slangWordDict;
	
	public SlangWordDict() {
		this.slangWordDict = new TreeMap<>();
	}
	
	public void add(String slang, ArrayList<String> definitions) {
		if (this.slangWordDict.containsKey(slang)) {
			System.out.println("This word has already existed");
			return;
		}
		this.slangWordDict.put(slang, definitions);
	}
	
	public void add(SlangWord slangWord) {
		if (this.slangWordDict.containsKey(slangWord.getSlang())) {
			System.out.println("This word has already existed");
			return;
		}
		this.slangWordDict.put(slangWord.getSlang(), slangWord.getDefinitions());
	}
	
	public SlangWord searchBySlang(String slang) {
		ArrayList<String> defs = this.slangWordDict.get(slang);
		return new SlangWord(slang, defs);
	}
}
