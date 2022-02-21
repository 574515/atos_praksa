package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class HandleTasks {

	private final String[] TASK_TYPES = { "bug", "task" };
	private final String[] STATUS = { "open", "closed", "inprogress" };
	private final Parsers parser = new Parsers();
	private TaskDAOImplementation tdaoi = new TaskDAOImplementation();
	private TaskUserDAOImplementation tudaoi = new TaskUserDAOImplementation();

	protected void addNewTask() throws SQLException {
		Task task = new Task();
		PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
		String oib;
		Boolean exists = false, flag = false;

		task.setId(parser.parseNewId());
		if (task.getId() == (-1))
			return;
		task.setName(JOptionPane.showInputDialog("Unesite naziv zadatka"));
		if (task.getName() == null)
			return;
		task.setDescription(JOptionPane.showInputDialog("Unesite opis zadatka"));
		if (task.getDescription() == null)
			return;
		task.setTaskType(
				(String) JOptionPane.showInputDialog(null, "Tip zadatka", "Odaberite tip zadatka",
						JOptionPane.QUESTION_MESSAGE, null, TASK_TYPES, TASK_TYPES[0]));
		if (task.getTaskType() == null)
			return;
		task.setStatus(
				(String) JOptionPane.showInputDialog(null, "Status zadatka", "Odaberite status zadatka",
						JOptionPane.QUESTION_MESSAGE, null, STATUS, STATUS[0]));
		if (task.getStatus() == null)
			return;
		task.setComplexity(parser.parseComplexity());
		if (task.getComplexity() == (-1))
			return;
		task.setTimeNeeded(parser.parseTimeNeeded());
		if (task.getTimeNeeded() == (-1))
			return;
		task.setStartDate(parser.parseDate("pocetka"));
		if (task.getStartDate() == null)
			return;
		task.setEndDate(parser.parseDate("kraja"));
		if (task.getEndDate() == null)
			return;
		do {
			oib = JOptionPane.showInputDialog("Unesite OIB [0 ako ne zelite dodijeliti korisnika]");
			if (oib.equals("0")) {
				flag = true;
				break;
			}
			if (pdaoi.getPerson(oib) != null)
				exists = true;
		} while (!exists);
		if (!flag)
			task.setUser(oib);
		tdaoi.addTask(task);
		if (!flag)
			tudaoi.addTU(task.getUser(), task.getId());
	}

	protected void listAllTasks() throws SQLException {
		List<Task> tasks = tdaoi.getTasks();

		System.out.println("-------------------------------");
		tasks.forEach((t) -> {
			System.out.println(t.toString());
			System.out.println("-------------------------------");
		});
	}

	protected void updateTask() throws SQLException {
		Task task;
		int id = parser.parseExistingId();

		task = tdaoi.getTask(id);
		task.setName(JOptionPane.showInputDialog("Unesite naziv zadatka", task.getName()));
		if (task.getName() == null)
			return;
		task.setDescription(JOptionPane.showInputDialog("Unesite opis zadatka", task.getDescription()));
		if (task.getDescription() == null)
			return;
		task.setTaskType(
				(String) JOptionPane.showInputDialog(null, "Tip zadatka", "Odaberite tip zadatka",
						JOptionPane.QUESTION_MESSAGE, null, TASK_TYPES, task.getTaskType()));
		if (task.getTaskType() == null)
			return;
		task.setStatus(
				(String) JOptionPane.showInputDialog(null, "Status zadatka", "Odaberite status zadatka",
						JOptionPane.QUESTION_MESSAGE, null, STATUS, task.getStatus()));
		if (task.getStatus() == null)
			return;
		task.setComplexity(parser.parseComplexity(task));
		if (task.getComplexity() == (-1))
			return;
		task.setTimeNeeded(parser.parseTimeNeeded(task));
		if (task.getTimeNeeded() == (-1))
			return;
		task.setStartDate(parser.parseDate(task, "pocetka"));
		if (task.getStartDate() == null)
			return;
		task.setEndDate(parser.parseDate(task, "kraja"));
		if (task.getEndDate() == null)
			return;
		tdaoi.updateTask(task);
	}

	protected void deleteTask() throws SQLException {
		Task task;
		int answer, id = parser.parseExistingId();

		task = tdaoi.getTask(id);
		answer = JOptionPane.showConfirmDialog(null,
				"Obrisi zadatak [" + task.getName() + " - ID: " + task.getId() + "]", "Jeste li sigurni?",
				JOptionPane.YES_NO_OPTION);
		if (answer == JOptionPane.YES_OPTION) {
			tudaoi.deleteTUPerTask(id);
			tdaoi.deleteTask(id);
		} else
			return;
	}

	protected void getByTimeSpent() throws SQLException {
		String oib;
		Boolean exists = false;
		do {
			oib = JOptionPane.showInputDialog("Unesite OIB");
			exists = parser.checkIfOibExists(oib, new PersonDAOImplemenation());
			if (oib == null)
				return;
			else if (!exists)
				JOptionPane.showMessageDialog(null, "Korisnik sa OIB-om [" + oib + "] ne postoji.", "Nije pronadjen.",
						JOptionPane.ERROR_MESSAGE);
		} while (!exists || !parser.checkOib(oib));
		List<TaskUser> taskUser = tudaoi.getTasksPerTime(oib);
		List<Task> tasks = new ArrayList<Task>();
		taskUser.forEach(t -> {
			try {
				tasks.add(tdaoi.getTask(t.getTaskId()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		int hoursSpent = 0;
		for (Task t : tasks)
			hoursSpent += t.getTimeNeeded();

		String finalHours = "";
		if (hoursSpent == 1)
			finalHours = "Utroseno: " + hoursSpent + " sat";
		if (hoursSpent <= 0)
			finalHours = "Utroseno: " + hoursSpent + " sati";
		if (hoursSpent > 1 && hoursSpent < 5)
			finalHours = "Utroseno: " + hoursSpent + " sata";
		if (hoursSpent >= 5)
			finalHours = "Utroseno: " + hoursSpent + " sati";

		JOptionPane.showMessageDialog(null, finalHours, "Sati za [" + oib + "]",
				JOptionPane.INFORMATION_MESSAGE);
	}

	protected void getLongestOpen() throws SQLException, ParseException {
		long longestTime = 0, tempTime;
		int id = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.", Locale.ENGLISH);
		Date currentDate = new Date(System.currentTimeMillis());
		Date startDate;
		List<Task> tasks = tdaoi.getTasks();

		for (Task task : tasks) {
			startDate = sdf.parse(task.getStartDate());
			tempTime = parser.getDateDiff(startDate, currentDate, TimeUnit.DAYS);
			if (tempTime > longestTime) {
				longestTime = tempTime;
				id = task.getId();
			}
		}

		Task longestOpenedTask = tdaoi.getTask(id);
		String output = longestOpenedTask.toString() + "\n--------------------------------------------\n" +
				"Otvoren [dana]: " + longestTime;

		JOptionPane.showMessageDialog(null, output, "Najduze otvoreni zadatak",
				JOptionPane.INFORMATION_MESSAGE);
	}

}
