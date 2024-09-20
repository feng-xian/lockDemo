package com.fx.demo.lockdemo.algorithm.practice_questions.od;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 设备最大可靠性
 * 一个设备由N种类型元器件组成(每种类型元器件只需要一个， 类型type编号从0~N-1);每个元器件均有可靠性属性reliability,可靠性越高的器件其价格price越贵。而设备的可靠性由组成设备的所有器件中可靠性最低的器件决定。给定预算S,购买N种元器件(每种类型元器件都需要购买一个)， 在不超过预算的情况下，请给出能够组成的设备的最大可靠性。
 * 输入描述
 * S N // S总的预算，N元器件的种类
 * total //元器件的总数，每种型号的元器件可以有多种:
 * 此后有total行具体器件的数据
 * type reliability price //type整数类型，代表元器件的类型编号从0 ~ N-1; reliabily整数类型，代表元器件的可靠性: price 整数类型，代表元器件的价格。
 * 输出描述
 * 符合预算的设备的最大可靠性，如果预算无法买产N种器件，则返回-1
 * 备注
 * 0 <= S,price <= 10000000
 * 0<=N<= 100
 * 0 <=type <= N-1
 * 0 <= total <= 100000
 * 0 < reliability <= 100000
 * 输入
 * 500 3
 * 6
 * 0 80 100
 * 0 90 200
 * 1 50 50
 * 1 70 210
 * 2 50 100
 * 2 60 150
 * 输出
 * 60
 */
public class DeviceReliability {

    private static int rela = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int S = scanner.nextInt();
        int typeNum = scanner.nextInt();
        int total = scanner.nextInt();

        ArrayList<Device> list = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            int typeN = scanner.nextInt();
            int priceN = scanner.nextInt();
            int reliabilyN = scanner.nextInt();
            Device device = new Device(typeN, reliabilyN, priceN);
            list.add(device);
        }

        LinkedList<Device> path = new LinkedList<>();

        dfs(list, S, typeNum, path);

        System.out.println(rela);
    }

    private static void dfs(ArrayList<Device> list, int s, int typeNum, LinkedList<Device> path) {
        if (path.size() == typeNum) {
            int sum = path.stream().mapToInt(d -> d.price).sum();
            if (sum > s) {
                return;
            }

            path.sort((a, b) -> b.reliabily - a.reliabily);
            rela = Math.max(path.getLast().reliabily, rela);
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            Device device = list.get(i);

            List<Integer> collect = path.stream().map(d -> d.type).collect(Collectors.toList());
            if (collect.contains(device.type)) {
                continue;
            }

            path.addLast(device);
            dfs(list, s, typeNum, path);
            // 回溯
            path.removeLast();
        }

    }


    public static class Device {
        int type;
        int price;
        int reliabily;

        public Device(int type, int price, int reliabily) {
            this.type = type;
            this.price = price;
            this.reliabily = reliabily;
        }
    }

}
