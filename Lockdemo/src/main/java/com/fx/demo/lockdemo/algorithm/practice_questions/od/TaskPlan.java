package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 高效的任务规划
 * 你有n台机器编号为1-n，每台都需要完成一项工作，
 * 机器经过配置后都能独立完成一项工作。
 * 假设第i台机器你需要花Bi分钟进行设置，
 * 然后开始运行，Ji分钟后完成任务。
 * 现在，你需要选择布置工作的顺序，使得用最短的时间完成所有工作。
 * 注意，不能同时对两台进行配置，
 * 但配置完成的机器们可以同时执行他们各自的工作。
 */
public class TaskPlan {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int n = scanner.nextInt();
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                ArrayList<Integer> mechine = new ArrayList<>();
                int B = scanner.nextInt();
                int J = scanner.nextInt();
                mechine.add(B);
                mechine.add(J);
                list.add(mechine);
            }
            int time = solution(list);
            System.out.println(time);
        }
        scanner.close();
    }

    private static int solution(ArrayList<ArrayList<Integer>> list) {
        list.sort((o1, o2) -> o2.get(1) - o1.get(1));

        int time = 0;
        int runTime = 0;
        for (ArrayList<Integer> mch : list) {
            time += mch.get(0);
            runTime = Math.max(runTime - mch.get(0), mch.get(1));
        }

        return time + runTime;
    }

}
