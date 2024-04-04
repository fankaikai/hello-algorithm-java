package com.fkk.code.LeetCode;

/**
 * 设计最小栈，检索是O(1)
 * 要符合栈的特性：先进后出。
 */
public class MinStack {

    private Node head;

    public MinStack() {
    }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val);
        } else {
            head = new Node(val, Math.min(head.min, val), head);
        }
    }

    public void pop() {
        if (head == null) {
            return;
        }
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    public static class Node {
        public int val;
        public Node next;
        //代表当前元素入栈前的最小值
        public int min;

        public Node(int val, int min) {
            this(val, min, null);
        }

        public Node(int val, int min, Node next) {
            this.val = val;
            this.next = next;
            this.min = min;
        }
    }

}
