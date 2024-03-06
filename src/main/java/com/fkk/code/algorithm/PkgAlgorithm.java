package com.fkk.code.algorithm;

import com.fkk.code.utils.Log;

/**
 * 01背包问题
 */
public class PkgAlgorithm {

    private static int knapsackDP(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c < cap; c++) {
                //每一次决策实际就是放不放入背包,不放入直接用前一个,对应二维数组上方的
                int no = dp[i - 1][c];
                //判断是否超重
                int weight = wgt[i];
                if (weight > c) {
                    //已经超重，不再放入
                    dp[i][c] = no;
                } else {
                    //选择放入 要根据前一个最佳决策决定当前的最佳值
                    //根据剩余空间确定索引
                    int m = c - weight;
                    int yes = dp[i - 1][m] + val[i - 1];
                    dp[i][c] = Math.max(yes, no);
                }
            }
        }

        return dp[n][cap];
    }

    /**
     * 优化背包空间：
     * 1. 二维数组实际上利用率不高，计算每一行的时候只用的上上一行的数据，再之前的已经不需要了，所以可以用一维数组优化
     * 2. 每一列的计算，需要用到当前列以及之前的数据，所以如果正序去遍历，新一行把老一行的覆盖了，会导致后面计算时取到错误的值。
     * 因此最好使用倒序遍历去计算
     */
    private static int knapsackDP2(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        int[] dp = new int[cap + 1];
        for (int i = 1; i < n; i++) {
            for (int c = cap; c >= 1; c--) {
                if (wgt[i - 1] <= c) {
                    dp[c] = Math.max(
                            dp[c],
                            dp[c - wgt[i - 1]] + val[i - 1]
                    );
                }
                //超出背包容量时，因为复用同一列的数组，dp[c]=dp[c],所以可以直接忽略
            }
        }

        return dp[cap];
    }


    /**
     * 模拟01背包问题
     */
    public static void mockPkgAlgo() {
        int[] wgt = new int[]{1, 2, 3};
        int[] val = new int[]{5, 11, 15};
        int cap = 4;
        int i = knapsackDP(wgt, val, cap);
        Log.d("01PKG", "i=" + i);
    }
}
