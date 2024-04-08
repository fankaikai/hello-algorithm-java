package com.fkk.code.LeetCode;

/**
 * 判断子序列
 */
public class LC392 {

    /**
     * 贪心策略
     * 时间复杂度O(m)
     * 空间复杂度O(1)
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == m;
    }
}
