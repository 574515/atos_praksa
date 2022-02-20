package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Menus {

    public void adminMenu(Person person) throws SQLException {
        String choice;
        String[] options = {
                "Kreiraj zadatak", "Izlistaj zadatke", "Izmijeni zadatak", "Obrisi zadatak",
                "Kreiraj zaposlenika", "Izlistaj zaposlenike", "Izmijeni zaposlenika", "Obrisi zaposlenika",
                "Izlaz" };
        HandleTasks task = new HandleTasks();
        while (true) {
            choice = (String) JOptionPane.showInputDialog(null, "Odaberite opciju",
                    "Dobrodosli, " + person.getFullName() + " [" + person.getOib() + "]",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (choice.equals(options[0])) {
                task.addNewTask();
            } else if (choice.equals(options[1])) {
                task.listAllTasks();
            } else if (choice.equals(options[2])) {
                task.updateTask();
            } else if (choice.equals(options[3])) {
                task.deleteTask();
            } else if (choice.equals(options[4])) {
            } else if (choice.equals(options[5])) {
            } else if (choice.equals(options[6])) {
            } else if (choice.equals(options[7])) {
            } else if (choice.equals(options[8])) {
                break;
            }
        }
    }

    public void suMenu() {

    }

    public void userMenu() {

    }

}
