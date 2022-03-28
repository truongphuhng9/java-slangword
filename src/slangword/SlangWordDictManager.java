package slangword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SlangWordDictManager {
	private static SlangWordDictManager instance = null;
	private SlangWordDict slangWordDict;
	private SlangWordDict backupSlangWordDict;
	private HashMap<Date, ArrayList<SlangWord>> searchSlangHistory;
	
	private SlangWordDictManager() {
		this.slangWordDict = new SlangWordDict();
		this.backupSlangWordDict = new SlangWordDict();
		this.searchSlangHistory = new HashMap<>();
	}
	public static SlangWordDictManager getInstance() {
		if (instance == null) {
			instance = new SlangWordDictManager();
		}
		return instance;
	}
	public void load(String filename) throws Exception {
		//System.out.println("Current Directory = " + System.getProperty("user.dir"));
		//System.out.println("Your slang DB path: ");
		//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		//String filename = reader.readLine();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		// Header not used
		br.readLine();
		while (true) {
			String row = br.readLine();
			if (row == null) break;
			SlangWord slangWord = new SlangWord(row);
			slangWordDict.add(slangWord);
			backupSlangWordDict.add(slangWord);			
		}
		br.close();
	}	
	
	public void addSlangWord() {
		do {
			try {
				System.out.println("Enter the word to be added: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String word = br.readLine();
				ArrayList<String> definitions = new ArrayList<>();
				do {
					System.out.println("Enter the definition of the word: ");
					String definition = br.readLine();
					definitions.add(definition);
					System.out.println("Do you want to add another definition? (y/n)");
					String answer = br.readLine();
					if (answer.equals("n")) break;
				} while (true);
			
				int status = slangWordDict.add(word, definitions);
				if (status == -1) {
					System.out.println("This word has already existed");
				} else if (status == 0) {
					System.out.println("The word has been added successfully");
				}
				System.out.println("Do you want to add another slang word? (y/n)");
				String answer = br.readLine();
				if (answer.equals("n")) break;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		} while (true);
	}
	
	public void searchBySlangWord() {
		System.out.println("Enter the slang word to be searched: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String slang = null;
		try {
			slang = br.readLine();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		Date date = new Date();
		SlangWord slangWord = slangWordDict.searchBySlang(slang);
		if (slangWord == null) {
			System.out.println("The word is not found");
		} else {
			System.out.println("The word is found");
			System.out.println(slangWord);
			this.recordHistory(date, slangWord);
		}
	}

	public void searchByDefinition() {
		System.out.println("Enter the definition to be searched: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String definition = null;
		try {
			definition = br.readLine();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		Date date = new Date();
		ArrayList<SlangWord> slangWords = slangWordDict.searchByDefinition(definition);
		if (slangWords.size() == 0) {
			System.out.println("The definition is not found");
		} else {
			System.out.println("The definition is found");
			for (SlangWord slangWord : slangWords) {
				System.out.println(slangWord);
				this.recordHistory(date, slangWord);
			}
		}
	}

	public void showHistory() {
		for (Map.Entry<Date, ArrayList<SlangWord>> entry : this.searchSlangHistory.entrySet()) {
			System.out.println("-------Date " + entry.getKey() + "-------");
			for (SlangWord slangWord : entry.getValue()) {
				System.out.println(slangWord);
			}
		}
	}

	public void recordHistory(Date date, SlangWord slangWord) {
		if (searchSlangHistory == null) {
			searchSlangHistory = new HashMap<>();
		}
		if (searchSlangHistory.containsKey(date)) {
			searchSlangHistory.get(date).add(slangWord);
		} else {
			ArrayList<SlangWord> slangWords = new ArrayList<>();
			slangWords.add(slangWord);
			searchSlangHistory.put(date, slangWords);
		}
	}

	public void printMenu() {
		System.out.println("1. Search by slang word");
		System.out.println("2. Search by definition");
		System.out.println("3. History");
		System.out.println("4. Add a new slang word");
		System.out.println("5. Exit");
	}
}
