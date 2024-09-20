package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 手机App防沉迷系统
 */
public class AppUseTime {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int appNum = scanner.nextInt();

        ArrayList<App> list = new ArrayList<>();
        for (int i = 0; i < appNum; i++) {
            String name = scanner.next();
            String p = scanner.next();
            String startTime = scanner.next();
            String endTime = scanner.next();
            App app = new App(name, p, startTime, endTime);
            list.add(app);
        }
        String timeStr = scanner.next();
        double time = Double.parseDouble(timeStr.replaceAll(":", "."));

        int p = -1;
        String name = "NA";
        for (int i = 0; i < list.size(); i++) {
            App app = list.get(i);
            if (app.startTime <= time && time <= app.endTime) {
                if (app.p > p) {
                    p = app.p;
                    name = app.name;
                }
            }
        }

        System.out.println(name);
    }

    public static class App {
        String name;
        int p;
        double startTime;
        double endTime;

        public App(String name, String p, String startTime, String endTime) {
            this.name = name;
            this.p = Integer.parseInt(p);
            this.startTime = Double.parseDouble(startTime.replaceAll(":", "."));
            this.endTime = Double.parseDouble(endTime.replaceAll(":", "."));
        }
    }

}
