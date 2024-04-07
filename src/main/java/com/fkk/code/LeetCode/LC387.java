package com.fkk.code.LeetCode;

import com.fkk.code.Main;
import com.fkk.code.utils.Log;

import java.util.HashMap;

/**
 * 字符串中第一个不重复的字符
 */
public class LC387 {

    /**
     * 时间复杂度O(n)
     * 空间复杂度O(1)，因为只有26个字母
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.get(s.charAt(i)) == 1) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 哈希表的另一种思路
     */
    public int firstUniqChar2(String s) {
        //value表示索引，遍历时，如果c不存在就存入索引值，如果已经存在就把值设置为-1
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (!hashMap.containsKey(c)) {
                hashMap.put(c, i);
            } else {
                hashMap.put(c, -1);
            }
        }

        //二次遍历，找出最小索引  res需要选择一个较大的值
        int res = length;
        for (char key : hashMap.keySet()) {
            int index = hashMap.get(key);
            if (index != -1 && index < res) {
                res = index;
            }
        }

        return res == length ? -1 : res;
    }

    /**
     * Hash表替换为数组
     */
    public int firstUniqChar3(String s) {
        int[] count = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            count[c - 'a']++;
        }
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (count[c - 'a'] == 1) {
                return i;
            }
        }
        return -1;

    }

    /**
     *
     */
    public int firstUniqChar4(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        int res = s.length();
        //遍历26个字母
        for (char c = 'a'; c <= 'z'; c++) {
            int first = s.indexOf(c);
            //字符串中有这个字符，且第一个和最后一个索引一致说明只有一个
            if (first != -1 && first == s.lastIndexOf(c)) {
                res = Math.min(first, res);
            }
        }

        return res == s.length() ? -1 : res;

    }

    public static void test() {
        LC387 lc = new LC387();
        int res = lc.firstUniqChar4("leetcode");
        Log.d("LC387", "leetcode===>" + res);

        int res2 = lc.firstUniqChar4("loveleetcode");
        Log.d("LC387", "loveleetcode===>" + res2);

        int res3 = lc.firstUniqChar4("aabb");
        Log.d("LC387", "leetcode===>" + res3);
    }

}
