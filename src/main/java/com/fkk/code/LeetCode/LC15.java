package com.fkk.code.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 三数之和
 */
public class LC15 {

    /**
     * 个人理解这个就是两数之和的变种
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //剪枝：三数之和为0，当进入后发现已经大于0了，后面就没必要再计算了
            if (nums[i] > 0) {
                return res;
            }
            //剪枝
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //剪枝
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    while (left < right && nums[left] == nums[left + 1]) left++;

                    right--;
                    left++;
                }
            }
        }

        return res;
    }
}
