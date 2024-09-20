package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 【队列】2023C-篮球游戏
 * 幼儿园里有一个放倒的圆桶，它是一个线性结构，允许在桶的右边将篮球放入，可以在桶的左边和右边将篮球取出。每个篮球有单独的编号，老师可以连续放入一个或多个篮球，小朋友可以在桶左边或右边将篮球取出，当桶里只有一个篮球的情况下，必须从左边取出。
 *
 * 如老师按顺序放入1、2、3、4、5 共5个编号的篮球，那么小朋友可以依次取出的编号为1、2、3、4、5或者3、1、2、4、5编号的篮球，无法取出5、1、3、2、4编号的篮球
 *
 * 其中3、1、2、4、5的取出场景为: 连续放入1、2、3号 -> 从右边取出3号 -> 从左边取出1号 -> 从左边取出2号 -> 放入4号 -> 从左边取出4号 -> 放入5号>从左边取出5号，简单起见，我们以L表示左，R表示右，此时的篮球的依次取出序列为"RLLLL"
 */
public class QuestionCode16 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String push = scanner.next();
        String result = scanner.next();
        scanner.close();

//        String push = "1,2,3,4,5";
//        String result = "3,1,2,4,5";
        String[] pushArrStr = push.split(",");
        String[] pollArrStr = result.split(",");

        if (pushArrStr.length != pollArrStr.length) {
            System.out.println("NO");
            return;
        }

        int[] pushArr = new int[pushArrStr.length];
        int[] pollArr = new int[pollArrStr.length];

        for (int i = 0; i < pollArrStr.length; i++) {
            pollArr[i] = Integer.parseInt(pollArrStr[i]);
        }

        for (int i = 0; i < pushArrStr.length; i++) {
            pushArr[i] = Integer.parseInt(pushArrStr[i]);
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        StringBuilder ans = new StringBuilder();

        int pollIndex = 0;
        for (int i = 0; i < pushArr.length; i++) {

            queue.add(pushArr[i]);

            while ((pollIndex < pollArr.length) && queue.contains(pollArr[pollIndex])) {
                int v = pollArr[pollIndex];
                // 左 L ，右 R
                if (queue.peekFirst().equals(v)) {
                    queue.pollFirst();
                    ans.append("L");
                    pollIndex++;
                }
                else if (queue.peekLast().equals(v)) {
                    queue.pollLast();
                    ans.append("R");
                    pollIndex++;
                }
                else {
                    System.out.println("NO");
                    return;
                }
            }
        }

        System.out.println(ans);

    }

}
