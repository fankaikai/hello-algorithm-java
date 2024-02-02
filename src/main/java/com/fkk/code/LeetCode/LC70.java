package com.fkk.code.LeetCode;

import java.util.Arrays;

/**
 * 爬楼梯:
 * https://leetcode.cn/problems/climbing-stairs/description/
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class LC70 {

    /**
     * 方法一：暴力搜索(超出时间限制)
     * 方法二：加入memo数组记忆重复操作
     */
    public static int climbStairs(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n, memo);
    }

    private static int dfs(int i, int[] memo) {
        if (i == 1 || i == 2) {
            return i;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        int count = dfs(i - 1, memo) + dfs(i - 2, memo);
        memo[i] = count;
        return count;
    }


    /**
     * 动态规划版本
     */
    public static int climbStairs2(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        //这里实际上dp[0] 没有用上
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
