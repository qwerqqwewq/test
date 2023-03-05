package org.zte.Test.low;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

//二元组个数
public class Test_20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        ArrayList<Integer> listM = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            listM.add(sc.nextInt());
        }

        int n = sc.nextInt();
        ArrayList<Integer> listN = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listN.add(sc.nextInt());
        }

        System.out.println(getResult(listM, listN));
    }

    public static int getResult(ArrayList<Integer> listM, ArrayList<Integer> listN) {
        HashSet<Integer> setM = new HashSet<Integer>(listM);
        HashSet<Integer> setN = new HashSet<Integer>(listN);

        HashMap<Integer, Integer> countM = new HashMap<>();
        for (Integer m : listM) {
            if (setN.contains(m)) {
                countM.put(m, countM.getOrDefault(m, 0) + 1);
            }
        }

        HashMap<Integer, Integer> countN = new HashMap<>();
        for (Integer n : listN) {
            if (setM.contains(n)) {
                countN.put(n, countN.getOrDefault(n, 0) + 1);
            }
        }

        int count = 0;
        for (Integer k : countM.keySet()) {
            count += countM.get(k) * countN.get(k);
        }

        return count;
    }
}