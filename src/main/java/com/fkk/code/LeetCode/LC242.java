package com.fkk.code.LeetCode;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * 有效的字母异位词
 */
public class LC242 {

    /**
     * 考虑使用map做引用统计
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public boolean isAnagram(String s, String t) {
        //优化点1：先判断长度是否相等
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char a : s.toCharArray()) {
            if (countMap.containsKey(a)) {
                countMap.put(a, countMap.get(a) + 1);
            } else {
                countMap.put(a, 1);
            }
        }

        for (char b : t.toCharArray()) {
            if (countMap.containsKey(b)) {
                countMap.put(b, countMap.get(b) - 1);
            } else {
                //发现有不存在，直接返回false
                return false;
            }
        }

        for (char key : countMap.keySet()) {
            if (countMap.get(key) != 0) {
                return false;
            }
        }

        return true;

    }

    /**
     * 思路2，合理进行剪枝
     * 时间复杂度O(n)
     * 空间复杂度O(1)
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        //26个字母
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            ++arr[c - 'a'];
        }

        for (char c : t.toCharArray()) {
            --arr[c - 'a'];
            //出现负数，直接返回false
            if (arr[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        //26个字母
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ++arr[s.charAt(i) - 'a'];
            --arr[t.charAt(i) - 'a'];
        }

        for (int m : arr) {
            if (m != 0) {
                return false;
            }
        }

        return true;
    }

}
