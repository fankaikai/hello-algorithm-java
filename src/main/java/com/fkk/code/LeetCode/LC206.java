package com.fkk.code.LeetCode;

import com.fkk.code.ds.ListNode;

import java.util.Stack;

/**
 * 反转链表
 */
public class LC206 {

    /**
     * 双指针迭代，
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public ListNode reverseList2(ListNode head) {
        ListNode res = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = res;
            res = curr;
            curr = next;
        }
        return res;
    }

    /**
     * 还可以使用递归解法
     * 时间复杂度是O(n)
     * 空间复杂度也是O(n)
     */
    public ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = reverseList3(head.next);
        //下面是对head进行操作，
        //1. 假设现在head.next已经是最后一个节点了，根据上边终止条件可知head.next 不为null
        //2. head.next 就是tail，tail.next = head, 也就是把尾节点指向前一个节点。
        //3. 前一个节点有可能是头节点，此时头节点指向第二个节点，反转后头节点需要指向null成为新的尾节点，否则会产生环。

        //节点K+1指向K节点，也就是(K+1).next = K, 进一步就是k.next.next = k
        head.next.next = head;
        //k+1指向K以后，记得K.next = null，防止产生环
        head.next = null;
        return node;
    }


    /**
     * 反转链表，尝试使用Stack，后进先出
     * 这种方法耗时较高
     */
    public ListNode reverseList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        //存
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            stack.push(temp);
        }

        ListNode res = new ListNode(-1);
        head = res;
        //取
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            head.next = pop;
            head = head.next;
        }

        return res.next;
    }
}
