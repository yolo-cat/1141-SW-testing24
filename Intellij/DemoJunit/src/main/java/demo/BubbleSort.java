package demo;

import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        int[] data;
        data = new int[10];
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            data[i] = r.nextInt(100);
        }

        System.out.println("\nBefore sort");
        for (int x : data) {
            System.out.printf("%4d", x);
        }

        BubbleSort bs = new BubbleSort();

        bs.bubbleSort(data);
        System.out.println("\nAfter Sort");

        for (int x : data) {
            System.out.printf("%4d", x);
        }
    }

    public void bubbleSort(int[] data) {
        int length = data.length;
        int temp = 0;

        for (int path = 0; path < length - 1; path++) {
            for (int i = 0; i < length - path - 1; i++) {
                if (data[i] > data[i + 1]) {
                    temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                }
            }
        }
    }
}