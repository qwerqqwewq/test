package org.zte.Test.high;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//最优资源分配
public class Test_20 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        String sequence = sc.next();

        getResult(m, n, sequence);
    }

    public static void getResult(int m, int n, String sequence) {
        double[] boardCard = new double[n];
        Arrays.fill(boardCard, m * 1.25);

        HashMap<Character, Integer> dict = new HashMap<>();
        dict.put('A', 1);
        dict.put('B', 2);
        dict.put('C', 8);

        for (int i = 0; i < sequence.length(); i++) {
            double need = 1.25 * dict.get(sequence.charAt(i));
            for (int j = 0; j < n; j++) {
                if (boardCard[j] >= need) {
                    boardCard[j] -= need;
                    break;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int unUsed = (int) (boardCard[i] / 1.25);
            int used = m - unUsed;

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < used; j++) {
                sb.append(1);
            }
            for (int k = 0; k < unUsed; k++) {
                sb.append(0);
            }
            System.out.println(sb);
        }
    }
}