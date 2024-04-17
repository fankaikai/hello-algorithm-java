package com.fkk.code.LeetCode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 验证栈序列
 */
public class LC946 {

    /**
     * 这题是真的没意思，描述的一塌糊涂
     * 实际就是考察，pushed代表入栈数据，poped代表出栈数据，找到合适的顺序，使得入栈出栈，最终栈为空。
     * <p>
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length){
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int k : pushed) {
            stack.push(k);
            while (!stack.isEmpty() && popped[j] == stack.peek()) {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }


}
