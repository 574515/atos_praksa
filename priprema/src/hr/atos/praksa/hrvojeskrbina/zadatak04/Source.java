package hr.atos.praksa.hrvojeskrbina.zadatak04;

import java.util.Random;

public class Source {

    private static final int NUM = 5;
    private static int max = 100, min = 0;

    public static void main(String[] args) {
        int arr[] = getNumbers(new Random());
        int multiples[] = { 3, 5, 11 };
        for (int num : arr) {
            System.out.println(isEvenOrOdd(num) == true ? num + " je paran broj." : num + " je neparan broj.");
            for (int multiple : multiples)
                findMultiples(num, multiple);
            System.out.println("----------------------------------");
        }
    }

    private static boolean isEvenOrOdd(int number) {
        return (number % 2 == 0);
    }

    private static void findMultiples(int number, int multiple) {
        System.out.print((number % multiple == 0) ? number + " je visekratnik od " + multiple + ".\n" : "");
    }

    private static int[] getNumbers(Random rand) {
        int i, arr[] = new int[Source.NUM];
        for (i = 0; i < Source.NUM; i++)
            arr[i] = rand.nextInt(max - min) + min;
        return arr;
    }
}
