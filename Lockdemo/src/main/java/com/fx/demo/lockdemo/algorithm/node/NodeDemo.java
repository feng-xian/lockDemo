package com.fx.demo.lockdemo.algorithm.node;

public class NodeDemo {

    public static void main(String[] args) {
        singleNode();
    }

    public static DoubleNode doubleNode() {
        DoubleNode node1 = new DoubleNode(1);
        DoubleNode node2 = new DoubleNode(2);
        DoubleNode node3 = new DoubleNode(3);
        DoubleNode node4 = new DoubleNode(4);
        DoubleNode node5 = new DoubleNode(5);
        DoubleNode node6 = new DoubleNode(6);
        node6.pre = node5;
        node5.next = node6;
        node5.pre = node4;
        node4.next = node5;
        node4.pre = node3;
        node3.next = node4;
        node3.pre = node2;
        node2.next = node3;
        node2.pre = node1;
        node1.next = node2;
//        DoubleNode doubleNode = reverseDoubleNode(node1);
//        printDoubleNode(doubleNode);
        return node1;
    }

    public static void singleNode() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node3.next = node4;
        node2.next = node3;
        node1.next = node2;
        Node head = null;
        printNode(node1);
        head = removeNodeByValue(node1, 2);
        printNode(head);
        head = reverseNode(node1);
        printNode(head);
    }

    private static Node removeNodeByValue(Node head, int num) {
        Node pre = head;
        Node next = head;

        if (head != null && head.value == num) {
            head = head.next;
        }

        while (next != null) {
            if (next.value == num) {
                pre.next = next.next;
            }
            else {
                pre = next;
            }

            next = next.next;
        }

        return head;
    }

    private static Node reverseNode(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public static void printNode(Node node) {

        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();

    }

    private static void printDoubleNode(DoubleNode head) {
        DoubleNode end = null;
        while (head != null) {
            System.out.print(head.value + " ");
            end = head;
            head = head.next;
        }
        System.out.println();

        while (end != null) {
            System.out.print(end.value + " ");
            end = end.pre;
        }
        System.out.println();

    }


    /**
     * 单链表
     */
    static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    static class DoubleNode {
        public int value;
        public DoubleNode next;

        public DoubleNode pre;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

}
