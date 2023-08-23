package part4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task41_3 {
    public static List<Integer> numbersCumSum (int N) {
        ArrayList<Integer> result = new ArrayList<>();
        if (N == 1) {
            result.add(1);
            return result;
        } else if (N == 2) {
            result.add(2);
            return result;
        } else {
            int currentSum = 0;
            int i = 1;
            while (currentSum != N) {
                if (N - currentSum <= i * 2) {
                    result.add(N - currentSum);
                    break;
                }
                result.add(i);
                currentSum += i;
                i++;
            } 
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        List<Integer> res = Task41_3.numbersCumSum(N);
        System.out.println(res.size());
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
        }
    }
}
