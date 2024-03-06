package com.fkk.code.jz;

import com.fkk.code.ds.TreeNode;

import java.util.Arrays;

/**
 * 重建二叉树
 */
public class JZ7 {

    /**
     * 前序遍历可以确定根节点
     * 中序遍历递归拆分左右子树
     */
    public TreeNode reConstructBinaryTree(int[] preOrder, int[] vinOrder) {
        int n = preOrder.length;
        int m = vinOrder.length;
        if (n == 0 || m == 0) {
            return null;
        }
        //先构建根节点
        TreeNode root = new TreeNode(preOrder[0]);
        for (int i = 0; i < vinOrder.length; i++) {
            //根据前序遍历根节点找到中序遍历中的根节点所在位置
            if (preOrder[0] == vinOrder[i]) {
                //构建左子树
                root.left = reConstructBinaryTree(
                        Arrays.copyOfRange(preOrder, 1, i + 1),
                        Arrays.copyOfRange(vinOrder, 0, i)
                );
                //构建右子树
                root.right = reConstructBinaryTree(
                        Arrays.copyOfRange(preOrder, i + 1, preOrder.length),
                        Arrays.copyOfRange(vinOrder, i + 1, vinOrder.length)
                );
                break;
            }
        }

        return root;
    }
}
