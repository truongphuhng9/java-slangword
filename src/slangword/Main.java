package slangword;

import java.io.*;

public class Main {	
	public static void main(String[] args) throws Exception {
		SlangWordDictManager manager = SlangWordDictManager.getInstance();
		manager.load("slang.txt");
		manager.addSlangWord();
	}
}
