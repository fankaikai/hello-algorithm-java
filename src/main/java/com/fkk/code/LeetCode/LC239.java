package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

import java.util.*;

/**
 * 滑动窗口的最大值
 */
public class LC239 {


    /**
     * TODO: 复杂度分析
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        //这里使用数组，每个数组容量都是2，arr[0]表示值，arr[1]表示下标
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>(1, new Comparator<int[]>() {
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] != arr2[0] ? arr2[0] - arr1[0] : arr2[1] - arr1[1];
            }
        });
        //先把前K个元素入堆
        for (int i = 0; i < k; i++) {
            maxHeap.offer(new int[]{nums[i], i});
        }
        int[] res = new int[nums.length - k + 1];
        int step = 0;
        //记录前K个元素最大值
        res[step++] = maxHeap.peek()[0];
        //剩余元素依次入堆
        for (int i = k; i < nums.length; i++) {
            maxHeap.offer(new int[]{nums[i], i});
            while (maxHeap.peek()[1] <= i - k) {
                maxHeap.poll();
            }
            res[step++] = maxHeap.peek()[0];
        }

        return res;
    }

    /**
     * TODO: 方法二,单调队列
     */
    public int[] maxSlidingWindow2(int[] nums, int k){
        return null;
    }

    /**
     * TODO: 方法三，分块预处理
     */
    public int[] maxSlidingWindow3(int[] nums, int k){
        return null;
    }



    public static void test() {
        int[] arr = {1, 3, -1, -3, 5, 3, 6, 7};
        Log.d("LC239", Arrays.toString(new LC239().maxSlidingWindow(arr, 3)));
    }


}
