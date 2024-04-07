package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

/**
 * 同构字符串
 */
public class LC205 {

    /**
     * 用一个字符替换另一个字符
     */
    public boolean isIsomorphic(String s, String t) {
        char[] charS = s.toCharArray();
        char[] charT = t.toCharArray();

        //ASCII 码取值范围
        int[] arrS = new int[256];
        int[] arrT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            char x = charS[i];
            char y = charT[i];

            //第一次都为0，肯定相等。如果后续发现有不相等的，说明出现一对多绑定。
            if (arrS[x] != arrT[y]) {
                return false;
            }

            //两个位置赋相同的坐标值，这里+1为了避开0
            arrS[x] = i + 1;
            arrT[y] = i + 1;
        }

        return true;
    }

    public static void test() {
        LC205 lc205 = new LC205();
        Log.d("LC205", "egg & add ==> " + lc205.isIsomorphic("egg", "add"));

    }
}
