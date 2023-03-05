package org.zte.Test.high;

import java.util.Arrays;
import java.util.Scanner;

//开放日活动，取出尽量少的球
public class Test_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sum = sc.nextInt();
        int n = sc.nextInt();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getResult(sum, arr, n));
    }

    public static String getResult(int sum, Integer[] arr, int n) {
        int total = Arrays.stream(arr).reduce((p, c) -> p + c).get();
        if (total <= sum) return "[]";

        int max_maxCapacity = Arrays.stream(arr).max((a, b) -> a - b).get();
        int min_maxCapacity = sum / n;

        final int min_maxCapacity_copy = min_maxCapacity;
        Integer[] ans =
                Arrays.stream(arr)
                        .map(count -> count > min_maxCapacity_copy ? count - min_maxCapacity_copy : 0)
                        .toArray(Integer[]::new);

        while (max_maxCapacity - min_maxCapacity > 1) {
            int maxCapacity = (max_maxCapacity + min_maxCapacity) / 2;

            // tmp数组保存的是每个桶移除的球的数量
            Integer[] tmp = new Integer[n];
            int remain = total;
            for (int i = 0; i < arr.length; i++) {
                // r是每个桶需要移除的球的个数，如果桶内球数超过maxCapacity，则需要移除超出部分，否则不需要移除
                int r = arr[i] > maxCapacity ? arr[i] - maxCapacity : 0;
                remain -= r;
                tmp[i] = r;
            }

            if (remain > sum) {
                max_maxCapacity = maxCapacity;
            } else if (remain < sum) {
                min_maxCapacity = maxCapacity;
                ans = tmp;
            } else {
                ans = tmp;
                break;
            }
        }

        return Arrays.toString(ans);
    }
}