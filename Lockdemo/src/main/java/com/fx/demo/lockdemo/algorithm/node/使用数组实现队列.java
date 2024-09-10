package com.fx.demo.lockdemo.algorithm.node;

public class 使用数组实现队列 {

    private int[] arr;
    private int pushi;
    private int polli;
    private int size;
    private int limit;

    public 使用数组实现队列(int limit) {
        this.arr = new int[limit];
        this.pushi = 0;
        this.polli = 0;
        this.size = 0;
        this.limit = limit;
    }

    public void push(int val){
        if (size == limit) {
            throw new RuntimeException("队列空间已满");
        }
        size++;
        arr[pushi] = val;
        pushi = nextIndex(pushi);
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("队列没有元素！");
        }
        size--;
        int val = arr[polli];
        polli = nextIndex(polli);
        return val;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private int nextIndex(int index) {

        if (index >= this.limit - 1) {
            return 0;
        }

        return ++index;
    }

}
