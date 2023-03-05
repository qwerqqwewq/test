package org.zte.Test.high;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//最多等和不相交连续子序列
public class Test_9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getResult(arr, n));
    }

    public static int getResult(int[] arr, int n) {
        // 记录相同和连续子序列的区间
        HashMap<Integer, ArrayList<Integer[]>> ranges = new HashMap<>();

        // 求解arr数组的前缀和数组dp
        int[] dp = new int[n];
        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + arr[i];
        }

        // 利用前缀和求差，求出所有连续子序列的和
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == 0) {
                    int sum = dp[j];
                    ranges.putIfAbsent(sum, new ArrayList<>());
                    ranges.get(sum).add(new Integer[] {0, j});
                } else {
                    int sum = dp[j] - dp[i - 1];
                    ranges.putIfAbsent(sum, new ArrayList<>());
                    ranges.get(sum).add(new Integer[] {i, j});
                }
            }
        }

        //    for (int i = 0; i < n; i++) {
        //      int sum = arr[i];
        //      ranges.putIfAbsent(sum, new ArrayList<>());
        //      ranges.get(sum).add(new Integer[] {i, i});
        //
        //      for (int j = i + 1; j < n; j++) {
        //        sum += arr[j];
        //        ranges.putIfAbsent(sum, new ArrayList<>());
        //        ranges.get(sum).add(new Integer[] {i, j});
        //      }
        //    }

        // 保存相同和不相交连续子序列的最大个数
        int max = 0;
        for (Integer key : ranges.keySet()) {
            ArrayList<Integer[]> range = ranges.get(key);
            max = Math.max(max, disjoint(range));
        }

        return max;
    }

    //  求不相交区间的最大个数
    public static int disjoint(ArrayList<Integer[]> ranges) {
        int count = 1; // 至少一个
        ranges.sort((a, b) -> a[1] - b[1]);

        Integer t = ranges.get(0)[1];
        for (int i = 1; i < ranges.size(); i++) {
            Integer[] range = ranges.get(i);
            Integer l = range[0];
            Integer r = range[1];

            if (t < l) {
                count++;
                t = r;
            }
        }
        return count;
    }
}
