package com.lhb.dp;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: LeetCodeRecord
 * @description: 39. 组合总和（中等）
 * @author: linhaibin
 * @create: 2022-04-21 01:03
 **/
public class _322CoinChange {
    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * <p>
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * <p>
     * 你可以认为每种硬币的数量是无限的。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     * 示例 2：
     * <p>
     * 输入：coins = [2], amount = 3
     * 输出：-1
     * 示例 3：
     * <p>
     * 输入：coins = [1], amount = 0
     * 输出：0
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 231 - 1
     * 0 <= amount <= 104
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/coin-change
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int target = 11;
        int[] coins = new int[]{1, 2, 5};
        System.out.println(JSON.toJSONString(solution.coinChange(coins, target)));


        Solution1 solution1 = new Solution1();
        System.out.println(JSON.toJSONString(solution1.coinChange(coins, target)));

        Solution2 solution2 = new Solution2();
        System.out.println(JSON.toJSONString(solution2.coinChange(coins, target)));

        /**
         * 递推动态规划
         */
        Solution3 solution3 = new Solution3();
        System.out.println(JSON.toJSONString(solution3.coinChange(coins, target)));


    }

    /**
     * 1、bfs 暴⼒递归
     * 正常的解法，暴力递归，低效
     */
    static class Solution {

        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            Arrays.sort(coins);
            Queue<Integer> queue = new LinkedList<>();
            for (int coin : coins) {
                if (coin > amount) {
                    continue;
                }
                queue.add(coin);
            }
            int count = 1;
            boolean[] visited = new boolean[amount + 1];
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    if (visited[cur]) {
                        continue;
                    }
                    if (cur == amount) {
                        return count;
                    }
                    visited[cur] = true;
                    for (int coin : coins) {
                        if (cur + coin > amount) {
                            continue;
                        }
                        queue.offer(coin + cur);
                    }

                }
                count++;
            }
            return -1;

        }

    }

    /**
     * 1、暴⼒递归
     * 自顶向下动态规划解法
     */
    static class Solution1 {
        int coinChange(int[] coins, int amount) {
            // 题⽬要求的最终结果是 dp(amount)
            return dp(coins, amount);
        }

        // 定义：要凑出⾦额 n，⾄少要 dp(coins, n) 个硬币
        int dp(int[] coins, int amount) {
            // base case
            if (amount == 0) return 0;
            if (amount < 0) return -1;
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                // 计算⼦问题的结果
                int subProblem = dp(coins, amount - coin);
                // ⼦问题⽆解则跳过
                if (subProblem == -1) continue;
                // 在⼦问题中选择最优解，然后加⼀
                res = Math.min(res, subProblem + 1);
            }
            return res == Integer.MAX_VALUE ? -1 : res;
        }
    }

    /**
     * 2、带备忘录的递归
     */
    static class Solution2 {
        int[] memo;

        int coinChange(int[] coins, int amount) {
            memo = new int[amount + 1];
            // dp 数组全都初始化为特殊值
            Arrays.fill(memo, -666);
            return dp(coins, amount);
        }

        int dp(int[] coins, int amount) {
            if (amount == 0) return 0;
            if (amount < 0) return -1;
            // 查备忘录，防⽌重复计算
            if (memo[amount] != -666)
                return memo[amount];
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                // 计算⼦问题的结果
                int subProblem = dp(coins, amount - coin);
                // ⼦问题⽆解则跳过
                if (subProblem == -1) continue;
                // 在⼦问题中选择最优解，然后加⼀
                res = Math.min(res, subProblem + 1);
            }
            // 把计算结果存⼊备忘录
            memo[amount] = (res == Integer.MAX_VALUE) ? -1 : res;
            return memo[amount];
        }
    }

    /**
     * 3、dp 数组的迭代解法
     */
    static class Solution3 {
        int coinChange(int[] coins, int amount) {
            int[] dp = new int[amount + 1];
            // 数组⼤⼩为 amount + 1，初始值也为 amount + 1
            Arrays.fill(dp, amount + 1);
            // base case
            dp[0] = 0;
            // 外层 for 循环在遍历所有状态的所有取值
            for (int i = 1; i < dp.length; i++) {
                // 内层 for 循环在求所有选择的最⼩值
                /**
                 * 从dp[0]计算到dp[11]，里面的值存的是最少次数就能筹够下标值的钱，
                 * 如dp[6]=2 就是2次就能筹够6元(1+5元或者5+1元)，
                 * 计算dp[11]的时候等于 dp[11-5元的coin]+1也就是等于dp[6]+1=3
                 *
                 */
                for (int coin : coins) {
                    // ⼦问题⽆解，跳过
                    if (i - coin < 0) {
                        continue;
                    }
                    // 最后一次dp数组的值[0, 1, 1, 2, 2, 1, 2, 2, 3, 3, 2, 3]
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
            return (dp[amount] == amount + 1) ? -1 : dp[amount];
        }
    }

    /**
     * 4、贪心 + dfs
     * 思路
     * 1. 贪心
     * 1.1. 想要总硬币数最少，肯定是优先用大面值硬币，所以对 coins 按从大到小排序
     * 1.2. 先丢大硬币，再丢会超过总额时，就可以递归下一层丢的是稍小面值的硬币
     * <p>
     * 2. 乘法对加法的加速
     * 2.1. 优先丢大硬币进去尝试，也没必要一个一个丢，可以用乘法算一下最多能丢几个
     * <p>
     * k = amount / coins[c_index] 计算最大能投几个
     * amount - k * coins[c_index] 减去扔了 k 个硬币
     * count + k 加 k 个硬币
     * <p>
     * 2.2. 如果因为丢多了导致最后无法凑出总额，再回溯减少大硬币数量
     * 3. 最先找到的并不是最优解
     * 3.1. 注意不是现实中发行的硬币，面值组合规划合理，会有奇葩情况
     * 3.2. 考虑到有 [1,7,10] 这种用例，按照贪心思路 10 + 1 + 1 + 1 + 1 会比 7 + 7 更早找到
     * 3.3. 所以还是需要把所有情况都递归完
     * <p>
     * 4. ans 疯狂剪枝
     * 4.1. 贪心虽然得不到最优解，但也不是没用的
     * 4.2. 我们快速算出一个贪心的 ans 之后，虽然还会有奇葩情况，但是绝大部分普通情况就可以疯狂剪枝了
     * <p>
     * 作者：ikaruga
     * 链接：https://leetcode-cn.com/problems/coin-change/solution/322-by-ikaruga/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution4 {
        int res = Integer.MAX_VALUE;

        public int coinChange(int[] coins, int amount) {
            if (amount == 0) {
                return 0;
            }
            Arrays.sort(coins);
            minCoin(coins, amount, coins.length - 1, 0);
            return res == Integer.MAX_VALUE ? -1 : res;
        }

        private void minCoin(int[] coins, int amount, int index, int count) {
            if (amount == 0) {
                res = Math.min(res, count);
                return;
            }
            if (index < 0) {
                return;
            }
            for (int i = amount / coins[index]; i >= 0 && i + count < res; i--) {
                minCoin(coins, amount - (i * coins[index]), index - 1, count + i);
            }
        }
    }

}
