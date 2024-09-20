package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定两个字符串s1和s2和正整数K，其中s1长度为n1，s2长度为n2，
 * 在s2中选一个子串，满足:
 *
 * 该子串长度为n1+k
 * 该子串中包含s1中全部字母，
 * 该子串每个字母出现次数不小于s1中对应的字母，
 * 我们称s2以长度k冗余覆盖s1，
 * 给定s1，s2，k,
 * 求最左侧的s2以长度k冗余覆盖s1的子串的首个元素的下标，
 * 如果没有返回-1。
 */
public class LeftChildStr {


    public static void main(String[] args) {
        String str = "qwerewre";
        char a = '\'';
        char b = '\"';
        char c = '\n';
        System.out.println(str.substring(0, 3));
//        Scanner scanner = new Scanner(System.in);
//
//        String s1 = scanner.next();
//        String s2 = scanner.next();
//        int k = scanner.nextInt();
//        scanner.close();
//
//        int index = maxLeftStrIndex(s1, s2, k);
//        System.out.println(index);
    }

    private static int maxLeftStrIndex(String s1, String s2, int k) {
        if (null == s2 || null == s1) {
            return -1;
        }
        if (s2.length() < (s1.length() + k)) {
            return -1;
        }

        char[] s2CharArray = s2.toCharArray();
        char[] s1CharArray = s1.toCharArray();

        HashMap<Character, Integer> mapS1 = arr2map(s1CharArray);
        HashMap<Character, Integer> mapS2 = new HashMap<>();

        int index = 0;
        while (index < s2CharArray.length) {
            if ((index + k) >= s2CharArray.length) {
                return -1;
            }
            if (mapS2.isEmpty()) {
                String substring = s2.substring(index, index + k);
                mapS2 = arr2map(substring.toCharArray());
            }

            if (compare(mapS2, mapS1)) {
                return index;
            }

            if (mapS2.get(s2CharArray[index]) > 1) {
                mapS2.put(s2CharArray[index], mapS2.get(s2CharArray[index]) - 1);
            }
            else  {
                mapS2.remove(s2CharArray[index]);
            }

            index++;
            if ((index + k) >= s2CharArray.length) {
                return -1;
            }
            if (mapS2.get(s2CharArray[index + k]) > 1) {
                mapS2.put(s2CharArray[index + k], mapS2.get(s2CharArray[index + k + 1]) + 1);
            }
            else  {
                mapS2.put(s2CharArray[index + k], 1);
            }

        }

//        for (int i = 0; i < s2CharArray.length; i++) {
//            if ((i + k) >= s2CharArray.length) {
//                return -1;
//            }
//
//            if (mapS2.isEmpty()) {
//                String substring = s2.substring(i, i + k);
//                mapS2 = arr2map(substring.toCharArray());
//            }
//
//
//            if (compare(mapS2, mapS1)) {
//                return i;
//            }
//        }


        return 0;
    }

    private static boolean compare(HashMap<Character, Integer> mapS2, HashMap<Character, Integer> mapS1) {
        if (!mapS2.keySet().containsAll(mapS1.keySet())) {
            return false;
        }

        for (Character key : mapS2.keySet()) {
            if (mapS2.get(key) < mapS1.get(key)) {
                return false;
            }
        }

        return true;
    }

    private static HashMap<Character, Integer> arr2map (char[] arr) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : arr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return map;
    }


}
