package org.zte.Test.high;

import java.util.*;
import java.util.stream.Stream;

class Main{

    public static Integer[][] finalList = new Integer[9][9];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer[][] list = new Integer[9][9];
        LinkedList<Integer[]> linkedList = new LinkedList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int a = in.nextInt();
                if (a == 0) {
                    Integer[] temp = new Integer[2];
                    temp[0] = i;
                    temp[1] = j;
                    linkedList.add(temp);
                }
                list[i][j] = a;
            }
        }
        getResult(list, linkedList);
        for (Integer[] a : finalList) {
            for (Integer b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }

    }

    public static boolean getResult(Integer[][] list, LinkedList<Integer[]> linkedList) {
        Integer[] a = linkedList.removeFirst();
        HashSet<Integer> set = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int i = 0; i < 9; i++) {
            set.remove(list[i][a[1]]);
            set.remove(list[a[0]][i]);
        }
        List<Integer> test = new ArrayList<>(set);
        if (linkedList.size() == 0) {
            if (test.size() == 1) {
                finalList = list;
            } else {
                return false;
            }
        } else {
            if (test.size() == 0) {
                return false;
            } else {
                for (Integer c : test) {
                    list[a[0]][a[1]] = c;
                    if (!getResult(list, linkedList)) continue;
                }
            }
        }
        return false;

    }

}