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
    public int add(Person person) throws SQLException {
        String query = "INSERT INTO korisnik VALUES (null, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, person.getFirstName());
        ps.setString(2, person.getLastName());
        ps.setString(3, person.getWorkplace());
        ps.setString(4, person.getOib());
        ps.setString(5, person.getPassword());
        int n = ps.executeUpdate();
        return n;
    }

    @Override
    public void delete(String oib) throws SQLException {
        String query = "DELETE FROM korisnik WHERE oib=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, oib);
        ps.executeUpdate();
    }

    @Override
    public Person getEmployee(String oib) throws SQLException {
        String query = "SELECT firstName, lastName, workplace, oib FROM korisnik WHERE oib=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, oib);
        Person person = new Person();
        ResultSet rs = ps.executeQuery();
        boolean check = false;
        while (rs.next()) {
            check = true;
            person.setFirstName(rs.getString("firstName"));
            person.setLastName(rs.getString("lastName"));
            person.setWorkplace(rs.getString("workplace"));
            person.setOib(rs.getString("oib"));
        }
        return (check == true) ? person : null;
    }

    @Override
    public List<Person> getEmployees() throws SQLException {
        String query = "SELECT firstName, lastName, workplace, oib FROM korisnik";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Person> ls = new ArrayList<Person>();
        while (rs.next()) {
            Person person = new Person();
            person.setFirstName(rs.getString("firstName"));
            person.setLastName(rs.getString("lastName"));
            person.setWorkplace(rs.getString("workplace"));
            person.setOib(rs.getString("oib"));
            ls.add(person);
        }
        return ls;
    }

    @Override
    public void update(Person person) throws SQLException {
        String query = "UPDATE korisnik SET firstName=?, lastName=?, workplace=?, password=? WHERE oib=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, person.getFirstName());
        ps.setString(2, person.getLastName());
        ps.setString(3, person.getWorkplace());
        ps.setString(4, person.getOib());
        ps.setString(5, person.getPassword());
        ps.executeUpdate();
    }
}
