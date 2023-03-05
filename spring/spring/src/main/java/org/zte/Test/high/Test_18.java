package org.zte.Test.high;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//去除多余空格
public class Test_18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        Integer[][] ranges =
                Arrays.stream(sc.nextLine().split(","))
                        .map(s -> Arrays.stream(s.split(" "))
                                .map(Integer::parseInt)
                                .toArray(Integer[]::new))
                        .toArray(Integer[][]::new);

        getResult(str, ranges);
    }

    public static void getResult(String str, Integer[][] ranges) {
        boolean quotaStart = false;
        ArrayList<Integer> needDel = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (i > 0 && ' ' == str.charAt(i) && ' ' == str.charAt(i - 1) && !quotaStart) {
                needDel.add(i);
            }

            if ('\'' == str.charAt(i)) {
                quotaStart = !quotaStart;
            }
        }

        char[] cArr = str.toCharArray();
        Integer[][] ans = Arrays.stream(ranges).map(Integer[]::clone).toArray(Integer[][]::new);

        for (Integer del : needDel) {
            cArr[del] = '\u0000';
            for (int i = 0; i < ranges.length; i++) {
                int start = ranges[i][0];
                if (del < start) {
                    ans[i][0]--;
                    ans[i][1]--;
                }
            }
        }

        System.out.println(new String(cArr).replace("\u0000", ""));

        StringBuilder sb = new StringBuilder();
        for (Integer[] an : ans) {
            sb.append(Arrays.toString(an));
        }
        System.out.println(sb);


    }
}