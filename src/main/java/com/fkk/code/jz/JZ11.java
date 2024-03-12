package com.fkk.code.jz;

/**
 * 旋转数组最小数字
 */
public class JZ11 {

    public int minNumberInRotateArray(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            //找到中点
            int m = left + (right - left) / 2;
            if (nums[m] > nums[right]) {
                //中间值比右侧值大，说明旋转点在[m+1,right]范围内
                left = m + 1;
            } else if (nums[m] < nums[right]){
                //中间值比右边值小，说明旋转点在左侧[left,m]区间内，这里m就有可能是旋转点
                right = m;
            } else {
                //中间值跟右侧一样大，无法判断，右侧左移一个位置继续尝试
                right--;
            }
        }

        return nums[left];
    }
}
