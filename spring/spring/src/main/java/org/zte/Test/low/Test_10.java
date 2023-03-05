package org.zte.Test.low;

import java.util.Arrays;
import java.util.Scanner;

//不含101的数
public class Test_10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int L = sc.nextInt();
        int R = sc.nextInt();
        int count = digitSearch(R) - digitSearch(L - 1);
        System.out.println(count);
    }

    public static int digitSearch(int num) {
        Integer[] arr =
                Arrays.stream(Integer.toBinaryString(num).split(""))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);

        int[][][] f = new int[arr.length][2][2];

        return dfs(0, true, f, arr, 0, 0);
    }

    public static int dfs(int p, boolean limit, int[][][] f, Integer[] arr, int pre, int prepre) {
        if (p == arr.length) return 1;

        if (!limit && f[p][pre][prepre] != 0) return f[p][pre][prepre];

        int max = limit ? arr[p] : 1;
        int count = 0;

        for (int i = 0; i <= max; i++) {
            if (i == 1 && pre == 0 && prepre == 1) continue;
            count += dfs(p + 1, limit && i == max, f, arr, i, pre);
        }

        if (!limit) f[p][pre][prepre] = count;

        return count;
    }
}
