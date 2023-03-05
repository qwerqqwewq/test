package org.zte.Test.low;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

//最短木板长度
public class Test_12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        System.out.println(getResult(m, a));
    }

    public static int getResult(int m, int[] a) {
        // 统计每种长度板的数量，记录到woods中，key是板长度，val是板数量
        HashMap<Integer, Integer> woods = new HashMap<>();
        for (Integer ai : a) {
            if (woods.containsKey(ai)) {
                Integer val = woods.get(ai);
                woods.put(ai, ++val);
            } else {
                woods.put(ai, 1);
            }
        }

        // 将统计到的板，按板长度排优先级，长度越短优先级越高，这里使用优先队列来实现优先级
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((b, c) -> b[0] - c[0]);
        for (Integer wood : woods.keySet()) {
            pq.offer(new Integer[] {wood, woods.get(wood)});
        }

        // 只要还有剩余的m长度，就将他补到最短板上
        while (m > 0) {
            // 如果只有一种板长度，那么就尝试将m平均分配到各个板上
            if (pq.size() == 1) {
                Integer[] info = pq.poll();
                int len = info[0];
                int count = info[1];
                return len + m / count;
            }

            // 如果有多种板长度
            // min1是最短板
            Integer[] min1 = pq.poll();
            // min2是第二最短板
            Integer[] min2 = pq.peek();

            // diff是最短板和第二最短板的差距
            int diff = min2[0] - min1[0];
            // 将所有最短板补足到第二短板的长度，所需要总长度total
            int total = diff * min1[1];

            // 如果m的长度不够补足所有最短板，那么说明此时最短板的长度就是题解
            if (total > m) {
                return min1[0] + m / min1[1];
            }
            // 如果m的长度刚好可以补足所有最短板，那么说明最短板可以全部升级到第二短板，且刚好用完m，因此第二短板的长度就是题解
            else if (total == m) {
                return min2[0];
            }
            // 如果m的长度足够长，能补足所有最短板到第二短板，还能有剩余，则将最短的数量加到第二短板的数量上，继续下轮循环
            else {
                m -= total;
                min2[1] += min1[1];
            }
        }

        return pq.peek()[0];
    }
}