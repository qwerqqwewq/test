package org.zte.Test.low;

import java.util.Scanner;

//最大利润
public class Test_17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try { // 此段try...catch不影响正常业务逻辑，只是有网友说可能存在如输入行数校验要返回0，因此加了
            int number = sc.nextInt();
            int days = sc.nextInt();

            int[] maxCount = new int[number];
            for (int i = 0; i < number; i++) {
                maxCount[i] = sc.nextInt();
            }

            int[][] prices = new int[number][days];
            for (int i = 0; i < number; i++) {
                for (int j = 0; j < days; j++) {
                    prices[i][j] = sc.nextInt();
                }
            }

            System.out.println(getResult(number, days, maxCount, prices));
        } catch (Exception e) {
            System.out.println(0);
        }
    }

    /**
     * @param number 几种商品
     * @param days 几天
     * @param maxCount 每种商品的最大囤货数量
     * @param prices 每种商品的在days天内的价格变动情况
     * @return 最大利润
     */
    public static int getResult(int number, int days, int[] maxCount, int[][] prices) {
        int ans = 0;
        for (int i = 0; i < number; i++) {
            int[] price = prices[i];
            for (int j = 0; j < days - 1; j++) {
                if (price[j] < price[j + 1]) {
                    ans += (price[j + 1] - price[j]) * maxCount[i];
                }
            }
        }
        return ans;
    }
}