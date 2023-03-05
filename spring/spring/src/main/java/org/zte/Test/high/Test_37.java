package org.zte.Test.high;

import java.util.*;
import java.util.stream.Collectors;

//垃圾短信识别
public class Test_37 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[][] serial = new int[num][2];
        for (int i = 0; i < num; i++) {
            serial[i][0] = in.nextInt();
            serial[i][1] = in.nextInt();
        }
        int tid = in.nextInt();
        boolean flag = false;

        LinkedList<Integer> send = new LinkedList<>();
        LinkedList<Integer> receive = new LinkedList<>();

        HashMap<Integer, Integer> sendCount = new HashMap<>();
        HashMap<Integer, Integer> receiveCount = new HashMap<>();

        for (int i = 0; i < num; i++) {
            int sid = serial[i][0];
            int rid = serial[i][1];

            if (sid == tid) {
                send.addLast(rid);
                sendCount.put(rid, sendCount.getOrDefault(rid, 0) + 1);
            }

            if (rid == tid) {
                receive.addLast(sid);
                receiveCount.put(sid, sendCount.getOrDefault(sid, 0) + 1);
            }
        }

        HashSet<Integer> sendSet = new HashSet<>(send);
        HashSet<Integer> receiveSet = new HashSet<>(receive);

        List<Integer> collect = send.stream().filter(receiveSet::contains).collect(Collectors.toList());

        int l = sendSet.size() - collect.size();
        int m = send.size() - receive.size();
        if (l > 5 || m > 10) flag = true;
        if (!flag) {
            for (Integer id : send) {
                if (sendCount.getOrDefault(id, 0) - receiveCount.getOrDefault(id, 0) > 5) {
                    flag = true;
                    break;
                }
            }
        }

        System.out.println(flag + " " + l + " " + m);

    }
}
