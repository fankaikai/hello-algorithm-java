package com.fkk.code.jz;

/**
 * 二进制中1的个数
 */
public class JZ15 {

    public int NumberOf1(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                res++;
            }
        }

        return res;
    }


    /**
     * 位运算优化
     * todo: 梳理思路
     */
    public int NumberOf1V2(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }

        return res;
    }
}
