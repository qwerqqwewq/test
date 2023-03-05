package org.zte.Test.low;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//等和子数组最小和
public class Test_6 {

    public static boolean[] choose_flag;
    public static int[] nums;
    public static int sum = 0;
    public static boolean flag = false;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("insert the sum of num:");
        int num = Integer.parseInt(in.nextLine());
        System.out.println("insert num:");
        String[] numString = in.nextLine().split(" ");
        choose_flag = new boolean[num];
        nums = new int[num];
        for (int i = 0; i < numString.length; i++) {
            nums[i] = Integer.parseInt(numString[i]);
            sum += nums[i];
        }

        ArrayList<Integer> factor = new ArrayList<>();
        Arrays.sort(new ArrayList[]{factor});
        for (int i = 2; i < sum; i++) {
            if (sum % i == 0) {
                factor.add(i);
            }
        }

        if (factor.size() == 0) {
            System.out.println(Arrays.toString(numString));
            System.exit(0);
        }
        int min_factor = 0;
        Arrays.sort(nums);

        flag = false;
        for (int fac : factor) {
            if (fac >= nums[num -1]) {
                dfs(0, fac, 0, 0);
                if (flag) {
                    min_factor = fac;
                    break;
                }
            }
        }
        if (min_factor == 0) {
            min_factor = sum;
        }
        System.out.println("min_factor = " + min_factor);

    }

    public static void dfs(int count, int factor, int index, int countI){
        if (count < factor && index < choose_flag.length && !choose_flag[index]) {
            choose_flag[index] = true;
            dfs(count + nums[index], factor, index + 1, countI);
            choose_flag[index] = false;
            dfs(count, factor, index + 1, countI);
        } else if (count == factor) {
            if (countI == sum / factor - 1) {
                flag = true;
            } else if (countI < (sum / factor) - 1) {
                int start = -1;
                for (int i = 0; i < choose_flag.length; i++) {
                    if (!choose_flag[i]) {
                        start = i;
                        break;
                    }
                }
                if (start == -1) {
                    return;
                }
                dfs(0, factor, start, countI + 1);
            }
        }
    }
}
