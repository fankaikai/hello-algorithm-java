package com.fkk.code.LeetCode;

import com.fkk.code.ds.Node;

import java.util.HashMap;

/**
 * 随机链表的复制
 */
public class LC138 {

    /**
     * 使用hashMap做映射
     * 时间复杂度：O(n)
     * 空间复杂度: O(n)
     */
    public Node copyRandomList(Node head) {
        Node copyNode = new Node(-1);
        Node copyHead = copyNode;

        Node curr = head;
        HashMap<Node, Node> nodeMap = new HashMap<>();

        //先遍历构建正常链表顺序
        while (curr != null) {
            Node node = new Node(curr.val);

            //构建复制链表
            copyNode.next = node;
            copyNode = copyNode.next;

            //构建映射关系
            nodeMap.put(curr, node);

            //指向下一个节点
            curr = curr.next;
        }

        //此时两个链表等长，再遍历一次
        copyNode = copyHead.next;
        curr = head;
        while (copyNode != null) {
            Node random = curr.random;
            if (random != null) {
                copyNode.random = nodeMap.get(random);
            }

            copyNode = copyNode.next;
            curr = curr.next;
        }
        return copyHead.next;
    }

    /**
     * 官方解法：递归 + 哈希表
     * 这样比迭代+哈希更简洁
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */

    HashMap<Node, Node> cachedMap = new HashMap<>();

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        //如果当前节点还没复制
        if (!cachedMap.containsKey(head)) {
            //创建复制节点并存储
            Node node = new Node(head.val);
            cachedMap.put(head, node);

            node.next = copyRandomList(head.next);
            node.random = copyRandomList(head.random);
        }

        return cachedMap.get(head);

    }

    /**
     * 官方思路：拼接
     * 其实就是把每个节点新创建的节点放在它后面
     * 这样第二次遍历的时候，就知道它后面的节点是它的node节点，它的random节点的next也是copy的
     */
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }
        //第一次遍历：创建节点
        Node step = head;
        while (step != null) {
            Node temp = new Node(step.val);
            temp.next = step.next;
            step.next = temp;
            step = step.next.next;
        }

        //第二次遍历: 设置random
        for (Node curr = head; curr != null; curr = curr.next.next) {
            if (curr.random != null) {
                curr.next.random = curr.random.next;
            }
        }

        //第三次遍历，取出来新创建的链表
        Node res = head.next;
        for (Node curr = head; curr != null; curr = curr.next) {
            Node copy = curr.next;
            //原来的链表要恢复原状，当前节点指向下一个旧节点，实际是copy.next
            curr.next = curr.next.next;
            //新节点指向下一个新节点
            //copy是新节点，copy.next 需要指向下一个新节点，要先判断下一个旧节点是否为null
            copy.next = copy.next != null ? copy.next.next : null;
        }
        return res;

    }
}
