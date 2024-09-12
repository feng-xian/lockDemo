package com.fx.demo.lockdemo.algorithm;

import java.util.*;

/**
 * 最大重合问题:
 * 给定很多线段，每个线段都有两个数[start,end]
 * 表示线段的开始位置以及结束位置，左右都是闭区间
 * 规定：
 * 1、线段的开始与结束位置都是整数值
 * 2、线段重合区域的长度必须 >= 1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class MaximumOverlap {


    /*
     * 解题思路：
     * 使用堆来解决，堆使用小根堆，
     * 思路：
     * 1、先将线段数组以start升序排序
     * 2、排序好的数据，拿出第一个
     * 3、在放入之前先判断小根堆中已有的数据有几个小于当前end，小于等于的全部移除小根堆，
     * 4、再将end放入小根堆
     * */
    public static void main(String[] args) {

        int[][] lineArr = {
                {1, 4},
                {3, 4},
                {2, 5},
        };


        int overlap = overlap(lineArr);
        System.out.println("overlap======>>" + overlap);

    }


    public static int overlap(int[][] lineArr) {

        if (lineArr == null || lineArr.length < 2) {
            return 0;
        }

        List<Line> lineList = buildAndSort(lineArr);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int maxNum = 0;
        for (Line line : lineList) {
            int count = lessNum(minHeap, line.start, line.end);

            maxNum = Math.max(count, maxNum);

        }

        return maxNum;

    }

    public static int lessNum(PriorityQueue<Integer> minHeap, Integer start, Integer end) {

        while ((!minHeap.isEmpty()) && (minHeap.peek() <= start)) {
            minHeap.poll();
        }

        minHeap.add(end);

        return minHeap.size();
    }

    public static List<Line> buildAndSort(int[][] lineArr) {
        ArrayList<Line> list = new ArrayList<>();
        for (int i = 0; i < lineArr.length; i++) {
            int[] ints = lineArr[i];
            Line line = new Line();
            line.start = ints[0];
            line.end = ints[1];
            list.add(line);
        }

        list.sort(new LineComparator());
        return list;
    }


    public static class Line {
        public Integer start;
        public Integer end;
    }

    public static class LineComparator implements Comparator<Line> {
        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }


}
