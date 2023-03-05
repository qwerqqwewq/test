package org.zte.Test.high;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//连接器问题
public class Test_5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        Integer[][] ranges = Arrays.stream(input.substring(1, input.length()-1).split("],\\["))
                .map(str->Arrays.stream(str.split(","))
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new)).toArray(Integer[][]::new);
        String connect = in.nextLine();
        List<Integer> integers = Arrays.stream(connect.substring(1, connect.length() - 1)
                .split(",")).map(Integer::parseInt)
                .collect(Collectors.toList());

        Arrays.sort(ranges, Comparator.comparingInt(a -> a[0]));
        LinkedList<Integer[]> mergeRanges = new LinkedList<>();
        mergeRanges.addLast(ranges[0]);

        LinkedList<Integer> diffs = new LinkedList<>();

        for (Integer[] value : ranges) {
            Integer[] last = mergeRanges.getLast();
            int s1 = last[0];
            int e1 = last[1];

            int s2 = value[0];
            int e2 = value[1];

            if (s2 <= e1) {
                mergeRanges.removeLast();
                mergeRanges.addLast(new Integer[]{s1, Math.max(e1, e2)});
            } else {
                diffs.addLast(s2 - e1);
                mergeRanges.addLast(value);
            }
        }
        diffs.sort((a, b) -> b - a);
        integers.sort((a, b) -> b - a);

        while (integers.size() > 0 && diffs.size() > 0) {
            if (integers.remove(integers.size() - 1) >= diffs.getLast()) {
                diffs.removeLast();
            }
        }

        System.out.println(diffs.size() + 1);

    }
}
