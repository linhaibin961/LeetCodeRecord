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

}
