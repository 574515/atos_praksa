package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

public interface TaskUserDAO {
    public int addTU(String oib, int id) throws SQLException;

    public void deleteTUPerTask(int id) throws SQLException;

    public void deleteTUPerUser(String oib) throws SQLException;

    public List<Task> getTasks(String oib) throws SQLException;

}
