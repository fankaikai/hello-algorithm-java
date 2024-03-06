package com.fkk.code.algorithm;

import com.fkk.code.utils.Log;

/**
 * 完全背包问题
 */
public class CompletePkgAlgorithm {

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
