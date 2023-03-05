package org.zte.Test.low;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

//计算数组中心位置
public class Test_19 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] arr =
                Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(arr));
    }

    public static int getResult(Integer[] nums) {
        BigInteger fact = BigInteger.valueOf(1);
        for (Integer num : nums) {
            fact = fact.multiply(BigInteger.valueOf(num));
        }

        BigInteger left = BigInteger.valueOf(1);
        BigInteger right = fact.divide(BigInteger.valueOf(nums[0]));

        if (left.compareTo(right) == 0) {
            return 0;
        }

        for (int i = 1; i < nums.length; i++) {
            left = left.multiply(BigInteger.valueOf(nums[i - 1]));
            right = right.divide(BigInteger.valueOf(nums[i]));

            if (left.compareTo(right) == 0) return i;
        }

        return -1;
    }

    //  public static int getResult(Integer[] nums) {
    //    int fact = 1;
    //    for (Integer num : nums) {
    //      fact *= num;
    //    }
    //
    //    int left = 1;
    //    int right = fact / nums[0];
    //
    //    if (left == right) {
    //      return 0;
    //    }
    //
    //    for (int i = 1; i < nums.length; i++) {
    //      left *= nums[i - 1];
    //      right /= nums[i];
    //
    //      if (left == right) return i;
    //    }
    //
    //    return -1;
    //  }
}