package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * boss收入
 * 一个XX产品行销总公司，只有一个boss，其有若干一级分销，一级分销又有若干二级分销，每个分销只有唯一的上级分销.
 * 规定，每个月，下级分销需要将自己的总收入 (自己的+下级上交的) 每满100元上交15元给自己的上级.
 * 现给出一组分销的关系，和每个分销的收入，请找出boss并计算出这个boss的收入。
 * 比如:
 * 收入100元上交15元;
 * 收入199元(99元不够100)上交15元;
 * 收入200元，上交30元。
 * <p>
 * 输入:
 * 分销关系和收入: [[分销id 上级分销的ld 收入，[分销id 上级分销的id 收入]，[分销id 级分销的id 收入]]
 * 分销ID范围 0…65535
 * 收入范围: 0…65535,单位元
 * 提示: 输入的数据只存在1个boss，不存在环路
 * 输出: [boss的ID，总收入]
 * <p>
 * 原文链接：https://blog.csdn.net/qq_33183456/article/details/131260150
 */
public class BossIncome {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        ArrayList<Income> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int id = scanner.nextInt();
            int parentId = scanner.nextInt();
            int money = scanner.nextInt();
            Income income = new Income(id, parentId, money);
            list.add(income);
        }

        String res = bossAllMoney(list);

        System.out.println(res);
    }

    private static String bossAllMoney(ArrayList<Income> list) {

        Set<Integer> parentIdSet = list.stream().map(e -> e.parentId).collect(Collectors.toSet());
        Set<Integer> idSet = list.stream().map(e -> e.id).collect(Collectors.toSet());

        int parentId = 0;
        for (Integer id : parentIdSet) {
            if (!idSet.contains(id)) {
                parentId = id;
            }
        }
        Income boss = new Income(parentId, -1, 0);
        list.add(boss);
        int money = process(list, parentIdSet, boss);
        return parentId + " " + money;
    }

    private static int process(ArrayList<Income> list, Set<Integer> parentSet, Income income) {
        if (!parentSet.contains(income.id)) {
            return (income.money / 100) * 15;
        }
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            Income in = list.get(i);
            if (in.parentId == income.id) {
                total += process(list, parentSet, in);
            }
        }
        if (income.parentId == -1) {
            return total;
        }
        return ((income.money + total) / 100) * 15;
    }

    public static class Income {
        int id;
        int parentId;
        int money;

        public Income(int id, int parentId, int money) {
            this.id = id;
            this.parentId = parentId;
            this.money = money;
        }
    }

}
