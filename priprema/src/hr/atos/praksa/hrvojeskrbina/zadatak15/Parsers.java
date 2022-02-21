package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

public class Parsers {

	public Parsers() {
	}

	public long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date2.getTime() - date1.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	protected boolean checkOib(String oib) {
		return oib.matches("^[0-9]{11}$");
	}

	protected boolean checkIfOibExists(String oib, PersonDAOImplemenation pdaoi) throws SQLException {
		List<Person> persons = pdaoi.getPeople();
		for (Person p : persons)
			if (p.getOib().equals(oib))
				return true;
		return false;
	}

	protected boolean firstPerson(PersonDAOImplemenation pdaoi) throws SQLException {
		List<Person> persons = pdaoi.getPeople();
		return persons.isEmpty();
	}

	protected String parseDate(String startEnd) {
		String date, regex = "^(0[1-9]|[12][0-9]|[3][01]).(0[1-9]|1[012]).\\d{4}.$";
		do {
			date = JOptionPane.showInputDialog("Unesite datum " + startEnd + " [dd.mm.yyyy.]");
			if (date == null)
				return null;
		} while (!date.matches(regex));
		return date;
	}

	protected String parseDate(Task task, String startEnd) {
		String date, regex = "^(0[1-9]|[12][0-9]|[3][01]).(0[1-9]|1[012]).\\d{4}.$";
		do {
			date = JOptionPane.showInputDialog("Unesite datum " + startEnd + " [dd.mm.yyyy.]", task.getEndDate());
			if (date == null)
				return null;
		} while (!date.matches(regex));
		return date;
	}

	protected int parseNewId() throws SQLException {
		String inputNumber;
		int num = -1;
		boolean error = true, exists = true;
		do {
			try {
				inputNumber = JOptionPane.showInputDialog("Unesite ID");
				if (inputNumber == null)
					return (-1);
				num = Integer.parseInt(inputNumber);
				exists = existsId(num);
				if (exists)
					JOptionPane.showMessageDialog(null, "Zadatak sa ID-om [" + num + "] postoji.", "Pogreska ID-a",
							JOptionPane.WARNING_MESSAGE);
				if (num > 0)
					error = false;
			} catch (NumberFormatException e) {
				inputNumber = JOptionPane.showInputDialog("Unesite ID");
				if (inputNumber == null)
					return (-1);
			}
		} while (error || num < 1 || exists);
		return num;
	}

	protected int parseExistingId() throws SQLException {
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

	protected boolean existsId(int id) throws SQLException {
		TaskDAOImplementation tdaoi = new TaskDAOImplementation();
		List<Task> tasks = tdaoi.getTasks();
		for (Task task : tasks) {
			if (task.getId() == id)
				return true;
		}
		return false;
	}

	protected int parseTimeNeeded() {
		String inputNumber;
		int num = -1;
		boolean error = true;
		do {
			try {
				inputNumber = JOptionPane.showInputDialog("Unesite sate");
				if (inputNumber == null)
					return (-1);
				num = Integer.parseInt(inputNumber);
				if (num > 0)
					error = false;
			} catch (NumberFormatException e) {
				inputNumber = JOptionPane.showInputDialog("Unesite sate");
				if (inputNumber == null)
					return (-1);
			}
		} while (error || num < 1);
		return num;
	}

	protected int parseTimeNeeded(Task task) {
		String inputNumber;
		int num = -1;
		boolean error = true;
		do {
			try {
				inputNumber = JOptionPane.showInputDialog("Unesite sate", task.getTimeNeeded());
				if (inputNumber == null)
					return (-1);
				num = Integer.parseInt(inputNumber);
				if (num > 0)
					error = false;
			} catch (NumberFormatException e) {
				inputNumber = JOptionPane.showInputDialog("Unesite sate", task.getTimeNeeded());
				if (inputNumber == null)
					return (-1);
			}
		} while (error || num < 1);
		return num;
	}

	protected int parseComplexity() {
		String inputNumber;
		int num = -1;
		boolean error = true;
		do {
			try {
				inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka [1, 10]");
				if (inputNumber == null)
					return (-1);
				num = Integer.parseInt(inputNumber);
				if (num >= 1 || num <= 10)
					error = false;
			} catch (NumberFormatException e) {
				inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka [1, 10]");
				if (inputNumber == null)
					return (-1);
			}
		} while (error || (num < 1 || num > 10));
		return num;
	}

	protected int parseComplexity(Task task) {
		String inputNumber;
		int num = -1;
		boolean error = true;
		do {
			try {
				inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka [1, 10]", task.getComplexity());
				if (inputNumber == null)
					return (-1);
				num = Integer.parseInt(inputNumber);
				if (num >= 1 || num <= 10)
					error = false;
			} catch (NumberFormatException e) {
				inputNumber = JOptionPane.showInputDialog("Unesite kompleksnost zadatka [1, 10]", task.getComplexity());
				if (inputNumber == null)
					return (-1);
			}
		} while (error || (num < 1 || num > 10));
		return num;
	}

}
