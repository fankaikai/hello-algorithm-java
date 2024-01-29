package com.fkk.code.algorithm;

import com.fkk.code.utils.ArrayUtils;
import com.fkk.code.utils.Log;
import com.fkk.code.utils.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

    /**
     * 选择排序
     * 1. 从第一个开始遍历，依次与后面的进行比较
     * 2. 找到最小的元素，挪到当前位置
     * 3. 从第二个位置开始，重复上述操作
     * 4. 最后只剩右侧一个元素时，一定是最大的元素，不必再替换。
     * 时间复杂度O(n²)
     * 空间复杂度O(1)
     */
    public static void selectionSort(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < length; j++) {
                //找到比当前元素小的记录
                if (nums[k] > nums[j]) {
                    k = j;
                }
            }
            //替换
            ArrayUtils.swap(nums, i, k);
        }
    }

    /**
     * 冒泡排序
     * 1. 从左侧开始，每个元素和后一个元素比较，交换大的元素到后面，比较范围 [0,n-1]
     * 2. 一轮循环走完，最大的元素就到了最右侧
     * 3. 开启新一轮循环，重复上述步骤，比较范围[0,n-2]
     * 4. 依次类推，直到左侧只剩下最后一个元素，就是最小值
     * 时间复杂度O(n²)
     * 空间复杂度O(1)
     */
    public static void bubbleSort(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            //内循环负责比较交换元素
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    ArrayUtils.swap(nums, j, j + 1);
                }
            }
        }
    }

    /**
     * 插入排序
     * 原地排序，但是选定一个位置，将数组分为左侧有序数组，右侧无序数组
     * 1. 选择第二个值作为初始base，缓存起来，相当于该位置空了出来
     * 2. 第一个位置初始只有一个元素，天然有序。
     * 3. 左侧有序数组从最右侧开始跟base两两比较，比base大的都向右移动一位
     * 4. 当找到比base小的元素位置j，右侧位置j+1就是目标位置。注意最左端时会出现j=-1，此时j+1就是0
     * 5. 重复以上操作到循环结束，构建有序数组
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     */
    public static void insertSort(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int base = nums[i];
            int j = i - 1;
            //base已经缓存，等于base拿出来空了一个位置，开始两两比较
            while (j >= 0 && nums[j] > base) {
                //比base大的都向右移动一位
                nums[j + 1] = nums[j];
                j--;
            }
            //while循环结束，j停留的位置 + 1，就是base最终的落点
            nums[j + 1] = base;
        }
    }

    /**
     * 快速排序
     * 1. 选取数组最左边元素作为pivot
     * 2. 使用i和j两个指针，分别从左右两端开始遍历，j从右往左找到第一个比p小的元素位置，i从左往右找到第一个比p大的元素位置
     * 2.1 情况1：i和j相遇之前就找到了，则交换元素位置，继续寻找。
     * 2.2 情况2：i和j相遇了，该位置就是分割点，停止循环
     * 3. 交换分割点和最左边元素pivot。此时pivot所在位置就是有序分割点，数组左边元素都比数组右边元素小
     * 4. 将分隔出来的左右两端数组分别重复执行1-3步骤，直到无法分割为止。
     * 这就是快速排序
     * 他的最佳时间复杂度O(n)，最差时间复杂度O(n²)
     * 平均时间复杂度O(nlogn)
     */
    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        //分组定位
        int i = left, j = right;
        while (i < j) {
            //从右往左，找到第一个比pivot小的元素，终止循环
            while (i < j && nums[j] >= nums[left]) {
                j--;
            }

            //从左往右，找到第一个比pivot大的元素，终止循环
            while (i < j && nums[i] <= nums[left]) {
                i++;
            }

            //交换i和j的位置
            ArrayUtils.swap(nums, i, j);
        }
        //循环结束，交换基准元素位置
        ArrayUtils.swap(nums, i, left);
        return i;
    }

    /**
     * 快排，但是优化空间复杂度
     */
    public static void quickSort2(int[] nums, int left, int right) {
        while (left < right) {
            int pivot = partition(nums, left, right);
            //左数组比较小，继续用快排
            if (pivot - left < pivot - right) {
                //先对左数组进行快排
                quickSort(nums, left, pivot - 1);
                //对右数组，再执行一次分组
                left = pivot + 1;
            } else {
                quickSort(nums, pivot + 1, right);
                right = pivot - 1;
            }
        }
    }

    public static void mockQuickSort() {
        int[] nums = new int[]{2, 4, 1, 0, 3, 5};
        Sort.quickSort(nums, 0, nums.length - 1);
        Log.d("QuickSort", "nums1:" + Arrays.toString(nums));

        int[] nums2 = {2, 4, 6, 1, 3, 7, 9, 8, 5};
        Sort.quickSort(nums2, 0, nums2.length - 1);
        Log.d("QuickSort", "nums2:" + Arrays.toString(nums2));
    }

    /**
     * 归并排序
     * 1. 拆：长数组二分递归拆解为单元素数组
     * 2. 归：从底层开始回归，小数组排序合并组成新的有序数组
     * 二叉树h层的2的h次方-1个节点，所以是O(log n)的复杂度，每层合并时是O(n)，综合时间复杂度O(nlogn)
     * <p>
     * 空间复杂度O(n)
     */
    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        //拆分数组
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        mergeArrays(nums, left, mid, right);
    }

    private static void mergeArrays(int[] nums, int left, int mid, int right) {
        //声明合并后的数组
        int[] temp = new int[right - left + 1];
        //合并两个有序数组[left,mid]、[mid+1,right]
        int i = left;
        int j = mid + 1;
        int index = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[index++] = nums[i++];
            } else {
                temp[index++] = nums[j++];
            }
        }
        //遍历剩余数组,理论只会执行其中一个while
        while (i <= mid) {
            temp[index++] = nums[i++];
        }
        while (j <= right) {
            temp[index++] = nums[j++];
        }

        //将合并后的数组重新赋值给nums
