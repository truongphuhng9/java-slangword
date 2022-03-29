package slangword;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SlangWordDictManager {
	private static SlangWordDictManager instance = null;
	private SlangWordDict slangWordDict;
	private SlangWordDict defaultSlangWordDict;
	private HashMap<Date, ArrayList<SlangWord>> searchSlangHistory;

	private SlangWordDictManager() {
		this.slangWordDict = new SlangWordDict();
		this.defaultSlangWordDict = new SlangWordDict();
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
			defaultSlangWordDict.add(slangWord);
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
				if (slangWordDict.containsKey(word)) {
					System.out.println("The word already exists!");
					return;
				}
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

	public void replaceAllDefinition() {
		do {
			try {
				System.out.println("Enter the word to be edited: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String word = br.readLine();
				ArrayList<String> definitions = new ArrayList<>();
				if (slangWordDict.containsKey(word)) {
					do {
						SlangWord slangWord = this.slangWordDict.get(word);
						System.out.println(slangWord);
						System.out.println("Enter the new definition of the word: ");
						String definition = br.readLine();
						definitions.add(definition);
						System.out.println("Do you want to add another definition? (y/n)");
						String answer = br.readLine();
						if (answer.equals("n")) break;
					} while (true);
					slangWordDict.replaceAllDefinition(word, definitions);
					System.out.println("The word has been edited successfully");
				} else {
					System.out.println("The word does not exist");
				}
				
				System.out.println("Do you want to edit another slang word? (y/n)");
				String answer = br.readLine();
				if (answer.equals("n")) break;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		} while (true);
	}

	public void addDefinitions() {
		do {
			try {
				System.out.println("Enter the word to be edited: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String word = br.readLine();
				ArrayList<String> definitions = new ArrayList<>();
				if (slangWordDict.containsKey(word)) {
					do {
						SlangWord slangWord = this.slangWordDict.get(word);
						System.out.println(slangWord);
						System.out.println("Enter the new definition of the word: ");
						String definition = br.readLine();
						definitions.add(definition);
						System.out.println("Do you want to add another definition? (y/n)");
						String answer = br.readLine();
						if (answer.equals("n")) break;
					} while (true);
					slangWordDict.addDefinitions(word, definitions);
					System.out.println("The word has been edited successfully");
				} else {
					System.out.println("The word does not exist");
				}
			
				System.out.println("Do you want to edit another slang word? (y/n)");
				String answer = br.readLine();
				if (answer.equals("n")) break;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		} while (true);
	}

	public void editSpecifiedDefinition() {
		do {
			try {
				System.out.println("Enter the word to be edited: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String word = br.readLine();
				if (slangWordDict.containsKey(word)) {
					SlangWord slangWord = this.slangWordDict.get(word);
					System.out.println(slangWord);
					System.out.println("Enter the index of the definition to be edited: ");
					BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
					int index = Integer.parseInt(br2.readLine());
					System.out.println("Enter the new definition of the word: ");
					String definition = br.readLine();
					slangWordDict.editSpecifiedDefinition(word, index, definition);
					System.out.println("The word has been edited successfully");
				} else {
					System.out.println("The word does not exist");
				}
				System.out.println("Do you want to edit another slang word? (y/n)");
				String answer = br.readLine();
				if (answer.equals("n")) break;
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		} while (true);
	}

	public void removeSlangWord() {
		do {
			try {
				System.out.println("Enter the word to be removed: ");
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String word = br.readLine();
				if (slangWordDict.containsKey(word)) {
					// Confirm removal
					System.out.println("Are you sure you want to remove the word? (y/n)");
					String answer = br.readLine();
					if (answer.equals("y")) {
						slangWordDict.remove(word);
						System.out.println("The word has been removed successfully");
					} else {
						System.out.println("The word has not been removed");
					}
				} else {
					System.out.println("The word does not exist");
				}
				System.out.println("Do you want to remove another slang word? (y/n)");
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
		SlangWord slangWord = this.slangWordDict.get(slang);
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

		ArrayList<SlangWord> slangWords = slangWordDict.getByDefinition(definition);
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
				System.out.println("- " + slangWord);
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

	public void resetToDefault() {
		this.slangWordDict = this.defaultSlangWordDict.deepClone();
		System.out.println("The dictionary has been reset to default");
	}

	public void randomWordOfTheDay() {
		SlangWord slangWord = this.getRandomSlangWord();
		System.out.println("The word of the day: ");
		System.out.println(slangWord);
	}

	public SlangWord getRandomSlangWord() {
		Random random = new Random();
		int index = random.nextInt(this.slangWordDict.size());
		ArrayList<String> words = new ArrayList<>(this.slangWordDict.keySet());
		if (words.size() == 0) {
			return null;
		} else {
			String word = words.get(index);
			return this.slangWordDict.get(word);
		}
	}

	public void saveToFile(String fileName) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write("Slang`Meaning");
			for (Map.Entry<String, ArrayList<String>> entry : this.slangWordDict.entrySet()) {
				SlangWord slangWord = new SlangWord(entry.getKey(), entry.getValue());
				bw.write(slangWord.toString());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public void quizByWord() {
		SlangWord question = this.getRandomSlangWord();
		ArrayList<String> answers = new ArrayList<>();

		// Generate the question and 4 answers
		if (question == null) {
			System.out.println("The dictionary is empty");
		} else {
			for (int i = 0; i < 3; ++i) {
				SlangWord randomSlangWord = this.getRandomSlangWord();
				if (question.getSlang().equals(randomSlangWord.getSlang())) {
					--i;
				} else {
					ArrayList<String> definitions = randomSlangWord.getDefinitions();
					int index = new Random().nextInt(definitions.size());
					answers.add(definitions.get(index));
				}
			}
			ArrayList<String> definitions = question.getDefinitions();
			int index = new Random().nextInt(definitions.size());
			answers.add(definitions.get(index));
			Collections.shuffle(answers);

			// Find the index of correct answer
			int correctIndex = 0;
			for (int i = 0; i < answers.size(); ++i) {
				if (question.containsDefinition(answers.get(i))) {
					correctIndex = i;
					break;
				}
			}

			System.out.println("The word: " + question.getSlang());
			System.out.println("What is the correct definition?");
			for (int i = 0; i < answers.size(); ++i) {
				System.out.println("(" + (i + 1) + ") " + answers.get(i));
			}
			System.out.println("Enter your answer: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int yourAnswer;

			do {
				try {
					yourAnswer = Integer.parseInt(br.readLine());
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					yourAnswer = -1;
				}
			} while (yourAnswer < 1 || yourAnswer > 4);
			if (yourAnswer == correctIndex + 1) {
				System.out.println("Correct!");
			} else {
				System.out.println("Wrong! The correct answer is: " + answers.get(correctIndex));
			}
		}
	}

	public void quizByDefinition() {
		SlangWord question = this.getRandomSlangWord();
		ArrayList<String> answers = new ArrayList<>();

		// Generate the question and 4 answers
		if (question == null) {
			System.out.println("The dictionary is empty");
		} else {
			for (int i = 0; i < 3; ++i) {
				SlangWord randomSlangWord = this.getRandomSlangWord();
				if (question.getSlang().equals(randomSlangWord.getSlang())) {
					--i;
				} else {
					answers.add(randomSlangWord.getSlang());
				}
			}
			answers.add(question.getSlang());
			Collections.shuffle(answers);

			// Find the index of correct answer
			int correctIndex = 0;
			for (int i = 0; i < answers.size(); ++i) {
				if (question.getSlang().equals(answers.get(i))) {
					correctIndex = i;
					break;
				}
			}

			System.out.println("The definition: " + question.getDefinitions().get(0));
			System.out.println("What is the correct word?");
			for (int i = 0; i < answers.size(); ++i) {
				System.out.println("(" + (i + 1) + ") " + answers.get(i));
			}
			System.out.println("Enter your answer: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int yourAnswer;

			do {
				try {
					yourAnswer = Integer.parseInt(br.readLine());
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
					yourAnswer = -1;
				}
			} while (yourAnswer < 1 || yourAnswer > 4);
			if (yourAnswer == correctIndex + 1) {
				System.out.println("Correct!");
			} else {
				System.out.println("Wrong! The correct answer is: " + answers.get(correctIndex));
			}
		}
	}

	public void printMenu() {
		System.out.println("1. Search by slang word");
		System.out.println("2. Search by definition");
		System.out.println("3. History");
		System.out.println("4. Add a new slang word");
		System.out.println("5. Edit a slang word");
		System.out.println("6. Remove a slang word");
		System.out.println("7. Reset to default");
		System.out.println("8. Random word");
		System.out.println("9. Quiz by word");
		System.out.println("10. Quiz by definition");
		System.out.println("11. Exit");
	}

	public void exit() {
		System.out.println("Bye!");
		System.exit(0);
	}
}
