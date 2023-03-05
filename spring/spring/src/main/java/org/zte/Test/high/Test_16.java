package org.zte.Test.high;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

//最大数字
public class Test_16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        System.out.println(getResult(str));
    }

    public static String getResult(String str) {
        // 每个数字字符的可用个数
        HashMap<Character, Integer> unused = new HashMap<>();
        // 每个数字字符的保留个数
        HashMap<Character, Integer> reserve = new HashMap<>();

        // 初始时，每个数字有多少个，就可用多少个，由于还未使用，因此保留个数为0
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            unused.put(c, unused.getOrDefault(c, 0) + 1);
            reserve.putIfAbsent(c, 0);
        }

        // 定义一个栈
        LinkedList<Character> stack = new LinkedList<>();

        // 遍历输入字符串的每个数字字符c
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // 如果该字符已经保留了2个了，则后续再出现该数字字符可以不保留
            if (reserve.get(c) == 2) {
                // 则可用c数字个数--
                unused.put(c, unused.get(c) - 1);
                continue;
            }

            // 比较当前数字c和栈顶数字top，如果c>top，那么需要考虑将栈顶数字弹出
            while (stack.size() > 0) {
                char top = stack.getLast();

                // 如果栈顶数字被弹出后，已保留的top字符数量和未使用的top字符数量之和大于等于2，则可以弹出，否则不可以
                if (top < c && unused.get(top) + reserve.get(top) - 1 >= 2) {
                    stack.removeLast();
                    reserve.put(top, reserve.get(top) - 1);
                } else {
                    break;
                }
            }

            // 选择保留当前遍历的数字c
            stack.add(c);
            // 则可用c数字个数--
            unused.put(c, unused.get(c) - 1);
            // 已保留c数字个数++
            reserve.put(c, reserve.get(c) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
