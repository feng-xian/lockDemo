package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 小王在进行游戏大闯关，有一个关卡需要输入一个密码才能通过，密码获得的条件如下：
 * 在一个密码本中，每一页都有一个由26个小写字母组成的若干位密码，
 * 从它的末尾开始依次去掉一位得到的新密码也在密码本中存在。
 * 请输出符合要求的密码，如果由多个符合要求的密码，则返回字典序最大的密码。
 * 若没有符合要求的密码，则返回空字符串。
 * 密码本由一个字符串数组组成，不同元素之间使用空格隔开，每一个元素代表密码本每一页的密码。
 * 输入：b eredderd bw bww bwwl bwwlm bwwln
 * 输出： bwwln
 *
 * "boolm" 和 "bwwln" 从末尾开始依次去掉一位得到密码在密码本中都存在，但是 “bwwln” 比 “bwwlm” 字典序排序大，所以应该返回 “bwwln”。
 */
public class FindPassword {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        scanner.close();

        String[] split = next.split(" ");

        HashSet<String> list = new HashSet<>();
        list.addAll(Arrays.asList(split));

        String psw = findPassword(list, split);
        System.out.println(psw);
    }

    private static String findPassword(HashSet<String> list, String[] split) {
        if (null == split || split.length == 0) {
            return "";
        }
        if (split.length == 1) {
            return split[0];
        }

        for (int i = split.length - 1; i >= 0; i--) {
            String str = split[i];
            while (list.contains(str)) {
                if (str.length() == 1) {
                    return split[i];
                }
                str = str.substring(0, str.length() - 1);
            }
        }

        return "";
    }


}
