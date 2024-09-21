package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.Scanner;

/**
 * 跳房子
 */
public class JumpHouse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        String arrStr = scanner.next();
        scanner.close();

        String[] split = arrStr
                .replaceAll("\\[", "")
                .replaceAll("]", "")
                .split(",");


        int[] arr = new int[split.length];

        int[] result = indexSumMin(arr, count);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }

    private static int[] indexSumMin(int[] arr, int count) {
        int sum = Integer.MAX_VALUE;
        int[] result = new int[2];
        int N = arr.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (((arr[i] + arr[j]) == count) && (i + j) < sum) {
                    sum = Math.min(sum, i + j);
                    result[0] = arr[i];
                    result[1] = arr[j];
                }
            }
        }

        return result;
    }

}
