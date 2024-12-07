package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 火星文计算
 * 已经火星人使用的运算符号为# $
 * 其与地球人的等价公式如下
 * x#y=2*x+3*y+4
 * x$y=3*x+y+2
 * x y是无符号整数
 * 地球人公式按照c语言规则进行计算
 * 火星人公式中$符优先级高于#相同的运算符按从左到右的顺序运算
 */
public class CalculationFormula {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        scanner.close();

//        String s = "7#6$5#12";
        int result = solution(s);
        System.out.print(result);

    }

    private static int solution(String s) {
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast("");
        char[] array = s.toCharArray();

        boolean preCul = false;
        for (int i = 0; i < array.length; i++) {

            if (isNum(array[i])) {

                String last = queue.getLast();
                if (!"#".equals(last) && !"$".equals(last)) {
                    String s1 = queue.removeLast();
                    queue.addLast(s1 + array[i]);
                }
                else {
                    queue.addLast(String.valueOf(array[i]));
                }

                continue;
            }

            if ('#' == array[i]) {
                if (preCul) {
                    int re = calcul4$(queue);
                    queue.addLast(String.valueOf(re));
                    preCul = false;
                }
                queue.addLast(String.valueOf(array[i]));
                continue;
            }

            if ('$' == array[i]) {
                if (preCul) {
                    int re = calcul4$(queue);
                    queue.addLast(String.valueOf(re));
                    preCul = false;
                }
                queue.addLast(String.valueOf(array[i]));
                preCul = true;
            }

        }

        return finalCulcal(queue);
    }

    private static int finalCulcal(LinkedList<String> queue) {

        String num1 = queue.removeFirst();
        String op = queue.removeFirst();
        String num2 = queue.removeFirst();

        int total = doCalcul(Integer.parseInt(num1), Integer.parseInt(num2), op);

        while (!queue.isEmpty()) {

            op = queue.removeFirst();
            String numb = queue.removeFirst();
            total = doCalcul(total, Integer.parseInt(numb), op);

        }

        return total;
    }

    private static int calcul4$(LinkedList<String> queue) {
        String num1 = queue.removeLast();
        String op = queue.removeLast();
        String num2 = queue.removeLast();
        return culcal1(Integer.parseInt(num2), Integer.parseInt(num1));
    }

    private static boolean isNum(char c) {
        if (c == '0') {
            return true;
        }
        if (c == '9') {
            return true;
        }
        return (c - '0') > 0 && ((c - '0' < 9));

    }

    private static int doCalcul(int x, int y, String op) {
        if ("#".equals(op)) {
            return culcal2(x, y);
        }
        return culcal1(x, y);
    }

    // # 计算
    private static int culcal2(int x, int y) {
        return 2 * x + 3 * y + 4;
    }

    // $ 计算
    private static int culcal1(int x, int y) {
        return 3 * x + y + 2;
    }

}
