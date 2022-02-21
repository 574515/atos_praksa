package hr.atos.praksa.hrvojeskrbina.zadatak15;

public class Task {

	String name, description, taskType, status, startDate, endDate, user;
	int complexity, id, timeNeeded;

	public Task() {
	}

	public Task(String name, String description, String taskType, String status, int complexity, int timeNeeded,
			String startDate, String endDate, String user) {
		this.name = name;
		this.description = description;
		this.taskType = taskType;
		this.status = status;
		this.complexity = complexity;
		this.timeNeeded = timeNeeded;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

	public int getTimeNeeded() {
		return timeNeeded;
	}

	public void setTimeNeeded(int timeNeeded) {
		this.timeNeeded = timeNeeded;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ID: " + id +
				"\nNaziv: " + name +
				"\nOpis: " + description +
				"\nTip: " + taskType +
				"\nStatus: " + status +
				"\nKompleksnost: " + complexity +
				"\nPotrebno vrijeme: " + timeNeeded +
				"\nDatum pocetka: " + startDate +
				"\nDatum zavrsetka: " + endDate;
	}

}
