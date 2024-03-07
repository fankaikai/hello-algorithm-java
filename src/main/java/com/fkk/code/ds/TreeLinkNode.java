package com.fkk.code.ds;

public class TreeLinkNode {
    public int val;

    /**
     * 左子树
     */
    public TreeLinkNode left = null;
    /**
     * 右子树
     */
    public TreeLinkNode right = null;
    /**
     * 父节点
     */
    public TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}
