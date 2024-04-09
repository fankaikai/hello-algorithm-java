package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

import java.util.*;

/**
 * 反转字符串中的单词
 * TODO: 寻找更好的解法
 */
public class LC151 {

    public String reverseWords(String s) {
        String[] split = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0; i--) {
            if (!split[i].isEmpty()) {
                sb.append(split[i].trim()).append(" ");
            }
        }
        return sb.toString().trim();
    }

    /**
     * 这是使用API的方法
     * 还有这一种是自己实现这些API
     */
    public String reverseWords2(String s) {
        List<String> list = Arrays.asList(s.trim().split("\\s+"));
        Collections.reverse(list);
        return String.join(" ", list);
    }

    /**
     * 使用双端队列
     */
    public String reverseWords3(String s) {
        //使用栈的话join的时候顺序依然是正序，需要手动迭代
//        Stack<String> stack = new Stack<>();
//        StringBuilder sb = new StringBuilder();
//        for (char c : s.trim().toCharArray()) {
//            if (sb.length() != 0 && c == ' ') {
//                stack.push(sb.toString());
//                sb.setLength(0);
//            } else if(c != ' '){
//                sb.append(c);
//            }
//        }
//        stack.push(sb.toString());
//        return String.join(" ", stack);
        Deque<String> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.trim().toCharArray()) {
            if (sb.length() != 0 && c == ' ') {
                queue.offerFirst(sb.toString());
                sb.setLength(0);
            } else if (c != ' ') {
                sb.append(c);
            }
        }
        queue.offerFirst(sb.toString());
        return String.join(" ", queue);
    }


    public static void test() {
        LC151 lc151 = new LC151();
        Log.d("LC151", "a good   example ===>[" + lc151.reverseWords("a good   example") + "]");
    }
}
