package com.lhb;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: LeetCodeRecord
 * @description: 560. 和为 K 的子数组
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 * @see https://leetcode-cn.com/problems/subarray-sum-equals-k
 **/
public class _560SubarraySumEqualsK {
    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,1], k = 2
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3], k = 3
     * 输出：2
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 2 * 104
     * -1000 <= nums[i] <= 1000
     * -107 <= k <= 107
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subarraySum2(new int[]{1, 1, 1}, 2));
        System.out.println(solution.subarraySum2(new int[]{1, 2, 3}, 3));
    }

    static class Solution {
        /**
         * 两层遍历循环，暴力处理
         * 可以理解为找到所有的子数组，每一个子数组算出和与k比较。
         */
        int subarraySum(int[] nums, int k) {
            int length = nums.length;
            int count = 0;
            // 第一个 for 循环可以理解为
            for (int left = 0; left < length; left++) {
                int sum = 0;
                for (int right = left; right < length; right++) {
                    sum += nums[right];
                    if (sum == k) {
                        count++;
                    }
                }
            }
            return count;
        }

        public int subarraySum2(int[] nums, int k) {
            int length = nums.length;
            int count = 0;
            Map<Integer, Integer> map = new HashMap();
            map.put(0, 1);
            int sum = 0;
            for (int i = 0; i < length; i++) {
                sum += nums[i];
                int rangeJ = sum - k;
                if (map.containsKey(rangeJ)) {
                    count += map.get(rangeJ);
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }
}
