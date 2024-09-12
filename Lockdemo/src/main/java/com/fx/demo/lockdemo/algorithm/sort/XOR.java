package com.fx.demo.lockdemo.algorithm.sort;

// 异或运算
public class XOR {

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5};
        int oddNum = findOddNum(arr);
        System.out.println(oddNum);
    }

    /**
     * 一个数组中一个数出行奇数次，其他数都是偶数次，怎么找到并打印这个出现奇数次的数
     *
     * @param arr
     * @return
     */
    public static int findOddNum(int[] arr) {
        int eor = 0;

        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        return eor;
    }

}
