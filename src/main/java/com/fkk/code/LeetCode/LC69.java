package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

/**
 * LeetCode 69 求X的平方根
 * https://leetcode.cn/problems/sqrtx/description/
 */
public class LC69 {

    /**
     * 这一题可以转换思路
     * 实际就是求0-x 之间的的某个数据K，使 K² <= x
     * 也就是二分查找中的寻找右边界
     */
    private static int mySqrt(int x) {
        //i=1 & ans=0、i=0&ans=-1 两种都能通过LeetCode测试，但是后者消耗内存稍微少一点
        int i = 0, j = x;
        int ans = -1;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 这种不使用ans的情况，也可以通过LeetCode测试
     * 这三种方式中消耗内存最多
     */
    public int mySqrt2(int x) {
        int i = 1, j = x;
        // int ans = 0;
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if ((long) mid * mid < x) {
                // ans = mid;
                i = mid + 1;
            } else if ((long) mid * mid > x) {
                j = mid - 1;
            } else {
                return mid;
            }
        }
        return j;
    }

    public static void mockTest() {
        print(0, 0);
        print(1, 1);
        print(4, 2);
        print(8, 2);
        print(13, 3);
        print(2147395599, 2);
    }

    private static void print(int x, int target) {
        Log.d("LC69", "[x=" + x + " t=" + target + "], realResult=" + mySqrt(x));
    }
}
