package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskUserDAOImplementation implements TaskUserDAO {

	static Connection con = ConnectionDB.getConnection();

	@Override
	public int addTU(String oib, int id) throws SQLException {
		String query = "INSERT INTO zadaciKorisnika VALUES (?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, oib);
		ps.setInt(2, id);
		int n = ps.executeUpdate();
		return n;
	}

	@Override
	public void deleteTUPerTask(int id) throws SQLException {
		String query = "DELETE FROM zadaciKorisnika WHERE zadatak=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	@Override
	public void deleteTUPerUser(String oib) throws SQLException {
		String query = "DELETE FROM zadaciKorisnika WHERE korisnik=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, oib);
		ps.executeUpdate();
	}

	@Override
	public List<TaskUser> getTasksPerTime(String oib) throws SQLException {
		String query = "SELECT * FROM zadaciKorisnika WHERE korisnik=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, oib);
		ResultSet rs = ps.executeQuery();
		List<TaskUser> ls = new ArrayList<TaskUser>();
		while (rs.next()) {
			TaskUser tu = new TaskUser();
			tu.setTaskId(rs.getInt("zadatak"));
			tu.setUserOib(rs.getString("korisnik"));
			ls.add(tu);
		}
		return ls;
	}

	@Override
	public TaskUser getTask(int id) throws SQLException {
		String query = "SELECT * FROM zadaciKorisnika WHERE zadatak=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, id);
		TaskUser task = new TaskUser();
		ResultSet rs = ps.executeQuery();
		boolean check = false;
		while (rs.next()) {
			check = true;
			task.setTaskId(rs.getInt("zadatak"));
			task.setUserOib(rs.getString("korisnik"));
		}
		return (check == true) ? task : null;
	}

	@Override
	public List<Task> getTasks(String oib) throws SQLException {
		String query = "SELECT kz.zadatak, z.* FROM zadaciKorisnika as kz, zadatak as z WHERE kz.korisnik=? AND kz.zadatak=z.id";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, oib);
		ResultSet rs = ps.executeQuery();
		List<Task> ls = new ArrayList<Task>();
		while (rs.next()) {
			Task task = new Task();
			task.setId(rs.getInt("id"));
			task.setName(rs.getString("name"));
			task.setDescription(rs.getString("description"));
			task.setTaskType(rs.getString("task_type"));
			task.setStatus(rs.getString("status"));
			task.setComplexity(rs.getInt("complexity"));
			task.setTimeNeeded(rs.getInt("time_needed"));
			task.setStartDate(rs.getString("start_date"));
			task.setEndDate(rs.getString("end_date"));
			ls.add(task);
		}
		return ls;
	}

}
