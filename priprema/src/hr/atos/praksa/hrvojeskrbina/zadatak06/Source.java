package hr.atos.praksa.hrvojeskrbina.zadatak06;

public class Source {

    public static void main(String[] args) {
        printHeader();
        printTable();
        printFooter();
    }

    public static void printHeader() {
        System.out.println("-------------------------------");
        System.out.println(": : :  TABLICA  MNOZENJA  : : :");
        System.out.println("-------------------------------");
        System.out.print(" * |");
        for (int i = 1; i <= 9; i++)
            System.out.format("%3d", i);
        System.out.println("\n-------------------------------");
    }

    public static void printTable() {
        int i, j;
        for (i = 1; i <= 9; i++) {
            System.out.format("%2d |", i);
            for (j = 1; j <= 9; j++)
                System.out.format("%3d", i * j);
            System.out.println();
        }
    }

    public static void printFooter() {
        System.out.println("-------------------------------");
        System.out.println(":  :  :  :  :  :  :  :by Hrvoje");
        System.out.println("-------------------------------");
    }

}
