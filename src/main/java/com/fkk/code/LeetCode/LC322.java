package com.fkk.code.LeetCode;

/**
 * 零钱兑换
 * https://leetcode.cn/problems/coin-change/description/
 */
public class LC322 {

    public int coinChange(int[] coins, int amount) {
        int i = coins.length - 1;
        int count = 0;
        while (amount > 0) {
            //找到仅次于amount的金额
            while (i > 0 && coins[i] > amount) {
                i--;
            }
            amount -= coins[i];
            count++;
        }
        return amount == 0 ? count : -1;
    }


}
