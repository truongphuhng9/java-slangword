package slangword;

import java.io.*;

public class Main {	
	public static void main(String[] args) throws Exception {
		SlangWordDictManager manager = SlangWordDictManager.getInstance();
		manager.load();
		while (true) {
			manager.printMenu();
			System.out.println("Your option: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String option = br.readLine();
			switch (option) {
			case "1":
				manager.searchBySlangWord();
				break;
			case "2":
				manager.searchByDefinition();
				break;
			case "3":
				manager.showHistory();
				break;
			case "4":
				manager.addSlangWord();
				break;
			case "5":
				String editOption = "";
				while (!editOption.equals("4")) {
					System.out.println("Choose edit option: ");
					System.out.println("1. Renew a word");
					System.out.println("2. Add definitions");
					System.out.println("3. Edit a definition");
					System.out.println("4. Backto main menu");
					
					editOption = br.readLine();
					switch (editOption) {
					case "1":
						manager.replaceAllDefinition();
						break;
					case "2":
						manager.addDefinitions();
						break;
					case "3":
						manager.editSpecifiedDefinition();
						break;
					case "4":
						break;
					default:
						System.out.println("Invalid option!");
						break;
					}
				}				
				break;
			case "6":
				manager.removeSlangWord();
				break;
			case "7":
				manager.resetToDefault();
				break;
			case "8":
				manager.randomWordOfTheDay();
				break;
			case "9":
				manager.quizByWord();
				break;
			case "10":
				manager.quizByDefinition();
				break;
			case "11":
				manager.exit();
				break;
			default:
				System.out.println("Invalid option");
				break;
			}
			System.out.println("Press enter to continue...");
			// read enter key (/r/n)
			// https://stackoverflow.com/questions/26184409/java-console-prompt-for-enter-input-before-moving-on
			try {
            	int read = System.in.read(new byte[2]);
        	} catch (IOException e) {
            	e.printStackTrace();
        	}
			// Clear screen
			// https://www.javatpoint.com/how-to-clear-screen-in-java
			System.out.print("\033[H\033[2J");  
			System.out.flush();
		}
	}
}
