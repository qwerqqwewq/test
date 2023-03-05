package org.zte.Test.low;

import java.util.Arrays;
import java.util.Scanner;

//简单的像素曝光
public class Test_26 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        Integer[] arr = Arrays.stream(str.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(arr));
    }

    public static int getResult(Integer[] arr) {
        int len = arr.length;
        double minDiff = Integer.MAX_VALUE;
        Integer ans = null;

        for (int k = -127; k <= 128; k++) {
            double sum = 0;
            for (Integer val : arr) {
                int newVal = val + k;
                // 新图的像素值会自动截取到[0,255]范围。当新像素值<0，其值会更改为0；当新像素值>255，其值会更改为255；
                newVal = Math.max(0, Math.min(newVal, 255));
                sum += newVal;
            }

            double diff = Math.abs(sum / len - 128);

            if (diff < minDiff) {
                minDiff = diff;
                ans = k;
            } else if (diff == minDiff && ans != null) {
                // 如有多个整数k都满足，输出小的那个k
                ans = Math.min(ans, k);
            }
        }

        return ans;
    }
}