package org.zte.Test.high;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//excel单元格数值统计
public class Test_10 {
    static Pattern p = Pattern.compile("^([A-Z])(\\d+)$");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rows = sc.nextInt();
        int cols = sc.nextInt();

        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = sc.next();
            }
        }

        String[] range = sc.next().split(":");

        System.out.println(getResult(matrix, rows, cols, range[0], range[1]));
    }

    /**
     * @param matrix 给定表格区域
     * @param rows 给定表格区域的行数
     * @param cols 给定表格区域的列数
     * @param start 选中区域的左上角位置
     * @param end 选中区域的右下角位置
     * @return 求和选中区域所有数的和
     */
    public static int getResult(String[][] matrix, int rows, int cols, String start, String end) {
        // 正则p于分解出Excel单元格位置坐标（形式如A1，B2，C3）的列和行，注意字母是列号，数字是行号
        Matcher m1 = p.matcher(start);
        Matcher m2 = p.matcher(end);

        if (m1.find() && m2.find()) {
            // 选中区域左上角坐标的解析
            int col_start = m1.group(1).charAt(0);
            int row_start = Integer.parseInt(m1.group(2));

            // 选中区域右下角坐标的解析
            int col_end = m2.group(1).charAt(0);
            int row_end = Integer.parseInt(m2.group(2));

            // ans保存选中区域所有数的和
            int ans = 0;
            // 从左上角坐标遍历到右下角坐标
            for (int j = col_start; j <= col_end; j++) {
                for (int i = row_start; i <= row_end; i++) {
                    char c = (char) j;
                    ans += getCell(c + "" + i, matrix);
                }
            }
            return ans;
        }

        // 异常情况，应该不会走到这步
        return 0;
    }

    /**
     * @param pos 指定Excel表格坐标，pos形式如A1，B2，C3
     * @param matrix Excel给定表格区域
     * @return 指定坐标pos的单元格内的值
     */
    public static int getCell(String pos, String[][] matrix) {
        Matcher m = p.matcher(pos);
        if (m.find()) {
            // 题目说列号取值A~Z，起始列A对应的码值65，A列等价于matrix矩阵的第0列
            int col = m.group(1).charAt(0) - 65;
            // 起始行1，等价于matrix矩阵的第0行
            int row = Integer.parseInt(m.group(2)) - 1;

            String cell = matrix[row][col];
            // 如果单元格内容以=开头，则为公式
            if (cell.startsWith("=")) {
                // 公式有三种情况
                // 等于某单元格的值，例如=B12
                // 两个单元格的双目运算（仅为+或-），形如=C1-C2、C3+B2
                String[] combine = cell.split("[\\=\\+\\-]");

                String cell1 = combine[1];

                // 对于 =A1 这种情况，cell2没有值
                String cell2 = null;
                if (combine.length > 2) {
                    cell2 = combine[2];
                }

                int cell1_val;
                if (cell1.matches("^-?\\d+$")) {
                    // 如果cell解析出来是值，则直接使用
                    cell1_val = Integer.parseInt(cell1);
                } else {
                    // 如果cell解析出来不是值，那就是Excel坐标
                    cell1_val = getCell(cell1, matrix);
                }

                // 同上
                int cell2_val;
                if (cell2 != null) {
                    if (cell2.matches("^-?\\d+$")) {
                        cell2_val = Integer.parseInt(cell2);
                    } else {
                        cell2_val = getCell(cell2, matrix);
                    }
                } else {
                    cell2_val = 0;
                }

                // 如果cell1和cell2是相加
                if (cell.contains("+")) {
                    matrix[row][col] = cell1_val + cell2_val + "";
                }
                // 如果cell1和cell2是相减
                else if (cell.contains("-")) {
                    matrix[row][col] = cell1_val - cell2_val + "";
                }
                // 如果没有运算，那就只可能是单值，直接使用
                else {
                    matrix[row][col] = cell1_val + "";
                }
            }

            // 如果单元格内容以=开头，则以上逻辑会将单元格内容更新为数值
            // 如果单元格内容不以=开头，则为可以直接使用的数值
            return Integer.parseInt(matrix[row][col]);
        }

        // 异常情况，应该不会走到这步
        return 0;
    }
}
