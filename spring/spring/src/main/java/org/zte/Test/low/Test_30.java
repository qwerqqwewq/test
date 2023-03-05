package org.zte.Test.low;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//获取软件最大版本号
public class Test_30 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        System.out.println(getResult(new String[]{str1, str2}));
    }

    public static String getResult(String[] versions) {
        String reg = "^(\\d+)(\\.(\\d+))(\\.(\\d+))?(\\-.+)?$";
        Pattern p = Pattern.compile(reg);

        Arrays.sort(versions, (v1, v2) -> {
            Matcher m1 = p.matcher(v1);
            Matcher m2 = p.matcher(v2);

            if (m1.find() && m2.find()) {
                Integer major1 = Integer.parseInt(m1.group(1));
                Integer major2 = Integer.parseInt(m2.group(1));

                if(major1 != major2) return major2 - major1;

                Integer minor1 = Integer.parseInt(m1.group(3));
                Integer minor2 = Integer.parseInt(m2.group(3));

                if(minor1 != minor2) return minor2 - minor1;

                String patch1 = m1.group(5);
                String patch2 = m2.group(5);

                if(patch1 != null && patch2 != null) {
                    int patch1_intVal = Integer.parseInt(patch1);
                    int patch2_intVal = Integer.parseInt(patch2);
                    if(patch1_intVal != patch2_intVal) {
                        return Integer.parseInt(patch2) - Integer.parseInt(patch1);
                    }
                } else if(patch1 != null) {
                    return -1;
                } else if(patch2 != null) {
                    return 1;
                }


                String mile1 = m1.group(6);
                String mile2 = m2.group(6);

                if(mile1 != null && mile2 != null) {
                    return mile2.compareTo(mile1);
                } else if(mile1 != null) {
                    return -1;
                } else if(mile2 != null) {
                    return 1;
                } else {
                    return 0;
                }
            }

            return 0;
        });

        return versions[0];
    }

}