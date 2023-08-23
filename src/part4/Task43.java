package part4;

import java.util.ArrayList;
import java.util.Scanner;

class MaxHeap {
    private ArrayList<Integer> tree;

    public MaxHeap(int n) {
        tree = new ArrayList<>(n);
    }

    public void insert(int priority) {
        tree.add(priority);
        this.siftUp(tree.size() - 1);
    }

    public int extractMax() {
        if (tree.size() == 1) {
            int max = tree.get(0);
            tree.remove(0);
            return max;
        }
        int max = tree.get(0);
        int lastPriority = tree.get(tree.size() - 1);
        tree.set(0, lastPriority);
        tree.remove(tree.size() - 1);
        siftDown(0);
        return max;
    }

    @Override
    public String toString() {
        return tree.toString();
    }

    private void siftUp(int currentIndex) {
        if (currentIndex == 0) return;
        int topNodeIndex = getTopNodeIndex(currentIndex);
        int currentPriority = tree.get(currentIndex);
        int topNodePriority = tree.get(topNodeIndex);
        if (topNodePriority < currentPriority) {
            swapIndices(topNodeIndex, currentIndex);
            siftUp(topNodeIndex);
        }
    }

    private void siftDown(int currentIndex) {
        int leftNodeIndex = getLeftNodeIndex(currentIndex);
        int rightNodeIndex = getRightNodeIndex(currentIndex);
        int currentValue = tree.get(currentIndex);
        if (leftNodeIndex >= tree.size()) {
            return;
        } else if (rightNodeIndex >= tree.size()) {
            int leftValue = tree.get(leftNodeIndex);
            if (currentValue < leftValue) {
                swapIndices(leftNodeIndex, currentIndex);
            }
        } else {
            int leftValue = tree.get(leftNodeIndex);
            int rightValue = tree.get(rightNodeIndex);
            if (currentValue < leftValue && currentValue < rightValue) {
                if (leftValue > rightValue) {
                    swapIndices(leftNodeIndex, currentIndex);
                    siftDown(leftNodeIndex);
                } else {
                    swapIndices(rightNodeIndex, currentIndex);
                    siftDown(rightNodeIndex);
                }
            } else if (currentValue < leftValue) {
                swapIndices(leftNodeIndex, currentIndex);
                siftDown(leftNodeIndex);
            } else if (currentValue < rightValue) {
                swapIndices(rightNodeIndex, currentIndex);
                siftDown(rightNodeIndex);
            }
        }
    }

    private void swapIndices(int firstIndex, int secondIndex) {
        int firstValue = tree.get(firstIndex);
        int secondValue = tree.get(secondIndex);
        tree.set(firstIndex, secondValue);
        tree.set(secondIndex, firstValue);
    }

    private int getTopNodeIndex(int currentIndex) {
        return currentIndex / 2;
    }

    private int getLeftNodeIndex(int currentIndex) {
        return 2 * currentIndex;
    }

    private int getRightNodeIndex(int currentIndex) {
        return 2 * currentIndex + 1;
    }
}

public class Task43 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int operationsCount = s.nextInt();
        MaxHeap heap = new MaxHeap(10);
        for (int i = 0; i < operationsCount + 1; i++) {
            String instruction = s.nextLine();
            String[] split = instruction.split(" ");
            String operation = split[0];
            if (operation.equals("Insert")) {
                heap.insert(Integer.parseInt(split[1]));
            } else if (operation.equals("ExtractMax")) {
                System.out.println(heap.extractMax());
            }
        }
        s.close();
    }
}
