package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.*;

/**
 * 找出两个整数数组中同时出现的整数
 *
 * 现有两个整数数组，需要你找出两个数组中同时出现的整数，并按照如下要求输出:
 * 1.有同时出现的整教时，先按照同时出现次数(整数在两个数组中都出现并目出现次数较少的那个)进行归类，然后按照出现次数从小到大依次按行输出。
 * 2.没有同时出现的整数时，输出NULL
 * 输入描述
 * 第一行为第一个整数数组，第二行为第二个整数数组，每行数中整数与整数之间以英文号分，数组长度的范围为[1，10000]之间的整数。
 * 输出描述
 * 按照出现次数从小到大依次按行输出，每行输出的格式为:
 * 出现次数：该出现次数下的整数升序排序的结果
 * 格式中的":"为英文冒号，整数间以英文逗号分隔
 * 示例1:
 * 输入
 * 5,3,6,-8,0,11
 * 2,8,8,8,-1,15
 * 输出
 * NULL
 * 说明
 * 两个整数数组没有同时出现的整数，输出NULL。
 * 示例2:
 * 输入:
 * 5,8,11,3,6,8,8,-1,11,2,11,11
 * 11,2,11,8,6,8,8,-1,8,15,3,-9,11
 * 输出
 * 1:-1,2,3,6
 * 3:8,11
 * 说明
 * 两整数数组中同时出现的整数为-12、3、 6、8、 11 ,其中同时出现次数为1的整数为-1,2,36(升序排序),同时出现次数为3的整数为8,11(升序排序)，先升序输出出现次数为1的整数，再升序输出出现次数为3的整数。
 */
public class AppearSimultaneously {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.next();
        String str2 = scanner.next();

//        String str1 = "5,8,11,3,6,8,8,-1,11,2,11,11";
//        String str2 = "11,2,11,8,6,8,8,-1,8,15,3,-9,11";

        String[] split1 = str1.split(",");
        String[] split2 = str2.split(",");
        Map<Integer, String> map = find(split1, split2);

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }


    }

    private static Map<Integer, String> find(String[] split1, String[] split2) {
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();

        for (int i = 0; i < split1.length; i++) {
            map1.put(split1[i], map1.getOrDefault(split1[i], 0) + 1);
        }
        for (int i = 0; i < split2.length; i++) {
            map2.put(split2[i], map2.getOrDefault(split2[i], 0) + 1);
        }

        HashMap<Integer, String> result = new HashMap<>();
        List<Integer> collect = new ArrayList<>();
        for (String s : map1.keySet()) {
            collect.add(Integer.parseInt(s));
        }
        collect.sort(Comparator.comparingInt(a -> a));

        for (Integer k : collect) {
            String key = String.valueOf(k);
            if (map2.containsKey(key)) {
                int times = Math.min(map1.get(key), map2.get(key));
                result.merge(times, key, (a, b) -> a + "," + b);
            }
        }

        return result;
    }

}
