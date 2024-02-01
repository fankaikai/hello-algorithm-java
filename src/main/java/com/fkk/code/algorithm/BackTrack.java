package com.fkk.code.algorithm;

import com.fkk.code.utils.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 回溯
 */
public class BackTrack {


    /**
     * 输入一个整数数组，其中不包含重复元素，返回所有可能的排列。
     */
    public static List<List<Integer>> permutationsI(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(new ArrayList<>(), nums, new boolean[nums.length], res);
        return res;
    }

    /**
     * 全排列：
     * <p>
     * duplicated和selected的区别：
     * 1. selected全局使用一个数组，是为了去重防止state重复添加元素
     * 2. duplicated 则是为了保证在每轮的遍历中相等的元素不会被重复拿来比较
     *
     * @param state    状态管理，记录解
     * @param choices  元素集合
     * @param selected 已选择
     * @param res      解集合
     */
    private static void backtrack(List<Integer> state, int[] choices, boolean[] selected, List<List<Integer>> res) {
        //达到原数组长度后，记录解
        if (state.size() == choices.length) {
            res.add(new ArrayList<>(state));
            return;
        }


        //如果有重复元素，可以通过hash表记录下来，提前进行剪枝
        HashSet<Integer> duplicated = new HashSet<>();

        //遍历所有选择
        for (int i = 0; i < choices.length; i++) {
            int n = choices[i];
            //剪枝：找到未使用过的元素
            if (!selected[i] && !duplicated.contains(n)) {
                duplicated.add(n);
                //状态更新
                selected[i] = true;
                state.add(n);
                //递归开始下一轮选择
                backtrack(state, choices, selected, res);
                //撤销状态
                selected[i] = false;
                state.remove(state.size() - 1);
            }
        }

    }

    /**
     * 给定一个正整数数组 nums 和一个目标正整数 target ，请找出所有可能的组合，使得组合中的元素和等于 target 。
     * 给定数组无重复元素，每个元素可以被选取多次。请以列表形式返回这些组合，列表中不应包含重复组合
     */
    public static List<List<Integer>> subsetSumINaive(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack2(new ArrayList<>(), target, 0, nums, res);
        return res;
    }

    /**
     * 这种方式会产生重复集合[4,5][5,4]这种
     */
    private static void backtrack2(List<Integer> state, int target, int total, int[] choices, List<List<Integer>> res) {
        if (total == target) {
            res.add(new ArrayList<>(state));
            return;
        }
        //遍历所有选择
        for (int choose : choices) {
            //超出预期，直接剪枝
            if (total + choose > target) {
                continue;
            }
            //状态更新
            state.add(choose);
            //递归开始下一轮选择
            backtrack2(state, target, total + choose, choices, res);
            //撤销状态
            state.remove(state.size() - 1);
        }

    }


    public static List<List<Integer>> subsetSumINaive2(int[] nums, int target) {
        List<Integer> state = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;//遍历起始点
        Arrays.sort(nums);
        backtrack3(state, target, nums, start, res);
        return res;
    }


    /**
     * backtrack2 的优化版
     * 优化点1：省略total，通过对target做减法来判断是否有解
     * 优化点2：choices排序后再进入递归循环
     * 优化点3：记录遍历起始点，减掉重复路径，原因：
     * backtrack2的写法是递归每一层遍历时，nums数组都要全遍历一遍。
     * 比如i=2时，递归进入下一轮遍历，nums还是要从0开始的话，2-0和2-1都已经在0-1、1-2的前置遍历中执行过了。
     * 因此只需要通过遍历起始点继续往后遍历即可。
     */
    private static void backtrack3(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        //剪枝：从start开始遍历，避免生成重复子集
        for (int i = start; i < choices.length; i++) {
            int choose = choices[i];
            //剪枝：超过target的分支不再继续求解
            if (target - choose < 0) {
                break;
            }
            //状态更新
            state.add(choose);
            //递归开始下一轮选择,start 选择当前索引
            backtrack3(state, target - choose, choices, i, res);
            //撤销状态
            state.remove(state.size() - 1);
        }
    }


    /**
     * 给定一个正整数数组 nums 和一个目标正整数 target ，请找出所有可能的组合，使得组合中的元素和等于 target 。
     * 给定数组可能包含重复元素，每个元素只可被选择一次。请以列表形式返回这些组合，列表中不应包含重复组合。
     * <p>
     * PS:
     * 这题与上面不一样的是：
     * 1. 数组中有重复元素
     * 2. 同一个元素不能被多次选择
     * 3. 求解中不能有重复组合
     */
    public static List<List<Integer>> subsetSumII(int[] nums, int target) {
        List<Integer> state = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;//遍历起始点
        Arrays.sort(nums);
        backtrack4(state, target, nums, start, res);
        return res;
    }

