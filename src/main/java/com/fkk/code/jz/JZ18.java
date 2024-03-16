package com.fkk.code.jz;

import com.fkk.code.ds.ListNode;

/**
 * 删除链表中的节点
 */
public class JZ18 {

    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
                return dummy.next;
            } else {
                node = node.next;
            }
        }
        return dummy.next;
    }
}
