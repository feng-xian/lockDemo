package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 整数对最小和
 * 给定两个整数数组array1、array2，数组元素按升序排列。
 *
 * 假设从array1、array2中分别取出一个元素可构成一对元素，现在需要取出k对元素，
 *
 * 并对取出的所有元素求和，计算和的最小值。
 *
 * 注意：
 *
 * 两对元素如果对应于array1、array2中的两个下标均相同，则视为同一对元素。
 *
 * 原文链接：https://blog.csdn.net/qq_33183456/article/details/131257733
 */
public class MinSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arrL = scanner.nextInt();
        int[] array1 = new int[arrL];
        for (int i = 0; i < arrL; i++) {
            array1[i] = scanner.nextInt();
        }

        arrL = scanner.nextInt();
        int[] array2 = new int[arrL];
        for (int i = 0; i < arrL; i++) {
            array2[i] = scanner.nextInt();
        }

        int number = scanner.nextInt();
        scanner.close();

        int minSum = minSum(array1, array2, number);
        System.out.println(minSum);
    }

    private static int minSum(int[] array1, int[] array2, int number) {

        if (null == array1
                || array1.length == 0
                || null == array2
                || array2.length == 0
                || number <= 0
                || number > (array1.length*array2.length)) {
            return 0;
        }

        int[] arrSum = new int[array1.length * array2.length];

        int index = 0;
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                arrSum[index++] = array1[i] + array2[j];
            }
        }

        Arrays.sort(arrSum);
        int sum = 0;
        for (int i = 0; i < number; i++) {
            sum += arrSum[i];
        }


        return sum;
    }

}
