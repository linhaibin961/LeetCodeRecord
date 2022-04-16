package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 654. 最⼤⼆叉树（中等）
 * @author: linhaibin
 * @create: 2022-04-15 23:03
 **/
public class _654MaximumBinaryTree {
    /**
     * 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
     * <p>
     * 创建一个根节点，其值为 nums 中的最大值。
     * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
     * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
     * 返回 nums 构建的 最大二叉树 。
     * <p>
     * 自己的理解：就是要构建一个二查搜索树
     * <p>
     * 示例 1：
     * //             6
     * // ┌───────────┴───────────┐
     * // 3                       5
     * // └─────┐           ┌─────┘
     * //       2           0
     * //       └──┐
     * //          1
     * <p>
     * 输入：nums = [3,2,1,6,0,5]
     * 输出：[6,3,5,null,2,0,null,null,1]
     * 解释：递归调用如下所示：
     * - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
     * - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
     * - 空数组，无子节点。
     * - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
     * - 空数组，无子节点。
     * - 只有一个元素，所以子节点是一个值为 1 的节点。
     * - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
     * - 只有一个元素，所以子节点是一个值为 0 的节点。
     * - 空数组，无子节点。
     * 示例 2：
     * // 3
     * // └─┐
     * //   2
     * //   └─┐
     * //     1
     * <p>
     * 输入：nums = [3,2,1]
     * 输出：[3,null,2,null,1]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 1000
     * nums 中的所有整数 互不相同
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
//        int[] ints = new int[]{3, 2, 1}; //中序
        int[] ints = new int[]{3, 2, 1, 6, 0, 5}; //该题构建完之后，中序这个顺序，但是不能通过中序构建一颗二叉树
        System.out.println(Arrays.toString(ints));

        TreeNode treeNode = TreeNodeUtils.createBinTree(ints);
        TreePrinter.print(treeNode);
        Solution solution = new Solution();
        TreeNode node = solution.constructMaximumBinaryTree(ints);

        TreePrinter.print(node);
    }


    static class Solution {
        /**
         * 这道题就是要构建一个二查搜索树
         * 通过中序构建二查树，实际上如果只知道中序是反序列化不了的
         *
         * @param nums
         * @return
         */
        public TreeNode constructMaximumBinaryTree(int[] nums) {

            TreeNode node = build(0, nums.length - 1, nums);
            return node;
        }

        private TreeNode build(int start, int end, int[] nums) {
            if (start > end) return null;
            int maxIndex = start;
            for (int i = start; i <= end; i++) {
                maxIndex = nums[i] > nums[maxIndex] ? i : maxIndex;
            }
            TreeNode left = build(start, maxIndex - 1, nums);
            TreeNode right = build(maxIndex + 1, end, nums);
            TreeNode node = new TreeNode(nums[maxIndex]);
            node.left = left;
            node.right = right;
            return node;
        }
    }
}
