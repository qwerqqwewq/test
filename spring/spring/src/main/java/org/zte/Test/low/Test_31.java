package org.zte.Test.low;

import java.util.*;

//整理扑克牌
public class Test_31 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        Integer[] arr = Arrays.stream(str.split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        System.out.println(getResult(arr));
    }

    public static String getResult(Integer[] arr) {
        HashMap<Integer, Integer> card = new HashMap<>();

        // 统计各种牌面的数量
        for (Integer num : arr) {
            if (card.containsKey(num)) {
                int val = card.get(num);
                card.put(num, ++val);
            } else {
                card.put(num, 1);
            }
        }

        // 统计组合，4代表炸弹，3+2代表葫芦，3代表三张，2代表对子，1代表单张
        HashMap<String, LinkedList<Integer[]>> combine = new HashMap<>();
        combine.put("4", new LinkedList<Integer[]>());
        combine.put("3+2", new LinkedList<Integer[]>());
        combine.put("3", new LinkedList<Integer[]>());
        combine.put("2", new LinkedList<Integer[]>());
        combine.put("1", new LinkedList<Integer[]>());

        // 首先将初始组合统计出来
        Set<Integer> cardKeys = card.keySet();
        for (Integer num : cardKeys) {
            switch (card.get(num)) {
                case 3:
                    combine.get("3").add(new Integer[] {num});
                    break;
                case 2:
                    combine.get("2").add(new Integer[] {num});
                    break;
                case 1:
                    combine.get("1").add(new Integer[] {num});
                    break;
                default:
                    combine
                            .get("4")
                            .add(
                                    new Integer[] {
                                            num, card.get(num)
                                    }); // 由于炸弹可能有4张以上相同牌面组成，因此既需要统计牌面num，也需要统计牌数card[num]
            }
        }

        // 炸弹排序，按照牌面值总和大小排序，总和越大，越高前，牌面总和 = 牌面值 * 牌数
        combine.get("4").sort((a, b) -> b[0] * b[1] - a[0] * a[1]);

        // 三张排序，牌面值越大，三张越大
        combine.get("3").sort((a, b) -> b[0] - a[0]);

        // 对子排序，牌面值越大，对子越大
        combine.get("2").sort((a, b) -> b[0] - a[0]);

        // 尝试组合出葫芦
        while (combine.get("3").size() > 0) {
            // 如果对子用完，三张还有一个，那么可以直接结束循环
            if (combine.get("2").size() == 0 && combine.get("3").size() == 1) break;

            // 否则，选取一个最大的三张
            Integer san_top = combine.get("3").removeFirst()[0];

            Integer tmp;
            // 如果此时没有对子了，胡总和第二大的三张的牌面，比最大的对子牌面大，则可以拆分三张，组合出葫芦
            if (combine.get("2").size() == 0
                    || (combine.get("3").size() > 0
                    && combine.get("3").get(0)[0] > combine.get("2").get(0)[0])) {
                tmp = combine.get("3").removeFirst()[0];
                // 拆分三张为对子的话，会多出一个单张
                combine.get("1").add(new Integer[] {tmp});
            } else {
                // 如果对子牌面比三张大，则不需要拆分三张，直接使用对子组合出葫芦
                tmp = combine.get("2").removeFirst()[0];
            }
            combine.get("3+2").add(new Integer[] {san_top, tmp}); // 葫芦元素含义：[三张牌面，对子牌面]
        }

        // 处理完葫芦后，就可以对单张进行降序了（因为组合葫芦的过程中，可能产生新的单张，因此单张排序要在葫芦组合得到后进行）
        combine.get("1").sort((a, b) -> b[0] - a[0]);

        // ans存放题解
        ArrayList<Integer> ans = new ArrayList<>();

        // 首先将炸弹放到ans中
        for (Integer[] vals : combine.get("4")) {
            int score = vals[0];
            int count = vals[1];
            for (int i = 0; i < count; i++) {
                ans.add(score);
            }
        }

        // 然后将葫芦放大ans中
        for (Integer[] vals : combine.get("3+2")) {
            int san = vals[0];
            int er = vals[1];
            for (int i = 0; i < 3; i++) ans.add(san);
            for (int i = 0; i < 2; i++) ans.add(er);
        }

        // 之后将三张放到ans中
        for (Integer[] vals : combine.get("3")) {
            for (int i = 0; i < 3; i++) ans.add(vals[0]);
        }

        // 接着是对子放到ans中
        for (Integer[] vals : combine.get("2")) {
            for (int i = 0; i < 2; i++) ans.add(vals[0]);
        }

        // 最后是单张放到ans中
        for (Integer[] vals : combine.get("1")) {
            ans.add(vals[0]);
        }

        StringJoiner sj = new StringJoiner(" ", "", "");
        for (Integer an : ans) {
            sj.add(an + "");
        }

        return sj.toString();
    }
}