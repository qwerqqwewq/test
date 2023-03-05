package org.zte.Test.high;

import java.util.HashSet;
import java.util.Scanner;

//上班之路
public class Test_33 {
    static int t, c, n, m;
    static String[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        t = sc.nextInt();
        c = sc.nextInt();

        n = sc.nextInt();
        m = sc.nextInt();

        matrix = new String[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = sc.next().split("");
        }

        System.out.println(getResult());
    }

    public static String getResult() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ("S".equals(matrix[i][j])) {
                    HashSet<Integer> path = new HashSet<>();
                    path.add(i * m + j);
                    return dfs(i, j, 0, 0, 0, path) ? "YES" : "NO";
                }
            }
        }
        return "NO";
    }

    // 元素含义【行偏移，列偏移，运动方向】，运动方向：1上，2下，3左，4右
    static int[][] offsets = {{-1, 0, 1}, {1, 0, 2}, {0, -1, 3}, {0, 1, 4}};

    /**
     * @param si 当前位置横坐标
     * @param sj 当前位置纵坐标
     * @param ut 已拐弯次数
     * @param uc 已破壁次数
     * @param lastDirect 上一次运动方向，初始为0，表示第一次运动不会造成拐弯
     * @param path 行动路径，用于记录走过的位置，避免走老路
     * @return 终点是否可达
     */
    public static boolean dfs(int si, int sj, int ut, int uc, int lastDirect, HashSet<Integer> path) {
        // 如果当前位置就是目的地，则返回true
        if ("T".equals(matrix[si][sj])) {
            return true;
        }

        // 有四个方向选择走下一步
        for (int[] offset : offsets) {
            int direct = offset[2];
            int newI = si + offset[0];
            int newJ = sj + offset[1];

            // flag1记录本次运动是否拐弯
            boolean flag1 = false;
            // flag2记录本次运动是否破壁
            boolean flag2 = false;

            // 如果下一步位置没有越界，则进一步检查
            if (newI >= 0 && newI < n && newJ >= 0 && newJ < m) {
                // 如果下一步位置已经走过了，则是老路，可以不走
                int pos = newI * m + newJ;
                if (path.contains(pos)) continue;

                // 如果下一步位置需要拐弯
                if (lastDirect != 0 && lastDirect != direct) {
                    // 如果拐弯次数用完了，则不走
                    if (ut + 1 > t) continue;
                    // 否则就走
                    flag1 = true;
                }

                // 如果下一步位置需要破壁
                if ("*".equals(matrix[newI][newJ])) {
                    // 如果破壁次数用完了，则不走
                    if (uc + 1 > c) continue;
                    // 否则就走
                    flag2 = true;
                }

                // 记录已走过的位置
                path.add(pos);

                // 继续下一步递归
                boolean res = dfs(newI, newJ, ut + (flag1 ? 1 : 0), uc + (flag2 ? 1 : 0), direct, path);

                // 如果某路径可以在给定的t,c下，到达目的地T，则返回true
                if (res) return true;

                // 否则，回溯
                path.remove(pos);
            }
        }
        return false;
    }
}
