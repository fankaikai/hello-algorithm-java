package com.fkk.code.jz;

import com.fkk.code.ds.ListNode;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 合并有序链表
 */
public class JZ25 {

    /**
     * 递归解法
     */
    public ListNode Merge(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return pHead1 != null ? pHead1 : pHead2;
        }
        if (pHead1.val <= pHead2.val) {
            pHead1.next = Merge(pHead1.next, pHead2);
            return pHead1;
        } else {
            pHead2.next = Merge(pHead1, pHead2.next);
            return pHead2;
        }
    }


    /**
     * 使用数组
     */
    public ListNode merge2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null) return pHead2;
        if (pHead2 == null) return pHead1;

        ArrayList<Integer> list = new ArrayList<>();
        while (pHead1 != null) {
            list.add(pHead1.val);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            list.add(pHead2.val);
            pHead2 = pHead2.next;
        }
        Collections.sort(list);
        ListNode head = new ListNode(list.get(0));
        ListNode current = head;
        for (int i = 1; i < list.size(); i++) {
            current.next = new ListNode(list.get(i));
            current = current.next;
        }

        return head;
    }

    public ListNode merge3(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null) return pHead2;
        if (pHead2 == null) return pHead1;

        ListNode res = new ListNode(-1);
        ListNode current = res;
        while (pHead1 != null && pHead2 != null) {
            if (pHead1.val <= pHead2.val) {
                current.next = pHead1;
                pHead1 = pHead1.next;
            } else {
                current.next = pHead2;
                pHead2 = pHead2.next;
            }
            current = current.next;
        }
        //拼接剩余链表
        while (pHead1 != null){
            current.next = pHead1;
            current = current.next;
            pHead1 = pHead1.next;
        }

        while (pHead2 != null){
            current.next = pHead2;
            current = current.next;
            pHead2 = pHead2.next;
        }

        return res.next;
    }
}
