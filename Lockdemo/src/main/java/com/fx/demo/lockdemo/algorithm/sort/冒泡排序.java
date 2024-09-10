package com.fx.demo.lockdemo.algorithm.sort;

import java.util.Arrays;

public class 冒泡排序 {


    public static void main(String[] args) {
        int[] ar = {5,6,3,2,9,1,3,4};
//        sort(ar);
        insertionSort(ar);
        System.out.println(new String(Arrays.toString(ar)));
    }

    /**
     * 插入排序
     * @param arr
     * @return
     */
    public static int[] insertionSort(int[] arr){
        if (null == arr || arr.length < 2) {
            return arr;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                int tmp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = tmp;
            }
        }

        return arr;
    }

    public static int[] sort(int[] arr) {
        if (null == arr || arr.length == 1) {
            return arr;
        }

        int length = arr.length;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length -1; j++) {

                if (arr[j] < arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }

            }
        }

        return arr;

    }

}