//        for (int k = 0; k < temp.length; k++) {
//            nums[left + k] = temp[k];
//        }
        System.arraycopy(temp, 0, nums, left, temp.length);
    }

    public static void mockMergeSort() {
        int[] nums = new int[]{2, 4, 1, 0, 3, 5};
        Sort.mergeSort(nums, 0, nums.length - 1);
        Log.d("MergeSort", "nums1:" + Arrays.toString(nums));

        int[] nums2 = {2, 4, 6, 1, 3, 7, 9, 8, 5};
        Sort.mergeSort(nums2, 0, nums2.length - 1);
        Log.d("MergeSort", "nums2:" + Arrays.toString(nums2));
    }

    /**
     * 堆排序
     * 1. 创建堆，预期结果是升序，使用大顶堆
     * 2. 将堆顶元素与堆尾元素交换，并将数组长度-1
     * 3. 对新的堆进行堆化操作
     * 4. 重复2和3操作，直到数组内可用元素仅剩一个时，此时数据就是有序的。
     * <p>
     * PS：这里尝试创建时使用从底到顶的堆化方式，插入时切换成从顶到底的方式，结果是不可行的
     * 因为两种方式得到的大顶堆，除了堆顶元素外，其他顺序不一致。如果插入的时候选择方式不一致，就会出现数据错乱的情况。
     * PS
     * 堆排序的时间复杂度是O(n log n)
     */
    public static void heapSort(int[] nums) {
        int size = nums.length;
        //创建大顶堆,模拟填充空堆，自上而下进行堆化，siftDown
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(nums, size, i);
        }

        //从堆中提取最大元素，
        for (int i = size - 1; i > 0; i--) {
            //交换堆顶和堆尾元素
            ArrayUtils.swap(nums, 0, i);
            //以根节点为起点，从顶至底进行堆化
            siftDown(nums, i, 0);
        }
    }


    /**
     * 从顶到底的堆化操作：适用于边插入边进行堆化的思路
     *
     * @param i 开始堆化的节点
     * @param n 数组可用长度
     */
    private static void siftDown(int[] nums, int n, int i) {
        while (true) {
            //先计算节点i的左右子节点索引
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int max = i;
            //比较节点i和左右节点，找到最大值
            if (left < n && nums[left] > nums[max]) {
                //左节点可用且比i节点大
                max = left;
            }
            //right节点可用，且跟之前比较出来的最大值，比较谁更大
            if (right < n && nums[right] > nums[max]) {
                max = right;
            }
            //i就是最大值，直接跳出循环
            if (max == i) {
                break;
            }
            //将最大值交换到堆顶
            ArrayUtils.swap(nums, i, max);
            //将i移动到发生交换的子树，开始下一轮堆化。
            i = max;
        }
    }

    /**
     * 尝试从低到顶堆化的方式做堆排序
     * 这个时间复杂度应该更高一些，是O(n²)
     */
    public static void heapSort2(int[] nums) {
        int size = nums.length;
        //创建大顶堆,模拟填充空堆，自下而上进行堆化
        siftUp(nums, size);

        //从堆中提取最大元素，
        for (int i = size - 1; i > 0; i--) {
            //交换堆顶和堆尾元素
            ArrayUtils.swap(nums, 0, i);
            //以根节点为起点，从顶至底进行堆化
            siftUp(nums, i);
        }
    }


    private static void siftUp(int[] nums, int n) {
        //找到最后一个非叶子节点
        int pivot = n / 2 - 1;

        while (pivot >= 0) {
            int left = 2 * pivot + 1;
            int right = 2 * pivot + 2;
            int max = pivot;
            if (left < n && nums[left] > nums[max]) {
                max = left;
            }
            if (right < n && nums[right] > nums[max]) {
                max = right;
            }
            //当前非叶子节点不是最大值，跟最大值进行交换
            if (pivot != max) {
                ArrayUtils.swap(nums, pivot, max);
            }
            //指向前一个非叶子节点，开启下一轮堆化
            pivot--;
        }
    }

    public static void mockHeapSort() {
        int[] nums = new int[]{3, 7, 16, 10, 13, 5};
        Sort.heapSort(nums);
        Log.d("HeapSort", "自上而下堆化：nums1:" + Arrays.toString(nums));

        int[] nums2 = {2, 14, 6, 21, 3, 17, 9, 8, 5};
        Sort.heapSort(nums2);
        Log.d("HeapSort", "自上而下堆化：nums2:" + Arrays.toString(nums2));

        int[] nums3 = new int[]{3, 7, 16, 10, 13, 5};
        Sort.heapSort2(nums3);
        Log.d("HeapSort", "自下而上堆化：nums1:" + Arrays.toString(nums3));

        int[] nums4 = {2, 14, 6, 21, 3, 17, 9, 8, 5};
        Sort.heapSort2(nums4);
        Log.d("HeapSort", "自下而上堆化：nums2:" + Arrays.toString(nums4));
    }

    /**
     * 桶排序
     * 1. 将元素平均分配到N个桶里
     * 2. 对N个桶进行Collections.sort排序
     * 3. 合并桶，输出到数组内，得到有序浮点数组。
     * 问题：如何选择桶的数量，使数据均匀分配呢？
     *
     */
    public static void bucketsSort(float[] nums) {
        //桶数量，数组长度一半
        int k = nums.length / 2;
        List<List<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            //构建桶
            buckets.add(new ArrayList<>());
        }
        for (float num : nums) {
            //计算命中桶索引
            int i = (int) num * k;
            //填充元素
            buckets.get(i).add(num);
        }
        //桶排序
        for (List<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        //合并, 其实这一步可以跟桶排序合并。
        int n = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                nums[n++] = num;
            }
        }

    }

    public static void mockBucketSort() {
        float[] nums = new float[]{0.49f, 0.96f, 0.82f, 0.09f, 0.57f, 0.43f, 0.91f, 0.75f, 0.15f, 0.37f};
        Sort.bucketsSort(nums);
        Log.d("bucketsSort", "nums:" + Arrays.toString(nums));
    }


    /**
     * 计数排序
     * 1. 先遍历nums数组找到最大数max
     * 2. 然后创建一个 长度为max+1的数组counter，这就意味着这个数组很大，nums中随便一个数字当做counter下标都不会越界。
     * 3. 遍历nums，统计每个元素出现的次数，并把元素作为下标放到counter中，counter下标天然有序。
     */
    public static void countingSort(int[] nums) {
        int max = -1;
        for (int num : nums) {
//            max = Math.max(num, max);
            if (num > max) {
                max = num;
            }
        }
        //防止空数组
        if (max == -1) {
            return;
        }

        int[] counter = new int[max + 1];
        for (int num : nums) {
            counter[num] = counter[num] + 1;
        }

        //输出counter
        int i = 0;
        for (int num = 0; num < counter.length; num++) {
            int count = counter[num];
            if (count > 0) {
                for (int j = 0; j < count; j++) {
                    nums[i++] = num;
                }
            }
        }

    }

    /**
     * 计数排序稳定版
     * 什么是稳定版？
     * 举个例子：假如数组中是对象，我希望实现排序后也能保持稳定，也就是输入数组中重复对象，原来排在前面的，形成有序数组后，该对象依然保持在前面。
     * 实现思路
     * 1. 选取最大值
     * 2. 构建counter数组
     * 3. counter数组转化成前缀和数组
     * 4. 构建临时有序数组res，前缀和数组中的元素可以用于定位res中的索引
     * 5. 倒序遍历num，结合前缀和数组，将元素有序的放入res中，并保持重复元素在原数组nums中的顺序
     * 6. 复制res数组给nums，实现稳定计数排序
     * <p>
     * 时间复杂度：O(n+max) 趋近于 O(n)
     * 空间复杂度：O(n+n+max)，记作O(n)
     */
    public static void countingSort2(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        int[] counter = new int[max + 1];
        //统计元素出现次数
        for (int num : nums) {
            counter[num] = counter[num] + 1;
        }

        //counter转换成前缀和数组
        for (int i = 0; i < counter.length - 1; i++) {
            counter[i + 1] += counter[i];
        }


        //生成有序数组，根据prefixSum确定元素索引,对于nums中的元素来说，counter(num) - 1 就是该重复元素出现的最后一个位置的索引下标
        int[] temp = new int[nums.length];

        //counter[nums]每拿一个元素，都要count--，最早取的索引肯定更大，也就适合通过倒序去取nums中的元素，放在重复元素靠后的位置
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            //计算该元素在有序数组中的下标
            int index = counter[num] - 1;
            //只要计算不出错，这里不应该会出现数组越界
            temp[index] = num;
            //前缀和自减1，用于下次计算索引
            counter[num]--;
        }

        //此时temp应该就是一个经过稳定排序的有序数组了。复制给nums
        System.arraycopy(temp, 0, nums, 0, temp.length);
    }

    public static void mockCountingSort() {
        int[] nums = new int[]{1, 0, 9, 1, 3, 4, 2, 3, 9, 0, 2, 5, 2, 6, 5, 1, 2, 3, 1,};
        Sort.countingSort(nums);
        Log.d("CountingSort", "简单版 nums:" + Arrays.toString(nums));

        int[] nums2 = new int[]{1, 0, 9, 1, 3, 4, 2, 3, 9, 0, 2, 5, 2, 6, 5, 1, 2, 3, 1,};
        Sort.countingSort2(nums2);
        Log.d("CountingSort", "稳定版 nums:" + Arrays.toString(nums2));
    }

    /**
     * 基数排序：
     * 计数排序的一个延伸，对于一组长数字比如学位号、身份证号等，单看每一位数字对比，可以使用计数排序。
     * 对所有位数依次进行排序后，整组长数字就是有序的了。
     * PS：
     * 为什么从最低位开始排序？因为后一轮的结果会覆盖前一轮的。
     * <p>
     * 基数排序的时间复杂度是：O(nk)，其中k是最大位数，对于d进制来说，时间复杂度是O((n+d)*k),趋近于O(n)
     * 如果k过大会导致时间复杂度超过O(n²)
     * <p>
     * 空间复杂度也是O(n + d)，一次循环占用的空间就是n+d
     */
    public static void radixSort(int[] nums) {
        int max = Integer.MIN_VALUE;
        //用于判断最大位数
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        //从个位开始
        for (int exp = 1; exp < max; exp *= 10) {
            //对指定位数进行计数排序
            countingSortDigit(nums, exp);
            Log.d("RadixSort", "exp=" + exp + ",排序结果" + Arrays.toString(nums));
        }

    }

    public static void countingSortDigit(int[] nums, int exp) {
        //这里counter范围是确定的0~9,所以max是确定的
        int[] counter = new int[10];

        //改造counter数组索引计算方式
        for (int num : nums) {
            int d = NumberUtils.digit(num, exp);
            counter[d]++;
        }

        //前缀和数组
        for (int i = 0; i < counter.length - 1; i++) {
            counter[i + 1] += counter[i];
        }

        //构建有序数组
        int[] temp = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            int d = NumberUtils.digit(num, exp);
            int index = counter[d] - 1;
            temp[index] = num;
            counter[d]--;
        }

        System.arraycopy(temp, 0, nums, 0, temp.length);

    }

    public static void mockRadixSort() {
        int[] nums = new int[]{10546151, 35663510, 42865989, 34862665, 81889223, 88990211, 72342113, 82050337, 63832996};
        Sort.radixSort(nums);
        Log.d("RadixSort", "final result:" + Arrays.toString(nums));
    }


    public static void mockSort() {
//        mockQuickSort();
//        mockMergeSort();
//        mockHeapSort();
//        mockBucketSort();
//        mockCountingSort();
        mockRadixSort();
    }


}
