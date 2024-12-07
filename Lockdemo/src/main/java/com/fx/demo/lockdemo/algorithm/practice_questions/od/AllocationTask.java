package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 分配任务
 */
public class AllocationTask {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        ArrayList<Task> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            list.add(new Task(start, end));
        }

        int taskNum = maxTask(list);
        System.out.println(taskNum);
    }

    private static int maxTask(ArrayList<Task> list) {
        if (null == list || list.size() == 0) {
            return 0;
        }

        list.sort((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        int count = 0;
        int day = -1;
        for (Task task : list) {
            if (day > task.end) {
                continue;
            }
            if (day <= task.start) {
                count++;
                day = task.start + 1;
                continue;
            }
            day++;
            count++;

        }

        return count;
    }


    public static class Task {
        int start;
        int end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
