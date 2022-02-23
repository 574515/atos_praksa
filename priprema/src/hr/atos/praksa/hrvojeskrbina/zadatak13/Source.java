package hr.atos.praksa.hrvojeskrbina.zadatak13;

import java.util.Random;

public class Source {

    static final int NUM_OF_PEOPLE = 5;

    public static void main(String[] args) {

        Person people[] = new Person[NUM_OF_PEOPLE];
        for (int i = 0; i < NUM_OF_PEOPLE; i++) {
            people[i] = new Person(getFirstName(), getLastName());
            people[i].goRun();
            people[i].goDoSquats();
            people[i].goEatHealthy();
            people[i].goLift();
            System.out.println("--------------------------------");
        }
    }

    private static String getFirstName() {
        Random rand = new Random();
        String[] fNames = { "John", "Eva", "Martin", "Tina", "James", "Anna", "Frank", "Samantha" };
        return fNames[rand.nextInt(fNames.length)];
    }

    private static String getLastName() {
        Random rand = new Random();
        String[] lNames = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez",
                "Martinez" };
        return lNames[rand.nextInt(lNames.length)];
    }

}
