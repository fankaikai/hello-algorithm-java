package com.fkk.code.jz;

import com.fkk.code.ds.TreeLinkNode;

import java.util.ArrayList;

/**
 * 二叉树下一个节点
 */
public class JZ8 {

    /**
     * 中序遍历，当前节点的下一个节点一定是右子树的递归得到的最左边的节点
     * 情况一：当前节点有右子树，则右子树的最左边节点一定是下一个节点
     * 情况二：当前节点没有右子树，父节点左节点是当前节点，则父节点就是下一个节点
     * 情况三：当前节点没有右子树，父节点右节点是当前节点，那就需要遍历父节点，
     * 找到当前节点属于哪个节点的左子树中，那这个节点一定是该节点的下一个节点，否则返回NULL
     */
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        //情况一
        if (pNode.right != null){
            TreeLinkNode node = pNode.right;
            while (node.left != null){
                node = node.left;
            }
            return node;
        }

        if (pNode.next == null) {
            return null;
        }

        //情况二
        if (pNode.next.left == pNode) {
            return pNode.next;
        }

        //情况三
        TreeLinkNode node = pNode.next;
        while (node.next != null) {
            if (node.next.left == node) {
                return node.next;
            } else {
                node = node.next;
            }
        }

        return null;
    }

    /**
     * 情况一
     */
    private TreeLinkNode findRightTree(TreeLinkNode node) {
        if (node.left == null) {
            return node;
        }
        return findRightTree(node.left);
    }


    /**
     * 思路2：找到根节点
     */
    public TreeLinkNode GetNext2(TreeLinkNode pNode) {
        TreeLinkNode root = pNode;
        //遍历找到根节点
        while (root.next != null) {
            root = root.next;
        }

        //根据根节点构建中序数组
        ArrayList<TreeLinkNode> nodes = new ArrayList<>();
        inorder(root, nodes);
        //这里不用比较最后一个，因为最后一个即使相等了，也没有下一个节点了。
        for (int i = 0; i < nodes.size() - 1; i++) {
            TreeLinkNode node = nodes.get(i);
            if (node == pNode) {
                return nodes.get(i + 1);
            }
        }

        return null;
    }

    /**
     * 构建中序集合
     */
    private void inorder(TreeLinkNode root, ArrayList<TreeLinkNode> nodes) {
        if (root != null) {
            inorder(root.left, nodes);
            nodes.add(root);
            inorder(root.right, nodes);
        }
    }
}
