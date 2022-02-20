package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

public class HandleUsers {

    protected static Person loggedUser;

    protected static void login() throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> persons = pdaoi.getPersons();
        Person login;
        Menus menu = new Menus();
        if (persons.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trenutno nema korisnika.");
            login = makeNewUser();
            pdaoi.add(login);
            loggedUser = login;
        } else {
            String oib, password;
            boolean correctPw = false, exists = false;
            int numOfTries = 1;
            do {
                do {
                    oib = JOptionPane.showInputDialog("Unesite OIB");
                } while (!checkOib(oib));
                login = pdaoi.getPerson(oib);
                if (login == null)
                    System.out.println("Nema osobe sa tim OIB-om.");
                else
                    exists = true;
            } while (!exists);
            do {
                String pwformat = "Unesite lozinku [" + numOfTries + " / 3]";
                password = JOptionPane.showInputDialog(pwformat);
                correctPw = login.getPassword().equals(password);
                if (!correctPw) {
                    numOfTries++;
                } else {
                    loggedUser = login;
                    break;
                }
                if (numOfTries > 3) {
                    System.out.println("Tri puta pogresan unos. Pokusajte ponovno.");
                    System.exit(1);
                }
            } while (!correctPw);
        }
        if (loggedUser.getRole().equals("admin"))
            menu.adminMenu(loggedUser);
        else if (loggedUser.getRole().equals("superuser"))
            menu.suMenu();
        else
            menu.userMenu();
    }

    protected static boolean checkOib(String oib) {
        return oib.matches("^[0-9]{11}$");
    }

    private static Person makeNewUser() throws SQLException {
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

    private static boolean checkIfOibExists(String oib) throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> persons = pdaoi.getPersons();
        for (Person p : persons)
            if (p.getOib().equals(oib))
                return true;
        return false;
    }

    private static boolean firstPerson() throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> persons = pdaoi.getPersons();
        return persons.isEmpty();
    }
}
