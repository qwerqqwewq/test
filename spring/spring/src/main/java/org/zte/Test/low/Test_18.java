package org.zte.Test.low;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

//最长密码
public class Test_18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = sc.nextLine().split(" ");
        System.out.println(getResult(arr));
    }

    public static String getResult(String[] arr) {
        Arrays.sort(arr, (a, b) -> a.length() != b.length() ? a.length() - b.length() : a.compareTo(b));

        LinkedList<String> link = new LinkedList<>(Arrays.asList(arr));
        HashSet<String> set = new HashSet<>(link);

        String ans = "";

        while (link.size() > 0) {
            String str = link.removeLast();
            int end = str.length() - 1;

            if(end == 0 && "".equals(ans)) {
                ans = str;
                continue;
            }

            while (set.contains(str.substring(0, end))) {
                if (end == 1) {
                    return str;
                } else {
                    end--;
                }
            }
        }

        return ans;
    }
}