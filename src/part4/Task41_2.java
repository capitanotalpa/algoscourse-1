package part4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Good {
    public Double w;
    public Double c;

    public Good(double w, double c) {
        this.c = c;
        this.w = w;
    }
}

public class Task41_2 {
    public static void endlessBackpack(List<Good> goods, Double capacity) {
        double worth = 0;
        double currentCap = 0;
        goods.sort(Collections.reverseOrder((o1, o2) -> Double.compare(o1.w / o1.c, o2.w / o2.c)));
        for (Good g: goods) {
            if (currentCap == capacity) break;
            if (currentCap + g.c > capacity) {
                // System.out.println("Adding " + (capacity - currentCap) + " of good with worth " + g.w + ", remaining part of w: " + (capacity - currentCap) * g.w / g.c);
                worth += (capacity - currentCap) * g.w / g.c;
                currentCap += capacity - currentCap;
                // System.out.println("Current cap: " + currentCap + ", current worth: " + worth);
                break;
            } else {
                // System.out.println("Adding " + g.c + " of good with worth " + g.w);
                worth += g.c > 0 ? g.w : 0;
                currentCap += g.c;
                // System.out.println("Current cap: " + currentCap + ", current worth: " + worth);
            }
        }
        System.out.printf("%.3f\n", worth);
        // System.out.println(currentCap);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        Double volume = s.nextDouble();
        ArrayList<Good> goods = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            goods.add(new Good(s.nextDouble(), s.nextDouble()));
        }
        Task41_2.endlessBackpack(goods, volume);
    }
}
