package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.Time;

import javax.swing.JOptionPane;

public class HandleTasks {

    private String[] taskTypes = { "bug", "task" };
    private String[] status = { "open", "closed", "inprogress" };

    protected Task addNewTask() {
        Task task = new Task();
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
        task.setStartDate(JOptionPane.showInputDialog("Unesite datum pocetka [dd.mm.yyyy.]: "));
        task.setEndDate(JOptionPane.showInputDialog("Unesite datum kraja [dd.mm.yyyy.]: "));
        return task;
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

}
