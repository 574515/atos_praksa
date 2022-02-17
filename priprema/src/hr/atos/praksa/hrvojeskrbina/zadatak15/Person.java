package hr.atos.praksa.hrvojeskrbina.zadatak15;

public class Person {

    String firstName, lastName, workplace, oib, password;

    public Person() {
    }

    public Person(String firstName, String lastName, String workplace, String oib, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.workplace = workplace;
        this.oib = oib;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "First name: " + firstName +
                "Last name: " + lastName +
                "Workplace: " + workplace +
                "OIB: " + oib;
    }
}
