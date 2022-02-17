package hr.atos.praksa.hrvojeskrbina.zadatak08;

import java.util.Scanner;

public class Source {

	public static void main(String[] args) {
		int inputMonth, monthValid;
		inputMonth = getMonth();
		monthValid = checkMonth(inputMonth);
		printCalendar(monthValid);
	}

	private static int getMonth() {
		Scanner input = new Scanner(System.in);
		int num = 0;
		do {
			try {
		        System.out.print("Unesite brojcanu vrijednost mjeseca: ");
				num = Integer.parseInt(input.next());
		    } catch (NumberFormatException e) {
		        System.out.format("Unesite brojcanu vrijednost mjeseca: ");
	            input.next();
		    }
		} while(num < 0 || num > 12);
		input.close();
		return num;
	}

	private static void printCalendar(int month) {
		System.out.println("\n  P  U  S  Č  P  S  N");		
		for(int i = 1; i <= month; i++) {
			System.out.format("%3d", i);
			if (i % 7 == 0) System.out.println();
		}		
	}

	private static int checkMonth(int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) return 31;
		else if (month == 2) return 29;
		else return 30;
	}

}
