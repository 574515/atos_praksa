package hr.atos.praksa.hrvojeskrbina.zadatak15;

import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JPanel panel;
    JLabel oibLabel, pwdLabel;
    JTextField oibText;
    JPasswordField pwdText;
    JButton submit, cancel, reset;

    Login() {
        oibLabel = new JLabel();
        oibLabel.setText("OIB:");
        oibText = new JTextField(11);
        oibText.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                }
            }
        });
        pwdLabel = new JLabel();
        pwdLabel.setText("Lozinka:");
        pwdText = new JPasswordField();
        submit = new JButton("Login");
        reset = new JButton("Obrisi");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                oibText.setText("");
                oibText.setBackground(Color.WHITE);
                pwdText.setText("");
                pwdText.setBackground(Color.WHITE);
            }
        });
        panel = new JPanel(new GridLayout(4, 2));
        panel.add(oibLabel);
        panel.add(oibText);
        panel.add(pwdLabel);
        panel.add(pwdText);
        panel.add(submit);
        panel.add(reset);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setTitle("Dobrodosli! Ulogirajte se.");
        setSize(400, 200);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String oib = oibText.getText(), password = pwdText.getText();
        PersonDAOImplemenation pdaoi = new PersonDAOImplemenation();
        try {
            List<Person> people = pdaoi.getPeople();
            for (Person person : people) {
                if (oib.trim().equals(person.getOib())) {
                    if (password.trim().equals(person.getPassword())) {
                        Menus.loggedUser = person;
                        this.dispose();
                        if (person.getRole().equals("admin")) {
                            Menus.adminMenu(person);
                        } else if (person.getRole().equals("superuser")) {
                            Menus.suMenu(person);
                        } else {
                            Menus.userMenu(person);
                        }
                    }
                } else {
                    oibText.requestFocus();
                    oibText.setBackground(Color.RED);
                    pwdText.setBackground(Color.RED);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
