package com.fkk.code.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 349. 两个数组的交集
 */
public class LC349 {

    //思路一，利用两个hashSet去解决
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for (int n1 : nums1) {
            set.add(n1);
        }

        Set<Integer> tempSet = new HashSet<>();
        for (int j : nums2) {
            if (set.contains(j)) {
                tempSet.add(j);
            }
        }
        int[] res = new int[tempSet.size()];
        int step = 0;
        for (int n : tempSet) {
            res[step] = n;
            step++;
        }
        return res;
    }

    /**
     * 思路2：先排序，然后双指针对比
     */
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int index1 = 0, index2 = 0;
//        Set<Integer> tempSet = new HashSet<>();
        int[] target = new int[nums1.length + nums2.length];
        int index = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            int n1 = nums1[index1];
            int n2 = nums2[index2];
            if (n1 > n2) {
                index2++;
            } else if (n1 < n2) {
                index1++;
            } else {
                //命中
                if (index == 0 || n1 != target[index - 1]) {
                    target[index++] = n1;
                }
                index1++;
                index2++;
            }
        }

        return Arrays.copyOfRange(target, 0, index);

//        int[] res = new int[tempSet.size()];
//        int step = 0;
//        for (int n : tempSet) {
//            res[step] = n;
//            step++;
//        }
//        return res;
    }

}
