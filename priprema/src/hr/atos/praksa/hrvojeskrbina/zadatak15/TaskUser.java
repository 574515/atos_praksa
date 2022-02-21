package hr.atos.praksa.hrvojeskrbina.zadatak15;

public class TaskUser {

	String userOib;
	int taskId;

	public TaskUser() {
	}

	public TaskUser(String userOib, int taskId) {
		this.userOib = userOib;
		this.taskId = taskId;
	}

	public String getUserOib() {
		return userOib;
	}

	public void setUserOib(String userOib) {
		this.userOib = userOib;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	@Override
	public String toString() {
		return "Zadatak: " + taskId + "\tKorisnik: [" + userOib + "]";
	}

}
