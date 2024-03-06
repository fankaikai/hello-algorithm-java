package com.fkk.code.jz;

import com.fkk.code.ds.ListNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 从头到尾打印链表
 */
public class JZ6 {

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        recursion(listNode, result);
        return result;
    }

    public void recursion(ListNode head, ArrayList<Integer> res) {
        if (head == null) {
            return;
        }
        //先往深层次遍历
        recursion(head.next, res);
        //倒序添加数据
        res.add(head.val);
    }


    /**
     * 利用栈
     * 时间复杂度 O(N)
     * 空间复杂度 O(N)
     */
    public ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
