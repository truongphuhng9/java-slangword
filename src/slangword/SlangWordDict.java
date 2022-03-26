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
	private TreeMap<String, List<String>> slangWordDict;
	
	public SlangWordDict() {
		this.slangWordDict = new TreeMap<>();
	}
	
	public void add(SlangWord slangWord) {
		
		this.slangWordDict.put(slangWord.getSlang(), slangWord.getDefinitions());
	}
	
}
