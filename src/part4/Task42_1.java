package part4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Pair {
    private String c;
    private int p;
    private String code;

    public Pair(String c, int p, String code) {
        this.c = c;
        this.p = p;
        this.code = code;
    }

    public String getC() {
        return c;
    }

    public int getP() {
        return p;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Pair<" + this.c + ", " + this.p + ", " + (code == null ? '-' : code) + ">";
    }
}

class PairComparator implements Comparator<Pair> {
    boolean reverse = false;
    public PairComparator(boolean reverse) {
        this.reverse = reverse;
    }
    @Override
    public int compare(Pair left, Pair right) {
        if (left.getP() < right.getP()) return -1 * (this.reverse ? -1 : 1 );
        else if (left.getP() == right.getP()) return 0;
        else return 1 * (this.reverse ? -1 : 1 );
    }
}

class NaivePriorityQueue {
    ArrayList<Pair> queue;
    boolean reverse = false;

    public NaivePriorityQueue(ArrayList<Pair> queue, boolean reverse) {
        queue.sort(new PairComparator(reverse));
        this.queue = queue;
        this.reverse = reverse;
    }

    public Pair extractMin() {
        queue.sort(new PairComparator(reverse));
        return queue.remove(0);
    }

    public void insert(Pair pair) {
        this.queue.add(pair);
    }

    public int getSize() {
        return queue.size();
    }

    public ArrayList<Pair> getList() {
        queue.sort(new PairComparator(reverse));
        return queue;
    }
}

public class Task42_1 {

    private NaivePriorityQueue pQueue;
    private Map<String, Integer> freqs;
    private ArrayList<Pair> freqsList;
    private Map<Character, String> codes;
    private int N;
    private String resultingCode;

    public void getFreqs(String text) {
        this.freqs = new HashMap<>();
        ArrayList<Pair> result = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            String currentChar = String.valueOf(text.charAt(i));
            if (!freqs.containsKey(currentChar)) {
                freqs.put(currentChar, 1);
            } else {
                int currentFreq = freqs.get(currentChar);
                freqs.put(currentChar, currentFreq + 1);
            }
        }
        for (Map.Entry<String, Integer> entry: freqs.entrySet()) {
            result.add(new Pair(entry.getKey(), entry.getValue(), null));
        }
        this.freqsList = result;
        this.N = freqs.size();
    }

    public void generateHuffmanCode() {
        this.codes = new HashMap<>();
        if (this.N == 1) {
            this.codes.put(this.freqsList.get(0).getC().toCharArray()[0], "1");
            return;
        }
        this.pQueue = new NaivePriorityQueue(this.freqsList, false);
        ArrayList<Pair> allNodes = new ArrayList<>();
        for (int i = this.N; i < 2 * this.N - 1; i++) {
            Pair first = this.pQueue.extractMin();
            allNodes.add(new Pair(first.getC(), first.getP(), "0"));
            Pair second = this.pQueue.extractMin();
            allNodes.add(new Pair(second.getC(), second.getP(), "1"));
            Pair newNode = new Pair(first.getC() + second.getC(), first.getP() + second.getP(), null);
            this.pQueue.insert(newNode);
        }
        allNodes.sort(new PairComparator(true));
        for (Pair entry: allNodes) {
            String character = entry.getC();
            String code = "";
            for (Pair node: allNodes) {
                if (node.getC().contains(character)) {
                    code += node.getCode();
                }
            }
            this.codes.put(character.toCharArray()[0], code);
        }
    }

    public void encodeHuffman(String text) {
        resultingCode = "";
        for (Character c: text.toCharArray()) {
            resultingCode += this.codes.get(c);
        }
    }

    public void printAnswer() {
        System.out.println(this.N + " " + this.resultingCode.length());
        for (Map.Entry<Character, String> entry: this.codes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(resultingCode);
    }

    public Map<Character, String> getCodes() {
        return this.codes;
    }

    public static void main(String[] args) {
        Task42_1 program = new Task42_1();
        Scanner s = new Scanner(System.in);
        String text = s.nextLine();
        program.getFreqs(text);
        program.generateHuffmanCode();
        program.encodeHuffman(text);
        program.printAnswer();
        s.close();
    }
}
