package com.fkk.code.algorithm;

import java.util.Arrays;

/**
 * 爬楼梯问题
 */
public class ClimbingStairs {

    //递归搜索：记忆化
    public static int climbingStairsDFS(int n) {
        //爬到第n阶台阶的总数
        int[] memory = new int[n + 1];
        Arrays.fill(memory, -1);
        return dfs(n, memory);
    }

    public static int dfs(int i, int[] memory) {
        if (i == 1 || i == 2) {
            return i;
        }
        if (memory[i] != -1) {
            return memory[i];
        }
        int count = dfs(i - 1, memory) + dfs(i - 2, memory);
        memory[i] = count;
        return count;
    }

    /**
     * 动态规划写法
     */
    public static int climbingStairsDP(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 优化空间
     */
    public static int climbingStairsDP2(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int a = 1, b = 2;
        for (int i = 3; i < n + 1; i++) {
            int temp = b;
            b = a + temp;
            a = temp;
        }
        return b;
    }

    /**
     *  带约束爬楼梯：动态规划，不能连续跳1阶台阶
     *  dp[i][j], i代表当前台阶，j代表上一轮跳了1或者2阶
     *  dp[i][1],代表上一轮跳了1阶，那上上一轮肯定不是1阶 dp[i][1] = dp[i-1][2]
     *  dp[i][2],代表上一轮跳了2阶，上上一轮可能是1或者2，dp[i][2] = dp[i-1][]
     */
    int climbingStairsConstraintDP(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        // 初始化 dp 表，用于存储子问题的解
        int[][] dp = new int[n + 1][3];
        // 初始状态：预设最小子问题的解
        dp[1][1] = 1;
        dp[1][2] = 0;
        dp[2][1] = 0;
        dp[2][2] = 1;
        // 状态转移：从较小子问题逐步求解较大子问题
        for (int i = 3; i <= n; i++) {
            dp[i][1] = dp[i - 1][2];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
        }
        return dp[n][1] + dp[n][2];
    }

}
