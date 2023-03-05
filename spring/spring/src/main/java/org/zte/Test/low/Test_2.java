package org.zte.Test.low;

import java.util.Scanner;

//农夫过河
public class Test_2
{
    public static int min_times;
    public static void main( String[] args )
    {

        System.out.print("print nums:");
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        min_times = 0;

        boat(a, b, c, 0, 0, 0);
        System.out.println("Minimal time is :" + min_times);
    }

    public static int boat(int sheep_local, int wolf_local, int sum, int sheep_side, int wolf_side, int times){
        if (sheep_local + wolf_local <= sum) {
            times += 1;
            if (min_times > times || min_times ==0) {
                min_times = times;
            }
            return times;
        } else {
            for (int i = 0; i <= sheep_local && i <= sum; i++) {
                for (int j = 0; j <= wolf_local && i + j <= sum; j++) {
                    if (i + j == 0) {
                        continue;
                    }

                    if ((sheep_local - i == 0 || sheep_local - i > wolf_local - j) && (sheep_side + i == 0 || sheep_side + i > wolf_side + j)) {
                        int result = boat(sheep_local - i, wolf_local - j, sum, sheep_side + i, wolf_side + i, times+1);
                        if (result != 0) {
                            if (result < min_times || min_times ==0) {
                                min_times = result;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}
