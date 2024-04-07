package com.fkk.code.LeetCode;

/**
 * LC409. 最长回文串
 * 这里有个细节：给定的字符串可以进行排列重组，寻找可以组成的最长回文串
 */
public class LC409 {

    /**
     * 如果是奇数，则只有一个字符是单独出现的
     * 如果是偶数，则多有字符都是成对出现的
     */
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray()) {
            count[c]++;
        }

        int res = 0;
        for (int c : count) {
            //计算偶数次
            res += (c / 2) * 2;

            //count是奇数时，可以取出来作为中间值，长度+1。但是后续总长度必须保持是奇数，所以后续判断偶数为false
            if (c % 2 == 1 && res % 2 == 0) {
                res++;
            }
        }
        return res;

    }

}
