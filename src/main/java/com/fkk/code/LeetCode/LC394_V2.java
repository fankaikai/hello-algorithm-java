package com.fkk.code.LeetCode;

/**
 * LC394. 字符串解码
 */
public class LC394_V2 {

    private int step = 0;

    public String decodeString(String s) {
        //全局变量，所以每次重置一下
        step = 0;
        return getDecodeString(s);
    }

    private String getDecodeString(String s) {
        //终止条件
        if (step == s.length() || s.charAt(step) == ']') {
            return "";
        }

        char curr = s.charAt(step);
        String res = "";
        int repeatTime = 1;

        if (Character.isDigit(curr)) {
            //获取重复次数
            repeatTime = getRepeatTime(s);
            //数字之后是左括号，直接跳过
            ++step;
            //递归获取字符
            String decodeString = getDecodeString(s);
            //字符之后就是右括号
            ++step;
            //构造字符串
            res = decodeString.repeat(repeatTime);
        } else if (Character.isLetter(curr)) {
            res = String.valueOf(s.charAt(step++));
        }

        //这里要继续进行后续的解码
        return res + getDecodeString(s);
    }

    private int getRepeatTime(String s) {
        int res = 0;
        while (step < s.length() && Character.isDigit(s.charAt(step))) {
            //这里为啥要减 0 字节
            res = res * 10 + s.charAt(step++) - '0';
        }
        return res;
    }

}
