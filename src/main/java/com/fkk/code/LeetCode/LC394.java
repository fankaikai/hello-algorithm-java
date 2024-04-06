package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

import java.util.Stack;

/**
 * LC394. 字符串解码
 */
public class LC394 {

    private static final String TAG = LC394.class.getSimpleName();

    /**
     * 方法一：使用栈去解析
     * LeetCode中文版官方题解真的垃圾，不如英文版的。
     */
    private int index = 0;

    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        index = 0;
        while (index < s.length()) {
            char temp = s.charAt(index);
            if (Character.isDigit(temp)) {
                //如果是数字，那就把当前位置往后的完整数字读出来。
                stack.push(getDigit(s));
            } else if (Character.isLetter(temp) || temp == '[') {
                //当是[ 或者 目标字节时，统统入栈,使用i++，执行完后再+1
                stack.push(String.valueOf(s.charAt(index++)));
            } else {
                //遇到了 ] ，此时需要执行出栈拼接
                ++index;
                //之前入栈的元素出栈，直到最近的 [ 出现为止
//                Stack<String> dest = new Stack<>();
                StringBuilder sb = new StringBuilder();
                while (!"[".equals(stack.peek())) {
//                    dest.push(stack.pop());
                    sb.insert(0, stack.pop());
                }
                //遍历dest构建元素
                String tempString = sb.toString();

                // 符号 [ 出栈
                stack.pop();

                //取出栈顶元素，此时一定是数字
                int repeatTime = Integer.parseInt(stack.pop());
                String resString = tempString.repeat(repeatTime);
                stack.push(resString);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.empty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    private String getDigit(String s) {
        StringBuilder sb = new StringBuilder();
        //遍历找到当前数字的末尾，下一个就是非数字了,比如[
        while (Character.isDigit(s.charAt(index))) {
            sb.append(s.charAt(index++));
        }
        return sb.toString();
    }



    /**
     * 这种解法无法应对，嵌套括号的情况
     */
    @Deprecated
    public String decodeString2(String s) {
        StringBuilder sb = new StringBuilder();
        int l = 0, r = 0;
        int start = 0;
        while (r < s.length() - 1 && r >= 0) {
            int lastR = r;
            r = s.indexOf(']', r + 1);
            l = s.indexOf('[', l + 1);

            if (r == -1) {
                sb.append(s.substring(lastR + 1));
            } else {
                int n = Integer.parseInt(s.substring(start, l));
                String e = s.substring(l + 1, r);
                start = r + 1;
                sb.append(e.repeat(Math.max(0, n)));
            }
        }

        return sb.toString();
    }

    public static void test() {
        LC394 lc = new LC394();
//        String decodeString = lc.decodeString2("3[a]2[c]");
//        Log.d("LC394", "3[a]2[c] ===> " + decodeString);
//
//        String decodeString1 = lc.decodeString2("2[abc]3[cd]ef");
//        Log.d(TAG, "2[abc]3[cd]ef ===> " + decodeString1);

        String decodeString = lc.decodeString("3[a]2[bc]");
        Log.d("LC394", "3[a]2[bc] ===> " + decodeString);

        String decodeString1 = lc.decodeString("2[abc]3[cd]ef");
        Log.d(TAG, "2[abc]3[cd]ef ===> " + decodeString1);

        String decodeString2 = lc.decodeString("2[ab3[c]]xy");
        Log.d(TAG, "2[ab3[c]]xy ===> " + decodeString2);

    }

}
