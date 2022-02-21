package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {

	public int addPerson(Person person) throws SQLException;

	public void deletePerson(String oib) throws SQLException;

	public Person getPerson(String oib) throws SQLException;

	public List<Person> getByWorkPlace(String workplace) throws SQLException;

	public List<Person> getPeople() throws SQLException;

	public void updatePerson(Person person) throws SQLException;

}
