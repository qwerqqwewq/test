package org.zte.Test.low;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

//通信误码
public class Test_25 {
    static ArrayList<Integer[]> nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(getResult(arr));
    }

    /**
     * @param arr 误码出现频率数组
     * @return 包含频率最高的误码最小子数组长度
     */
    public static int getResult(int[] arr) {
        HashMap<Integer, ArrayList<Integer>> idxs = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            Integer code = arr[i];
            idxs.putIfAbsent(code, new ArrayList<>());
            idxs.get(code).add(i);
        }

        ArrayList<Integer> countList =
                idxs.values().stream()
                        .sorted(
                                (a, b) -> {
                                    if (a.size() != b.size()) {
                                        return b.size() - a.size();
                                    } else {
                                        int alen = a.get(a.size() - 1) - a.get(0);
                                        int blen = b.get(b.size() - 1) - b.get(0);
                                        return alen - blen;
                                    }
                                })
                        .limit(1)
                        .collect(Collectors.toList())
                        .get(0);

        return countList.get(countList.size() - 1) - countList.get(0) + 1;
    }
}