package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindBoxPsw {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String arrStr = scanner.next();
        String key = scanner.next();

        

    }

    private static ArrayList<String> getLetter(String str) {

        str = str.toLowerCase();

        Pattern compile = Pattern.compile("[a-z]");
        Matcher matcher = compile.matcher(str);

        ArrayList<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }

        list.sort(String::compareTo);
        return list;
    }

}
