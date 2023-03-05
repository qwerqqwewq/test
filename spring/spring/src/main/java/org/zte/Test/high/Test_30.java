package org.zte.Test.high;

import java.util.Arrays;
import java.util.Scanner;

//蓄水库
public class Test_30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] h =
                Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(h));
    }

    public static String getResult(Integer[] h) {
        Integer[] ans = new Integer[] {0, 0, 0};

        int l = 0;
        int r = h.length - 1;
        int maxSum = 0;

        while (l < r) {
            int sum = 0;
            int lower = Math.min(h[l], h[r]);

            for (int i = l; i <= r; i++) {
                sum += Math.max(0, lower - h[i]);
            }

            if (sum >= maxSum) {
                ans = new Integer[] {l, r, sum};
                maxSum = sum;
            }

            if (h[l] < h[r]) {
                if (h[r - 1] >= h[r]) r--;
                else l++;
            } else if (h[l] > h[r]) {
                if (h[l + 1] >= h[l]) l++;
                else r--;
            } else {
                if (h[l + 1] > h[r - 1]) l++;
                else r--;
            }
        }

        if (ans[2] == 0) return "0";
        return ans[0] + " " + ans[1] + ":" + ans[2];
    }
}