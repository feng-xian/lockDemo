package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 数组组成最小数字
 *
 */
public class ArrayMinNum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        scanner.close();

        String[] split = next.split(",");

        int[] arr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }

        int minNum = buildMinNum(arr);
        System.out.println(minNum);

    }

    private static int buildMinNum(int[] arr) {
        Arrays.sort(arr);

        int N = Math.min(arr.length, 3);
        String[] strArr = new String[N];
        for (int i = 0; i < N; i++) {
            strArr[i] = String.valueOf(arr[i]);
        }

        Arrays.sort(strArr);

        StringBuilder numStr = new StringBuilder();
        for (String s : strArr) {
            numStr.append(s);
        }

        return Integer.parseInt(numStr.toString());
    }

}
