package org.zte.Test.low;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//单向链表中间节点
public class Test_3 {

    public static void main(String[] args) {
        System.out.println("insert first line:");
        Scanner in = new Scanner(System.in);

        String first = in.nextLine();
        String[] s = first.split(" ");

        String a = s[0];
        int num = Integer.parseInt(s[1]);

        List<String[]> list = new ArrayList<>();
        List<String[]> list_real = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            System.out.println("insert the " + (i + 1) + " num");
            list.add(in.nextLine().split(" "));
            if (list.get(i)[0].equals(a)) {
                list_real.add(list.get(i));
            }
        }

        while (!list_real.get(list_real.size() - 1)[2].equals("-1")) {
            for (String[] temp : list) {
                if (temp[0].equals(list_real.get(list_real.size() - 1)[2])) {
                    list_real.add(temp);
                }
            }
        }
        int size = list_real.size() / 2;
        System.out.println("size = " + list_real.get(size)[1]);


    }
}
