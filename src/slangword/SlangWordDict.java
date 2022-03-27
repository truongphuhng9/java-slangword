package slangword;

import java.util.*;

public class SlangWordDict {
	/*
	 * Tree Map data structure for fast traverse and order
	 * ?WHY TREE MAP: Because it maintain ORDER.
	 * 
	 * Reference: 
	 * TreeMap definition and methods: https://www.javatpoint.com/java-treemap
	 * Find substring
	 * */
	private TreeMap<String, ArrayList<String>> slangWordDict;
	
	public SlangWordDict() {
		this.slangWordDict = new TreeMap<>();
	}
	
	public int add(String slang, ArrayList<String> definitions) {
		if (this.containsKey(slang)) {
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
		if (this.slangWordDict.containsKey(slang)) {
			ArrayList<String> defs = this.slangWordDict.get(slang);
			return new SlangWord(slang, defs);
		}
		return null;
	}

	public ArrayList<SlangWord> searchByDefinition(String matchString) {
		ArrayList<SlangWord> slangWordList = new ArrayList<>();
		for (String slang : this.slangWordDict.keySet()) {
			ArrayList<String> defs = this.slangWordDict.get(slang);
			for (String def : defs) {
				if (def.contains(matchString)) {
					slangWordList.add(new SlangWord(slang, defs));
				}
			}
		}
		return slangWordList;
	}
}
