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
	
	public int add(String slang, ArrayList<String> definitions) {
		if (this.containsKey(slangWord.getSlang())) {
			System.out.println("This word has already existed");
			return -1;
		}
		this.slangWordDict.put(slang, definitions);
		return 0;
	}
	
	public int add(SlangWord slangWord) {
		if (this.containsKey(slangWord.getSlang())) {
			return -1;
		}
		this.slangWordDict.put(slangWord.getSlang(), slangWord.getDefinitions());
		return 0;
	}

	public boolean containsKey(String slang) {
		return this.slangWordDict.containsKey(slang);
	}
	
	public SlangWord searchBySlang(String slang) {
		ArrayList<String> defs = this.slangWordDict.get(slang);
		return new SlangWord(slang, defs);
	}
}
