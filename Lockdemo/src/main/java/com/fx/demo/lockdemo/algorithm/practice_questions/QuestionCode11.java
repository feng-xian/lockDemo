package com.fx.demo.lockdemo.algorithm.practice_questions;

/**
 * 定义何为step sum?
 * 比如680，680+68+6=754，680的step sum叫754
 * 给定一个正数num，判断它是不是某个数的step sum
 *
 * 使用二分法，进行每个数尝试
 */
public class QuestionCode11 {

    public static void main(String[] args) {
//        int num = 7286;
        int num = 8093;
        int result = step(num);
        System.out.println(result);
    }

    private static int step(int num) {
        if (num <= 0) {
            return 0;
        }

        int L = 0, R = num;

        while (L <= R) {
            int mid = (L + R) / 2;
            int stepSum = getStepSum(mid);
            if (stepSum == num) {
                return mid;
            }
            if (stepSum > num) {
                R = mid - 1;
            }
            if (stepSum < num) {
                L = mid + 1;
            }
        }

        return 0;
    }

    private static int getStepSum(int number) {
        String strN = String.valueOf(number);

        int sum = number;
        while (strN.length() > 1) {
            strN = strN.substring(0, strN.length() - 1);
            sum += Integer.parseInt(strN);
        }

        return sum;

    }


}
