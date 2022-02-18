package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

public class HandleUsers {

    protected static Person loggedUser;

    protected static void login() throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> persons = pdaoi.getPersons();
        if (persons.isEmpty()) {
            System.out.println("Trenutno nema korisnika.");
            System.exit(0);
        } else {
            String oib, password;
            boolean correctPw = false, exists = false;
            Person login;
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
    }

    protected static boolean checkOib(String oib) {
        return oib.matches("^[0-9]{11}$");
    }
}
