package com.fkk.code.LeetCode;

import java.util.Stack;

/**
 * 双栈实现队列: 一个栈作为输入栈，一个栈作为输出栈。
 * 输入栈中的元素总是后来添加的，所以先去输出栈找，找不到就把输入栈元素添加到输出栈
 * 两次先进后出，负负得正就是先进先出了。
 */
public class MyQueue {

    private final Stack<Integer> inStack;
    private final Stack<Integer> outStack;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /**
     * 将元素x推到队列末尾
     * 时间复杂度O(1)
     * n次push空间
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * 从队列开头移出并返回元素
     * 官方解释：对于每个元素，最多出栈和入栈各两次，平均时间复杂度O(1)
     */
    public int pop() {
        if (outStack.empty()) {
            in2out();
        }
        return outStack.pop();
    }

    /**
     * 返回队列开头的元素
     * 复杂度分析同pop
     */
    public int peek() {
        if (outStack.empty()) {
            in2out();
        }
        return outStack.peek();
    }

    private void in2out() {
        while (!inStack.empty()) {
            outStack.push(inStack.pop());
        }
    }

    /**
     * 判断队列是否为空
     * true-空
     *
     * 时间复杂度O(1)
     */
    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }
}
