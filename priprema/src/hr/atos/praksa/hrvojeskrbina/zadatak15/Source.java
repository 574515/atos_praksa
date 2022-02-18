package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

public class Source {

    public static void main(String[] args) throws SQLException {
        // HandleUsers.login();
        // if (HandleUsers.loggedUser != null) {
        // TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        // HandleTasks ht = new HandleTasks();
        // tdaoi.addTask(ht.addNewTask());

        // List<Task> tasks = tdaoi.getTasks();

        // tdaoi.getTask(tasks.get(0).getid());
        // } else
        // System.out.println("Nope");
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        HandleTasks ht = new HandleTasks();
        tdaoi.addTask(ht.addNewTask());

        List<Task> tasks = tdaoi.getTasks();

        System.out.println(tdaoi.getTask(tasks.get(0).getid()));
    }

}
