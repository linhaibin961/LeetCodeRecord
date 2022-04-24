package com.lhb.dp;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 53. 最大子数组和（简单）
 * @author: linhaibin
 * @create: 2022-04-22 01:03
 **/
public class _53MaximumSubarray {
    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * <p>
     * 子数组 是数组中的一个连续部分。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     * 示例 2：
     * <p>
     * 输入：nums = [1]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：nums = [5,4,-1,7,8]
     * 输出：23
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(JSON.toJSONString(solution.maxSubArray(coins)));


    }

    /**
     * 动态规划
     */
    static class Solution {

        public int maxSubArray(int[] nums) {
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            }
            int res = Integer.MIN_VALUE;
            for (int i : dp) {
                res = Math.max(i, res);
            }
            return res;
        }
    }

}
