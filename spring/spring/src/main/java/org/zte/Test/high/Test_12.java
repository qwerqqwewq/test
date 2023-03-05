package org.zte.Test.high;

import java.util.*;

//分奖金
public class Test_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();

        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getResult(arr, m));
    }

    public static String getResult(int[] arr, int m) {
        LinkedList<Integer[]> stack = new LinkedList<>();
        Integer[] nextBigger = new Integer[m];
        Arrays.fill(nextBigger, -1);

        for (int i = 0; i < arr.length; i++) {
            while (stack.size() > 0) {
                Integer[] top = stack.peek();
                int idx = top[0];
                int val = top[1];

                if (arr[i] > val) {
                    stack.pop();
                    nextBigger[idx] = i;
                } else {
                    break;
                }
            }

            Integer[] ele = {i, arr[i]};
            stack.push(ele);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            Integer idx = nextBigger[i];

            if (idx == -1) {
                ans.add(arr[i]);
            } else {
                ans.add((idx - i) * (arr[idx] - arr[i])); // 距离 * 数字差值
            }
        }

        StringJoiner sj = new StringJoiner(" ", "", "");
        for (Integer an : ans) sj.add(an + "");
        return sj.toString();
    }
}
