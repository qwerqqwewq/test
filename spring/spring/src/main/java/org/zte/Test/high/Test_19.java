package org.zte.Test.high;

import java.util.*;

//统计差异值大于相似值二元组
public class Test_19 {

    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int res_sum=0;
        String[] nums = in.nextLine().split(" ");
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int x = Integer.valueOf(nums[i]);
                int y = Integer.valueOf(nums[j]);
                if (handle(x,y)) {
                    res_sum++;
                }
            }
        }
        System.out.println(res_sum);
    }

    static boolean handle(int num_1, int num_2) {
        StringBuilder common=new StringBuilder();
        StringBuilder difference=new StringBuilder();
        String string_1 = Integer.toBinaryString(num_1);
        String string_2 = Integer.toBinaryString(num_2);
        int len_1 = string_1.length() - 1;
        int len_2 = string_2.length() - 1;
        while (len_1 >= 0 || len_2 >= 0) {
            char val_1=' ';
            char val_2=' ';
            if (len_1>=0){
                val_1=string_1.charAt(len_1);
            }
            if (len_2>=0){
                val_2=string_2.charAt(len_2);
            }
            if (val_1==val_2){
                common.append('1');
                difference.append('0');
            }else {
                common.append('0');
                difference.append('1');
            }
            len_1--;
            len_2--;
        }
        //二进制
        int common_num=Integer.valueOf(String.valueOf(common.reverse()),2);
        int difference_num=Integer.valueOf(String.valueOf(difference.reverse()),2);
        return difference_num>common_num;
    }

}