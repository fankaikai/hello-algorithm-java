package com.fkk.code.utils;

public class NumberUtils {

    /**
     * 获取元素 num 的第 k 位，其中 exp = 10^(k-1)
     */
    public static int digit(int num, int exp) {
        // 传入 exp 而非 k 可以避免在此重复执行昂贵的次方计算
        return (num / exp) % 10;
    }
}
