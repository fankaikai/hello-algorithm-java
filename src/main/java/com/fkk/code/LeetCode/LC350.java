package com.fkk.code.LeetCode;

import com.fkk.code.utils.Log;

import java.util.*;

/**
 * 349. 两个数组的交集II
 * 思路一：哈希表
 * 思路二：双指针应该也可以，先进行排序，然后进行对比，发现重复的就输出，不需要去重。指针一起增加，也会自动排除数量不一致的相同数据。
 */
public class LC350 {

    //思路一，重复元素也要输出，但是要数量对应，考虑使用hashMap
    public static int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n1 : nums1) {
            if (map.containsKey(n1)) {
                map.put(n1, map.get(n1) + 1);
            } else {
                map.put(n1, 1);
            }
        }

//        ArrayList<Integer> list = new ArrayList<>();
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int n2 : nums2) {
            if (map.containsKey(n2)) {
                Integer time = map.getOrDefault(n2, 0);
                if (time > 0) {
//                    list.add(n2);
                    intersection[index++] = n2;
                    time--;
                    if (time > 0) {
                        map.put(n2, time);
                    } else {
                        map.remove(n2);
                    }
                }
            }
        }
//        int[] res = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            res[i] = list.get(i);
//        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    public static void mockTest() {
        print(new int[]{2, 2}, intersection(
                new int[]{1, 2, 2, 1},
                new int[]{2, 2}
        ));

        print(new int[]{4, 9}, intersection(
                new int[]{4, 9, 5},
                new int[]{9, 4, 9, 8, 4}
        ));
    }

    private static void print(int[] expect, int[] real) {
        Log.d("LC350", "预期结果：" + Arrays.toString(expect) + ", 输出结果：" + Arrays.toString(real));
    }


}
