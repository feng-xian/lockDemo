package com.fx.demo.lockdemo.algorithm.practice_questions.pratice;

/**
 * 一个数组中只有两种字符'G'和'B’想让所有的G都放在左侧，
 * 所有的B都放在右侧但是只能在相邻字符之间进行交换操作返回至少需要交换几次
 * 比如 BBGGBGBBGB
 * 通过上面的规则交换，最终一定能得到一个GGGGBBBBBB的字符串
 */
public class QuestionCode02 {

    public static void main(String[] args) {
        char[] arr = {'B','B','G','B','B','G','G','B','B','G','B','G', 'B'};
        System.out.println(changeTimes(arr));
    }

    private static int changeTimes(char[] arr) {
        int changeG = 0;

        if (arr == null || arr.length == 0) {
            return changeG;
        }

        int N = arr.length;
        int L = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] == 'G') {
                changeG += i - L++;
            }
        }
        int changeB = 0;
        int R = N - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] == 'B') {
                changeB += (R--) - i;
            }
        }

        return Math.min(changeG, changeB);
    }




}
