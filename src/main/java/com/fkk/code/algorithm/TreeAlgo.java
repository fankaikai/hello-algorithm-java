package com.fkk.code.algorithm;

import com.fkk.code.ds.TreeNode;

import java.util.HashMap;

public class TreeAlgo {

    /**
     * 给定一棵二叉树的前序遍历 preorder 和中序遍历 inorder ，请从中构建二叉树，返回二叉树的根节点。假设二叉树中没有值重复的节点
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> inorderMap = new HashMap<>();
        //inorder数组主要是方便查询，所以使用索引表优化查询速度
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(i, inorder[i]);
        }
        return dfs(preorder, inorderMap, 0, 0, inorder.length - 1);
    }

    /**
     * 通过递归遍历的方式构建
     */
    private static TreeNode dfs(int[] preorder, HashMap<Integer, Integer> inorderMap, int i, int l, int r) {
        if (l > r) {
            return null;
        }
        //前序遍历找到根节点
        int rootVal = preorder[i];
        //根据根节点在中序遍历数组找到根节点的索引
        Integer m = inorderMap.get(rootVal);

        //构造子树
        TreeNode rootNode = new TreeNode(rootVal);
        //构建左子树,左子树根节点是 i+1。中序数组范围[l,m-1]
        rootNode.left = dfs(preorder, inorderMap, i + 1, l, m - 1);
        //构建右子树,前序遍历：右子树的根节点是 i+1 + (m-l), 中序遍历：[m+1,r]
        rootNode.right = dfs(preorder, inorderMap, i + 1 + (m - l), m + 1, r);
        return rootNode;
    }
}
