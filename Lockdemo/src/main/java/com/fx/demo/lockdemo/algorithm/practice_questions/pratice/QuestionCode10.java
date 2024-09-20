package com.fx.demo.lockdemo.algorithm.practice_questions.pratice;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 给定一个str，str表示公式
 * str表示一式里可能有整数、加减乘除符号和左右括号多层str=“48*((70-65)-43)8*1"，返回-1816。
 * str=“3+1*4"，返回7
 * str="3+(1*4)”，返回7.
 * 说明】
 * 1.可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
 * 2.如果是负数，就需要用括号括起来，比如-3)”但如果负数作为公式的开头或括号部分4*的开头则可以没有括号，都是合法的。、比如"-3*4“和“(-3*4)
 * 3.不用考虑计算过程中会发生溢出的情况。
 */
public class QuestionCode10 {

    public static void main(String[] args) {
        String str = "48*((70-65)-43)-8*1";

        int[][] result = calculate(str, 0);
        System.out.println(result[0][0] + "--------" + result[0][1]);
        System.out.println(str.length());
    }

    private static int[][] calculate(String str, int index) {
        int[][] result = new int[1][2];
        LinkedList<String> queue = new LinkedList<>();

        char[] arr = str.toCharArray();

        StringBuilder curr = new StringBuilder();
        int isC = 0;
        for (int i = index; i < arr.length; i++) {
            System.out.println("i----------------------------->>>" + i);
            if (isNum(arr[i])) {
                curr.append(arr[i]);
                if (i == arr.length - 1) {

                }
                continue;
            }
            if (isOperate(arr[i])) {

                if (isC == 1) {
                    String num1 = queue.removeLast();
                    String operate = queue.removeLast();
                    String num2 = queue.removeLast();

                    if (Objects.equals("*", operate)) {
                        int val = Integer.parseInt(num2) * Integer.parseInt(num1);
                        queue.addLast(String.valueOf(val));
                    }
                    if (Objects.equals("/", operate)) {
                        int val = Integer.parseInt(num2) / Integer.parseInt(num1);
                        queue.addLast(String.valueOf(val));
                    }
                    isC = 0;
                }

                if (!curr.toString().isEmpty()) {
                    queue.addLast(curr.toString());
                    curr = new StringBuilder();
                }


                if (arr[i] == '*' || arr[i] == '/') {
                    queue.addLast(String.valueOf(arr[i]));
                    isC = 1;
                } else {
                    queue.addLast(String.valueOf(arr[i]));
                }
                continue;

            }
            if (arr[i] == '(') {
                System.out.println("i+1=" + (i + 1));
                int[][] chaRe = calculate(str, i + 1);
                queue.addLast(String.valueOf(chaRe[0][0]));
                i = chaRe[0][1];
                System.out.println("i=======>>" + i);
                continue;
            }
            if ((arr[i] == ')') || (i == arr.length - 1)) {
                // 计算栈中的结果并返回
                String num1 = "";
                if (!curr.toString().isEmpty()) {
                    num1 = curr.toString();
                } else {
                    num1 = queue.removeLast();
                }
                String operate = queue.removeLast();
                String num2 = queue.removeLast();
                int val = doCalcul(Integer.parseInt(num2), Integer.parseInt(num1), operate);

                while (!queue.isEmpty()) {
                    String op = queue.removeLast();
                    int v = Integer.parseInt(queue.removeLast());
                    val = doCalcul(v, val, op);
                }

                result[0][0] = val;
//                result[0][1] = i + 1;
                result[0][1] = i ;
                return result;

            }
        }
        return result;
    }

    private static int doCalcul(int num1, int num2, String op) {
        if ("+".equals(op)) {
            return num1 + num2;
        } else if ("-".equals(op)) {
            return num1 - num2;
        } else if ("*".equals(op)) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }

    private static boolean isOperate(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean isNum(char c) {
        return c == '0' || c == '1'
                || c == '2' || c == '3' || c == '4'
                || c == '5' || c == '6' || c == '7'
                || c == '8' || c == '9';
    }


}
