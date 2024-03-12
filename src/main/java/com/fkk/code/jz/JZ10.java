package com.fkk.code.jz;

/**
 * 斐波那契数列
 */
public class JZ10 {

    public int fibonacci (int n) {
        if (n == 1 || n == 2){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
