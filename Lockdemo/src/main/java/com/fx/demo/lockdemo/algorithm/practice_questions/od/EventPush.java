package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 事件推送
 * 同一个数轴X上有两个点的集合A={A1, A2, …, Am}和B={B1, B2, …, Bn}，
 * Ai和Bj均为正整数，A、B已经按照从小到大排好序，A、B均不为空，
 * 给定一个距离R(正整数)，
 * 列出同时满足如下条件的所有（Ai, Bj）数对：
 *
 * Ai <= Bj
 * Ai, Bj之间的距离小于等于R
 * 在满足1,2的情况下，每个Ai只需输出距离最近的Bj
 * 输出结果按Ai从小到大的顺序排序
 */
public class EventPush {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int r = scanner.nextInt();

        int[] arrA = new int[m];
        int[] arrB = new int[n];

        for (int i = 0; i < m; i++) {
            arrA[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            arrB[i] = scanner.nextInt();
        }

        List<String> result = solution(arrA, arrB, r);
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static List<String> solution(int[] arrA, int[] arrB, int r) {
        ArrayList<String> list = new ArrayList<>();

        int  ia = 0, ib = 0;

        while ((ia < arrA.length) && (ib < arrB.length)) {
            if (arrA[ia] > arrB[ib]) {
                ib++;
                continue;
            }
            if ((arrA[ia] - arrB[ib]) > r) {
                ia++;
                continue;
            }
            if (ia == ib) {
                String string = arrA[ia++] + " " + arrB[ib++];
                list.add(string);
                continue;
            }

            if (ia > ib) {
                int ib_c = ib + 1;
                int successIndex = ib;
                while (ib_c < arrB.length) {
                    if (arrA[ia] <= arrB[ib_c] && (arrA[ia] - arrB[ib_c]) <= r && (ib_c - ia) < (successIndex - ia)) {
                        successIndex = ib_c;
                        ib_c++;
                    }
                    else {
                        break;
                    }
                }
                String string = arrA[ia++] + " " + arrB[successIndex++];
                list.add(string);
                ib = successIndex;
                continue;
            }


        }

        return list;
    }

}
