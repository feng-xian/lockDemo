package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 机器人活动区间
 * https://wiki.amoscloud.com/zh/ProgramingPractice/NowCoder/Adecco/Topic0153
 */
public class RobotMobileArea {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

//        int[][] arr = {
//            {1, 2, 5, 2},
//            {2, 4, 4, 5},
//            {3, 5, 7, 1},
//            {4, 6, 2, 4}
//        };

        int areaNum = maxMobileArea(arr);
        System.out.println(areaNum);

    }

    private static int maxMobileArea(int[][] arr) {
        if (null == arr || arr.length == 0) {
            return 0;
        }

        LinkedList<String> oldPath = new LinkedList<>();
        int maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                String location = i + String.valueOf(j);
                if (oldPath.contains(location)) {
                    continue;
                }

                LinkedList<String> list = new LinkedList<>();
                int sumA = process(arr, i, j, arr[i][j], list);
                oldPath.addAll(list);
                maxSum = Math.max(sumA, maxSum);
            }
        }

        return maxSum;
    }

    private static int process(int[][] arr, int i, int j, int befV, LinkedList<String> locationList) {
        String location = i + String.valueOf(j);
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length) {
            return 0;
        }
        if (locationList.contains(location)) {
            return 0;
        }
        if (Math.abs(arr[i][j] - befV) > 1) {
            return 0;
        }
        locationList.addLast(location);
        int sum = 0;
        sum += process(arr, i + 1, j, arr[i][j], locationList);
        sum += process(arr, i - 1, j, arr[i][j], locationList);
        sum += process(arr, i, j + 1, arr[i][j], locationList);
        sum += process(arr, i, j - 1, arr[i][j], locationList);

//        locationList.removeLast();

        return sum + 1;
    }

}
