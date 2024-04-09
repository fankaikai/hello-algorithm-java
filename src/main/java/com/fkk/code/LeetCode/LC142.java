package com.fkk.code.LeetCode;

import com.fkk.code.ds.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表II
 */
public class LC142 {

    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode step = head;

        while (step != null) {
            if (set.contains(step)) {
                return step;
            }
            set.add(step);
            step = step.next;
        }
        return null;
    }


    /**
     * 双指针或者三指针思路：数学推导
     * 1. 快慢指针判断是否有环，fast和slow
     * 2. fast = 2 * slow
     * 3. 假定进入环之前距离是a，从入环到相遇点是b，相遇点到入环点是c
     * 4. 相遇时慢指针走过的距离 a + b， 快指针走过距离 a + n*(b+c) + b
     * 5. 根据2可知 2(a + b) = a + n*(b+c) + b 推导后可以得到 a = c + (n-1)(b + c)
     * 意思就是环之前的距离等于入环后走n-1圈 + 相遇点到入环处距离c 恰好是入环前的距离a
     * <p>
     * 由此可以得到思路，相遇后，慢指针继续走，快指针或者新指针从头节点开始走，相遇时就是入环节点。
     *
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                //说明有环
                break;
            }
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

}
