package com.fx.demo.lockdemo.algorithm.practice_questions.pratice;

import java.util.HashMap;

/**
 * 给定一个字符串Str返回Str的所有子序列中有多少不同的字面值
 */
public class QuestionCode14 {

    public static void main(String[] args) {
        String str = "bccaccbaabbc";
        int num = diffStrChild(str);
        System.out.println(num);
    }

    /**
     * 不通子序列有多少个
     * @param str
     * @return
     */
    private static int diffStrChild(String str) {
        if (null == str || str.length() == 0) {
            return 0;
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        char[] array = str.toCharArray();
        int all = 1;

        for (int i = 0; i < array.length; i++) {
            int newAdd = all;
            int newAll = newAdd + all - (map.getOrDefault(array[i], 0));
            all = newAll;
            map.put(array[i], newAdd);
        }

        return all;
    }

}
