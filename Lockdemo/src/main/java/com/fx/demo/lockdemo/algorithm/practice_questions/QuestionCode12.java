package com.fx.demo.lockdemo.algorithm.practice_questions;

/**
 * 跳跃游戏
 * 给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 * https://leetcode.cn/problems/jump-game/description/
 */
public class QuestionCode12 {

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 1, 4};
        int step = canJumpStep(arr);
        System.out.println(step);
    }

    private static int canJumpStep(int[] arr) {
        if ((null == arr) || (arr.length == 0)
                || ((arr.length == 1) && (arr[0] == 0))) {
            return 0;
        }
        int step = 1;
        int next = arr[0];

        for (int i = 0; i < arr.length; i++) {
            ++step;
            int max = max(arr, next, i);
            if (max == arr.length - 1) {
                return step;
            }

            next = Math.min(arr[max] + max, arr.length - 1);
            i = max;
        }

        return step;
    }

    private static int max(int[] arr, int next, int i) {
        int j = i + 1;
        int max = 0;
        int index = j;
        while (j <= next) {
            if ((arr[j] + j) >= arr.length - 1) {
                return arr.length - 1;
            }
            if (((arr[j] + j) >= max)) {
                index = j;
                max = arr[j] + j;
            }
            j++;
        }
        return index;
    }


}
