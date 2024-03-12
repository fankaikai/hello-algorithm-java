package com.fkk.code.jz;

/**
 * 矩阵中的路径
 */
public class JZ12 {

    public boolean hasPath(char[][] matrix, String word) {
        //先对字符串进行拆分
        char[] words = word.toCharArray();
        //双重循环，挨个查看
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (dfs(matrix, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] matrix, char[] words, int i, int j, int index) {
        //先判断终止条件：超出数组范围、不符合查找条件
        if (i < 0 || i >= matrix.length
                || j < 0 || j >= matrix[0].length
                || words[index] != matrix[i][j]) {
            return false;
        }

        //如果找全了
        if (index == words.length - 1) {
            return true;
        }

        //标记路径
        char temp = matrix[i][j];
        matrix[i][j] = '#';

        //上下左右四个方向寻找
        boolean res = dfs(matrix, words, i - 1, j, index + 1)
                || dfs(matrix, words, i + 1, j, index + 1)
                || dfs(matrix, words, i, j - 1, index + 1)
                || dfs(matrix, words, i, j + 1, index + 1);
        //回退代码
        matrix[i][j] = temp;
        return res;
    }
}
