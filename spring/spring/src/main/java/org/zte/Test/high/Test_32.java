package org.zte.Test.high;

import java.util.Arrays;
import java.util.Scanner;

//区块链文件转储
public class Test_32 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());
        Integer[] f =
                Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(m, f));
    }

    public static int getResult(int m, Integer[] f) {
        int l = 0, r = 0;
        int sum = 0, max = 0;

        int n = f.length;

        while (r < n) {
            // 尝试右指针右移一下的新和（注意初始时右指针右移后指向0）
            int newSum = sum + f[r];

            // 如果新和超过了m，即SATA盘容量，则右指针不能右移，并且还需要左指针右移来减少旧和
            if (newSum > m) {
                sum -= f[l++]; // 左指针右移只会减少旧和，因此不会产生最大值
            }
            // 如果新和小于m，则当前尝试的右指针右移可行，因此 sum += F[r]，并且我们下一步还可以继续尝试让右指针右移，即r++
            else if (newSum < m) {
                sum += f[r++];
                max = Math.max(sum, max); // 右指针右移时会增加旧和，因此可能会产生最大值
            }
            // 如果新和等于m，那么说明已经找到了一个容量和SATA盘相同的连续文件大小，即此时已经是最大值了，可以直接返回
            else {
                return m;
            }
        }

        return max;
    }
}