package hr.atos.praksa.hrvojeskrbina.zadatak13;

import java.util.Random;

public class Source {

	public static void main(String[] args) {
		int numOfPerson = 5;
		Person persons[] = new Person[numOfPerson];
		for (int i = 0; i < numOfPerson; i++) {
			persons[i] = new Person(getFirstName(), getLastName());
			persons[i].goRun();
			persons[i].goDoSquats();
			persons[i].goEatHealthy();
			persons[i].goLift();
			System.out.println("--------------------------------");
		}
	}

	private static String getFirstName() {
		Random rand = new Random();
		String[] fNames = {"John", "Eva", "Martin", "Tina", "James", "Anna", "Frank", "Samantha"};
		return fNames[rand.nextInt(fNames.length)];
	}

	private static String getLastName() {
		Random rand = new Random();
		String[] lNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
		return lNames[rand.nextInt(lNames.length)];
	}

}
