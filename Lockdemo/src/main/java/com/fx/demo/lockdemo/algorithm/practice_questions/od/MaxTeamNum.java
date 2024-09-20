package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最多团队
 * 用数组代表每个人的能力，一个比赛活动要求参赛团队的最低能力值为N，每个团队可以由1人或2人组成，
 *
 * 且1个人只能参加1个团队， 请计算出最多可以派出多少支符合要求的团队？
 *
 * 输入描述:
 * 5
 * 3 1 5 7 9
 * 8
 * 第一行数组代表总人数，范围[1,500000]
 * 第二行数组代表每个人的能力，每个元素的取值范围[1, 500000]，数组的大小范围[1,500000]
 * 第三行数值为团队要求的最低能力值，范围[1, 500000]
 */
public class MaxTeamNum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        int[] arr = new int[num];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        int min = scanner.nextInt();
        scanner.close();

        int teamNum = maxTeamNum(arr, min);
        System.out.println(teamNum);
    }

    private static int maxTeamNum(int[] arr, int min) {
        Arrays.sort(arr);

        int N = arr.length;
        if (arr[0] >= min) {
            return N;
        }

        int count = 0;
        int e = N - 1;
        for (int i = 0; i < N; i++) {


            while (arr[e] >= min) {
                count++;
                e--;
            }

            if (e <= i) {
                break;
            }

            if ((arr[i] + arr[e]) >= min) {
                count++;
                e--;
            }

        }

        return count;
    }


}
