package hr.atos.praksa.hrvojeskrbina.zadatak12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Source {

	public static void main(String[] args) throws IOException {
		String path = getPath();
		countWords(path);
	}

	private static String getPath() {
		Scanner input = new Scanner(System.in);
		String path = "";
		boolean isValid = false;
		do {
			System.out.print("Unesite putanju: ");
			path = input.next();
			isValid = isValidPath(path);
		} while (!isValid);
		input.close();
		return path;
	}

	private static boolean isValidPath(String path) {
		File file = new File(path);
		return file.isFile();
	}

	private static void countWords(String path) throws IOException {
		String readLine;
		int wordCounter = 0;
		Map<String, Integer> wordsMap = new TreeMap<String, Integer>();
		BufferedReader buffReader = new BufferedReader(new FileReader(path));

		while((readLine = buffReader.readLine()) != null) {
			String[] wordsArray = readLine.split("\\s+");
			for (String word : wordsArray) {
				word = word.toLowerCase();
				if (wordsMap.containsKey(word)) {
					wordCounter = wordsMap.get(word);
					wordsMap.put(word, wordCounter + 1);
				} else wordsMap.put(word, 1);
			}
		}

		int iter = wordsMap.size();
		System.out.println("------------------------");
		System.out.println("Rijec (broj ponavljanja)");
		System.out.println("------------------------");

		for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
			if (iter != 1) {
		 	   System.out.print(entry.getKey() + " (" + entry.getValue() + ")\n");
				iter--;
			}
			else {
				System.out.print(entry.getKey() + " (" + entry.getValue() + ")");
				System.out.print("\n------------------------");
			}
		}
		buffReader.close();
	}
}
