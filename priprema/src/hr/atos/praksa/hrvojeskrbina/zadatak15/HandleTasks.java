package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class HandleTasks {

    private final String[] TASK_TYPES = { "bug", "task" };
    private final String[] STATUS = { "open", "closed", "inprogress" };

    protected void addNewTask() throws SQLException {
        Task task = new Task();
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        task.setId(parseNewId());
        task.setName(JOptionPane.showInputDialog("Unesite naziv zadatka"));
        task.setDescription(JOptionPane.showInputDialog("Unesite opis zadatka"));
        task.setTaskType(
                (String) JOptionPane.showInputDialog(null, "Tip zadatka", "Odaberite tip zadatka",
                        JOptionPane.QUESTION_MESSAGE, null, TASK_TYPES, TASK_TYPES[0]));
        task.setStatus(
                (String) JOptionPane.showInputDialog(null, "Status zadatka", "Odaberite status zadatka",
                        JOptionPane.QUESTION_MESSAGE, null, STATUS, STATUS[0]));
        task.setComplexity(parseComplexity());
        task.setTimeNeeded(parseTimeNeeded());
        task.setStartDate(parseDate());
        task.setEndDate(parseDate());

        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        String oib;
        Boolean exists = false;
        do {
            oib = JOptionPane.showInputDialog("Unesite OIB");
            if (pdaoi.getPerson(oib) != null)
                exists = true;
        } while (!exists);
        task.setUser(oib);
        tdaoi.addTask(task);
        TaskUserDAOImplementation tudaoi = new TaskUserDAOImplementation();
        tudaoi.addTU(task.getUser(), task.getId());
    }

    protected void listAllTasks() throws SQLException {
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        List<Task> tasks = tdaoi.getTasks();
        tasks.forEach((t) -> {
            System.out.println(t.toString());
            System.out.println("-------------------------------");
        });
    }

    protected void updateTask() throws SQLException {
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        Task task;
        int id = parseExistingId();
        task = tdaoi.getTask(id);
        task.setName(JOptionPane.showInputDialog("Unesite naziv zadatka", task.getName()));
        task.setDescription(JOptionPane.showInputDialog("Unesite opis zadatka", task.getDescription()));
        task.setTaskType(
                (String) JOptionPane.showInputDialog(null, "Tip zadatka", "Odaberite tip zadatka",
                        JOptionPane.QUESTION_MESSAGE, null, TASK_TYPES, task.getTaskType()));
        task.setStatus(
                (String) JOptionPane.showInputDialog(null, "Status zadatka", "Odaberite status zadatka",
                        JOptionPane.QUESTION_MESSAGE, null, STATUS, task.getStatus()));
        task.setComplexity(parseComplexity(task));
        task.setTimeNeeded(parseTimeNeeded(task));
        task.setStartDate(parseDate(task));
        task.setEndDate(parseDate(task));
        tdaoi.updateTask(task);
    }

    protected void deleteTask() throws SQLException {
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        TaskUserDAOImplementation tudaoi = new TaskUserDAOImplementation();
        Task task;
        int id = parseExistingId();
        task = tdaoi.getTask(id);
        int answer = JOptionPane.showConfirmDialog(null,
                "Obrisi zadatak [" + task.getName() + " - ID: " + task.getId() + "]", "Jeste li sigurni?",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            tudaoi.deleteTUPerTask(id);
            tdaoi.deleteTask(id);
        } else
            return;
    }

    private String parseDate() {
        String date, regex = "^(0[1-9]|[12][0-9]|[3][01]).(0[1-9]|1[012]).\\d{4}.$";
        do {
            date = JOptionPane.showInputDialog("Unesite datum pocetka [dd.mm.yyyy.]");
        } while (!date.matches(regex));
        return date;
    }

    private String parseDate(Task task) {
        String date, regex = "^(0[1-9]|[12][0-9]|[3][01]).(0[1-9]|1[012]).\\d{4}.$";
        do {
            date = JOptionPane.showInputDialog("Unesite datum kraja [dd.mm.yyyy.]", task.getEndDate());
        } while (!date.matches(regex));
        return date;
    }

    private int parseNewId() throws HeadlessException, SQLException {
        String inputNumber;
        int num = -1;
        boolean error = true;
        do {
            try {
                inputNumber = JOptionPane.showInputDialog("Unesite ID");
                num = Integer.parseInt(inputNumber);
                if (num > 0)
                    error = false;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite ID");
            }
        } while (error || num < 1 || existsId(num));
        return num;
    }

    private int parseExistingId() throws SQLException {
        String inputNumber;
        int num = -1;
        boolean error = true;
        do {
            try {
                inputNumber = JOptionPane.showInputDialog("Unesite ID");
                num = Integer.parseInt(inputNumber);
                if (num > 0)
                    error = false;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite ID");
            }
        } while (error || num < 1);
        return num;
    }

    private boolean existsId(int id) throws SQLException {
        TaskDAOImplementation tdaoi = new TaskDAOImplementation();
        List<Task> tasks = tdaoi.getTasks();
        for (Task task : tasks) {
            if (task.getId() == id)
                return true;
        }
        return false;
    }

    private int parseTimeNeeded() {
        String inputNumber;
        int num = -1;
        boolean error = true;
        do {
            try {
                inputNumber = JOptionPane.showInputDialog("Unesite sate");
                num = Integer.parseInt(inputNumber);
                if (num > 0)
                    error = false;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite sate");
            }
        } while (error || num < 1);
        return num;
    }

    private int parseTimeNeeded(Task task) {
        String inputNumber;
        int num = -1;
        boolean error = true;
        do {
            try {
                inputNumber = JOptionPane.showInputDialog("Unesite sate", task.getTimeNeeded());
                num = Integer.parseInt(inputNumber);
                if (num > 0)
                    error = false;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite sate", task.getTimeNeeded());
            }
        } while (error || num < 1);
        return num;
    }

    private int parseComplexity() {
        String inputNumber;
        int num = -1;
        boolean error = true;
        do {
            try {
                inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka [1, 10]");
                num = Integer.parseInt(inputNumber);
                if (num >= 1 || num <= 10)
                    error = false;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka [1, 10]");
            }
        } while (error || (num < 1 || num > 10));
        return num;
    }

    private int parseComplexity(Task task) {
        String inputNumber;
        int num = -1;
        boolean error = true;
        do {
            try {
                inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka [1, 10]", task.getComplexity());
                num = Integer.parseInt(inputNumber);
                if (num >= 1 || num <= 10)
                    error = false;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka [1, 10]", task.getComplexity());
            }
        } while (error || (num < 1 || num > 10));
        return num;
    }

}
