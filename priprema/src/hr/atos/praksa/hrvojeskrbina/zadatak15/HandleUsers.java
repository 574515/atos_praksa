package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

public class HandleUsers {

    protected Person addNewUser() throws SQLException {
        Person person = new Person();
        Boolean exists = true;
        person.setFirstName(JOptionPane.showInputDialog("Unesite ime"));
        person.setLastName(JOptionPane.showInputDialog("Unesite prezime"));
        person.setWorkplace(JOptionPane.showInputDialog("Unesite mjesto rada"));
        do {
            person.setOib(JOptionPane.showInputDialog("Unesite OIB"));
            exists = checkIfOibExists(person.getOib());
        } while (exists || !checkOib(person.getOib()));
        person.setPassword(JOptionPane.showInputDialog("Unesite lozinku"));
        if (firstPerson())
            person.setRole("admin");
        return person;
    }

    protected void listAllUsers() throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> people = pdaoi.getPeople();
        people.forEach((p) -> {
            System.out.println(p.toString());
            System.out.println("---------------------");
        });
    }

    protected void updateUser() throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        Person person;
        String oib;
        Boolean exists = false;
        do {
            oib = JOptionPane.showInputDialog("Unesite OIB");
            exists = checkIfOibExists(oib);
        } while (!exists || !checkOib(oib));
        person = pdaoi.getPerson(oib);
        person.setFirstName(JOptionPane.showInputDialog("Unesite ime", person.getFirstName()));
        person.setLastName(JOptionPane.showInputDialog("Unesite prezime", person.getLastName()));
        person.setWorkplace(JOptionPane.showInputDialog("Unesite mjesto rada", person.getWorkplace()));
        do {
            person.setOib(JOptionPane.showInputDialog("Unesite OIB", person.getOib()));
        } while (!checkOib(person.getOib()));
        person.setPassword(JOptionPane.showInputDialog("Unesite lozinku", person.getPassword()));
        person.setRole(JOptionPane.showInputDialog("Unesite ulogu", person.getRole()));
        pdaoi.update(person);
    }

    protected void deleteUser() throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        Person person;
        String oib;
        Boolean exists = false;
        do {
            oib = JOptionPane.showInputDialog("Unesite OIB");
            exists = checkIfOibExists(oib);
        } while (!exists || !checkOib(oib));

        person = pdaoi.getPerson(oib);
        int answer = JOptionPane.showConfirmDialog(null,
                "Obrisi korisnika [" + person.getFullName() + " - OIB: " + person.getOib() + "]", "Jeste li sigurni?",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            pdaoi.delete(oib);
            if (firstPerson())
                System.exit(0);
        } else
            return;
    }

    protected boolean checkOib(String oib) {
        return oib.matches("^[0-9]{11}$");
    }

    private static boolean checkIfOibExists(String oib) throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> persons = pdaoi.getPeople();
        for (Person p : persons)
            if (p.getOib().equals(oib))
                return true;
        return false;
    }

    private static boolean firstPerson() throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> persons = pdaoi.getPeople();
        return persons.isEmpty();
    }
}
