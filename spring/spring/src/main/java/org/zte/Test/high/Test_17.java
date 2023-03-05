package org.zte.Test.high;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

//最差产品奖
public class Test_17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        Integer[] arr =
                Arrays.stream(sc.next().split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(arr, m));
    }

    public static String getResult(Integer[] arr, int m) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            min = Math.min(min, arr[i]);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(min);

        int j = m;
        while (j < arr.length) {
            if (arr[j - m] > min) {
                min = Math.min(min, arr[j]);
            } else {
                if (arr[j] <= min) {
                    min = arr[j];
                } else {
                    min = arr[j - m + 1];
                    for (int i = j - m + 2; i <= j; i++) {
                        min = Math.min(min, arr[i]);
                    }
                }
            }

            ans.add(min);
            j++;
        }

        StringJoiner sj = new StringJoiner(",");

        for (Integer an : ans) {
            sj.add(an + "");
        }

        return sj.toString();
    }
}
