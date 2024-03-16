package com.fkk.code.jz;

/**
 * 剪绳子
 */
public class JZ14 {

    public int cutRope(int n) {
        if (n == 2 || n == 3) {
            return n - 1;
        }
        int res = 1;
        while (n > 4) {
            n = n - 3;
            res *= n;
        }
        return n * res;
    }
}
