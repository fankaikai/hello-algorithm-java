package com.fkk.code.LeetCode;

import com.fkk.code.ds.ListNode;

/**
 * LC21. 合并两个有序链表
 */
public class LC21 {

    /**
     * 迭代法
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)  这是因为并没有开辟新的空间，只是新增了两个指针。
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode(-1);
        ListNode node = res;

        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                node.next = list2;
                list2 = list2.next;
            } else {
                node.next = list1;
                list1 = list1.next;
            }
            node = node.next;
        }

        node.next = list1 != null ? list1 : list2;
        return res.next;
    }

    /**
     * 递归法：这样不需要额外生命ListNode，但是会消耗栈空间
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }

        if(list2 == null){
            return list1;
        }

        if(list1.val <= list2.val){
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }

        list2.next = mergeTwoLists(list2.next, list1);
        return list2;
    }
}
