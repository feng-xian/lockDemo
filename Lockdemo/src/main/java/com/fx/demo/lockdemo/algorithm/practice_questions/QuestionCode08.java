package com.fx.demo.lockdemo.algorithm.practice_questions;

/**
 * 字串交错问题
 * 给定两个子字串str1,str2，
 * 给定一个总字符串str3， 判断str3是否由str1，str2组成
 */
public class QuestionCode08 {

    public static void main(String[] args) {
        String str1 = "aaabfsk";
        String str2 = "aaacffkk";
        String str3 = "aaaacaabfffskkk";
        System.out.println(isInterleave(str1, str2, str3));
    }


    /**
     * 动态规划
     * @param str1
     * @param str2
     * @param str3
     * @return
     */
    private static boolean isInterleave(String str1, String str2, String str3) {
        if (null == str2 || null == str1 || null == str3) {
            return false;
        }

        if ((str1.length() + str2.length()) != str3.length()) {
            return false;
        }

        char[] arrS1 = str1.toCharArray();
        char[] arrS2 = str2.toCharArray();
        char[] arrS3 = str3.toCharArray();

        boolean[][] dp = new boolean[arrS1.length + 1][arrS2.length + 1];

        dp[0][0] = true;
        for (int i = 1; i <= arrS1.length; i++) {
            if (arrS1[i - 1] != arrS3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }

        for (int i = 1; i <= arrS2.length; i++) {
            if (arrS2[i - 1] != arrS3[i - 1]) {
                break;
            }
            dp[0][i] = true;
        }

        for (int i = 1; i <= arrS1.length; i++) {
            for (int j = 1; j <= arrS2.length; j++) {
                if (((arrS3[i + j - 1] == arrS1[i - 1] && dp[i-1][j]))
                || (arrS3[i + j - 1] == arrS2[j - 1]) && dp[i][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }

        return dp[arrS1.length][arrS2.length];
    }

}
