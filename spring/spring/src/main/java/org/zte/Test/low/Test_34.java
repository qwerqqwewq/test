package org.zte.Test.low;

import java.util.LinkedList;
import java.util.Scanner;

//投篮大赛
public class Test_34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        String[] ops = str.split(" ");

        System.out.println(getResult(ops));
    }

    public static int getResult(String[] ops) {
        // ans用于保存每轮的得分
        LinkedList<Integer> ans = new LinkedList<>();
        String reg = "^\\-?\\d+$";

        for (String op : ops) {
            // 如果op是整数，则表示本轮得分，直接加入ans
            if (op.matches(reg)) {
                ans.addLast(Integer.parseInt(op));
            } else {
                switch (op) {
                    // 如果op是+，则表示本轮得分是前两轮得分之和，注意越界处理
                    case "+":
                        if (ans.size() < 2) return -1;
                        ans.addLast(ans.getLast() + ans.get(ans.size() - 2));
                        break;
                    // 如果op是D，表示本轮得分是前一轮得分的双倍，注意越界处理
                    case "D":
                        if (ans.size() < 1) return -1;
                        ans.addLast(ans.getLast() * 2);
                        break;
                    // 如果op是C，则表示本轮无得分，且上一轮得分无效，需要去除
                    case "C":
                        // 感谢网友m0_71826536的提示，由于题目说：对于“C”和“D”操作，题目数据不保证记录此操作时前面存在一个有效的分数，因此这里C操作，不能直接removeLast，需要先判断ans是否有数据
                        if (ans.size() < 1) return -1;
                        ans.removeLast();
                        break;
                }
            }
        }

        int sum = 0;
        for (Integer an : ans) {
            sum += an;
        }
        return sum;
    }
}

