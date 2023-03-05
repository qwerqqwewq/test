package org.zte.Test.low;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

//积木最远距离
public class Test_22 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getResult(arr));
    }

    public static int getResult(int[] arr) {
        HashMap<Integer, LinkedList<Integer>> idx = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            idx.putIfAbsent(num, new LinkedList<>());
            idx.get(num).add(i);
        }

        int ans = -1;

        for (Integer k : idx.keySet()) {
            LinkedList<Integer> link = idx.get(k);
            if (link.size() > 1) {
                ans = Math.max(ans, link.getLast() - link.getFirst());
            }
        }

        return ans;
    }
}
