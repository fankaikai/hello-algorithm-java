package com.fkk.code.LeetCode;

import com.fkk.code.ds.TreeNode;

/**
 * LeetCode 222. 完全二叉树的节点个数
 * https://leetcode.cn/problems/count-complete-tree-nodes/description/
 */
public class LC222 {

    /**
     * 普通二叉树求解
     * 思路：递归找到叶子节点算1个，对于每个小子树就是最多记作3，依次回归聚合得到总数。时间复杂度O(n)，空间复杂度O(log n)
     * 思路2：未实现，可以使用队列+层序遍历的方式，暴力计算。时间和空间复杂度都是O(n)
     */
    private int countNodes(TreeNode node) {
        if (node == null) {
            return 0;
        }
        //找到叶子节点
        if (node.left == null && node.right == null) {
            return 1;
        }

        //非叶子节点
        return countNodes(node.left) + countNodes(node.right) + 1;
    }

    /**
     * 思路2：对比左右子树深度
     * 完全二叉树，叶子节点一定是从尽量靠左的。所以可以根据左右子树的深度对比来进行计算
     * 左子树深度left对比右子树深度right
     * 如果left == right, 说明左右子树深度一致，左子树一定是一个满二叉树，但是右子树不一定。
     * 如果left != right，说明左子树不是满二叉树，右子树少一层，肯定是满二叉树，可以根据深度计算节点数。左子树不一定
     * 根据递归的思想，我们可以不断的递归去计算每个子树是否是满二叉树，直到最小子树或者叶子节点。
     */
    private int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftLevel = countLevels(root.left);
        int rightLevel = countLevels(root.right);

        if (leftLevel == rightLevel) {
            //左右子树一致，说明左子树是满二叉树
            return countNodes(root.right) + (1 << leftLevel - 1) + 1;
        } else {
            //左右子树不一致，说明右子树是满二叉树
            return countNodes(root.left) + (1 << rightLevel - 1) + 1;
        }

    }

    private int countLevels(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }


}




