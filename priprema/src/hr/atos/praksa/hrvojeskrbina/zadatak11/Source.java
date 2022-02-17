package hr.atos.praksa.hrvojeskrbina.zadatak11;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Source {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		System.out.print("Unesite svoje ime: ");
		String name = input.nextLine();
		System.out.format("Pozdrav, %s. Odaberite jezik.", name);
		printMessage(input, name);
		input.close();

	}

	public static String[] selectLanguage(Scanner input) {
		int choice = 0;
		System.out.println("Odaberite jezik koji zelite.");
		System.out.print("\n[1] German\n[2] Spanish\n[3] French\n\nChoice: ");
		input.reset();
		String inputNumber = input.next();
		do {
			try {
				choice = Integer.parseInt(inputNumber);
			} catch (NumberFormatException e) {
				System.out.println("Odaberite jezik koji zelite.");
				System.out.print("\n[1] German\n[2] Spanish\n[3] French\n\nChoice: ");
					inputNumber = input.next();
			}
		} while (choice <= 0 || choice > 3);
		input.close();
		String[] lang = new String[4];
		
		if (choice == 1) {
			lang[0] = "de";
			lang[1] = "DE";
			lang[2] = "es";
			lang[3] = "ES";
		}
		else if (choice == 2) {
			lang[0] = "es";
			lang[1] = "ES";
			lang[2] = "fr";
			lang[3] = "FR";
		}
		else {
			lang[0] = "fr";
			lang[1] = "FR";
			lang[2] = "de";
			lang[3] = "DE";
		}
		return lang;
	}

	public static void printMessage(Scanner input, String name) {
		Locale yourLocale, botLocale;
		ResourceBundle yourMessages, botMessages;

		String[] lang = selectLanguage(input);
		String botName = "Jason";
		yourLocale = new Locale(lang[0], lang[1]);
		botLocale = new Locale(lang[2], lang[3]);

		yourMessages = ResourceBundle.getBundle("hr.atos.praksa.hrvojeskrbina.zadatak11.MessagesBundle", yourLocale);
		botMessages = ResourceBundle.getBundle("hr.atos.praksa.hrvojeskrbina.zadatak11.MessagesBundle", botLocale);

		System.out.format("You: %s\nBot: %s", yourMessages.getString("hello"), botMessages.getString("hello"));
		System.out.println("\n------------------------");

		System.out.format("You: %s %s.\n", yourMessages.getString("mynameis"), name);
		System.out.format("Bot: %s, %s. %s %s.\n", botMessages.getString("nicetomeetyou"), name, botMessages.getString("mynameis"), botName);
		System.out.format("You: %s %s.", yourMessages.getString("nicetomeetyou"), botName);
		System.out.println("\n------------------------");
		System.out.format("You: %s, %s.\nBot: %s, %s.\n", yourMessages.getString("goodbye"), botName, botMessages.getString("goodbye"), name);
		System.out.println("\n------------------------");
	}

}
