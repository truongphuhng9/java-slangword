package slangword;

import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		System.out.println("Your slang DB path: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String file = reader.readLine();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String firstRow = br.readLine();
		System.out.println("First row: " + firstRow);
		br.close();
		reader.close();
	}
}
