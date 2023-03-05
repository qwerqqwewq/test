package org.zte.Test.low;

import java.util.Arrays;
import java.util.Scanner;

//模拟商场优惠打折
public class Test_9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = sc.nextInt();
        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();

        int[] arr = new int[x];
        for (int i = 0; i < x; i++) {
            arr[i] = sc.nextInt();
        }

        getResult(arr, m, n, k);
    }

    public static void getResult(int[] arr, int m, int n, int k) {
        for (int i = 0; i < arr.length; i++) {
            Integer[][] ans = new Integer[4][2]; // 4的含义对应4种使用券的方式：MN,NM,MK,NK,  2的含义对应每种方式下：剩余总价，剩余券数量
            int price = arr[i];

            int[] resM = M(price, m); // 先满减
            int[] resN = N(price, n); // 先打折

            // MN
            int[] resMN_N = N(resM[0], n); // 满减后打折
            ans[0] =
                    new Integer[] {
                            resMN_N[0], m + n - resM[1] - resMN_N[1]
                    }; // resMN_N[0]是 “满减后打折” 的剩余总价， m + n - resM[1] - resMN_N[1] 是 该种用券方式的: 总券数 m+n， 剩余券数
            // resM[1] + resMN_N[1], 因此使用掉的券数： m+n - (resM[1] + resMN_N[1])

            // NM
            int[] resNM_M = M(resN[0], m); // 打折后满减
            ans[1] = new Integer[] {resNM_M[0], n + m - resN[1] - resNM_M[1]};

            // MK
            int[] resMK_K = K(resM[0], k); // 满减后无门槛
            ans[2] = new Integer[] {resMK_K[0], m + k - resM[1] - resMK_K[1]};

            // NK
            int[] resNK_K = K(resN[0], k); // 打折后无门槛
            ans[3] = new Integer[] {resNK_K[0], n + k - resN[1] - resNK_K[1]};

            Arrays.sort(
                    ans,
                    (a, b) ->
                            a[0].equals(b[0])
                                    ? a[1] - b[1]
                                    : a[0] - b[0]); // 对ans进行排序，排序规则是：优先按剩余总价升序，如果剩余总价相同，则再按“使用掉的券数量”升序
            System.out.println(ans[0][0] + " " + ans[0][1]);
        }
    }

    /**
     * @param price 总价
     * @param m 满减券数量
     * @return 总价满减后结果，对应数组含义是 [用券后剩余总价， 剩余满减券数量]
     */
    public static int[] M(int price, int m) {
        while (price >= 100 && m > 0) {
            price -= price / 100 * 10; // 假设price=340，那么可以优惠 340/100 * 10 = 30元
            m--;
        }
        return new int[] {price, m};
    }

    /**
     * @param price 总价
     * @param n 打折券数量
     * @return 总价打折后结果，对应数组含义是 [用券后剩余总价， 剩余打折券数量]
     */
    public static int[] N(int price, int n) {
        if (n >= 1) {
            price = (int) Math.floor((price * 0.92));
            n--;
        }
        return new int[] {price, n};
    }

    /**
     * @param price 总价
     * @param k 无门槛券数量
     * @return 无门槛券用后结果，对应数组含义是 [用券后剩余总价， 剩余无门槛券数量]
     */
    public static int[] K(int price, int k) {
        while (price > 0 && k > 0) {
            price -= 5;
            price = Math.max(price, 0); // 感谢m0_71826536提供的思路，当无门槛券过多时，是有可能导致优惠后总价低于0的情况的，此时我们应该避免总价小于0的情况
            k--;
        }
        return new int[] {price, k};
    }
}
