package com.fx.demo.lockdemo.algorithm.practice_questions;

/**
 * 分糖果问题
 * 给定数组arr，表示每个孩子的分数
 * 要求
 * 1、每个孩子至少一颗糖
 * 2、如果[i] > [i-1] 则 第i为的孩子得到的糖数要比i-1多
 * 3、如果[i] > [i+1] 则 第i为的孩子得到的糖数要比i+1多
 * 按上面规则进行分配，总的需要多少糖，返回糖数
 */
public class QuestionCode07 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 7, 8, 6, 5, 4, 1, 8, 5, 6, 1};
        int min = minCandy(arr);
        System.out.println(min);
    }

    private static int minCandy(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        left[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        right[arr.length - 1] = 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            else {
                right[i] = 1;
            }
        }

        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += Math.max(right[i], left[i]);
        }

        return sum;
    }

}
