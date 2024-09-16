package com.fx.demo.lockdemo.algorithm.practice_questions;

/**
 * 如果一个节点X，
 * 它左树结构和右树结构完全一样
 * 那么我们说以X为头的子树是相等子树
 * 给定一棵二叉树的头节点head返回head整棵树上有多少棵相等子树
 */
public class QuestionCode09 {


    public static void main(String[] args) {
        Node head = new Node(2);
        int num = sameNodeNum(head);
        System.out.println(num);
    }

    private static int sameNodeNum(Node head) {
        if (null == head) {
            return 0;
        }

        return sameNodeNum(head.left) + sameNodeNum(head.right) + (same(head.left, head.right) ? 1 : 0);
    }

    private static boolean same(Node h1, Node h2) {

        if (h1 == null && h2 != null) {
            return false;
        }
        if (h1 != null && h2 == null) {
            return false;
        }
        if (h1 == null && h2 == null) {
            return true;
        }

        return (h1.value == h2.value) && same(h1.left, h2.left) && same(h1.right, h2.right);
    }


    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }


}
