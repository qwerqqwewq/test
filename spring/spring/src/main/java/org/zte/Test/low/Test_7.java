package org.zte.Test.low;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringJoiner;

//字符串重新排列
public class Test_7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = sc.nextLine().split(" ");
        System.out.println(getResult(arr));
    }

    public static String getResult(String[] arr) {
        arr =
                Arrays.stream(arr)
                        .map(
                                str -> {
                                    char[] cArr = str.toCharArray();
                                    Arrays.sort(cArr);
                                    return new String(cArr);
                                })
                        .toArray(String[]::new);

        HashMap<String, Integer> count = new HashMap<>();
        for (String s : arr) {
            count.put(s, count.getOrDefault(s, 0) + 1);
        }

        Arrays.sort(
                arr,
                (a, b) ->
                        !count.get(a).equals(count.get(b))
                                ? count.get(b) - count.get(a)
                                : a.length() != b.length() ? a.length() - b.length() : a.compareTo(b));

        StringJoiner sj = new StringJoiner(" ", "", "");
        for (String s : arr) {
            sj.add(s);
        }
        return sj.toString();
    }
}
