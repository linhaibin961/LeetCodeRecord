package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @program: LeetCodeRecord
 * @description: 698. 划分为k个相等的⼦集（中等）
 * @author: linhaibin
 * @create: 2022-04-20 15:03
 **/
public class _698PartitionToKEqualSumSubsets {
    /**
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
     * 输出： True
     * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
     * 示例 2:
     * <p>
     * 输入: nums = [1,2,3,4], k = 3
     * 输出: false
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= k <= len(nums) <= 16
     * 0 < nums[i] < 10000
     * 每个元素的频率在 [1,4] 范围内
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        System.out.println(JSON.toJSONString(nums));
//        System.out.println(JSON.toJSONString(solution.canPartitionKSubsets(nums, 4)));

        int[] nums2 = new int[]{1, 2, 3, 4};
        System.out.println(JSON.toJSONString(nums2));
        System.out.println(JSON.toJSONString(solution.canPartitionKSubsets(nums2, 3)));

        int[] nums3 = new int[]{2, 2, 2, 2, 3, 4, 5};
        System.out.println(JSON.toJSONString(nums3));
        System.out.println(JSON.toJSONString(solution.canPartitionKSubsets(nums3, 4)));


    }

    static class Solution {

        public boolean canPartitionKSubsets(int[] nums, int k) {
            int totalSum = Arrays.stream(nums).sum();
            if (totalSum % k != 0) {
                return false;
            }
            int target = totalSum / k;
            System.out.println("totalSum=" + totalSum + ",target=" + target);
            /* 降序排序 nums 数组 */
            Arrays.sort(nums);
            for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
                // 交换 nums[i] 和 nums[j]
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                if (nums[i] > target || nums[j] > target) {
                    return false;
                }
            }
            boolean[] used = new boolean[nums.length];
            int[] bucket = new int[k];
            return backtrack(nums, 0, used, bucket, target);
        }

        private boolean backtrack(int[] nums, int index, boolean[] used, int[] bucket, int target) {
            if (index == nums.length) {
                // 检查所有桶的数字之和是否都是 target
                for (int i = 0; i < bucket.length; i++) {
                    if (bucket[i] != target) {
                        return false;
                    }
                }
                // nums 成功平分成 k 个⼦集
                return true;
            }
            for (int i = 0; i < bucket.length; i++) {
                if (used[index]) {
                    continue;
                }
                if (bucket[i] + nums[index] > target) {
                    continue;
                }
                bucket[i] += nums[index];
                used[i] = true;
                if (backtrack(nums, index + 1, used, bucket, target)) {
                    return true;
                }
                bucket[i] -= nums[index];
                used[i] = false;
            }
            return false;
        }
    }

    /**
     * labuladong解法二，以桶的视角维度
     */
    static class Solution2 {
        public boolean canPartitionKSubsets(int[] nums, int k) {
            // 排除一些基本情况
            if (k > nums.length) return false;
            int sum = 0;
            for (int v : nums) sum += v;
            if (sum % k != 0) return false;

            int used = 0; // 使用位图技巧
            int target = sum / k;
            // k 号桶初始什么都没装，从 nums[0] 开始做选择
            return backtrack(k, 0, nums, 0, used, target);
        }

        HashMap<Integer, Boolean> memo = new HashMap<>();

        boolean backtrack(int k, int bucket,
                          int[] nums, int start, int used, int target) {
            // base case
            if (k == 0) {
                // 所有桶都被装满了，而且 nums 一定全部用完了
                return true;
            }
            if (bucket == target) {
                // 装满了当前桶，递归穷举下一个桶的选择
                // 让下一个桶从 nums[0] 开始选数字
                boolean res = backtrack(k - 1, 0, nums, 0, used, target);
                // 缓存结果
                memo.put(used, res);
                return res;
            }

            if (memo.containsKey(used)) {
                // 避免冗余计算
                return memo.get(used);
            }

            for (int i = start; i < nums.length; i++) {
                // 剪枝
                if (((used >> i) & 1) == 1) { // 判断第 i 位是否是 1
                    // nums[i] 已经被装入别的桶中
                    continue;
                }
                if (nums[i] + bucket > target) {
                    continue;
                }
                // 做选择
                used |= 1 << i; // 将第 i 位置为 1
                bucket += nums[i];
                // 递归穷举下一个数字是否装入当前桶
                if (backtrack(k, bucket, nums, i + 1, used, target)) {
                    return true;
                }
                // 撤销选择
                used ^= 1 << i; // 使用异或运算将第 i 位恢复 0
                bucket -= nums[i];
            }

            return false;
        }

    }
}
