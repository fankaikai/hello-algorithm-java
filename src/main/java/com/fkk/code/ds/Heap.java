package com.fkk.code.ds;

import com.fkk.code.utils.ArrayUtils;
import com.fkk.code.utils.Log;

import java.util.Arrays;

/**
 * siftUp提供了两种思路
 *
 */
public class Heap {

    /**
     * 从底到顶进行堆化：
     * 1. 找到最后一个非叶子节点，对最小子堆进行堆化
     * 2. 每次执行完1，都去找前一个非叶子节点，重复1的操作
     * 3. 一直执行到根节点堆化完成。
     * <p>
     * 1. 适用于无序数组直接从低到顶就行堆化
     * 2. 每次队尾插入一个新元素，也可以再执行一次堆化操作。
     *
     * 时间复杂度O(n)
     */
    private static void siftUp(int[] nums) {
        //找到最后一个非叶子节点
        int n = nums.length;
        int pivot = n / 2 - 1;

        while (pivot >= 0) {
            int left = 2 * pivot + 1;
            int right = 2 * pivot + 2;
            int max = pivot;
            if (left < n && nums[left] > nums[max]) {
                max = left;
            }
            if (right < n && nums[right] > nums[max]) {
                max = right;
            }
            //当前非叶子节点不是最大值，跟最大值进行交换
            if (pivot != max) {
                ArrayUtils.swap(nums, pivot, max);
            }
            //指向前一个非叶子节点，开启下一轮堆化
            pivot--;
        }
    }

    /**
     * 空堆依次插入元素使用的siftUp
     * 时间复杂度O(log n)
     */
    public static void siftUp2(int[] nums, int i) {
        while (true) {
            // 获取节点 i 的父节点
            int p = (i - 1) / 2;
            // 当“越过根节点”或“节点无须修复”时，结束堆化
            if (p < 0 || nums[i] <= nums[p])
                break;
            // 交换两节点
            ArrayUtils.swap(nums, i, p);
            // 循环向上堆化
            i = p;
        }

    }

    public static void mockMaxHeap() {
        int[] nums = {2, 14, 6, 21, 3, 17, 9, 38, 5};
        for (int i = 0; i < nums.length - 1; i++) {
            siftUp2(nums, i);
        }
        Log.d("MaxHeap", "空堆依次插入元素构建大顶堆 siftUp-->" + Arrays.toString(nums));

        int[] nums2 = {2, 14, 6, 21, 3, 17, 9, 38, 5};
        siftUp(nums2);
        Log.d("MaxHeap", "无序数组构建大顶堆 siftUp-->" + Arrays.toString(nums2));
    }

}
