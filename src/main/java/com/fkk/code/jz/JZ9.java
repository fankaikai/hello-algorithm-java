package com.fkk.code.jz;

import java.util.Stack;

/**
 * 两个栈构建队列
 */
public class JZ9 {

    /**
     * 优化版，stack1只负责插入
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        Stack<Integer> temp = stack2.isEmpty() ? stack1 : stack2;
        temp.push(node);
    }

    public int pop() {
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return -1;
        }
        Stack<Integer> real, dest;
        if (stack1.isEmpty()) {
            real = stack2;
            dest = stack1;
        } else {
            real = stack1;
            dest = stack2;
        }

        while (!real.isEmpty()) {
            dest.push(real.pop());
        }
        Integer result = dest.pop();

        while (!dest.isEmpty()) {
            real.push(dest.pop());
        }
        return result;
    }

}
