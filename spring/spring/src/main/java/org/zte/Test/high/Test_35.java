package org.zte.Test.high;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//最优高铁城市
public class Test_35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int can = sc.nextInt();
        int must = sc.nextInt();

        int[][] cans = new int[can][3];
        for (int i = 0; i < can; i++) {
            cans[i][0] = sc.nextInt();
            cans[i][1] = sc.nextInt();
            cans[i][2] = sc.nextInt();
        }

        int[][] musts = new int[must][2];
        for (int i = 0; i < must; i++) {
            musts[i][0] = sc.nextInt();
            musts[i][1] = sc.nextInt();
        }

        System.out.println(getResult(n, cans, musts));
    }

    /**
     * @param n 一共几个城市
     * @param cans 哪些城市之间可以修建高铁，以及修建费用
     * @param musts 哪些城市之间必须修建高铁
     * @return 最少费用
     */
    public static int getResult(int n, int[][] cans, int[][] musts) {
        UnionFindSet ufs = new UnionFindSet(n);

        // 为了方便统计“必建高铁”的费用，我们需要将cans数组改造为cansMap，key为'1-2' 两个城市，val为 这两个城市建高铁的费用
        HashMap<String, Integer> cansMap = new HashMap<>();
        for (int[] can : cans) {
            int city1 = can[0], city2 = can[1], fee = can[2];
            String key = city1 < city2 ? city1 + "-" + city2 : city2 + "-" + city1;
            cansMap.put(key, fee);
        }

        int minFee = 0;
        for (int[] must : musts) {
            // 计入必建高铁的费用到minFee中
            String key = must[0] < must[1] ? must[0] + "-" + must[1] : must[1] + "-" + must[0];
            minFee += cansMap.get(key);
            // 并将必建高铁的两个城市纳入同一个连通分量重
            ufs.union(must[0], must[1]);
        }

        //  如果必建高铁本身已经满足所有城市通车了，那么直接返回minFee
        if (ufs.count == 1) return minFee;

        // 否则，按照求解最小生成树的Kruskal算法，将高铁线（即图的边）按照成本费用（即边的权重）升序
        Arrays.sort(cans, (a, b) -> a[2] - b[2]);

        // 遍历排序后的cans，每次得到的都是当前的最小权重边
        for (int[] can : cans) {
            int city1 = can[0], city2 = can[1], fee = can[2];
            // 如果对应城市已经接入高铁线（即处于连通分量中）则再次合入就会产生环，因此不能合入，否则就可以合入
            //      if (ufs.fa[city1] != ufs.fa[city2]) {
            if (ufs.find(city1) != ufs.find(city2)) {
                ufs.union(city1, city2);
                // 若可以合入，则将对应的建造成本计入minFee
                minFee += fee;
            }

            // 如果此时，所有城市都通车了（即并查集中只有一个连通分量），则提前结束循环
            if (ufs.count == 1) break;
        }

        // 如果循环完，发现并查集中还有多个连通分量，那么代表有的城市无法通车，因此返回-1
        if (ufs.count > 1) return -1;

        return minFee;
    }
}

