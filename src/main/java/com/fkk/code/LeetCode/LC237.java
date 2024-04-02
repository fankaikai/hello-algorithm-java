package com.fkk.code.LeetCode;

import com.fkk.code.ds.ListNode;

/**
 * 删除链表中的节点
 * 访问不到头节点，确定不是尾节点，每个节点唯一。
 * 如何删除呢？
 */
public class LC237 {

    /**
     * 换一种思路，访问不到前一个节点。那就删除下一个节点
     * 然后把当前节点值改为下一个节点
     *
     * 这题该说是敲掉思维里的墙呢还是脑筋急转弯呢？
     */
    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        node.next = next.next;
        node.val = next.val;
    }

}
