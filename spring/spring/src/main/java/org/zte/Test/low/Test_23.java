package org.zte.Test.low;

import java.util.*;

//猜灯谜
public class Test_23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] issues = sc.nextLine().split(",");
        String[] answers = sc.nextLine().split(",");

        System.out.println(getResult(issues, answers));
    }

    public static String getResult(String[] issues, String[] answers) {
        ArrayList<String> ans = new ArrayList<>();

        for (String issue : issues) {
            String[] issueDeal = getSortedAndDistinctStr(issue);
            boolean find = false;

            for (String answer : answers) {
                String[] answerDeal = getSortedAndDistinctStr(answer);

                if(issueDeal[0].equals(answerDeal[0]) || issueDeal[1].equals(answerDeal[1])) {
                    ans.add(answer);
                    find = true;
                    // break; // 如果一个谜面对应多个谜底，这里就不能break，如果一个谜面只对应一个谜底，那这里就要break，考试的时候都试下
                }
            }

            if(!find) {
                ans.add("not found");
            }
        }

        StringJoiner sj = new StringJoiner(",","","");
        for (String an : ans) {
            sj.add(an);
        }
        return sj.toString();
    }

    public static String[] getSortedAndDistinctStr(String str) {
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        String sorted_str = new String(arr); // 字典序排序后的字符串

        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (char c : str.toCharArray()) set.add(c);
        String distinct_str = set.toString(); // 去重后的字符串

        return new String[]{sorted_str, distinct_str};
    }
}