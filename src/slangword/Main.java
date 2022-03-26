package slangword;

import java.io.*;

public class Main {	
	public static void main(String[] args) throws Exception {
		SlangWordDict slangWordDict = new SlangWordDict();
		
		System.out.println("Current Directory = " + System.getProperty("user.dir"));
		System.out.println("Your slang DB path: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String file = reader.readLine();		
		BufferedReader br = new BufferedReader(new FileReader(file));
		// Header not used
		br.readLine();
		// Records
		System.out.println("Loading data...");
		while (true) {
			String row = br.readLine();
			if (row == null) break;
			SlangWord slangWord = new SlangWord(row);
			slangWordDict.add(slangWord);			
		}
		System.out.println("Data loaded!");
		br.close();
		reader.close();
	}
}
