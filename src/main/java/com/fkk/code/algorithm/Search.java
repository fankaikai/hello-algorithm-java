package com.fkk.code.algorithm;

public class Search {

    //二分查找：递归法
    public static int binarySearch(int[] nums, int target) {
        return dfs(nums, target, 0, nums.length - 1);
    }

    //递归二分查找
    private static int dfs(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] > target) {
            return dfs(nums, target, left, mid - 1);
        } else if (nums[mid] < target) {
            return dfs(nums, target, mid + 1, right);
        } else {
            return mid;
        }
    }
}
