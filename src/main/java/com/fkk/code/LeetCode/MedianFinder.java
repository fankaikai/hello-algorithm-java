package com.fkk.code.LeetCode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 这一题的思路是使用堆，所以涉及两个问题：
 * 1. 堆是怎么实现的？
 * 2. 使用什么堆？几个堆？数据如何选择入堆？
 */
public class MedianFinder {

    /**
     * 小顶堆，存比中位数大的元素
     */
    private final Queue<Integer> minHeap;

    /**
     * 大顶堆，存比中位数小的元素
     */
    private final Queue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    /**
     * TODO: 时间复杂度？
     */
    public void addNum(int num) {
        if (minHeap.size() != maxHeap.size()) {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        } else {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }
    }

    /**
     * 总数是偶数：大顶堆和小顶堆的堆顶元素平均值就是中位数
     * 总数是奇数：大顶堆的堆顶元素就是中位数
     */
    public double findMedian() {
        return (minHeap.size() != maxHeap.size()) ? minHeap.peek() : ((minHeap.peek() + maxHeap.peek()) / 2.0);
    }
}
