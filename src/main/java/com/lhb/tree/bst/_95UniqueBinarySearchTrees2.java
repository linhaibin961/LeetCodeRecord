package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreePrinter;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 95. 不同的⼆叉搜索树II（中等）
 * @author: linhaibin
 * @create: 2022-04-18 10:03
 **/
public class _95UniqueBinarySearchTrees2 {
    /**
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：n = 3
     * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：[[1]]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 8
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TreeNode> treeNodes = solution.generateTrees(4);
        for (TreeNode treeNode : treeNodes) {
            TreePrinter.print(treeNode);
        }
    }


    static class Solution {


        /* 主函数 */
        public List<TreeNode> generateTrees(int n) {
            if (n == 0) return new LinkedList<>();
            // 构造闭区间 [1, n] 组成的 BST
            return build(1, n);
        }

        /* 构造闭区间 [lo, hi] 组成的 BST */
        List<TreeNode> build(int lo, int hi) {
            List<TreeNode> res = new LinkedList<>();
            // base case
            if (lo > hi) {
                // 这行一定要加
                res.add(null);
                return res;
            }
            if (lo == hi) {
                res.add(new TreeNode(lo));
                return res;
            }

            // 1、穷举 root 节点的所有可能。
            for (int i = lo; i <= hi; i++) {
                // 2、递归构造出左右子树的所有合法 BST。
                List<TreeNode> leftTree = build(lo, i - 1);
                List<TreeNode> rightTree = build(i + 1, hi);
                // 3、给 root 节点穷举所有左右子树的组合。
                for (TreeNode left : leftTree) {
                    for (TreeNode right : rightTree) {
                        // i 作为根节点 root 的值
                        TreeNode root = new TreeNode(i);
                        root.left = left;
                        root.right = right;
                        res.add(root);
                    }
                }
            }

            return res;
        }
    }
}
