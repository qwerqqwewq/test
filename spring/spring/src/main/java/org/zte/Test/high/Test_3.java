package org.zte.Test.high;

import java.util.*;

//无向图染色
public class Test_3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] edges = new int[n][2];
        for (int i = 0; i < n; i++) {
            edges[i][0] = in.nextInt();
            edges[i][1] = in.nextInt();
        }

        System.out.println(getResult(edges, m));
    }

    public static int getResult(int[][] edges, int node) {
        HashMap<Integer, ArrayList<Integer>> collection = new HashMap<>();
        for (int[] edge : edges) {
            collection.putIfAbsent(edge[0], new ArrayList<>());
            collection.get(edge[0]).add(edge[1]);
            collection.putIfAbsent(edge[1], new ArrayList<>());
            collection.get(edge[1]).add(edge[0]);
        }

        return dfs(collection, node, 1, 1, new LinkedList<>());
    }

    public static int dfs(HashMap<Integer, ArrayList<Integer>> connect,
                           int m, int index, int count,
                           LinkedList<ArrayList<Integer>> path){
        if (path.size() == m) return count;

        outer:
        for (int i = index; i < m; i++) {
            for (ArrayList<Integer> p : path) {
                if (p.contains(i)) continue outer;
            }

            count++;
            if (connect.containsKey(i)) {
                path.addLast(connect.get(i));
                count = dfs(connect, m, i + 1, count, path);
                path.removeLast();
            } else {
                count = dfs(connect, m, i + 1, count, path);
            }
        }
        return count;
    }
}
