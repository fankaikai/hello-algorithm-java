package com.fkk.code.LeetCode;

import java.math.BigDecimal;

/**
 * 字符串相加
 */
public class LC415 {

    /**
     * 不允许转换成整数，也不能使用BigDecimal
     * 可以考虑逐个字节去计算
     *
     * 时间复杂度O(maxLength)
     * 空间复杂度O(1)
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        //进位
        int add = 0;
        StringBuilder sb = new StringBuilder();
//        while (i >= 0 || j >= 0 || add != 0) {
        while (i >= 0 || j >= 0) {
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = a + b + add;
            sb.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }

        if(add != 0){
            sb.append(add);
        }
        return sb.reverse().toString();
    }

}
