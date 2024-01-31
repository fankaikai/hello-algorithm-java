package com.fkk.code.LeetCode;

import com.fkk.code.ds.ListNode;

/**
 * 链表排序
 * https://leetcode.cn/problems/sort-list/description/
 */
public class LC148 {

    //尝试归并排序的思路
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }

    /**
     * 自上而下的方式:
     * 时间复杂度O(n log n)
     * 空间复杂度O(log n)，递归调用栈空间
     *
     * @param tail 指向链表尾部
     */
    public ListNode sortList(ListNode head, ListNode tail) {
        //递归终止条件1：节点为null
        if (head == null) {
            return head;
        }
        //递归终止条件2：已经指向链表尾部
        if (head.next == tail) {
            head.next = null;
            return head;
        }

        //快慢指针找到链表的中间点
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }

        //找到尾部后递归拆分链表
        ListNode mid = slow;
        ListNode left = sortList(head, mid);
        ListNode right = sortList(mid, tail);

        //合并&排序链表
        return mergeList(left, right);
    }

    /**
     * 合并有序链表
     */
    public static ListNode mergeList(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(0);
        ListNode temp = head, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val < temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }

        return head.next;
    }

}
