package org.zte.Test.high;

import java.util.Scanner;

//探索地块建立
public class Test_4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int c = in.nextInt();
        int k = in.nextInt();

        int[][] fields = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) fields[i][j] = in.nextInt();
        }

        int count = 0;

//        int[][] new_fields = new int[n - c + 1][m - c + 1];

        for (int i = 0; i < n - c + 1; i++) {
            for (int p = 0; p < m - c + 1; p++) {
                int sum = 0;
                for (int j = 0; j < c; j++) {
                    for (int q = 0; q < c; q++) {
                        sum += fields[i + j][p + q];
                    }
                }
                if (sum >= k) count++;
            }
        }

        System.out.println("count = " + count);

/*
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                new_fields[i][0] += fields[i][j];
            }

            for (int j = 1; j < m - c + 1; j++) {
                new_fields[i][j] = new_fields[i][j] - fields[i][j - 1] + fields[i][j + c - 1];
            }

            for (int j = 0; j < m - c + 1; j++) {

            }

        }
*/




    }




}
