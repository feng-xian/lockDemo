package com.fx.demo.lockdemo.algorithm.practice_questions;

/**
 * 现有司机N*2人，调度中心会将所有司机平分给A、B两个区域
 * 第i个司机去A可得收入为income[i][0]
 * 第i个司机去B可得收入为income[i][1],
 * 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱
 */
public class QuestionCode04 {

    public static void main(String[] args) {
        int[][] income = new int[10][2];

        int money = maxMoney(income);
        System.out.println(money);
    }

    private static int maxMoney(int[][] income) {
        if (null == income || income.length == 0 || income.length / 2 != 0) {
            return 0;
        }
        int N = income.length / 2;

        return process(income, 0, N);
    }

    /**
     * @param income 收入表
     * @param index  下一个司机
     * @param rest   A区域的空闲司机数
     * @return 在rest内的最大分配收益数
     */
    private static int process(int[][] income, int index, int rest) {

        if (index == income.length) {
            return 0;
        }

        if (rest == 0) {
            return income[index][1] + process(income, index + 1, rest);
        }

        if (income.length - index == rest) {
            return income[index][0] + process(income, index + 1, rest);
        }

        int v2 = income[index][0] + process(income, index + 1, rest);
        int v1 = income[index][1] + process(income, index + 1, rest);

        return Math.max(v1, v2);
    }


}
