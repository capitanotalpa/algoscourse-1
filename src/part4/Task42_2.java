package part4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task42_2 {
    private Map<String, Character> codes;
    private int N;
    private String text = "";

    public void getUserInput(Scanner s) {
        this.codes = new HashMap<>();
        String firstLine = s.nextLine();
        String[] nums = firstLine.split(" ");
        N = Integer.parseInt(nums[0]);
        for (int i = 0; i < N; i++) {
            String line = s.nextLine();
            String[] charAndCode = line.split(": ");
            this.codes.put(charAndCode[1], charAndCode[0].charAt(0));
        }
        text = s.nextLine();
    }

    public void decodeHuffman() {
        String result = "";
        String currentWord = "";
        for (char c: text.toCharArray()) {
            currentWord += c;
            if (this.codes.get(currentWord) != null) {
                result += this.codes.get(currentWord);
                currentWord = "";
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Task42_2 program = new Task42_2();
        program.getUserInput(s);
        program.decodeHuffman();
    }
}
