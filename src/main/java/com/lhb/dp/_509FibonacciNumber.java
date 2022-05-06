package com.lhb.dp;

import com.alibaba.fastjson.JSON;

/**
 * @program: LeetCodeRecord
 * @description: 509. 斐波那契数（简单）
 * @author: linhaibin
 * @create: 2022-04-21 01:03
 **/
public class _509FibonacciNumber {
    /**
     * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     * <p>
     * F(0) = 0，F(1) = 1
     * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * 给定 n ，请计算 F(n) 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 2
     * 输出：1
     * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
     * 示例 2：
     * <p>
     * 输入：n = 3
     * 输出：2
     * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
     * 示例 3：
     * <p>
     * 输入：n = 4
     * 输出：3
     * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= n <= 30
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/fibonacci-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.fib(4)));

        Solution2 solution2 = new Solution2();
        System.out.println(JSON.toJSONString(solution2.fib(4)));

        Solution3 solution3 = new Solution3();
        System.out.println(JSON.toJSONString(solution3.fib(4)));


    }

    /**
     * 1、暴⼒递归
     * 正常的解法，暴力递归，低效
     */
    static class Solution {

        public int fib(int n) {
            if (n == 1 || n == 2) {
                return 1;
            }
            return fib(n - 1) + fib(n - 2);

        }

    }

    /**
     * 2、带备忘录的递归解法
     * 备忘录解法，效率和动态规划一样了。
     * 不过不算动态规划
     */
    static class Solution2 {

        public int fib(int n) {
            int[] mem = new int[n + 1];
            return helper(n, mem);
        }
        private int helper(int n, int[] mem) {
            if (mem[n] != 0) {
                return mem[n];
            }
            if (n == 1 || n == 2) {
                return 1;
            }
            mem[n - 1] = helper(n - 1, mem);
            mem[n - 2] = helper(n - 2, mem);
            mem[n] = mem[n - 1] + mem[n - 2];
            return mem[n];
        }
    }

    static class Solution3 {

        /**
         * 动态规划
         *
         * @param n
         * @return
         */
        public int fib(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];

        }

        /**
         * 动态规划优化
         * 空间复杂度为O(1)
         *
         * @param n
         * @return
         */
        int fib2(int n) {
            if (n == 0 || n == 1) {
                // base case
                return n;
            }
            // 分别代表 dp[i - 1] 和 dp[i - 2]
            int dp_i_1 = 1, dp_i_2 = 0;
            for (int i = 2; i <= n; i++) {
                // dp[i] = dp[i - 1] + dp[i - 2];
                int dp_i = dp_i_1 + dp_i_2;
                // 滚动更新
                dp_i_2 = dp_i_1;
                dp_i_1 = dp_i;
            }
            return dp_i_1;
        }
    }

}
