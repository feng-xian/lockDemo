package com.fx.demo.lockdemo.algorithm.practice_questions;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给定一个数组arr，代表每个人的能力值。
 * 再给定一个非负数k如果两个人能力差值正好为k，
 * 那么可以凑在一起比赛
 * 一局比赛只有两个人返回最多可以同时有多少场比赛
 */
public class QuestionCode05 {

    public static void main(String[] args) {
        int[] arr = {7, 5, 1, 1, 3, 3};
        int k = 2;
        int count = maxGame(arr, k);
        System.out.println(count);
    }

    private static int maxGame(int[] arr, int k) {

        if (null == arr || arr.length < 3 || k < 0) {
            return 0;
        }
        Arrays.sort(arr);

        int L = 0, R = 1;
        int gameCount = 0;

        ArrayList<Integer> old = new ArrayList<>();
        while (R < arr.length) {

            if ((arr[R] - arr[L]) == k) {
                gameCount++;
                old.add(R);
                R++;
                while (L < R) {
                    if (!old.contains(++L)) {
                        break;
                    }
                }
            } else {
                R++;
            }
        }

        return gameCount;
    }


}
