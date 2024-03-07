package com.fkk.code.jz;

import com.fkk.code.ds.TreeNode;

/**
 * JZ54 二叉搜索树的第k个节点
 * 二叉搜索树总是满足左节点<根节点<右节点
 */
public class JZ54 {

    private int count = 0;
    private TreeNode result = null;

    public int KthNode(TreeNode proot, int k) {
        inorder(proot, k);
        return result == null ? -1 : result.val;
    }

    private void inorder(TreeNode root, int k) {
        if (root == null || count > k) {
            return;
        }
        //递归找到左子树叶子节点后开始回溯
        inorder(root.left, k);

        count++;
        if (count == k) {
            result = root;
        }
        //去右子树查询
        inorder(root.right, k);
    }
}
