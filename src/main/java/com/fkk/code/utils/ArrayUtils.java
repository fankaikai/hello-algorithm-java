package com.fkk.code.utils;

public class ArrayUtils {

    public static void swap(int[] nums, int src, int dst) {
        int temp = nums[src];
        nums[src] = nums[dst];
        nums[dst] = temp;
    }
}
