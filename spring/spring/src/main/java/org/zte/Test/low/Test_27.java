package org.zte.Test.low;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//货币单位换算
public class Test_27 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.next();
        }

        System.out.println(getResult(arr));
    }

    public static int getResult(String[] arr) {
        String reg = "(\\d+)((CNY)|(JPY)|(HKD)|(EUR)|(GBP)|(fen)|(cents)|(sen)|(eurocents)|(pence))";

        HashMap<String, Double> exchange = new HashMap<>();
        exchange.put("CNY", 100.0);
        exchange.put("JPY", 100.0 / 1825 * 100);
        exchange.put("HKD", 100.0 / 123 * 100);
        exchange.put("EUR", 100.0 / 14 * 100);
        exchange.put("GBP", 100.0 / 12 * 100);
        exchange.put("fen", 1.0);
        exchange.put("cents", 100.0 / 123);
        exchange.put("sen", 100.0 / 1825);
        exchange.put("eurocents", 100.0 / 14);
        exchange.put("pence", 100.0 / 12);

        double ans = 0.0;
        Pattern p = Pattern.compile(reg);

        for (String s : arr) {
            Matcher m = p.matcher(s);
            while (true) {
                if (m.find()) {
                    Double amount = Double.parseDouble(m.group(1));
                    String unit = m.group(2);
                    ans += amount * exchange.get(unit);
                } else {
                    break;
                }
            }
        }

        return (int) ans;
    }

}