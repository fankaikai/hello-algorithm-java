package com.fkk.code.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 螺旋矩阵
 */
public class LC54 {


    /**
     * 顺时针遍历矩阵，方向就是 right --> down --> left --> up
     * 四个指针，记录行遍历过的最大和最小值，列遍历过的最大和最小值
     * <p>
     * Direction: right=0,down=1,left=2,up=3
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new ArrayList<>();
        }

        //四个边界
        int left = 0, right = matrix.length - 1, top = 0, bottom = matrix[0].length - 1;
        //创建相等容量数组
        Integer[] res = new Integer[(right + 1) * (bottom + 1)];
        int x = 0;

        while (true) {
            //left 2 right
            for (int i = left; i <= right; i++) {
                res[x++] = matrix[top][i];
            }

            //从左往右执行完毕，准备执行上到下，检查下一行是否越界
            if (++top > bottom) break;

            //top 2 bottom
            for (int i = top; i <= bottom; i++) {
                res[x++] = matrix[i][right];
            }

            //执行完毕上到下，准备执行右到左，校验往左一行是否越界
            if (left > --right) break;

            //right 2 left
            for (int i = right; i >= left; i--) {
                res[x++] = matrix[bottom][i];
            }

            //右到左直行完毕，准备执行下到上，检查上移动一行是否越界
            if (top > --bottom) break;

            //bottom 2 top
            for (int i = bottom; i >= top; i--) {
                res[x++] = matrix[i][left];
            }
            //下到上执行完毕，准备再次执行左到右，检查向右移动一行是否越界
            if (++left > right) break;

        }

        return Arrays.asList(res);

    }
}
