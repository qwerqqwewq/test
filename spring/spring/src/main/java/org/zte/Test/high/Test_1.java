package org.zte.Test.high;

import java.util.*;

//最大化控制资源成本
public class Test_1 {
    public static int max_machine = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("insert the num of task");
        int num = Integer.parseInt(in.nextLine());
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            System.out.println("the " + (i + 1) + " task:");
            String[] s = in.nextLine().split(" ");
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(Integer.parseInt(s[0]));
            arrayList.add(Integer.parseInt(s[1]));
            arrayList.add(Integer.parseInt(s[2]));
            lists.add(arrayList);
            max_machine = Math.max(max_machine, Integer.parseInt(s[2]));
        }
        cal_public_range(lists);
        System.out.println("max_machine=" + max_machine);
    }
    public static void cal_public_range(ArrayList<ArrayList<Integer>> ranges) {
        Comparator<ArrayList<Integer>> myComparator = new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if (!o1.get(0).equals(o2.get(0))) {
                    return o1.get(0) - o2.get(0);
                } else {
                    return o1.get(1) - o2.get(1);
                }
            }
        };
        ranges.sort(myComparator);
        ArrayList<Integer> arrayList = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < ranges.size(); i++) {
            for (int j = i + 1; j < ranges.size(); j++) {
                int left = Math.max(ranges.get(i).get(0), ranges.get(j).get(0));
                int right = Math.min(ranges.get(i).get(1), ranges.get(j).get(1));
                if (left <= right) {
                    max = ranges.get(i).get(2) + ranges.get(j).get(2);
                    for (int k = j + 1; k < ranges.size(); k++) {
                        left = Math.max(ranges.get(k).get(0), left);
                        right = Math.min(ranges.get(k).get(1), right);
                        if (left <= right) {
                            max += ranges.get(k).get(2);
                        }
                    }
                }
                max_machine = Math.max(max, max_machine);
            }
        }

    }
}
