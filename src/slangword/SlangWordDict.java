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

	public SlangWord get(String slang) {
		if (this.slangWordDict.containsKey(slang)) {
			return new SlangWord(slang, this.slangWordDict.get(slang));
		}
		return null;
	}

	public int size() {
		return this.slangWordDict.size();
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

	public int replaceAllDefinition(String slang, ArrayList<String> definitions) {
		if (!this.containsKey(slang)) {
			return -1;
		}
		this.slangWordDict.put(slang, definitions);
		return 0;
	}

	public int addDefinitions(String slang, ArrayList<String> definitions) {
		if (!this.containsKey(slang)) {
			System.out.println("This word has not existed");
			return -1;
		}
		this.slangWordDict.get(slang).addAll(definitions);
		return 0;
	}

	public int editSpecifiedDefinition(String slang, int index, String definition) {
		if (!this.containsKey(slang)) {
			System.out.println("This word has not existed");
			return -1;
		}
		ArrayList<String> definitions = this.slangWordDict.get(slang);
		definitions.set(index, definition);
		this.slangWordDict.put(slang, definitions);
		return 0;
	}

	public int remove(String slang) {
		if (!this.containsKey(slang)) {
			return -1;
		}
		this.slangWordDict.remove(slang);
		return 0;
	}

	public boolean containsKey(String slang) {
		return this.slangWordDict.containsKey(slang);
	}

	public ArrayList<SlangWord> getByDefinition(String matchString) {
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

	public Set<Map.Entry<String, ArrayList<String>>> entrySet() {
		return this.slangWordDict.entrySet();
	}

	public Set<String> keySet() {
		return this.slangWordDict.keySet();
	}
	
	public SlangWordDict deepClone() {
		SlangWordDict newDict = new SlangWordDict();
		for (String slang : this.slangWordDict.keySet()) {
			ArrayList<String> defs = this.slangWordDict.get(slang);
			newDict.add(slang, defs);
		}
		return newDict;
	}
}
