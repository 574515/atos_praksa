package hr.atos.praksa.hrvojeskrbina.zadatak09;

import java.util.Random;

public class Source {

    public static void main(String[] args) {

        int[] electricityBill = getRandomElectricityBill();
        getBillValue(electricityBill);
        int i, peak = 5000, prices[] = new int[6], increment = peak;
        for (i = 0; i < 6; i++) {
            if (i == 0)
                prices[i] = 4500;
            else
                prices[i] = increment -= 1000;
        }
        printTable(electricityBill, prices, peak);

    }

    public static void xPositionOnTable(int[] electricityBill, int price) {
        int[] monthPosition = new int[12];
        int i, j;
        for (i = 0; i <= 11; i++)
            monthPosition[i] = i * 3;
        i = j = 0;

        while (i <= 33) {
            if (electricityBill[j] == price) {
                if (i == monthPosition[j]) {
                    System.out.print(" x");
                    i += 1;
                    j++;
                } else {
                    System.out.print("  ");
                    i++;
                }
                System.out.print(" ");
                i += 2;
            } else
                j++;
            if (j == 12)
                break;
        }
    }

    public static void getBillValue(int[] electricityBill) {
        for (int i = 0; i < 12; i++) {
            if (electricityBill[i] <= 500)
                electricityBill[i] = 0;
            else if (electricityBill[i] > 500 && electricityBill[i] <= 1500)
                electricityBill[i] = 1000;
            else if (electricityBill[i] > 1500 && electricityBill[i] <= 2500)
                electricityBill[i] = 2000;
            else if (electricityBill[i] > 2500 && electricityBill[i] <= 3500)
                electricityBill[i] = 3000;
            else if (electricityBill[i] > 3500 && electricityBill[i] < 4500)
                electricityBill[i] = 4000;
            else if (electricityBill[i] >= 4500)
                electricityBill[i] = 4500;
        }
    }

    private static int[] getRandomElectricityBill() {
        Random rand = new Random();
        int i, months = 12;
        int[] electricityBill = new int[months];
        for (i = 0; i < months; i++)
            electricityBill[i] = rand.nextInt(5000);
        return electricityBill;
    }

    private static void printTable(int[] electricityBill, int[] prices, int peak) {
        System.out.format("%5dkn - |", peak);
        System.out.format("\n          |");
        xPositionOnTable(electricityBill, prices[0]);
        System.out.format("\n%5dkn - |", prices[1]);
        xPositionOnTable(electricityBill, prices[1]);
        for (int i = 2; i <= 5; i++) {
            System.out.format("\n          |");
            System.out.format("\n%5dkn - |", prices[i]);
            xPositionOnTable(electricityBill, prices[i]);
        }
        System.out.println("\n           -- -- -- -- -- -- -- -- -- -- -- --");
        System.out.println("            1  2  3  4  5  6  7  8  9 10 11 12");
    }

}
