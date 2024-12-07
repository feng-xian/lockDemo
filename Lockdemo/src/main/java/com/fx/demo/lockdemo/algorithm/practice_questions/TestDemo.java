package com.fx.demo.lockdemo.algorithm.practice_questions;

import java.util.*;

public class TestDemo {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList();

        list.add(1);
        list.add(2);

        System.out.println(list.peek());
        System.out.println(list.poll());
        System.out.println(list.poll());

        list.sort((o1, o2) -> o2 - o1);
        Integer[] aa = {1,5,9};
        Arrays.sort(aa, ((o1, o2) -> o2 -o1));


        String str = "erewrewqe";
        char[] array = str.toCharArray();


        int c = 'z' - 'a';
        System.out.println(c);

    }


    public static class Com implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

}
