package com.fx.demo.lockdemo.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class mergeSort {

    public static void main(String[] args) {
        int[] arr = {9, 7, 4, 3, 6, 7, 8, 3, 3, 4, 2, 1, 6, 0};
        process(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void process(int[] arr, int L, int R) {

        if (L == R) {
            return;
        }
        int mid = (L + R) / 2;
        process(arr, L, mid);
        process(arr, mid + 1, R);

        doMerge(arr, L, mid, R);

    }

    private static void doMerge(int[] arr, int L, int mid, int R) {
        int N = R - L + 1;
        int[] cacheArr = new int[N];

        int p1 = L, p2 = mid + 1;
        int i = 0;

        while ((p1 <= mid) && (p2 <= R)) {
            cacheArr[i++] = (arr[p1] <= arr[p2]) ? arr[p1++] : arr[p2++];
        }

        while ((p1 <= mid) && (p2 > R)) {
            cacheArr[i++] = arr[p1++];
        }

        while ((p1 > mid) && (p2 <= R)) {
            cacheArr[i++] = arr[p2++];
        }

        for (int j = 0; j < N; j++) {
            arr[L + j] = cacheArr[j];
        }

    }


    /**
     * 堆就两个操作， heapInsert， heapFiy
     * 大根堆
     * 堆中，i的父节点为 （i - 1）/ 2
     *
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, arr[index], arr[(index - 1) / 2]);
            index = (index - 1) / 2;
        }
    }

    /**
     * 大跟堆
     * 堆中，节点i的 左子节点（2*i + 1）/2 右子节点（2*i + 2） / 2
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    private static void heapFiy(int[] arr, int index, int heapSize) {

        int left = (index * 2 + 1) / 2;
        while (left < heapSize) {

            int largest = (((left + 1) < heapSize) && (arr[left] < arr[left + 1])) ? (left + 1) : left;

            if (arr[largest] <= arr[index]) {
                break;
            }

            swap(arr, largest, index);
            index = largest;
            left = (index * 2 + 1) / 2;

        }


    }

    private static void swap(int[] arr, int i, int i1) {
        int s = arr[i];
        arr[i] = arr[i1];
        arr[i1] = s;
    }

}
