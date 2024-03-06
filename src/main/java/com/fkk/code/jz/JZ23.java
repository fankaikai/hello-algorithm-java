package com.fkk.code.jz;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 牛客网-剑指offer JZ23 数组中重复的数字
 * https://www.nowcoder.com/exam/oj/ta?page=1&tpId=13&type=265
 */
public class JZ23 {

    public int duplicate(int[] numbers) {
        if (numbers == null) {
            return -1;
        }
        int size = numbers.length;
        if (size <= 1) {
            return -1;
        }

        Arrays.sort(numbers);
        for (int i = 1; i < size; i++) {
            if (numbers[i] == numbers[i - 1]) {
                return numbers[i];
            }
        }

        return -1;

    }

    /**
     * 事件复杂度O(N)
     * 额外使用了空间，空间复杂度O(N)
     */
    public int duplicate2(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        for (int number : numbers) {
            if (set.contains(number)) {
                return number;
            }
            set.add(number);
        }

        return -1;

    }

    /**
     * 时间复杂度O(N)
     * 空间复杂度O(1),没有使用额外的空间
     */
    public int duplicate3(int[] numbers) {
        int i = 0;
        while (i < numbers.length) {
            if (numbers[i] == i) {
                i++;
                continue;
            } else {
                if (numbers[i] == numbers[numbers[i]]) {
                    return numbers[i];
                } else {
                    int temp = numbers[i];
                    numbers[i] = numbers[temp];
                    numbers[temp] = temp;
                }
            }
        }

        return -1;
    }

}
