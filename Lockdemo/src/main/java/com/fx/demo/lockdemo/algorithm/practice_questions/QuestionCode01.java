package com.fx.demo.lockdemo.algorithm.practice_questions;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点
 * 给定一个正数K，代表绳子的长度
 * 返回绳子最多压中几个点?
 * 即使绳子边缘处盖住点也算盖住
 */
public class QuestionCode01 {


    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 7, 9, 12, 14, 16};
        int k = 6;
        System.out.println(tradition(arr, k));
        System.out.println(dichotomy(arr, k));
    }

    /**
     * 使用二分法， N*Log(n)
     *
     * @param arr
     * @param k
     * @return
     */
    private static int dichotomy(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int maxP = 0;
        int N = arr.length;

        for (int i = N - 1; i > 0; i--) {
            int tag = arr[i] - k;
            int L = 0, R = i;

            int index = 0;
            while (L <= R) {
                int mid = (R + L) / 2;
                if (arr[mid] == tag) {
                    index = mid;
                    break;
                }
                if (arr[mid] > tag) {
                    R = mid - 1;
                    index = mid;
                }

                if (arr[mid] < tag) {
                    L = mid + 1;
                }
            }
            int count = i - index + 1;
            if (count > maxP) {
                maxP = count;
            }
        }

        return maxP;
    }


    /**
     * 传统解法，双层for O(n^2)
     *
     * @return
     */
    public static int tradition(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }

        int maxP = 0;

        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j <= i; j++) {
                if ((arr[i] - arr[j]) > k) {
                    break;
                }
                ++count;
                if (count > maxP) {
                    maxP = count;
                }
            }
        }

        return maxP;
    }


}
