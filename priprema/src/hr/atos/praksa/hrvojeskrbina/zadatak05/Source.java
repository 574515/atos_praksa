package hr.atos.praksa.hrvojeskrbina.zadatak05;

public class Source {

    private static int interval_start = -25, interval_end = 131;

    public static void main(String[] args) {
        int counter = 0;
        if (!checkInterval())
            System.out.println("Pogreska intervala. Pocetak mora biti manji od 10, a kraj veci od 100.");
        else {
            for (int i = interval_start; i < interval_end; i++) {
                if (i <= 18)
                    counter += 4;
                if (i % 20 == 0)
                    continue;
                if (i > 18)
                    counter -= 1;
                if (i >= 75) {
                    System.out.println(counter);
                    break;
                }
            }
        }
    }

    public static boolean checkInterval() {
        return (interval_start < 10 && interval_end > 100);
    }

}
