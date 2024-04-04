package com.fkk.code.LeetCode;

import java.util.Stack;

/**
 * 有效括号
 */
public class LC20 {

    /**
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public boolean isValid(String s) {
        //优化, 长度不是偶数，直接判负
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if ((c == ']' && pop != '[')
                        || (c == '}' && pop != '{')
                        || (c == ')' && pop != '(')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
