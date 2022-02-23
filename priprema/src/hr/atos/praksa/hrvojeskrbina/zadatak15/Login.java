package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {

    private final JPanel PANEL;
    private final JLabel OIB_LABEL, PWD_LABEL;
    private final JTextField OIB_TEXT;
    private final JPasswordField PWD_TEXT;
    private final JButton SUBMIT, RESET;
    private final PersonDAOImplemenation PDAOI = new PersonDAOImplemenation();

    Login() {
        OIB_LABEL = new JLabel();
        OIB_LABEL.setText("OIB:");
        OIB_TEXT = new JTextField(11);
        OIB_TEXT.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        PWD_LABEL = new JLabel();
        PWD_LABEL.setText("Lozinka:");
        PWD_TEXT = new JPasswordField();
        SUBMIT = new JButton("Login");
        RESET = new JButton("Obrisi");
        RESET.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OIB_TEXT.setText("");
                OIB_TEXT.setBackground(Color.WHITE);
                PWD_TEXT.setText("");
                PWD_TEXT.setBackground(Color.WHITE);
            }
        });
        PANEL = new JPanel(new GridLayout(4, 2));
        PANEL.add(OIB_LABEL);
        PANEL.add(OIB_TEXT);
        PANEL.add(PWD_LABEL);
        PANEL.add(PWD_TEXT);
        PANEL.add(SUBMIT);
        PANEL.add(RESET);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SUBMIT.addActionListener(this);
        add(PANEL, BorderLayout.CENTER);
        PANEL.setBorder(new EmptyBorder(10, 10, 10, 10));
        setTitle("Dobrodosli! Ulogirajte se.");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String oib = OIB_TEXT.getText();
        String password = String.valueOf(PWD_TEXT.getPassword());
        Menus menu = new Menus();
        try {
            List<Person> people = PDAOI.getPeople();
            for (Person person : people) {
                if (oib.trim().equals(person.getOib())) {
                    if (password.trim().equals(person.getPassword())) {
                        Menus.loggedUser = person;
                        this.dispose();
                        if (person.getRole().equals("admin")) {
                            menu.adminMenu(person);
                        } else if (person.getRole().equals("superuser")) {
                            menu.suMenu(person);
                        } else {
                            menu.userMenu(person);
                        }
                    }
                } else {
                    OIB_TEXT.requestFocus();
                    OIB_TEXT.setBackground(Color.RED);
                    PWD_TEXT.setBackground(Color.RED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
