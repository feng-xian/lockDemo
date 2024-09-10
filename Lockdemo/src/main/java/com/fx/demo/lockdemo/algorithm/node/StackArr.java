package com.fx.demo.lockdemo.algorithm.node;

/**
 * 使用数组实现栈
 */
public class StackArr {

    public static void main(String[] args) {
        StackArr stackArr = new StackArr(6);
        stackArr.push(5);
        stackArr.push(4);
        stackArr.push(3);
        stackArr.push(2);

        System.out.println(stackArr.pop());
        System.out.println(stackArr.pop());
        System.out.println(stackArr.pop());
        System.out.println(stackArr.pop());
    }

    private int[] arr;
    private int index;
    private int size;

    private int limit;

    public StackArr(int limit) {
        this.index = -1;
        this.size = limit;
        this.arr = new int[limit];
    }

    /**
     * 把项压入堆栈顶部。
     *
     * @param val
     */
    public void push(int val) {
        if (this.size == this.limit) {
            throw new RuntimeException("栈队列已满！");
        }
        arr[++this.index] = val;
        size++;

    }

    /**
     * 移除堆栈顶部的对象，并作为此函数的值返回该对象
     *
     * @return
     */
    public int pop() {
        if (this.size == 0) {
            throw new RuntimeException("栈中没有元素！");
        }
        int val = arr[index];
        size--;
        index--;
        return val;
    }


    /**
     * 查看堆栈顶部的对象，但不从堆栈中移除它
     *
     * @return
     */
    public int peek() {
        if (this.size == 0) {
            throw new RuntimeException("栈中没有元素！");
        }
        return arr[index];
    }

}
