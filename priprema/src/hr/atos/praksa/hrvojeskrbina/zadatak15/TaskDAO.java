package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

public interface TaskDAO {
    public int addTask(Task task) throws SQLException;

    public void deleteTask(int id) throws SQLException;

    public Task getTask(int id) throws SQLException;

    public List<Task> getTasks() throws SQLException;

    public void updateTask(Task task) throws SQLException;
}
