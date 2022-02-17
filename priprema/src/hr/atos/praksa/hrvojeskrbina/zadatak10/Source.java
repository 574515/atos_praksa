package hr.atos.praksa.hrvojeskrbina.zadatak10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.*;

public class Source {

    public static void main(String[] args) {
        String path, phrase;
        Scanner input = new Scanner(System.in);
        path = getPath(input);
        phrase = getPhrase(input);
        input.close();
        File files = new File(path);
        pathContainsPhrase(files.listFiles(), phrase);
    }

    private static String getPath(Scanner input) {
        String path = "";
        boolean isValid = false;
        do {
            System.out.print("Unesite putanju: ");
            path = input.next();
            isValid = isValidPath(path);
        } while (!isValid);
        return path;
    }

    private static boolean isValidPath(String path) {
        File file = new File(path);
        return file.isDirectory();
    }

    private static String getPhrase(Scanner input) {
        String phrase = "";
        boolean isValid = false;
        do {
            System.out.print("Unesite frazu: ");
            phrase = input.next();
            isValid = isValidRegEx(phrase);
        } while (!isValid);
        return phrase;
    }

    private static boolean isValidRegEx(String phrase) {
        String regex = "^[A-Za-z0-9 _!-.?]*[A-Za-z0-9_!-.?][A-Za-z0-9 _!-.?]*$";
        Pattern p = Pattern.compile(regex);
        if (phrase == null)
            return false;
        Matcher m = p.matcher(phrase);
        return m.matches();
    }

    private static void pathContainsPhrase(File[] files, String phrase) {
        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String readString;
                try {
                    while ((readString = reader.readLine()) != null)
                        if (readString.contains(phrase)) System.out.println(file.getPath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
