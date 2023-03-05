package org.zte.Test.high;

import java.util.HashMap;
import java.util.Scanner;

//优雅子数组
public class Test_25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getResult(arr, n, k));
    }

    public static Integer getResult(Integer[] arr, Integer n, Integer k) {
        int ans = 0;

        int l = 0;
        int r = 0;
        HashMap<Integer, Integer> count = new HashMap<>();

        while (l < n && r < n) {
            Integer c = arr[r];
            count.put(c, count.getOrDefault(c, 0) + 1);
            if (count.get(c) >= k) {
                ans += n - r;

                count.put(arr[l], count.get(arr[l]) - 1);
                l++;

                count.put(c, count.get(c) - 1);
                r--;
            }
            r++;
        }

        return ans;
    }
}
