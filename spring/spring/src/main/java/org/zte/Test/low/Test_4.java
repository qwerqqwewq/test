package org.zte.Test.low;

import java.util.Arrays;
import java.util.Scanner;

//租车骑绿岛
public class Test_4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("insert first line");
        String first = in.nextLine();
        String[] s = first.split(" ");
        int m = Integer.parseInt(s[0]);
        int n = Integer.parseInt(s[1]);
        System.out.println("insert second line");
        String second = in.nextLine();
        String[] s1 = second.split(" ");
        if (s1.length != n) {
            System.out.println("error numbers");
            System.exit(0);
        }
        int[] weight = Arrays.stream(s1).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(weight);

        int max_num = 0;
        if (weight[0] * 2 > m) {
            max_num = n;
        } else {
            for (int i = 0, j = n - 1; i <= j; ) {
                if (i != j - 1) {
                    max_num += 1;
                    if (weight[i] + weight[j] <= m) {
                        i++;
                    }
                    j--;
                } else {
                    max_num++;
                    break;
                }
            }
        }
        System.out.println("max_num = " + max_num);
    }
}
