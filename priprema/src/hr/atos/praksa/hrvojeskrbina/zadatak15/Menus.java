package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

public class Menus {

    protected static Person loggedUser;

    protected static void login() throws SQLException {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> persons = pdaoi.getPeople();
        HandleUsers hu = new HandleUsers();
        Person login;
        if (persons.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trenutno nema korisnika.");
            login = hu.addNewUser();
            pdaoi.add(login);
            loggedUser = login;
        } else {
            String oib, password;
            boolean correctPw = false, exists = false;
            int numOfTries = 1;
            do {
                do {
                    oib = JOptionPane.showInputDialog("Unesite OIB");
                } while (!hu.checkOib(oib));
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
            adminMenu(loggedUser);
        else if (loggedUser.getRole().equals("superuser"))
            suMenu();
        else
            userMenu();
    }

    public static void adminMenu(Person person) throws SQLException {
        String choice;
        String[] options = {
                "Kreiraj zadatak", "Izlistaj zadatke", "Izmijeni zadatak", "Obrisi zadatak",
                "Kreiraj zaposlenika", "Izlistaj zaposlenike", "Izmijeni zaposlenika", "Obrisi zaposlenika",
                "Izlaz" };
        HandleTasks task = new HandleTasks();
        HandleUsers user = new HandleUsers();
        while (true) {
            choice = (String) JOptionPane.showInputDialog(null, "Odaberite opciju",
                    "Dobrodosli, " + person.getFullName() + " [" + person.getOib() + "]",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice == null)
                break;
            else {
                if (choice.equals(options[0])) {
                    task.addNewTask();
                } else if (choice.equals(options[1])) {
                    task.listAllTasks();
                } else if (choice.equals(options[2])) {
                    task.updateTask();
                } else if (choice.equals(options[3])) {
                    task.deleteTask();
                } else if (choice.equals(options[4])) {
                    user.addNewUser();
                } else if (choice.equals(options[5])) {
                    user.listAllUsers();
                } else if (choice.equals(options[6])) {
                    user.updateUser();
                } else if (choice.equals(options[7])) {
                    user.deleteUser();
                } else if (choice.equals(options[8])) {
                    break;
                }
            }
        }
    }

    public static void suMenu() {

    }

    public static void userMenu() {

    }

}
