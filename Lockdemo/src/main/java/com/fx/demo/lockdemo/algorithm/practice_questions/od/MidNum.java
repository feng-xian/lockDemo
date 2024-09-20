package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 计算最接近的数
 */
public class MidNum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        scanner.close();

        String[] split = next.split("]");
        int k = Integer.parseInt(split[1].substring(1, 2));
        String[] arrStr = split[0].substring(1).split(",");

        int[] arr = new int[arrStr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }

        int res = findMidIndex(arr, k);
        System.out.println(res);
    }

    private static int findMidIndex(int[] arr, int k) {
        int mid = Arrays.stream(arr).sorted().toArray()[arr.length / 2];

        int index = 0;
        int c = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int end = i + k - 1;
            if (end >= arr.length) {
                return index;
            }
            int sum = sum(arr, i, end);

            if (c <= Math.abs(mid - sum)) {
                index = i;
                c = sum;
            }

        }


        return index;
    }

    private static int sum(int[] arr, int index, int end) {
        int sum = arr[index];

        if (index == end) {
            return Math.abs(sum);
        }
        for (int i = index + 1; i <= end; i++) {
            sum -= arr[i];
        }
        return Math.abs(sum);
    }


}
