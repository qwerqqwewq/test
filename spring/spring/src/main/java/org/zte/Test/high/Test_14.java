package org.zte.Test.high;

import java.util.LinkedList;
import java.util.Scanner;

//基站维护工程师
public class Test_14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        System.out.println(getResult(matrix, n));
    }

    public static int getResult(int[][] matrix, int n) {
        boolean[] used = new boolean[n];
        LinkedList<Integer> path = new LinkedList<>();
        int[] ans = {Integer.MAX_VALUE};

        dfs(n, used, path, ans, matrix);

        return ans[0];
    }

    public static void dfs(
            int n, boolean[] used, LinkedList<Integer> path, int[] ans, int[][] matrix) {
        if (path.size() == n - 1) {
            int dis = matrix[0][path.get(0)];
            for (int i = 0; i < path.size() - 1; i++) {
                int p = path.get(i);
                int c = path.get(i + 1);
                dis += matrix[p][c];
            }
            dis += matrix[path.getLast()][0];
            ans[0] = Math.min(ans[0], dis);
            return;
        }

        for (int i = 1; i < n; i++) {
            if (!used[i]) {
                path.push(i);
                used[i] = true;
                dfs(n, used, path, ans, matrix);
                used[i] = false;
                path.pop();
            }
        }
    }
}