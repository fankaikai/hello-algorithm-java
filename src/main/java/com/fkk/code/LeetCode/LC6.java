package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Z字形变换
 */
public class LC6 {

    /**
     *
     */
    public String convert(String s, int numRows) {
        if (numRows < 2) return s;
        //key-行数，每一行的元素
        HashMap<Integer, StringBuilder> hashMap = new HashMap<>();
        for (int i = 0; i < numRows; i++) {
            hashMap.put(i, new StringBuilder());
        }
        int step = 0;
        boolean isUp = true;
        for (int i = 0; i < s.length(); i++) {
            char m = s.charAt(i);
            hashMap.get(step).append(m);
            if (step == numRows - 1) {
                isUp = false;
            } else if (step == 0) {
                isUp = true;
            }

            if (isUp) {
                step++;
            } else {
                step--;

            }
        }

        Set<Integer> keySet = hashMap.keySet();
        StringBuilder res = new StringBuilder();
        for (Integer key : keySet) {
            StringBuilder stringBuilder = hashMap.get(key);
            res.append(stringBuilder.toString().trim());
        }
        return res.toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows < 2) return s;
        //key-行数，每一行的元素
        ArrayList<StringBuilder> rowsList = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rowsList.add(new StringBuilder());
        }
        int step = 0;
        int flag = -1;

        for (char ch : s.toCharArray()) {
            rowsList.get(step).append(ch);
            if (step == 0 || step == numRows - 1) flag = -flag;
            step += flag;
        }


        StringBuilder res = new StringBuilder();
        for (StringBuilder stringBuilder : rowsList) {
            res.append(stringBuilder.toString().trim());
        }
        return res.toString();
    }

    public static void test() {
        LC6 lc6 = new LC6();

        String s1 = "PAYPALISHIRING";
        String expect = "PAHNAPLSIIGYIR";
        String res1 = lc6.convert(s1, 3);
        printLog(s1, expect, res1);
        Log.d("LC6", "=====================分割线=========================");
        String s2 = "PAYPALISHIRING";
        String expect2 = "PINALSIGYAHRPI";
        String res2 = lc6.convert(s2, 4);
        printLog(s2, expect2, res2);
        Log.d("LC6", "=====================分割线=========================");
        String s3 = "AB";
        String expect3 = "PINALSIGYAHRPI";
        String res3 = lc6.convert(s3, 1);
        printLog(s3, expect3, res3);


    }

    private static void printLog(String s, String expect, String res) {
        Log.d("LC6", "s1===> " + s
                + "\nres===> " + res
                + "\nexpect===>" + expect
                + "\nis Right? ==>" + (res.equals(expect))
        );
    }
}
