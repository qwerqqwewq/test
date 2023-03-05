package org.zte.Test.low;

import java.util.ArrayList;
import java.util.Scanner;

//单词倒序
public class Test_28 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        System.out.println(getResult(str));
    }

    public static String getResult(String str) {
        ArrayList<Integer> idxs = new ArrayList<>();

        idxs.add(-1);
        for (int i = 0; i < str.length(); i++) {
            if (",.? ".indexOf(str.charAt(i)) != -1) {
                idxs.add(i);
            }
        }
        idxs.add(str.length());

        char[] chars = str.toCharArray();

        for (int i = 0; i < idxs.size() - 1; i++) {
            int l = idxs.get(i) + 1;
            int r = idxs.get(i + 1) - 1;

            while (l < r) {
                char tmp = chars[l];
                chars[l] = chars[r];
                chars[r] = tmp;
                l++;
                r--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }
}