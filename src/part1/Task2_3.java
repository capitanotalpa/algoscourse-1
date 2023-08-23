package part1;

import java.util.Scanner;

public class Task2_3 {
    public static int euclidGCD(int n, int m) {
        if (n == 0) {
            return m;
        }
        if (m == 0) {
            return n;
        }
        if (n >= m) {
            return euclidGCD(n % m, m);
        }
        return euclidGCD(n, m % n);
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        System.out.print(Task2_3.euclidGCD(n, m));
    }
}
