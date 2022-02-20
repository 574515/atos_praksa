package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

import javax.swing.JOptionPane;

public class HandleTasks {

    private String[] taskTypes = { "bug", "task" };
    private String[] status = { "open", "closed", "inprogress" };

    protected void addNewTask() throws SQLException {
        Task task = new Task();
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        task.setName(JOptionPane.showInputDialog("Unesite naziv zadataka"));
        task.setDescription(JOptionPane.showInputDialog("Unesite opis zadatka"));
        task.setTaskType(
                (String) JOptionPane.showInputDialog(null, "Tip zadatka", "Odaberite tip zadatka",
                        JOptionPane.QUESTION_MESSAGE, null, taskTypes, taskTypes[0]));
        task.setStatus(
                (String) JOptionPane.showInputDialog(null, "Status zadatka", "Odaberite status zadatka",
                        JOptionPane.QUESTION_MESSAGE, null, status, status[0]));
        task.setComplexity(parseComplexity());
        task.setTimeNeeded(parseTimeNeeded());
        task.setStartDate(JOptionPane.showInputDialog("Unesite datum pocetka [dd.mm.yyyy.]"));
        task.setEndDate(JOptionPane.showInputDialog("Unesite datum kraja [dd.mm.yyyy.]"));
        tdaoi.addTask(task);
    }

    protected void listAllTasks() throws SQLException {
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        List<Task> tasks = tdaoi.getTasks();
        tasks.forEach((t) -> {
            System.out.format(
                    "ID: %d\nNaziv: %s\nOpis: %s\nTip: %s\nStatus: %s\nKompleksnost: %d\nPotrebno vrijeme: %tl\nDatum pocetka: %s\nDatum zavrsetka: %s\n-------------------------------",
                    t.getId(), t.getName(), t.getDescription(), t.getTaskType(), t.getStatus(), t.getComplexity(),
                    t.getTimeNeeded(), t.getStartDate(), t.getEndDate());
        });
    }

    protected void updateTask() throws SQLException {
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        Task task;
        int id = parseId();
        task = tdaoi.getTask(id);
        task.setName(JOptionPane.showInputDialog("Unesite naziv zadataka", task.getName()));
        task.setDescription(JOptionPane.showInputDialog("Unesite opis zadatka", task.getDescription()));
        task.setTaskType(
                (String) JOptionPane.showInputDialog(null, "Tip zadatka", "Odaberite tip zadatka",
                        JOptionPane.QUESTION_MESSAGE, null, taskTypes, task.getTaskType()));
        task.setStatus(
                (String) JOptionPane.showInputDialog(null, "Status zadatka", "Odaberite status zadatka",
                        JOptionPane.QUESTION_MESSAGE, null, status, task.getStatus()));
        task.setComplexity(parseComplexity(task));
        task.setTimeNeeded(parseTimeNeeded(task));
        task.setStartDate(JOptionPane.showInputDialog("Unesite datum pocetka [dd.mm.yyyy.]", task.getStartDate()));
        task.setEndDate(JOptionPane.showInputDialog("Unesite datum kraja [dd.mm.yyyy.]", task.getEndDate()));
        tdaoi.updateTask(task);
    }

    protected void deleteTask() throws SQLException {
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        Task task;
        int id = parseId();
        task = tdaoi.getTask(id);
        int answer = JOptionPane.showConfirmDialog(null,
                "Obrisi zadatak [" + task.getName() + " - ID: " + task.getId() + "]", "Jeste li sigurni?",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            tdaoi.deleteTask(id);
        } else
            return;
    }

    private int parseId() {
        String inputNumber = JOptionPane.showInputDialog("Unesite ID zadatka");
        int num;
        boolean error = false;
        while (!error) {
            try {
                num = Integer.parseInt(inputNumber);
                if (num >= 1)
                    return num;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite ID zadatka");
            }
        }
        return -1;
    }

    private Time parseTimeNeeded() {
        String inputNumber = JOptionPane.showInputDialog("Unesite sate");
        long num;
        boolean error = false;
        while (!error) {
            try {
                num = Long.parseLong(inputNumber);
                if (num > 0 && num < 24) {

                    return new Time(num * 3600000);
                }
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite sate");
            }
        }
        return new Time(-1);
    }

    private Time parseTimeNeeded(Task task) {
        String inputNumber = JOptionPane.showInputDialog("Unesite sate", task.getTimeNeeded());
        long num;
        boolean error = false;
        while (!error) {
            try {
                num = Long.parseLong(inputNumber);
                if (num > 0 && num < 24) {

                    return new Time(num * 3600000);
                }
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite sate", task.getTimeNeeded());
            }
        }
        return new Time(-1);
    }

    private int parseComplexity() {
        String inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka");
        int num;
        boolean error = false;
        while (!error) {
            try {
                num = Integer.parseInt(inputNumber);
                if (num >= 1 && num <= 10)
                    return num;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka");
            }
        }
        return -1;
    }

    private int parseComplexity(Task task) {
        String inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka", task.getComplexity());
        int num;
        boolean error = false;
        while (!error) {
            try {
                num = Integer.parseInt(inputNumber);
                if (num >= 1 && num <= 10)
                    return num;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka", task.getComplexity());
            }
        }
        return -1;
    }

}
