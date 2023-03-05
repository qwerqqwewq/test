package org.zte.Test.low;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

//h he hel hello o ok n ni nin ninj ninja
//真正的密码
public class Test_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[] s1 = s.split(" ");

        HashSet<String> list = new HashSet<>(Arrays.asList(s1));

        String true_pwd = "";

        for (String pwd : list) {
            boolean flag = true;
            for (int i = 1; i <= pwd.length(); i++) {
                System.out.println("pwd:" + pwd);
                String aa = pwd.substring(0, i);
                System.out.println("aa:" + aa);
                if (!list.contains(aa)) {
                    flag = false;
                    break;
                }
            }
            System.out.println("flag:" + flag);
            if (flag) {
                if (true_pwd.length() < pwd.length()) {
                    true_pwd = pwd;
                } else if (true_pwd.length() == pwd.length()) {
                    if (true_pwd.compareTo(pwd) > 0) {
                        true_pwd = pwd;
                    }
                }
            }
        }
        System.out.println("true_pwd = " + true_pwd);
    }
}
