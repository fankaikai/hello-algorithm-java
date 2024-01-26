package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 丢失的数字
 * https://leetcode.cn/problems/missing-number/description/
 * 思路1：哈希表
 * 思路2：快排 Arrays.sort,对比下标。时间复杂度O(n log n)未实现。
 * 思路3：异或运算。。。吃了没文化的亏啊。。。
 */
public class LC268 {

    /**
     * 思路1：哈希表，list或者Set都可以
     * 时间复杂度O(n)
     */
    public int missingNumber(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        for (int i = 0; i <= nums.length; i++) {
            if (!list.contains(i)) {
                return i;
            }
        }

        return -1;
    }

    //思路：求差值
    public static int missingNumber2(int[] nums) {
        int sums = 0;
        for (int num : nums) {
            sums += num;
        }
        int calSum = (nums.length + 1) * nums.length / 2;
        return calSum - sums;
    }

    //异或运算
    public static int missingNumber3(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 0; i <= nums.length; i++) {
            xor ^= i;
        }
        return xor;
    }

    public static void mockTest() {
//        print(2, missingNumber2(new int[]{3, 0, 1}));
//        print(2, missingNumber2(new int[]{0, 1}));
//        print(1, missingNumber2(new int[]{0}));
//        print(8, missingNumber2(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));

        print(2, missingNumber3(new int[]{3, 0, 1}));
        print(2, missingNumber3(new int[]{0, 1}));
        print(1, missingNumber3(new int[]{0}));
        print(8, missingNumber3(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    private static void print(int target, int result) {
        Log.d("LC268", "预期目标：" + target + ",输出目标" + result);
    }
}
