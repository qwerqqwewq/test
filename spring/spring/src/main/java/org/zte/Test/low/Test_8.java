package org.zte.Test.low;
import java.util.Scanner;

//最小调整顺序次数
public class Test_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int m = n * 2;

        String[] cmds = new String[m];
        for (int i = 0; i < m; i++) {
            cmds[i] = sc.nextLine();
        }

        System.out.println(getResult(cmds));
    }

    public static int getResult(String[] cmds) {
        int size = 0;
        boolean isSorted = true;
        int count = 0;

        for (int i = 0; i < cmds.length; i++) {
            String cmd = cmds[i];
            if (cmd.startsWith("head add")) {
                if (size > 0 && isSorted) isSorted = false;
                size++;
            } else if (cmd.startsWith("tail add")) {
                size++;
            } else {
                if (size == 0) continue;
                if (!isSorted) {
                    count++;
                    isSorted = true;
                }
                size--;
            }
        }

        return count;
    }
}