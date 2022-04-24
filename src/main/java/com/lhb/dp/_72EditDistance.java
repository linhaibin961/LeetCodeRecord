package com.lhb.dp;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 72. 编辑距离（困难）
 * @author: linhaibin
 * @create: 2022-04-21 01:03
 **/
public class _72EditDistance {
    /**
     * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
     * <p>
     * 你可以对一个单词进行如下三种操作：
     * <p>
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：word1 = "horse", word2 = "ros"
     * 输出：3
     * 解释：
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * 示例 2：
     * <p>
     * 输入：word1 = "intention", word2 = "execution"
     * 输出：5
     * 解释：
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= word1.length, word2.length <= 500
     * word1 和 word2 由小写英文字母组成
     * 通过次数244,617提交次数393,703
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/edit-distance
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "hor";
        String s2 = "r";
        System.out.println(JSON.toJSONString(solution.minDistance(s1, s2)));


    }

    /**
     * 动态规划
     */
    static class Solution {

        public int minDistance(String s1, String s2) {
            int m = s1.length(), n = s2.length();
            int[][] dp = new int[m + 1][n + 1];
            // base case
            for (int i = 1; i <= m; i++) {
                dp[i][0] = i;
            }
            for (int j = 1; j <= n; j++) {
                dp[0][j] = j;
            }
            printArray(dp);
            // 自底向上求解
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。
                        dp[i][j] = min(
                                dp[i - 1][j] + 1,// 删除
                                dp[i][j - 1] + 1,// 插入
                                dp[i - 1][j - 1] + 1// 替换
                        );
                    }
                }
            }
            System.out.println();
            printArray(dp);
            // 储存着整个 s1 和 s2 的最小编辑距离
            return dp[m][n];
        }

        private void printArray(int[][] dp) {
            System.out.println();
            for (int[] ints : dp) {
                System.out.println(JSON.toJSONString(ints));

            }
        }

        int min(int a, int b, int c) {
            return Math.min(a, Math.min(b, c));
        }
    }

}
