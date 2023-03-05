package org.zte.Test.high;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

//区间交叠问题
public class Test_6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Integer[][] ranges = new Integer[n][2];
        for (int i = 0; i < n; i++) {
            ranges[i] = Arrays.stream(in.nextLine().split(","))
                    .map(Integer::parseInt).toArray(Integer[]::new);
        }

        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));

        LinkedList<Integer[]> stack = new LinkedList<>();
        stack.add(ranges[0]);

        for (Integer[] range : ranges) {
            while (true) {
                if (stack.size() == 0) {
                    stack.add(range);
                    break;
                }

                Integer[] top = stack.getLast();
                int s0 = top[0];
                int e0 = top[1];

                int s1 = range[0];
                int e1 = range[1];

                if (s1 <= s0) {
                    if (e1 <= s0) {
                        break;
                    } else if (e1 < e0) {
                        break;
                    } else {
                        stack.removeLast();
                    }
                } else if (s1 < e0) {
                    if (e1 <= e0) {
                        break;
                    } else {
                        stack.add(new Integer[]{e0, e1});
                        break;
                    }
                } else {
                    stack.add(range);
                    break;
                }
            }

        }
        System.out.println(stack.size());
    }
}
