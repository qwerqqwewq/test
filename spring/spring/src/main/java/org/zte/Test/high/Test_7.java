package org.zte.Test.high;

import java.util.LinkedList;
import java.util.Scanner;

//最大平分数组
public class Test_7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            list.add(in.nextInt());
        }

        list.sort((a, b) -> b - a);
        int sum = 0;
        boolean flag = false;

        for (Integer ele : list) {
            sum += ele;
        }
        while (m >= 1) {
            LinkedList<Integer> linkedList = new LinkedList<>(list);
            if (canPartition(linkedList, sum, m)) {
                System.out.println(m);
                flag = true;
            }
            m--;
        }
        if (!flag) {
            System.out.println(1);
        }

    }

    public static boolean canPartition(LinkedList<Integer> linkedList, int sum, int m) {
        if (sum % m != 0) return false;

        int subSum = sum / m;
        if (subSum < linkedList.get(0)) return false;

        while (linkedList.size() > 0 && linkedList.get(0) == subSum) {
            linkedList.removeFirst();
            m--;
        }
        int[] buckets = new int[m];
        return partition(linkedList, 0, buckets, subSum);

    }

    public static boolean partition(LinkedList<Integer> list,
                                    int index, int[] buckets, int subSum) {
        if (index==list.size()) return true;

        int select = list.get(index);
        for (int i = 0; i < buckets.length; i++) {
            if (i>0&&buckets[i] == buckets[i-1]) continue;
            if (select + buckets[i] <= subSum) {
                buckets[i] += select;
                if (partition(list, index+1,buckets,subSum)) return true;
                buckets[i] -= select;
            }
        }
        return false;
    }


}
