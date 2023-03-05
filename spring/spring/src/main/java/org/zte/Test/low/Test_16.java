package org.zte.Test.low;

import java.util.*;

//端口合并
public class Test_16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());

        if (m > 10 || m < 1) {
            System.out.println("[[]]");
            return;
        }

        Integer[][] matrix = new Integer[m][];
        for (int i = 0; i < m; i++) {
            matrix[i] =
                    Arrays.stream(sc.nextLine().split(",")).map(Integer::parseInt).toArray(Integer[]::new);
        }

        System.out.println(Arrays.deepToString(getResult(matrix)));
    }

    public static Integer[][] getResult(Integer[][] matrix) {
        boolean flag = true;

        while (flag) {
            flag = false;
            for (int i = matrix.length - 1; i >= 1; i--) {
                if (matrix[i] == null || matrix[i].length <= 1) continue;
                for (int j = i - 1; j >= 0; j--) {
                    if (matrix[j] == null || matrix[j].length <= 1) continue;
                    if (canMerge(matrix[i], matrix[j])) {
                        ArrayList<Integer> tmp = new ArrayList<>();
                        Collections.addAll(tmp, matrix[i]);
                        Collections.addAll(tmp, matrix[j]);
                        matrix[j] = new HashSet<Integer>(tmp).stream().sorted().toArray(Integer[]::new);
                        matrix[i] = null;
                        flag = true;
                        break;
                    }
                }
            }
        }

        return Arrays.stream(matrix).filter(Objects::nonNull).toArray(Integer[][]::new);
    }

    public static boolean canMerge(Integer[] m, Integer[] n) {
        // 从用例1输出的2,3,2来看，未合并的端口组是不能去重和排序的，因此这里需要浅克隆下，避免改变原数组顺序
        m = m.clone();
        n = n.clone();

        Arrays.sort(m);
        Arrays.sort(n);

        int i = 0;
        int j = 0;
        int count = 0;

        while (i < m.length && j < n.length && count < 2) {
            if (m[i].equals(n[j])) {
                i++;
                j++;
                count++;
            } else if (m[i] > n[j]) {
                j++;
            } else {
                i++;
            }
        }

        return count >= 2;
    }
}
