package com.fx.demo.lockdemo.algorithm.sort;

public class 二分法 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 4, 5, 6, 7, 8, 8, 8, 9, 9};
        exist(arr, 52);
        findFirst(arr, 3);
    }

    /**
     * 使用二分法，在有序数组arr中，查找num的起始位置，如果不存在则为-1
     * @param arr
     * @param num
     */
    public static void findFirst(int[] arr, int num) {
        int left = 0;
        int right = arr.length + 1;
        int mid = (left + right)/2;
        int min = -1;
        while (left <= right) {
            if (arr[mid] == num) {
                min = mid;
                right = mid - 1;
            }
            if (arr[mid] < num) {
                left = mid + 1;
            }
            if (arr[mid] > num) {
                right = mid - 1;
            }
            mid = (left + right)/2;
        }
        System.out.println("起始位置为：" + min);

    }

    /**
     * 使用二分法，判断arr数组中是否存在num
     *
     * @param arr
     * @param num
     */
    public static void exist(int[] arr, int num) {
        int left = 0;
        int right = arr.length + 1;
        int mid = (left + right) / 2;
        boolean flag = false;
        while (left < right) {

            if (arr[mid] == num) {
                flag = true;
                break;
            }

            if (arr[mid] > num) {
                right = mid - 1;
            }

            if (arr[mid] < num) {
                left = mid + 1;
            }
            mid = (left + right) / 2;

        }
        System.out.println("是否存在：" + flag);
    }

}
