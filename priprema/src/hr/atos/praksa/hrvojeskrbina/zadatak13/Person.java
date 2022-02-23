package hr.atos.praksa.hrvojeskrbina.zadatak13;

public class Person implements Workout {
    String firstName, lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    String getFirstName() {
        return firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getLastName() {
        return lastName;
    }

    String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public void goRun() {
        System.out.println(getFullName() + " went for a run.");
    }

    @Override
    public void goLift() {
        System.out.println(getFullName() + " lifted some weights.");
    }

    @Override
    public void goDoSquats() {
        System.out.println(getFullName() + " did few squats.");
    }

    @Override
    public void goEatHealthy() {
        System.out.println(getFullName() + " had a healthy meal.");
    }
}