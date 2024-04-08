package com.fkk.code.LeetCode;

import com.fkk.code.ds.ListNode;

import javax.swing.plaf.InsetsUIResource;
import java.util.HashSet;

/**
 * 相交链表
 */
public class LC160 {
    /**
     * 使用哈希表
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode nodeA = headA;
        while (nodeA != null) {
            hashSet.add(nodeA);
            nodeA = nodeA.next;
        }

        ListNode nodeB = headB;
        while (nodeB != null) {
            if (hashSet.contains(nodeB)) {
                return nodeB;
            }
            nodeB = nodeB.next;
        }

        return null;
    }


    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int countA = 0;
        int countB = 0;

        ListNode stepA = headA;
        ListNode stepB = headB;

        while (stepA != null) {
            countA++;
            stepA = stepA.next;
        }

        while (stepB != null) {
            countB++;
            stepB = stepB.next;
        }

        stepA = headA;
        stepB = headB;

        if (countA > countB) {
            while (countA > countB) {
                stepA = stepA.next;
                countA--;
            }
        } else if (countA < countB) {
            while (countB > countA) {
                stepB = stepB.next;
                countB--;
            }
        }

        while (stepA != null) {
            if (stepA == stepB) {
                return stepA;
            }
            stepA = stepA.next;
            stepB = stepB.next;
        }

        return null;
    }
}
