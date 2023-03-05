package org.zte.Test.high;

import java.util.LinkedList;
import java.util.Scanner;

//机房布局
public class Test_23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();

        System.out.println(getResult(str));
    }

    public static int getResult(String str) {
        int n = str.length();
        LinkedList<Integer[]> stack = new LinkedList<>();
        boolean stick = false;

        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == 'M') {
                // // 如果机柜A两边都是机柜，或者没有间隔，则无法给机柜A放电箱，返回-1
                boolean left = i - 1 < 0 || str.charAt(i - 1) == 'M';
                boolean right = i + 1 >= n || str.charAt(i + 1) == 'M';
                if (left && right) return -1;

                // 将求解最少电箱问题，转化为区间交集问题
                Integer[] range = {Math.max(0, i - 1), Math.min(n - 1, i + 1)};

                if (stack.size() > 0 && !stick) {
                    int e1 = stack.getLast()[1];
                    int s2 = range[0];

                    if (e1 == s2) {
                        stack.removeLast();
                        stick = true;
                    }
                } else {
                    stick = false;
                }
                stack.addLast(range);
            }
        }

        return stack.size();
    }
}
