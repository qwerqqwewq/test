package org.zte.Test.low;

import java.util.HashMap;
import java.util.Scanner;

//完美走位
public class Test_5 {
    public static void main(String[] args) {
        System.out.println("please input the string:");
        Scanner in = new Scanner(System.in);
        String word = in.nextLine();
        int size = word.length();
        if (size % 4 != 0) {
            System.out.println("cannot be a perfect");
            System.exit(0);
        }
        HashMap<Character, Integer> count = new HashMap<>();
        for (int i = 0; i < size; i++) {
            char c = word.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        int avg = size / 4;
        int total = 0;
        boolean flag = true;

        for (Character c : count.keySet()) {
            if (count.get(c) > avg) {
                flag = false;
                count.put(c, count.get(c) - avg);
                total += count.get(c);
            } else {
                count.put(c, 0);
            }
        }

        if (flag) {
            System.out.println(0);
            System.exit(0);
        }

        int i = 0;
        int j = 0;
        int minLen = word.length() + 1;

        while (j < word.length()) {
            Character jc = word.charAt(j);

            if (count.get(jc) > 0) {
                total--;
            }
            count.put(jc, count.get(jc) - 1);

            while (total == 0) {
                minLen = Math.min(minLen, j - i + 1);

                Character ic = word.charAt(i);
                if (count.get(ic) >= 0) {
                    total++;
                }
                count.put(ic, count.get(ic) + 1);

                i++;
            }
            j++;
        }
        System.out.println("minLen = " + minLen);


    }
}