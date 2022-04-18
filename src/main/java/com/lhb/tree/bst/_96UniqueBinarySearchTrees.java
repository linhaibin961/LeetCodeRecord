package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

/**
 * @program: LeetCodeRecord
 * @description: 96. 不同的⼆叉搜索树（中等）
 * @author: linhaibin
 * @create: 2022-04-18 10:03
 **/
public class _96UniqueBinarySearchTrees {
    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：n = 3
     * 输出：5
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：1
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 19
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numTrees(3));
    }


    static class Solution {

        int[][] mem;

        /**
         * @return
         */
        public int numTrees(int n) {
            mem = new int[n + 1][n + 1];
            return count(1, n);
        }

        private int count(int low, int hi) {
            if (low > hi) {
                return 1;
            }
            if (mem[low][hi] != 0) {
                return mem[low][hi];
            }
            int sum = 0;
            for (int i = low; i <= hi; i++) {
                int left = count(low, i - 1);
                int right = count(i + 1, hi);
                sum += left * right;
            }
            mem[low][hi] = sum;

            return sum;
        }

    }
}
