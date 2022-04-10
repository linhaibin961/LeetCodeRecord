package com.lhb.binarySearch;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 704. ⼆分查找（简单）
 * @author: linhaibin
 * @create: 2022-04-09 22:43
 **/
public class _34FindFirstAndLastPositionOfElementInSortedArray {

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * <p>
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     * <p>
     * 进阶：
     * <p>
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     * <p>
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     * <p>
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= nums.length <= 105
     * -109 <= nums[i] <= 109
     * nums 是一个非递减数组
     * -109 <= target <= 109
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        int[] ints = new int[]{5, 7, 7, 8, 8, 10};

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.searchRange(ints, 8)));

    }


    static class Solution {
        public int[] searchRange(int[] nums, int target) {

            int leftBound = leftBound(nums, target);
            int rightBound = rightBound(nums, target);
            return new int[]{leftBound, rightBound};
        }

        public int binarySearch(int[] nums, int target) {
            int length = nums.length - 1;
            int left = 0, right = length;
            int mid = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] == target) {
                    return mid;
                }
            }
            return -1;
        }

        public int leftBound(int[] nums, int target) {
            int length = nums.length - 1;
            int left = 0, right = length;
            int mid = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] == target) {
                    right = mid - 1;
                }
            }
            if (left > length || nums[left] != target) {
                return -1;
            }
            return left;
        }

        public int rightBound(int[] nums, int target) {
            int length = nums.length - 1;
            int left = 0, right = length;
            int mid = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] == target) {
                    left = mid + 1;
                }
            }
            if (right < 0 || nums[right] != target) {
                return -1;
            }
            return right;
        }
    }

}
