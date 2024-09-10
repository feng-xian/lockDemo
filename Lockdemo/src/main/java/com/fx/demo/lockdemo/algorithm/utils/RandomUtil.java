package com.fx.demo.lockdemo.algorithm.utils;

/**
 * @ClassName: RandomUtil
 * @Author: 凤仙
 * @Date: 2023/8/2 0002 10:13
 * @Description 对数器
 * @Version: 1.0
 */
public class RandomUtil {

    public static void main(String[] args) {

        int[] ints = sortRandomArr(15, 20);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();

    }

    private static void printArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组未生成功！");
            return;
        }

        for (int j : arr) {
            System.out.print(j + " ");
        }
        System.out.println();
    }

    /**
     * 值交换
     * @param arr
     * @return
     */
    private static void swap(int[] arr, int i, int j) {
        int maxData = arr[i];
        arr[i] = arr[j];
        arr[j] = maxData;
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            for (int i = end; i > 0; i--) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, (i - 1));
                }
            }
        }
    }

    /**
     * 随机有序数组
     *
     * @param randomArrSize
     * @param range
     * @return
     */
    public static int[] sortRandomArr(int randomArrSize, int range) {
        int[] randomArr = randomArr(randomArrSize, range);
        insertSort(randomArr);
        printArr(randomArr);
        return randomArr;
    }

    public static int[] randomArrNotSort(int randomArrSize, int range) {
        int size = buildArrSize(randomArrSize);
        int[] arr = new int[size];
        arr[0] = generateNum(range);
        for (int i = 1; i < size; i++) {
            int num = 0;

            do {
                num = generateNum(range);
            } while (arr[i - 1] == num);

            arr[i] = num;
        }
        printArr(arr);
        return arr;
    }

    /**
     * 随机数组
     *
     * @param randomArrSize
     * @param range
     * @return
     */
    public static int[] randomArr(int randomArrSize, int range) {
        int arrSize = buildArrSize(randomArrSize);

        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = generateNum(range);
        }
        printArr(arr);
        return arr;
    }

    private static int buildArrSize(int range) {
        int arrSize = 0;

        while (arrSize <= 0) {
            arrSize = generateNum(range);
        }
        return arrSize;
    }

    public static int generateNum(int range) {
        return (int) (Math.random() * range);
    }


}
