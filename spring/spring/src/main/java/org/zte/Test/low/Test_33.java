package org.zte.Test.low;

import java.util.*;

//任务总执行时长
public class Test_33 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        Integer[] arr = Arrays.stream(str.split(",")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(arr[0], arr[1], arr[2]));
    }

    public static String getResult(int taskA, int taskB, int num) {
        if(taskA == taskB) {
            return Arrays.toString(new int[]{taskA * num});
        }

        TreeSet<Integer> ans = new TreeSet<>();
        for(int i=0; i<=num; i++) {
            ans.add(taskA * i + taskB * (num - i));
        }

        return ans.toString();
    }
}