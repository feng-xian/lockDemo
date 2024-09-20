package com.fx.demo.lockdemo.algorithm.practice_questions.pratice;

import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 最小包含区间问题
 * 多个有序数组
 * list={5,8,9,12,67,120,500}
 * list2={1,24,57,70,127,340,560}
 * list3={501,570,780,1001,1367,1670,1900,2333,3456}
 * 求数组中的数字组成的区间，每个数组至少有一个元素落在该区间上，且是最小的区间
 */
public class QuestionCode15 {

    public static void main(String[] args) {
        int[] list = {2, 3, 7};
        int[] list2 = {4, 10, 12};
        int[] list3 = {1, 6, 14};

        int[] minRange = minRangeA(list, list2, list3);

    }

    private static int[] minRangeA(int[] list, int[] list2, int[] list3) {
        int[] result = new int[2];
        HashMap<Integer, Location> map = new HashMap<>();




        return new int[0];
    }

    private static class Location {
        int value;
        int index;
        int num;
    }

}
