package com.fx.demo.lockdemo.algorithm.practice_questions.pratice;

/**
 * 在每行每列有序的二维数组中，求小于等于k的数有多少个
 */
public class QuestionCode13 {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8},
                {4, 5, 6, 7, 8, 9}
        };
        int k = 9;
        int num = getLessNum(arr, k);
        System.out.println(num);
    }

    private static int getLessNum(int[][] arr, int k) {
        if (null == arr || arr.length == 0) {
            return 0;
        }
        if (arr[0][0] > k) {
            return 0;
        }

        return process(arr, k, 0, arr[0].length - 1);
    }

    private static int process (int[][] arr, int k, int i, int j) {

        if (i >= arr.length || j < 0) {
            return 0;
        }
        int count1 = 0;
        if (arr[i][j] <= k) {
            count1 = j + 1;
            count1 +=  process(arr, k, i + 1, j);
        }

        if (arr[i][j] > k) {
            count1 += process(arr, k, i, j - 1);
        }
        return count1;

    }

}