    private static void backtrack4(List<Integer> state, int target, int[] choices, int start, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(state));
            return;
        }
        //剪枝：同一个元素不能重复选择，所以要从start开始
        for (int i = start; i < choices.length; i++) {
            int choose = choices[i];
            //剪枝：超过target的分支不再继续求解
            if (target - choose < 0) {
                break;
            }

            //剪枝：元素与前一个重复，剪除重复分支。
            //这个元素还没有被选择，所以如果使用i>0,那这个元素与前一个重复的情况也会被检出。
            //这一题应该有点问题，因为结论是要求你不要有重复元素。
            if (i > 3 && choices[i - 1] == choose) {
                continue;
            }
            //状态更新
            state.add(choose);
            //递归开始下一轮选择,start 选择当前索引
            backtrack4(state, target - choose, choices, i + 1, res);
            //撤销状态
            state.remove(state.size() - 1);
        }
    }

    /**
     * n皇后问题:
     * 根据国际象棋的规则，皇后可以攻击与同处一行、一列或一条斜线上的棋子。
     * 给定 n 个皇后和一个 nxn 大小的棋盘，寻找使得所有皇后之间无法相互攻击的摆放方案。
     * <p>
     * 每一种解法就是一个棋盘摆法，是List<List> 代表m行k列
     * 所有解法输出也就是 List<List<List>>>
     * <p>
     * 时间复杂度：每层可选n*(n-1)*(n-2)...2*1，所以是O(n!)，因为有剪枝操作，所以实际优于该复杂度
     * 空间复杂度O(n²)
     */
    public static List<List<List<String>>> nQueens(int n) {
        //初始化nxn大小的棋盘
        List<List<String>> state = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> column = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                column.add("#");
            }
            state.add(column);
        }
        boolean[] columns = new boolean[n];
        boolean[] diags1 = new boolean[2 * n - 1];
        boolean[] diags2 = new boolean[2 * n - 1];
        List<List<List<String>>> res = new ArrayList<>();
        backtrack5(0, n, state, res, columns, diags1, diags2);
        return res;
    }


    private static void backtrack5(int row, int n, List<List<String>> state, List<List<List<String>>> res, boolean[] columns, boolean[] diags1, boolean[] diags2) {
        //终止条件：放完所有行数
        if (row == n) {
            List<List<String>> tempState = new ArrayList<>();
            for (List<String> tempRow : state) {
                tempState.add(new ArrayList<>(tempRow));
            }
            res.add(tempState);
            return;
        }

        //遍历列
        for (int column = 0; column < n; column++) {
            //计算主对角线和次对角线
            //主对角线的取值范围是[-n+1,n-1]之间，这里要计算索引不能使用负整数，所以使用n-1保证索引从0开始。
            int d1 = row - column + n - 1;
            int d2 = row + column;
            //剪枝: 所在列、所在主次对角线不能存在皇后
            if (!columns[column] && !diags1[d1] && !diags2[d2]) {
                //更新状态: 放置Q
                state.get(row).set(column, "Q");
                //当前列、主对角线、次对角线 更新为占据状态
                columns[column] = diags1[d1] = diags2[d2] = true;

                //递归
                backtrack5(row + 1, n, state, res, columns, diags1, diags2);

                //释放状态
                state.get(row).set(column, "#");
                columns[column] = diags1[d1] = diags2[d2] = false;
            }
        }

    }

    public static void mockNQueens() {
        int n = 4;
        Log.d("BackTrack", "N皇后问题，n = " + n);
        List<List<List<String>>> nQueens = BackTrack.nQueens(n);
        for (int i = 0; i < nQueens.size(); i++) {
            List<List<String>> queen = nQueens.get(i);
            int num = i + 1;
            Log.d("BackTrack", "N皇后问题 解法" + num + " ===>");
            for (List<String> row : queen) {
                Log.d("BackTrack", Arrays.toString(row.toArray()));
            }
            Log.d("BackTrack", "解法" + num + " ===> END ");
        }
    }


    public static void mockBackTrack() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> lists = BackTrack.permutationsI(nums);
        Log.d("BackTrack", "整数数组的全部可能排列===>");
        for (List<Integer> list : lists) {
            Log.d("BackTrack", Arrays.toString(list.toArray()));
        }

        int[] nums2 = new int[]{3, 4, 5};
        List<List<Integer>> targetList = BackTrack.subsetSumINaive(nums2, 9);
        Log.d("BackTrack", "正整数数组元素之和等于target的可能组合===>");
        Log.d("BackTrack", "未去重版本：");
        for (List<Integer> list : targetList) {
            Log.d("BackTrack", Arrays.toString(list.toArray()));
        }

        int[] nums3 = new int[]{3, 4, 5};
        List<List<Integer>> targetList2 = BackTrack.subsetSumINaive2(nums3, 9);
        Log.d("BackTrack", "去重版本：");
        for (List<Integer> list : targetList2) {
            Log.d("BackTrack", Arrays.toString(list.toArray()));
        }

        int[] nums4 = new int[]{1, 3, 3, 4, 3, 5};
        List<List<Integer>> targetList4 = BackTrack.subsetSumII(nums4, 9);
        Log.d("BackTrack", "数组元素重复，输出不重复：");
        for (List<Integer> list : targetList4) {
            Log.d("BackTrack", Arrays.toString(list.toArray()));
        }

        mockNQueens();
    }

}
