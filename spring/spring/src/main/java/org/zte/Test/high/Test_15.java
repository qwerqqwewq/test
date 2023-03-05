package org.zte.Test.high;

import java.util.*;
//数字加减游戏
public class Test_15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = sc.nextInt();
        }

        int x = 0;
        int diff = arr[1] - arr[0];
        while (true) {
            if ((diff - arr[2] * x) % arr[3] == 0 || (diff + arr[2] * x) % arr[3] == 0) {
                System.out.println(Math.abs(x));
                break;
            }
            x++;
        }
    }
}
