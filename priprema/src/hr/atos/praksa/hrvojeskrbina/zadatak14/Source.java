package hr.atos.praksa.hrvojeskrbina.zadatak14;

import javax.swing.JOptionPane;

public class Source extends Integrator {

    public static void main(String[] args) {
        Source src = new Source();
        double[] variables = getVariableInput();
        int N = 2000;
        src.setType(getTypeChoice());
        JOptionPane.showMessageDialog(null, "Površina ispod krivulje iznosi " + src.integrate(variables, N) + ".");
    }

    protected static double[] getVariableInput() {
        double[] variables = new double[4]; // A, B, T1, T2
        variables[0] = getInput("A");
        variables[1] = getInput("B");
        variables[2] = getInput("T1");
        do {
            variables[3] = getInput("T2");
            if (variables[3] <= variables[2])
                JOptionPane.showMessageDialog(null, "Unesite broj veći od " + variables[2], "Pogreška unosa",
                        JOptionPane.ERROR_MESSAGE);
        } while (variables[3] <= variables[2]);
        return variables;
    }

    protected static double getInput(String variable) {
        String inputNumber = JOptionPane.showInputDialog("Unesite " + variable);
        double num;
        boolean error = false;
        while (!error) {
            try {
                num = Double.parseDouble(inputNumber);
                if (num >= 0.0d)
                    return num;
            } catch (NumberFormatException e) {
                inputNumber = JOptionPane.showInputDialog("Unesite " + variable);
            }
        }
        return -1.0d;
    }

    protected static String getTypeChoice() {
        String[] choices = { "sin", "cos", "tan", "cot" };
        String input = (String) JOptionPane.showInputDialog(null, "Tip funkcije", "Odaberite tip funkcije",
                JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
        return input;
    }

}
