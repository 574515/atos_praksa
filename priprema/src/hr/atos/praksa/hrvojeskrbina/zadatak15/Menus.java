package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

public class Menus {

    protected static Person loggedUser;

    protected static void login() throws SQLException, Exception {
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        List<Person> persons = pdaoi.getPeople();
        HandleUsers hu = new HandleUsers();
        Person login;
        if (persons.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trenutno nema korisnika.");
            hu.addNewUser();
            login();
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
            suMenu(loggedUser);
        else
            userMenu(loggedUser);
    }

    public static void adminMenu(Person person) throws SQLException, Exception {
        if (!Menus.loggedUser.getRole().equals("admin"))
            throw new Exception("Nemate ovlasti za ovu radnju.");
        String choice;
        final String[] OPTIONS = {
                "Kreiraj zadatak", "Izlistaj zadatke", "Izmijeni zadatak", "Obrisi zadatak",
                "Kreiraj zaposlenika", "Izlistaj zaposlenike", "Izmijeni zaposlenika", "Obrisi zaposlenika",
                "Izlaz" };
        HandleTasks task = new HandleTasks();
        HandleUsers user = new HandleUsers();
        while (true) {
            choice = (String) JOptionPane.showInputDialog(null, "Odaberite opciju",
                    "Dobrodosli, " + person.getFullName() + " [" + person.getOib() + "]",
                    JOptionPane.QUESTION_MESSAGE, null, OPTIONS, OPTIONS[0]);

            if (choice == null)
                break;
            else {
                if (choice.equals(OPTIONS[0])) {
                    task.addNewTask();
                } else if (choice.equals(OPTIONS[1])) {
                    task.listAllTasks();
                } else if (choice.equals(OPTIONS[2])) {
                    task.updateTask();
                } else if (choice.equals(OPTIONS[3])) {
                    task.deleteTask();
                } else if (choice.equals(OPTIONS[4])) {
                    user.addNewUser();
                } else if (choice.equals(OPTIONS[5])) {
                    user.listAllUsers();
                } else if (choice.equals(OPTIONS[6])) {
                    user.updateUser();
                } else if (choice.equals(OPTIONS[7])) {
                    user.deleteUser();
                } else if (choice.equals(OPTIONS[8])) {
                    break;
                }
            }
        }
    }

    public static void suMenu(Person person) throws SQLException, Exception {
        if (!Menus.loggedUser.getRole().equals("admin") && !Menus.loggedUser.getRole().equals("superuser"))
            throw new Exception("Nemate ovlasti za ovu radnju.");
        String choice;
        final String[] OPTIONS = { "Kreiraj zadatak", "Izlistaj zadatke", "Kreiraj zaposlenika", "Izlistaj zaposlenike",
                "Izlaz" };
        HandleTasks task = new HandleTasks();
        HandleUsers user = new HandleUsers();
        while (true) {
            choice = (String) JOptionPane.showInputDialog(null, "Odaberite opciju",
                    "Dobrodosli, " + person.getFullName() + " [" + person.getOib() + "]",
                    JOptionPane.QUESTION_MESSAGE, null, OPTIONS, OPTIONS[0]);

            if (choice == null)
                break;
            else {
                if (choice.equals(OPTIONS[0])) {
                    task.addNewTask();
                } else if (choice.equals(OPTIONS[1])) {
                    task.listAllTasks();
                } else if (choice.equals(OPTIONS[2])) {
                    user.addNewUser();
                } else if (choice.equals(OPTIONS[3])) {
                    user.listAllUsers();
                } else if (choice.equals(OPTIONS[4])) {
                    break;
                }
            }
        }
    }

    public static void userMenu(Person person) throws SQLException, Exception {
        String choice;
        final String[] OPTIONS = { "Izlistaj zadatke", "Izlistaj zaposlenike",
                "--------------", "Izlaz" };
        HandleTasks task = new HandleTasks();
        HandleUsers user = new HandleUsers();
        while (true) {
            choice = (String) JOptionPane.showInputDialog(null, "Odaberite opciju",
                    "Dobrodosli, " + person.getFullName() + " [" + person.getOib() + "]",
                    JOptionPane.QUESTION_MESSAGE, null, OPTIONS, OPTIONS[0]);

            if (choice == null)
                break;
            else {
                if (choice.equals(OPTIONS[0])) {
                    task.listAllTasks();
                } else if (choice.equals(OPTIONS[1])) {
                    user.listAllUsers();
                } else if (choice.equals(OPTIONS[2])) {
                    continue;
                } else if (choice.equals(OPTIONS[3])) {
                    break;
                }
            }
        }
    }

}
