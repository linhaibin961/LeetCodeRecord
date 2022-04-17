package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreePrinter;

import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 106. 从中序与后序遍历序列构造⼆叉树（中等）
 * @author: linhaibin
 * @create: 2022-04-16 23:03
 **/
public class _106ConstructBinaryTreeFromInorderAndPostorderTraversal {
    /**
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     * <p>
     *  
     * <p>
     * 示例 1:
     * //       3
     * // ┌─────┴─────┐
     * // 9          20
     * //          ┌──┴──┐
     * //         15     7
     * <p>
     * <p>
     * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
     * 输出：[3,9,20,null,null,15,7]
     * 示例 2:
     * <p>
     * 输入：inorder = [-1], postorder = [-1]
     * 输出：[-1]
     *  
     * <p>
     * 提示:
     * <p>
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * -3000 <= inorder[i], postorder[i] <= 3000
     * inorder 和 postorder 都由 不同 的值组成
     * postorder 中每一个值都在 inorder 中
     * inorder 保证是树的中序遍历
     * postorder 保证是树的后序遍历
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] inOrder = new int[]{9, 3, 15, 20, 7}; // 中序
        int[] postOrder = new int[]{9, 15, 7, 20, 3}; // 后序

        Solution solution = new Solution();
        TreeNode tree = solution.buildTree(inOrder, postOrder);

        TreePrinter.print(tree);
    }


    static class Solution {
        /**
         * @param inorder   中序数组
         * @param postorder 后序数组
         * @return
         */
        public TreeNode buildTree(int[] inorder, int[] postorder) {

            TreeNode node = helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
            return node;
        }

        private TreeNode helper(int[] inorder, int inStart, int inEnd,
                                int[] postorder, int postStart, int postEnd) {
            if (postStart > postEnd || inStart > inEnd) return null;
            // root 节点对应的值就是前序遍历数组的第⼀个元素
            int rootVal = postorder[postEnd];
            // 根节点在中序数组的索引位置
            int rootNodeInInorderIndex = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == rootVal) {
                    rootNodeInInorderIndex = i;
                    break;
                }
            }
            // 关键的地方，通过中序数组，计算右子树有多个节点
            int rightSize = inEnd - rootNodeInInorderIndex;
            // 递归构建左节点
            TreeNode left = helper(inorder, inStart, rootNodeInInorderIndex - 1,
                    postorder, postStart, postEnd - rightSize - 1);
            // 递归构建右节点
            TreeNode right = helper(inorder, rootNodeInInorderIndex + 1, inEnd,
                    postorder, postEnd - rightSize, postEnd - 1);
            // 构建根节点
            TreeNode root = new TreeNode(rootVal);
            root.left = left;
            root.right = right;
            return root;
        }
    }

}
