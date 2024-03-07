package com.fkk.code.jz;

import com.fkk.code.ds.TreeNode;

import java.util.ArrayList;

/**
 * JZ68 二叉搜索树的最近公共祖先
 */
public class JZ68 {

    /**
     * 情况一：如果其中一个就是根节点，直接返回该节点
     * 情况二：如果指定值在左右两边，那当前节点就是根节点
     * 情况三：如果指定值在一侧，那就去那一侧寻找
     */
    public int lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }
        int n = root.val;
        if ((p >= n && q <= n) || (p <= n && q >= n)) {
            return n;
        } else if (p <= n && q <= n) {
            //左子树查找
            return lowestCommonAncestor(root.left, p, q);
        } else {
            //右子树查找
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    /**
     * 遍历树，分别找到对应pq节点，记录路径，最后对比路径，找到最后一个相同的节点就是公共父节点。
     * 找到最近的公共祖先的隐藏含义是在这之上的节点都是重复的，所以查找路径的时候，要找最后一个相同节点
     * 也就是找到第一个不相同的节点，前一个就是公共祖先。
     */
    public int lowestCommonAncestor2(TreeNode root, int p, int q) {
        ArrayList<Integer> pList = getPath(root, p);
        ArrayList<Integer> qList = getPath(root, q);
        int res = -1;
        for (int i = 0; i < pList.size() && i < qList.size(); i++) {
            int x = pList.get(i);
            int y = qList.get(i);

            if (x == y) {
                res = x;
            } else {
                break;
            }
        }
        return res;
    }

    public ArrayList<Integer> getPath(TreeNode root, int target) {
        ArrayList<Integer> path = new ArrayList<>();
        TreeNode node = root;
        while (target != node.val) {
            path.add(node.val);
            if (target < node.val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        path.add(node.val);
        return path;
    }
}
