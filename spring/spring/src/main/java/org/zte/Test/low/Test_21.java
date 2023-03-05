package org.zte.Test.low;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

//打印机队列
public class Test_21 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[][] matrix = new String[n][];

        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            matrix[i] = s;
        }

        getResult(matrix);
    }

    public static void getResult(String[][] matrix) {
        // print中存放每台打印机的等待队列
        HashMap<String, PriorityQueue<Integer[]>> print = new HashMap<>();

        // 文件的编号定义为”IN P NUM”事件发生第 x 次，此处待打印文件的编号为x。编号从1开始。
        int x = 1;
        for (String[] task : matrix) {
            // IN,OUT都有type和printId
            String type = task[0];
            String printId = task[1];

            if ("IN".equals(type)) {
                // IN还有priority
                String priority = task[2];
                // arr是打印任务
                Integer[] arr = {x, Integer.parseInt(priority)};
                // 为打印机printId设置打印优先级，打印任务的priority越大，优先级越高
                print.putIfAbsent(printId, new PriorityQueue<>((a, b) -> b[1] - a[1]));
                // 将打印任务加入对应打印机
                print.get(printId).offer(arr);
                x++;
            } else {
                // 打印机等待队列中取出优先级最高的打印任务arr
                Integer[] arr = print.get(printId).poll();
                if (arr != null) {
                    // arr[0]是x
                    System.out.println(arr[0]);
                } else {
                    // 如果此时没有文件可以打印，请输出”NULL“。
                    System.out.println("NULL");
                }
            }
        }
    }
}
