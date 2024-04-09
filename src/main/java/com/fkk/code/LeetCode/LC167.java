package com.fkk.code.LeetCode;

/**
 * 两数之和II 输入有序数组
 * 要求：
 * 1. 不能使用重复元素
 * 2. 空间复杂度O(1)
 */
public class LC167 {

    /**
     * 这种解法超出时间限制了
     */
    public int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length - 1; i++) {
            int a = numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                int b = numbers[j];
                if (a != b && a + b == target) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return null;
    }


    /**
     * 双指针，两头逼近
     */
    public int[] twoSum2(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            }
            if (sum > target) {
                j--;
            } else {
                i++;
            }
        }

        return null;
    }


}
