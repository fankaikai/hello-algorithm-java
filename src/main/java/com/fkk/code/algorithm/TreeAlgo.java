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
            //这里key是中序数组的值，value是值对应下标
            inorderMap.put(inorder[i], i);
        }
        return dfs(preorder, inorderMap, 0, 0, inorder.length - 1);
    }

    /**
     * 通过递归遍历的方式构建
     *
     * @param preStart 前序数组中开始节点位置, 也就是根节点位置
     * @param inLeft 中序数组左侧起始位置
     * @param inRight 中序数组右侧起始位置
     */
    private static TreeNode dfs(
            int[] preorder,
            HashMap<Integer, Integer> inorderMap,
            int preStart,
            int inLeft,
            int inRight
    ) {
        if (inLeft > inRight) {
            return null;
        }
        //前序遍历找到根节点
        int rootVal = preorder[preStart];
        //根据根节点在中序遍历数组找到根节点的索引
        Integer m = inorderMap.get(rootVal);

        // 索引m是中序数组中根节点位置，索引左边是左子树的数据[l,m-1]，右边是右子树的数据[m+1,r]
        // 由此计算出，前序数组中，左子树的根节点是 preStart+1, 右子树的根节点是 m-l + preStart+1

        //构造子树
        TreeNode rootNode = new TreeNode(rootVal);
        //构建左子树,左子树根节点是 preStart+1。中序数组范围[inLeft,m-1]
        rootNode.left = dfs(preorder, inorderMap, preStart + 1, inLeft, m - 1);
        //构建右子树,前序遍历：右子树的根节点是 preStart+1 + (m-inLeft), 中序遍历：[m+1,inRight]
        rootNode.right = dfs(preorder, inorderMap, preStart + 1 + (m - inLeft), m + 1, inRight);
        return rootNode;
    }
}
