package com.fkk.code.LeetCode;

import com.fkk.code.ds.ListNode;

/**
 * 链表的中间节点
 */
public class LC876 {


    /**
     * 单指针
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public ListNode middleNode(ListNode head) {
        int count = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        curr = head;
        int step = 0;
        while (step < count / 2) {
            step++;
            curr = curr.next;
        }
        return curr;
    }


    /**
     * 快慢指针
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public ListNode middleNode2(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
