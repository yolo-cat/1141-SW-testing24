package xdemo;
import java.util.Random;

/**
 * BubbleSort_Lab 範例程式，示範冒泡排序的基本寫法。
 * 本程式有 bug，請同學觀察與修正。
 */
public class BubbleSort_Lab {

    public static void main(String[] args) {
        int[] data; // 宣告一個整數陣列 data
        data = new int[10]; // 配置 10 個元素的空間給 data
        Random r = new Random(); // 建立亂數產生器 r

        for (int i = 0; i < 10; i++) { // 產生 10 個亂數填入 data
            data[i] = r.nextInt(100); // 亂數範圍 0~99
        }

        BubbleSort_Lab bs = new BubbleSort_Lab(); // 建立 BubbleSort_Lab 物件 bs

        bs.bubbleSort(data); // 呼叫 bubbleSort 方法進行排序
        System.out.println("Bubble sort complete"); // 顯示排序完成
    }

    public void bubbleSort(int[] data) {
        int length = data.length - 1; // 計算陣列最大索引值
        int temp = 0; // 宣告暫存變數 temp

        for (int path = 0; path < length; path++) { // 外層迴圈，控制排序輪數
            for (int i = 0; i < length - path; i++) { // 內層迴圈，逐一比較相鄰元素
                if (data[i] > data[i + 1]) { // 如果前一個比後一個大，則交換
                    temp = data[i]; // 將 data[i] 存到 temp
                    data[i] = data[i + 1]; // 修正：將 data[i+1] 存到 data[i]
                    data[i + 1] = temp; // 修正：將 temp 存到 data[i+1]
                }
            }
        }
    }
}