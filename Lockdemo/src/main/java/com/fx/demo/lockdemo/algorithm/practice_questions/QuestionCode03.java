package com.fx.demo.lockdemo.algorithm.practice_questions;

/**
 * 给定一个数组arr，你可以在每个数字之前决定+或者-
 * 但是必须所有数字都参与再给定一个数target，
 * 请问后算出target的方法数是多少?
 */
public class QuestionCode03 {

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 8, 9, 8, 10};
        int target = 50;

        int sum = sumMethod(arr, target);
        System.out.println(sum);
    }

    /**
     * 非负数 - 暴力递归解法
     *
     * @param arr
     * @param target
     * @return
     */
    private static int sumMethod(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        return process(arr, 0, target);
    }

    private static int process(int[] arr, int index, int target) {

        if (index >= arr.length) {
            return target == 0 ? 1 : 0;
        }
        if (target <= 0) {
            return 0;
        }

        return process(arr, index + 1, target - arr[index]) + process(arr, index + 1, target + arr[index]);
    }


}
