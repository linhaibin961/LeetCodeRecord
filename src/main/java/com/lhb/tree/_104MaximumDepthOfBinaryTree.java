package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 104. ⼆叉树的最⼤深度（简单）
 * @author: linhaibin
 * @create: 2022-04-17 00:03
 **/
public class _104MaximumDepthOfBinaryTree {
    /**
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例：
     * 给定二叉树 [3,9,20,null,null,15,7]，
     * <p>
     * //   3
     * //  / \
     * // 9  20
     * //   /  \
     * //  15   7
     * 返回它的最大深度 3 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{3, 9, 20, -1, -1, 15, 7}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(array);
        TreePrinter.print(treeNode);
        Solution solution = new Solution();
        System.out.println(solution.maxDepth(treeNode));
    }


    static class Solution {
        int max = 0;


        /**
         * 我自己想的用广度优先，逐层遍历
         *
         * @param root 二叉树
         * @return
         */
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return max;
            }
            Queue<TreeNode> queue = new LinkedList();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode treeNode = queue.poll();
                    if (treeNode.left != null) {
                        queue.offer(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.offer(treeNode.right);
                    }
                }
                max++;
            }
            return max;
        }

        /**
         * labuladong 遍历二叉树思维解法开始
         *
         * 这个解法应该很好理解，但为什么需要在前序位置增加 depth，在后序位置减⼩ depth？
         * 因为前⾯说了，前序位置是进⼊⼀个节点的时候，后序位置是离开⼀个节点的时候，
         * depth 记录当前递归到的节点深度，你把 traverse 理解成在⼆叉树上游⾛的⼀个指针，所以当然要这样维护。
         */
        // 记录最⼤深度
        int res = 0;
        // 记录遍历到的节点的深度
        int depth = 0;

        // 主函数
        int maxDepth2(TreeNode root) {
            traverse(root);
            return res;
        }

        // ⼆叉树遍历框架
        void traverse(TreeNode root) {
            if (root == null) {
                // 到达叶⼦节点，更新最⼤深度
                res = Math.max(res, depth);
                return;
            }
            // 前序位置
            depth++;
            traverse(root.left);
            traverse(root.right);
            // 后序位置
            depth--;
        }

        /**
         * labuladong 遍历二叉树思维解法结束
         */

        /**
         * labuladong 分解问题思维解法开始
         */
        // 定义：输⼊根节点，返回这棵⼆叉树的最⼤深度
        int maxDepth3(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 利⽤定义，计算左右⼦树的最⼤深度
            int leftMax = maxDepth(root.left);
            int rightMax = maxDepth(root.right);
            // 整棵树的最⼤深度等于左右⼦树的最⼤深度取最⼤值，
            // 然后再加上根节点⾃⼰
            int res = Math.max(leftMax, rightMax) + 1;
            return res;
        }
        /**
         * labuladong 分解问题思维解法结束
         */
    }
}
