package part4;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
class RightEndComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] odin, int[] dva) {
        if (odin[1] < dva[1]) return -1;
        else if (odin[1] == dva[1]) return 0;
        else return 1;
    }
}

public class Task41_1 {
    public static void pointsOnSegments(ArrayList<int[]> segments) {
        segments.sort(new RightEndComparator());
        ArrayList<Integer> result = new ArrayList<>();
        int currentResult = segments.get(0)[1];
        result.add(currentResult);
        for (int i = 1; i < segments.size(); i++) {
            int[] seg = segments.get(i);
            int l = seg[0];
            int r = seg[1];
            if (l > currentResult) {
                currentResult = r;
                result.add(currentResult);
            }
        }
        System.out.println(result.size());
        for (int res: result) {
            System.out.print(res + " ");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        ArrayList<int[]> segments = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            int[] seg = new int[2];
            seg[0] = s.nextInt();
            seg[1] = s.nextInt();
            segments.add(seg);
        }
        Task41_1.pointsOnSegments(segments);
    }
}
