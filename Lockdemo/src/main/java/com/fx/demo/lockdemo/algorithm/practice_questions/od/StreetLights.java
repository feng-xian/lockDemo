package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 在一条笔直的公路上安装了N个路灯，
 * 从位置0开始安装，路灯之间间距固定为100米
 * 每个路灯都有自己的照明半径
 * 请计算第一个路灯和最后一个路灯之间，
 * 无法照明的区间的长度和。
 *
 * 第一行为一个数N，表示路灯个数，1 <= N <= 100000
 * 第二行为N个空格分割的数，表示路灯的照明半径，1 <= 照明半径
 *
 * 无法照明的区间的长度和。
 */
public class StreetLights {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int lightNum = scanner.nextInt();
        int[] rangeArr = new int[lightNum];
        for (int i = 0; i < lightNum; i++) {
            rangeArr[i] = scanner.nextInt();
        }

        int result = sumRange(rangeArr);
        System.out.println(result);

    }

    private static int sumRange(int[] rangeArr) {

        int N = rangeArr.length;

        int lightSum = 0;
        for (int i = 0; i < N; i++) {
            if (i != rangeArr.length - 1) {
                lightSum += Math.min(rangeArr[i] + rangeArr[i + 1], 100);
            }
        }

        return ((N - 1) * 100) - lightSum;

    }

}
