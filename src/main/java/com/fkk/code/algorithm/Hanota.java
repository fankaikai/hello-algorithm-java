package com.fkk.code.algorithm;

import com.fkk.code.utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Hanota {

    /**
     * 汉诺塔问题：
     * 给定三根柱子，记为 A、B 和 C 。
     * 起始状态下，柱子 A 上套着个圆盘，它们从上到下按照从小到大的顺序排列。
     * 我们的任务是要把这个圆盘移到柱子 C 上，并保持它们的原有顺序不变（如图 12-10 所示）。
     * 在移动圆盘的过程中，需要遵守以下规则：
     * 1. 圆盘只能从一根柱子顶部拿出，从另一根柱子顶部放入。
     * 2. 每次只能移动一个圆盘。
     * 3. 小圆盘必须时刻位于大圆盘之上。
     */
    public static void solveHanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        dfs(A.size(), A, B, C);
    }

    /**
     * 汉诺塔：将大问题拆解为小问题
     * 1. 只有一个圆盘时，直接移动到目标圆柱即可
     * 2. 有两个圆盘时，先把上边的一个圆盘移动到缓存盘，剩下一个圆盘按照1的方式移动到目标柱子，然后再把缓存的盘子移动到目标柱子
     * 3. 3个及盘子时，上边两个盘子，按照2的方式移动到缓存柱子，按照1把最下面的盘子移动到目标柱子，再把缓存柱子移动到目标柱子
     * 4. 三个以上时，先移动n-1个盘子到缓存柱子，再移动最后一个盘子，然后把缓存的盘子移动到目标柱子。
     * 上述步骤显然用递归解决更好，终止条件是仅剩1个盘子，然后按照4的逻辑开始递归。
     * 每层递归，变化的主要是三个柱子的定位，是作为缓冲柱子，还是目标柱子。
     * <p>
     * 其实很显然，f(n) = f(n-1)+f(1) + f(n-1)，f(1)无法再分，可以想象成一个n层的递归树，每个节点就是一个子问题。
     * 因此时间复杂度就是 O(2^n)
     * 空间复杂度来说，占用了n层的栈帧空间，所以就是O(n)
     *
     * @param n      操作圆盘数量
     * @param src    存有圆盘的柱子
     * @param buffer 柱子
     * @param target 目标柱子
     */
    private static void dfs(int n, List<Integer> src, List<Integer> buffer, List<Integer> target) {
        //终止条件：柱子中只剩一个圆盘时，直接移动到目标柱子
        if (n == 1) {
            move(src, target);
            return;
        }
        //先递归把n-1个元素从初始柱子挪到缓冲柱子
        dfs(n - 1, src, target, buffer);
        //剩余的一个元素，直接挪到目标柱子去
        move(src, target);
        //再把缓存柱子重新挪到目标柱子
        dfs(n - 1, buffer, src, target);
    }

    private static void move(List<Integer> src, List<Integer> target) {
        int element = src.remove(src.size() - 1);
        target.add(element);
    }

    public static void mockHanota() {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>(), list3 = new ArrayList<>();
        for (int i = 10; i >= 0; i--) {
            list1.add(i);
        }
        solveHanota(list1, list2, list3);
        Log.d("Hanota", "A.size=" + list1.size()
                + ", C.size=" + list3.size()
                + ", C==>" + Arrays.toString(list3.toArray())
        );
    }
}
