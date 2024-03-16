package com.fkk.code.jz;

import com.fkk.code.ds.ListNode;

/**
 * 链表中倒数最后K个节点
 */
public class JZ22 {

    public ListNode FindKthToTail(ListNode pHead, int k) {
        if (pHead == null) {
            return null;
        }
        int count = 0;
        ListNode res = pHead;
        while (res != null) {
            count++;
            res = res.next;
        }
        if (count < k) {
            return null;
        }

        res = pHead;
        int len = count - k;

        while (len > 0) {
            res = res.next;
            len--;
        }

        return res;

    }
}
