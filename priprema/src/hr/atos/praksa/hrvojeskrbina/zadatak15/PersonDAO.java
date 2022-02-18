package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {

    public int add(Person emp) throws SQLException;

    public void delete(String oib) throws SQLException;

    public Person getPerson(String oib) throws SQLException;

    public List<Person> getPersons() throws SQLException;

    public void update(Person emp) throws SQLException;
}
