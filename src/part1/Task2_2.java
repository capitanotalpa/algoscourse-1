package part1;

import java.util.Scanner;
import java.util.ArrayList;

public class Task2_2 {
    public static long fibPisano(long n, int m) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        ArrayList<Long> cycle = new ArrayList<>(10);
        cycle.add(0, 0L);
        cycle.add(1, 1L);
        long period = n;
        long current = 0;
        for (int i = 2; i <= n; i++) {
            long prev1 = cycle.get(i - 1);
            long prev2 = cycle.get(i - 2);
            current = (prev1 + prev2) % m;
            cycle.add(current);
            if (prev2 == 0L && prev1 == 1L && i != 2L) {
                period = i - 2L;
                break;
            }
        }
        if (period == n) {
            return current;
        }
        long cycleIndex = n % period;
        return cycle.get((int)cycleIndex);
    }

    public static int fibLastDigit(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int prev2 = 0;
        int prev1 = 1;
        int current = 0;
        for (int i = 2; i <= n; i++) {
            current = (prev1 + prev2) % 10;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }

    public static int fibBuffer(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int prev2 = 0;
        int prev1 = 1;
        int current = 0;
        for(int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
        }
        return current;
    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n = s.nextLong();
        int m = s.nextInt();
        System.out.print(Task2_2.fibPisano(n, m));
    }
}
