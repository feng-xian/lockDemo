package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.Scanner;

/**
 * 猜数字
 * 一个人设定一组四码的数字作为谜底，另一方猜。
 *
 * 每猜一个数，出数者就要根据这个数字给出提示，提示以XAYB形式呈现，直到猜中位置。
 *
 * 其中X表示位置正确的数的个数（数字正确且位置正确），而Y表示数字正确而位置不对的数的个数。
 *
 * 例如，当谜底为8123，而猜谜者猜1052时，出题者必须提示0A2B。
 *
 * 例如，当谜底为5637，而猜谜者才4931时，出题者必须提示1A0B。
 *
 * 当前已知N组猜谜者猜的数字与提示，如果答案确定，请输出答案，不确定则输出NA。
 * 原文链接：https://blog.csdn.net/2401_86417859/article/details/140910032
 */
public class GuessNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        String[][] arr = new String[num][2];
        for (int i = 0; i < num; i++) {
            String numberS = scanner.next();
            String tip = scanner.next();
            arr[i][0] = numberS;
            arr[i][1] = tip;
        }

        String res = findAns(arr);
        System.out.println(res);
    }

    private static String findAns(String[][] arr) {
        if (null == arr || arr.length == 0) {
            return "NA";
        }

        for (int i = 0; i <= 9999; i++) {
            String strNum = getStrNum(i);
            boolean flag = isValid(arr, strNum);
            if (flag) {
                return strNum;
            }
        }

        return "NA";
    }

    private static boolean isValid(String[][] arr, String strNum) {

        for (int i = 0; i < arr.length; i++) {

            String str = arr[i][0];
            String tip = arr[i][1];

            int a = 0, b = 0;

            int[] countArr = new int[10];
            int[] countGuessArr = new int[10];

            char[] array = strNum.toCharArray();
            for (int j = 0; j < array.length; j++) {
                if (array[j] == str.charAt(j)) {
                    a++;
                }
                else {
                    countArr[str.charAt(j) - '0']++;
                    countGuessArr[array[j] - '0']++;
                }
            }

            for (int j = 0; j < 10; j++) {
                b += Math.min(countArr[j], countGuessArr[j]);
            }

            String tipNew = a + "A" + b + "B";
            if (!tipNew.equals(tip)) {
                return false;
            }
        }

        return true;
    }


    private static String getStrNum(int i) {
        String s = String.valueOf(i);
        if (s.length() == 4) {
            return s;
        }
        if (s.length() == 3) {
            return "0" + s;
        }
        if (s.length() == 2) {
            return "00" + s;
        }
        return "000" + s;
    }

}
