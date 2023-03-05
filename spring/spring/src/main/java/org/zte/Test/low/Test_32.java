package org.zte.Test.low;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//找数字
public class Test_32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix, n, m));
    }

    public static String getResult(int[][] matrix, int n, int m) {
        HashMap<Integer, ArrayList<Integer[]>> nums = new HashMap<>();

        // 统计输入矩阵中，相同数字的位置
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Integer num = matrix[i][j];
                Integer[] arr = {i, j};
                nums.putIfAbsent(num, new ArrayList<>());
                nums.get(num).add(arr);
            }
        }

        // 遍历矩阵每一个元素，和其他相同数字的位置求距离，，取最小距离
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int num = matrix[i][j];

                int minDis = Integer.MAX_VALUE;
                for (Integer[] pos : nums.get(num)) {
                    int i1 = pos[0];
                    int j1 = pos[1];

                    if (i1 != i || j1 != j) {
                        int dis = Math.abs(i1 - i) + Math.abs(j1 - j);
                        minDis = Math.min(minDis, dis);
                    }
                }

                matrix[i][j] = minDis == Integer.MAX_VALUE ? -1 : minDis;
            }
        }

        return Arrays.toString(Arrays.stream(matrix).map(Arrays::toString).toArray(String[]::new));
    }
}