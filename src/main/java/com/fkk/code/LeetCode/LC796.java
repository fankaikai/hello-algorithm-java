package com.fkk.code.LeetCode;

/**
 * 旋转字符串
 */
public class LC796 {

    /**
     * 这题解题思路真牛逼
     * 1. s 和 goal长度相等才有可能
     * 2. 旋转字符串可以一位一位的旋转，也可以一次旋转部分字符串。s = L + R
     * 因此满足条件时 goal + goal = RL+RL= R + s + L
     * 说明goal + goal 中如果包含s则可以旋转得到符合条件的字符串
     */
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (goal + goal).contains(s);
    }
}
