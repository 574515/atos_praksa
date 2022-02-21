package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class HandleUsers {

    private final String[] ROLES = { "admin", "superuser", "user" };
    private Parsers parser = new Parsers();
    private PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();

    protected void addNewUser() throws SQLException {
        Person person = new Person();
        Boolean exists = true;
        String oib;

        person.setFirstName(
                JOptionPane.showInputDialog(null, "Unesite ime", "Ime zaposlenika", JOptionPane.QUESTION_MESSAGE));
        if (person.getFirstName() == null)
            return;
        person.setLastName(JOptionPane.showInputDialog(null, "Unesite prezime", "Prezime zaposlenika",
                JOptionPane.QUESTION_MESSAGE));
        if (person.getLastName() == null)
            return;
        person.setWorkplace(JOptionPane.showInputDialog(null, "Unesite mjesto rada", "Mjesto rada zaposlenika",
                JOptionPane.QUESTION_MESSAGE));
        if (person.getWorkplace() == null)
            return;
        do {
            oib = JOptionPane.showInputDialog(null, "Unesite OIB", "OIB zaposlenika", JOptionPane.QUESTION_MESSAGE);
            exists = parser.checkIfOibExists(oib, pdaoi);
            if (oib == null)
                return;
            else if (exists)
                JOptionPane.showMessageDialog(null, "Korisnik sa OIB-om [" + oib + "] vec postoji.", "Pogreska unosa.",
                        JOptionPane.ERROR_MESSAGE);
        } while (exists || !parser.checkOib(oib));
        person.setOib(oib);
        person.setPassword(JOptionPane.showInputDialog(null, "Unesite lozinku", "Lozinka zaposlenika",
                JOptionPane.QUESTION_MESSAGE));
        if (person.getPassword() == null)
            return;
        if (parser.firstPerson(pdaoi))
            person.setRole("admin");
        else
            person.setRole(
                    (String) JOptionPane.showInputDialog(null, "Uloga korisnika", "Odaberite ulogu korisnika",
                            JOptionPane.QUESTION_MESSAGE, null, ROLES, ROLES[0]));
        if (person.getRole() == null)
            return;
        pdaoi.addPerson(person);
    }

    protected void listAllUsers() throws SQLException {
        List<Person> people = pdaoi.getPeople();

        System.out.println("--------------------------");
        people.forEach((p) -> {
            System.out.println(p.toString());
            System.out.println("--------------------------");
        });
    }

    protected void updateUser() throws SQLException {
        Person person;
        String oib;
        Boolean format, exists = false;

        do {
            oib = JOptionPane.showInputDialog("Unesite OIB");
            exists = parser.checkIfOibExists(oib, pdaoi);
            if (oib == null)
                return;
            else if (!exists)
                JOptionPane.showMessageDialog(null, "Korisnik sa OIB-om [" + oib + "] ne postoji.", "Nije pronadjen.",
                        JOptionPane.ERROR_MESSAGE);
        } while (!exists || !parser.checkOib(oib));
        person = pdaoi.getPerson(oib);
        person.setFirstName(JOptionPane.showInputDialog("Unesite ime", person.getFirstName()));
        if (person.getFirstName() == null)
            return;
        person.setLastName(JOptionPane.showInputDialog("Unesite prezime", person.getLastName()));
        if (person.getLastName() == null)
            return;
        person.setWorkplace(JOptionPane.showInputDialog("Unesite mjesto rada", person.getWorkplace()));
        if (person.getWorkplace() == null)
            return;
        do {
            oib = JOptionPane.showInputDialog("Unesite OIB", person.getOib());
            if (oib == null)
                return;
            format = parser.checkOib(oib);
            if (!format)
                JOptionPane.showMessageDialog(null, "Format OIB-a [" + oib + "] nije dobar.", "Nije pronadjen.",
                        JOptionPane.ERROR_MESSAGE);
        } while (!format);
        person.setOib(oib);
        person.setPassword(JOptionPane.showInputDialog("Unesite lozinku", person.getPassword()));
        if (person.getPassword() == null)
            return;
        System.out.println(Menus.loggedUser.toString());
        if (Menus.loggedUser.getOib().equals(oib)) {
            JOptionPane.showMessageDialog(null, "Ne mozete sami sebi promijeniti ulogu.", "Nije dozvoljeno.",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            person.setRole(
                    (String) JOptionPane.showInputDialog(null, "Uloga korisnika", "Odaberite ulogu korisnika",
                            JOptionPane.QUESTION_MESSAGE, null, ROLES, person.getRole()));
            if (person.getRole() == null)
                return;
        }
        pdaoi.updatePerson(person);
    }

    protected void deleteUser() throws SQLException {
        TaskUserDAOImplementation tudaoi = new TaskUserDAOImplementation();
        Person person;
        String oib;
        Boolean exists = false;

        do {
            oib = JOptionPane.showInputDialog("Unesite OIB");
            exists = parser.checkIfOibExists(oib, pdaoi);
            if (oib == null)
                return;
            else if (!exists)
                JOptionPane.showMessageDialog(null, "Korisnik sa OIB-om [" + oib + "] ne postoji.", "Nije pronadjen.",
                        JOptionPane.ERROR_MESSAGE);
        } while (!exists || !parser.checkOib(oib));

        List<Task> tasks = tudaoi.getTasks(oib);
        int answer, numOfTasks = tasks.size();

        person = pdaoi.getPerson(oib);
        answer = JOptionPane.showConfirmDialog(null,
                "Obrisi korisnika [" + person.getFullName() + " - OIB: " + person.getOib() + "]\nKorisnik ima "
                        + numOfTasks + " zadatak(a).",
                "Jeste li sigurni?",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            tudaoi.deleteTUPerUser(oib);
            pdaoi.deletePerson(oib);
            if (parser.firstPerson(pdaoi))
                System.exit(0);
        } else
            return;
    }

    protected void getByWorkplace() throws SQLException {
        String workplace = JOptionPane.showInputDialog("Unesite radno mjesto");
        if (workplace == null)
            return;
        List<Person> people = pdaoi.getByWorkPlace(workplace);
        JOptionPane.showMessageDialog(null, "Broj radnika: " + people.size(), "Rezultati za [" + workplace + "]",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
