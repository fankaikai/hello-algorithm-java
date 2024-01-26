package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

/**
 * LeetCode 35: 搜索插入位置
 * https://leetcode.cn/problems/search-insert-position/
 */
public class LC35 {

    private static int searchInsert(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int m = i + (j - i) / 2;
            if (nums[m] < target) {
                //说明在区间右侧，下次在（m+1，j）范围内查找
                i = m + 1;
            } else if (nums[m] > target) {
                //在区间左侧,下次在（i,m-1）范围内查找
                j = m - 1;
            } else {
                //找到了直接返回
//                return m;
                //如果有重复元素，就先不直接返回，而是继续寻找重复元素的边界
                j = m-1;
            }
        }
        //找不到,找不到的话要找插入点
        return i;
    }

    public static void mockTest() {
        int[] nums = {1, 3, 5, 6};
        Log.d("LC35","target 5 ==>" + (searchInsert(nums, 5) == 2));
        Log.d("LC35","target 2 ==>" + (searchInsert(nums, 2) == 1));
        Log.d("LC35","target 7 ==>" + (searchInsert(nums, 7) == 4));
    }


}
