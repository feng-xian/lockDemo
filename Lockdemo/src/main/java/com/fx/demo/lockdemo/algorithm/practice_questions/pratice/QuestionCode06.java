package com.fx.demo.lockdemo.algorithm.practice_questions.pratice;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;

/**
 * 给定一个正数数组arr，代表若干人的体重
 * 再给定一个正数limit，表示所有船共同拥有的载重量
 * 每艘船最多坐两人，且不能超过载重
 * 想让所有的人同时过河，
 * 并且用最好的分配方法让船尽量少返回最少的船数
 */
public class QuestionCode06 {


    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 5, 5, 5, 5, 9, 9, 9};
        int limit = 10;

        int min = minNum(arr, limit);
        System.out.println(min);
    }

    private static int minNum(int[] arr, int limit) {
        if (arr == null || arr.length == 0 || limit <= 0) {
            return 0;
        }

        Arrays.sort(arr);
        int count = 0;
        int L = 0, R = arr.length - 1;
        while (L <= R) {
            if (L == R) {
                count++;
                break;
            }
            if ((arr[L] + arr[R]) <= limit) {
                L++;
            }
            count++;
            R--;

        }

        return count;
    }


}
