package com.fkk.code.LeetCode;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 无重复字符的最长子串
 */
public class LC3 {


    /**
     * 这题主要是求长度，不用返回具体内容
     */
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (hashMap.containsKey(c)) {
                start = Math.max(hashMap.get(c) + 1, start);
            }
            max = Math.max(i - start + 1, max);
            hashMap.put(c, i);
        }
        return max;
    }
}
