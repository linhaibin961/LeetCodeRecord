package com.lhb.sort;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 912. 排序数组（中等）
 * @author: linhaibin
 * @create: 2022-4-17 20:50:21
 **/
public class _912SortAnArray {

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};
        Solution solution = new Solution();
        int[] sortArray = solution.sortArray(nums);
        System.out.println(Arrays.toString(sortArray));
    }

    /**
     * 给你一个整数数组 nums，请你将该数组升序排列。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [5,2,3,1]
     * 输出：[1,2,3,5]
     * 示例 2：
     * <p>
     * 输入：nums = [5,1,1,2,0,0]
     * 输出：[0,0,1,1,2,5]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 5 * 104
     * -5 * 104 <= nums[i] <= 5 * 104
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class Solution {
        // ⽤于辅助合并有序数组
        private static int[] temp;

        /**
         * 归并排序
         *
         * @param nums
         * @return
         */
        public int[] sortArray(int[] nums) {
            temp = new int[nums.length];
            sort(nums, 0, nums.length - 1);
            return nums;
        }

        private void sort(int[] nums, int start, int end) {
            if (start >= end) {
                return;
            }
            // 算中间位置
            int mid = start + (end - start) / 2;
            // 左边进行排序
            sort(nums, start, mid);
            // 右边进行排序
            sort(nums, mid + 1, end);

            merge(nums, start, mid, end);
        }

        private void merge(int[] nums, int start, int mid, int end) {
            // 先把 nums[start..end] 复制到辅助数组中
            // 以便合并后的结果能够直接存⼊ nums
            for (int i = start; i <= end; i++) {
                temp[i] = nums[i];
            }
            // 数组双指针技巧，合并两个有序数组
            int i = start, j = mid + 1;
            for (int p = start; p <= end; p++) {
                if (i == mid + 1) {
                    // 左半边数组已全部被合并
                    nums[p] = temp[j++];
                } else if (j == end + 1) {
                    // 右半边数组已全部被合并
                    nums[p] = temp[i++];
                } else if (temp[i] > temp[j]) {
                    // 双指针合并两个有序列表
                    nums[p] = temp[j++];
                } else {
                    nums[p] = temp[i++];
                }
            }
        }
    }

    /**
     * 快速排序解法
     */
    static class Solution2 {

        public static void sort(int[] nums) {
            // 为了避免出现耗时的极端情况，先随机打乱
            shuffle(nums);
            // 排序整个数组（原地修改）
            sort(nums, 0, nums.length - 1);
        }

        private static void sort(int[] nums, int lo, int hi) {
            if (lo >= hi) {
                return;
            }
            // 对 nums[lo..hi] 进行切分
            // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
            int p = partition(nums, lo, hi);

            sort(nums, lo, p - 1);
            sort(nums, p + 1, hi);
        }

        // 对 nums[lo..hi] 进行切分
        private static int partition(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            // 关于区间的边界控制需格外小心，稍有不慎就会出错
            // 我这里把 i, j 定义为开区间，同时定义：
            // [lo, i) <= pivot；(j, hi] > pivot
            // 之后都要正确维护这个边界区间的定义
            int i = lo + 1, j = hi;
            // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
            while (i <= j) {
                while (i < hi && nums[i] <= pivot) {
                    i++;
                    // 此 while 结束时恰好 nums[i] > pivot
                }
                while (j > lo && nums[j] > pivot) {
                    j--;
                    // 此 while 结束时恰好 nums[j] <= pivot
                }
                // 此时 [lo, i) <= pivot && (j, hi] > pivot

                if (i >= j) {
                    break;
                }
                swap(nums, i, j);
            }
            // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
            swap(nums, lo, j);
            return j;
        }

        // 洗牌算法，将输入的数组随机打乱
        private static void shuffle(int[] nums) {
            Random rand = new Random();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 生成 [i, n - 1] 的随机数
                int r = i + rand.nextInt(n - i);
                swap(nums, i, r);
            }
        }

        // 原地交换数组中的两个元素
        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
