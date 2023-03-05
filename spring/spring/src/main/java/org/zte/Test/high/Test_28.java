package org.zte.Test.high;

import java.util.Scanner;
import java.util.Arrays;

//士兵过河
public class Test_28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int t = sc.nextInt();

        int[] times = new int[n];

        for (int i = 0; i < n; i++) {
            times[i] = sc.nextInt();
        }

        System.out.println(getResult(n, t, times));
    }

    public static String getResult(int n, int t, int[] times) {
        Arrays.sort(times);

        int[] dp = new int[n];

        dp[0] = times[0];
        if (dp[0] > t) return "0 0";

        dp[1] = getMax(times[0], times[1]);
        if (dp[1] > t) return 1 + " " + dp[0];

        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1] + times[0] + getMax(times[0], times[i]),
                    dp[i - 2] + times[0] + getMax(times[i - 1], times[i]) + times[1] + getMax(times[0], times[1]));

            if (dp[i] > t) return i + " " + dp[i - 1];
        }

        return n + " " + dp[n - 1];
    }

    public static int getMax(int t1, int t2) {
        if (t1 * 10 < t2) {
            return t1 * 10;
        }
        return t2;
    }
}