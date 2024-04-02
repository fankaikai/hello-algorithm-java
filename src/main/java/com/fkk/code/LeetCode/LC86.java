package com.fkk.code.LeetCode;

import com.fkk.code.ds.ListNode;

/**
 * 分隔链表
 */
public class LC86 {

    /**
     * 方法一：遍历出大小链表再组合
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(-1);
        ListNode smallHead = small;
        ListNode big = new ListNode(-1);
        ListNode bigHead = big;

        while (head != null) {
            //这里一定要把大于等于x的全都放到big链表中，否则顺序会出问题
            if (head.val >= x) {
                big.next = head;
                big = big.next;
            } else {
                small.next = head;
                small = small.next;
            }
            head = head.next;
        }
        //尾部置为null
        big.next = null;
        small.next = bigHead.next;

        return smallHead.next;
    }

    /**
     * 方法2：可以尝试在原链表操作，把大于等于x的链表取出来串起来
     * 思路：找到第一个大于等于x的节点，然后把后面小于x的全都移动到该节点前面去
     *
     * 但是这种思路需要记录small链表的尾节点、big链表的头节点，big链表后续遍历也需要两个节点
     * 总体算下来至少需要四个节点。
     */
    public ListNode partition2(ListNode head, int x) {
        //todo
        return null;
    }
}
