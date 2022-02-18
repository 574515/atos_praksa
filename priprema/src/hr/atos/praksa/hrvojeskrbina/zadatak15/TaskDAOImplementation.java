package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImplementation implements TaskDAO {

    static Connection con = ConnectionDB.getConnection();

    @Override
    public int addTask(Task task) throws SQLException {
        String query = "INSERT INTO zadatak VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, task.getName());
        ps.setString(2, task.getDescription());
        ps.setString(3, task.getTaskType());
        ps.setString(4, task.getStatus());
        ps.setInt(5, task.getComplexity());
        ps.setTime(6, task.getTimeNeeded());
        ps.setString(7, task.getStartDate());
        ps.setString(8, task.getEndDate());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public void deleteTask(int id) throws SQLException {
        String query = "DELETE FROM zadatak WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public Task getTask(int id) throws SQLException {
        String query = "SELECT * FROM zadatak WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        Task task = new Task();
        ResultSet rs = ps.executeQuery();
        boolean check = false;
        while (rs.next()) {
            check = true;
            task.setId(rs.getInt("id"));
            task.setName(rs.getString("name"));
            task.setDescription(rs.getString("description"));
            task.setTaskType(rs.getString("task_type"));
            task.setStatus(rs.getString("status"));
            task.setComplexity(rs.getInt("complexity"));
            task.setTimeNeeded(rs.getTime("time_needed"));
            task.setStartDate(rs.getString("start_date"));
            task.setEndDate(rs.getString("end_date"));
        }
        return (check == true) ? task : null;
    }

    @Override
    public List<Task> getTasks() throws SQLException {
        String query = "SELECT * FROM zadatak";
        PreparedStatement ps = con.prepareStatement(query);
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
            task.setTimeNeeded(rs.getTime("time_needed"));
            task.setStartDate(rs.getString("start_date"));
            task.setEndDate(rs.getString("end_date"));
            ls.add(task);
        }
        return ls;
    }

    @Override
    public void updateTask(Task task) throws SQLException {
        String query = "UPDATE zadatak SET name=?, description=?, task_type=?, status=?, complexity=?, time_needed=?, start_date=?, end_date=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, task.getName());
        ps.setString(2, task.getDescription());
        ps.setString(3, task.getTaskType());
        ps.setString(4, task.getStatus());
        ps.setInt(5, task.getComplexity());
        ps.setTime(6, task.getTimeNeeded());
        ps.setString(7, task.getStartDate());
        ps.setString(8, task.getEndDate());
        ps.executeUpdate();
    }
}
