package org.zte.Test.low;

import java.util.*;

//开心消消乐
public class Test_29 {
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

    public static int getResult(int[][] matrix, int n, int m) {
        UnionFindSet ufs = new UnionFindSet(n * m);

        // 八个方向的偏移量
        Integer[][] offsets = {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
        };

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] != 1) {
                    ufs.count--;
                    continue;
                }

                // 不要紧张，这里固定循环8次，不会造成O(n^3)
                for (Integer[] offset : offsets) {
                    int newI = i + offset[0];
                    int newJ = j + offset[1];

                    if (newI >= 0 && newI < n && newJ >= 0 && newJ < m && matrix[newI][newJ] == 1) {
                        ufs.union(i * m + j, newI * m + newJ);
                    }
                }
            }
        }

        return ufs.count;
    }
}

// 并查集
class UnionFindSet {
    int[] fa;
    int count;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        this.count = n;
        for (int i = 0; i < n; i++) this.fa[i] = i;
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            return (this.fa[x] = this.find(this.fa[x]));
        }
        return x;
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        if (x_fa != y_fa) {
            this.fa[y_fa] = x_fa;
            this.count--;
        }
    }
}
