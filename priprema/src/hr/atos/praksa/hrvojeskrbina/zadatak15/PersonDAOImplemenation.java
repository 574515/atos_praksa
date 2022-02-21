package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImplemenation implements PersonDAO {

	static Connection con = ConnectionDB.getConnection();

	@Override
	public int addPerson(Person person) throws SQLException {
		String query = "INSERT INTO korisnik VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, person.getOib());
		ps.setString(2, person.getFirstName());
		ps.setString(3, person.getLastName());
		ps.setString(4, person.getWorkplace());
		ps.setString(5, person.getPassword());
		ps.setString(6, person.getRole());
		int n = ps.executeUpdate();
		return n;
	}

	@Override
	public void deletePerson(String oib) throws SQLException {
		String query = "DELETE FROM korisnik WHERE oib=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, oib);
		ps.executeUpdate();
	}

	@Override
	public Person getPerson(String oib) throws SQLException {
		String query = "SELECT * FROM korisnik WHERE oib=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, oib);
		Person person = new Person();
		ResultSet rs = ps.executeQuery();
		boolean check = false;
		while (rs.next()) {
			check = true;
			person.setOib(rs.getString("oib"));
			person.setFirstName(rs.getString("firstName"));
			person.setLastName(rs.getString("lastName"));
			person.setWorkplace(rs.getString("workplace"));
			person.setPassword(rs.getString("pwd"));
			person.setRole(rs.getString("usr_role"));
		}
		return (check == true) ? person : null;
	}

	@Override
	public List<Person> getByWorkPlace(String workplace) throws SQLException {
		String query = "SELECT * FROM korisnik WHERE workplace=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, workplace);
		ResultSet rs = ps.executeQuery();
		List<Person> ls = new ArrayList<Person>();
		while (rs.next()) {
			Person person = new Person();
			person.setOib(rs.getString("oib"));
			person.setFirstName(rs.getString("firstName"));
			person.setLastName(rs.getString("lastName"));
			person.setWorkplace(rs.getString("workplace"));
			person.setPassword(rs.getString("pwd"));
			person.setRole(rs.getString("usr_role"));
			ls.add(person);
		}
		return ls;
	}

	@Override
	public List<Person> getPeople() throws SQLException {
		String query = "SELECT * FROM korisnik";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<Person> ls = new ArrayList<Person>();
		while (rs.next()) {
			Person person = new Person();
			person.setOib(rs.getString("oib"));
			person.setFirstName(rs.getString("firstName"));
			person.setLastName(rs.getString("lastName"));
			person.setWorkplace(rs.getString("workplace"));
			person.setPassword(rs.getString("pwd"));
			person.setRole(rs.getString("usr_role"));
			ls.add(person);
		}
		return ls;
	}

	@Override
	public void updatePerson(Person person) throws SQLException {
		String query = "UPDATE korisnik SET oib=?, firstName=?, lastName=?, workplace=?, pwd=?, usr_role=? WHERE oib=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, person.getOib());
		ps.setString(2, person.getFirstName());
		ps.setString(3, person.getLastName());
		ps.setString(4, person.getWorkplace());
		ps.setString(5, person.getPassword());
		ps.setString(6, person.getRole());
		ps.setString(7, person.getOib());
		ps.executeUpdate();
	}

}
