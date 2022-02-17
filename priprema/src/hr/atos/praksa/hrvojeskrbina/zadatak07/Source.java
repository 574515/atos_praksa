package hr.atos.praksa.hrvojeskrbina.zadatak07;

import java.util.Scanner;

public class Source {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int start, end;
		start = getNumber(input, "pocetka");
		do {
			end = getNumber(input, "kraja");
			if (end <= start) System.out.println("Kraj mora biti veci od pocetka!");
		} while(end <= start);
		input.close();
		isDivisible(start, end);
	}

	public static int getNumber(Scanner input, String startOrEnd) {
        System.out.format("Unesite brojcanu vrijednost %s: ", startOrEnd);
		String inputNumber = input.next();
		int num;
		boolean error = false;
		while (!error) {
			try {
				num = Integer.parseInt(inputNumber);
		        if (num >= 0) return num;
		    } catch (NumberFormatException e) {
		        System.out.format("Unesite brojcanu vrijednost %s: ", startOrEnd);
		            inputNumber = input.next();
		    }
		}
		return -1;
	}

	public static void isDivisible(int start, int end) {
		int counter = 0;
		String word = "";
		for(int i = start + 1; i <= end - 1; i++) {
			if ((i % 2 == 0) && (i % 3 == 0))
				counter++;
		}
		if (counter == 1) word = "broj";
		else if (counter == 2 || counter == 3 || counter == 4) word = "broja";
		else word = "brojeva";
		System.out.format("%d %s iz intervala <%d, %d> je djeljivo sa 6.\n", counter, word, start, end);
	}
}
