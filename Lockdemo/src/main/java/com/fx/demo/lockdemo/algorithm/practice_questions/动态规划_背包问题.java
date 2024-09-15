package com.fx.demo.lockdemo.algorithm.practice_questions;


/**
 * 给定两个等长数组 w[]  v[]
 * w表示占用空间，v表示价值。比如数组i位置，则表示，i号货物占用空间为w[i]，价值为 v[i]
 * 给背包的空间为p，求装满背包的最大价值
 */
public class 动态规划_背包问题 {


    public static void main(String[] args) {
        int[] w = {5, 6, 2, 3, 1, 4};
        int[] v = {6, 7, 8, 6, 4, 1};
        int p = 8;

        int maxValue = maxValue(w, v, p);
        System.out.println(maxValue);
    }

    /**
     * 暴力递归解法
     *
     * @param w
     * @param v
     * @param p
     * @return
     */
    private static int maxValue(int[] w, int[] v, int p) {
        if (null == w || null == v || w.length != v.length || w.length == 0 || p == 0) {
            return 0;
        }

        return process(w, v, 0, p);
    }

    private static int process(int[] w, int[] v, int index, int p) {
        if (p < 0) {
            return 0;
        }
        if (index >= w.length) {
            return 0;
        }

        int value1 = process(w, v, index + 1, p);
        int value2 = 0;
        if (p > w[index]) {
            // 能装
            value2 = v[index] + process(w, v, index + 1, p - w[index]);
        }

        return Math.max(value1, value2);
    }

}
