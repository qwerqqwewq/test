package org.zte.Test.high;
import java.util.*;

//过滤组合字符串
public class Test_2 {
    static String[] map = {"abc", "def", "ghi", "jkl", "mno", "pqr", "st", "uv", "wx", "yz"};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer[] arr =
                Arrays.stream(sc.next().split("")).map(Integer::parseInt).toArray(Integer[]::new);
        String filter = sc.next();

        System.out.println(getResult(arr, filter));
    }

    public static String getResult(Integer[] arr, String filter) {
        String[] newArr = Arrays.stream(arr).map(val -> map[val]).toArray(String[]::new);

        char[] cArr = filter.toCharArray();
        Arrays.sort(cArr);
        filter = new String(cArr);

        ArrayList<String> res = new ArrayList<>();
        dfs(newArr, 0, new LinkedList<>(), res, filter);

        StringJoiner sj = new StringJoiner(" ", "", "");
        for (String str : res) {
            sj.add(str);
        }
        return sj.toString();
    }

    public static void dfs(
            String[] arr, int index, LinkedList<Character> path, ArrayList<String> res, String filter) {
        if (index == arr.length) {
            // 过滤这些完全包含屏蔽字符串的每一个字符的字符串
            if (!include(path, filter)) {
                StringBuilder sb = new StringBuilder();
                for (Character c : path) {
                    sb.append(c);
                }
                res.add(sb.toString());
            }
            return;
        }

        for (int i = 0; i < arr[index].length(); i++) {
            path.addLast(arr[index].charAt(i));
            dfs(arr, index + 1, path, res, filter);
            path.removeLast();
        }
    }

    public static boolean include(LinkedList<Character> path, String filter) {
        StringBuilder sb = new StringBuilder();
        path.stream().sorted().forEach(sb::append);
        String src = sb.toString();

        if (filter.length() > src.length()) return false;

        int i = 0;
        int j = 0;

        while (i < src.length() && j < filter.length()) {
            if (src.charAt(i) == filter.charAt(j)) j++;
            i++;
        }

        return j == filter.length();
    }
}