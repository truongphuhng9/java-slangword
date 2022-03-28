package slangword;

import java.io.*;

public class Main {	
	public static void main(String[] args) throws Exception {
		SlangWordDictManager manager = SlangWordDictManager.getInstance();
		manager.load("./data/slang.txt");
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
			case "5":
				System.exit(0);
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
