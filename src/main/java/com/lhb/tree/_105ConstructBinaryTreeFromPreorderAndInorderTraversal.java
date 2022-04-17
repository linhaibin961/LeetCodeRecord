package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.Arrays;
import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 105. 从前序与中序遍历序列构造⼆叉树（中等）
 * @author: linhaibin
 * @create: 2022-04-16 23:03
 **/
public class _105ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /**
     * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
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
     * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     * 输出: [3,9,20,null,null,15,7]
     * 示例 2:
     * <p>
     * 输入: preorder = [-1], inorder = [-1]
     * 输出: [-1]
     *  
     * <p>
     * 提示:
     * <p>
     * 1 <= preorder.length <= 3000
     * inorder.length == preorder.length
     * -3000 <= preorder[i], inorder[i] <= 3000
     * preorder 和 inorder 均 无重复 元素
     * inorder 均出现在 preorder
     * preorder 保证 为二叉树的前序遍历序列
     * inorder 保证 为二叉树的中序遍历序列
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7}; // 前序
        int[] inOrder = new int[]{9, 3, 15, 20, 7}; // 中序

        Solution solution = new Solution();
        TreeNode tree = solution.buildTree(preOrder, inOrder);

        TreePrinter.print(tree);
    }


    static class Solution {
        /**
         * @param preorder 前序数组
         * @param inorder  中序数组
         * @return
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {

            TreeNode node = helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
            return node;
        }

        private TreeNode helper(int[] preorder, int preStart, int preEnd,
                                int[] inorder, int inStart, int inEnd) {
            if (preStart > preEnd || inStart > inEnd) return null;
            // root 节点对应的值就是前序遍历数组的第⼀个元素
            int rootVal = preorder[preStart];
            // 根节点在中序数组的索引位置
            int rootNodeInInorderIndex = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == rootVal) {
                    rootNodeInInorderIndex = i;
                    break;
                }
            }
            // 关键的地方，通过中序数组，计算左子树有多个节点
            int leftSize = rootNodeInInorderIndex - inStart;
            // 递归构建左节点
            TreeNode left = helper(preorder, preStart + 1, preStart + leftSize,
                    inorder, inStart, rootNodeInInorderIndex - 1);
            // 递归构建右节点
            TreeNode right = helper(preorder, preStart + leftSize + 1, preEnd,
                    inorder, rootNodeInInorderIndex + 1, inEnd);
            // 构建根节点
            TreeNode root = new TreeNode(rootVal);
            root.left = left;
            root.right = right;
            return root;
        }

        /**
         * 迭代方式（不建议深入理解了）
         * 我们用一个栈保存已经遍历过的节点，遍历前序遍历的数组，一直作为当前根节点的左子树，
         * 直到当前节点和中序遍历的数组的节点相等了，那么我们正序遍历中序遍历的数组，
         * 倒着遍历已经遍历过的根节点（用栈的 pop 实现），找到最后一次相等的位置，把它作为该节点的右子树。
         * <p>
         * 作者：windliang
         * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         *
         * @param preorder
         * @param inorder
         * @return
         */
        public TreeNode buildTree2(int[] preorder, int[] inorder) {
            if (preorder.length == 0) {
                return null;
            }
            Stack<TreeNode> roots = new Stack<TreeNode>();
            int pre = 0;
            int in = 0;
            //先序遍历第一个值作为根节点
            TreeNode curRoot = new TreeNode(preorder[pre]);
            TreeNode root = curRoot;
            roots.push(curRoot);
            pre++;
            //遍历前序遍历的数组
            while (pre < preorder.length) {
                //出现了当前节点的值和中序遍历数组的值相等，寻找是谁的右子树
                if (curRoot.val == inorder[in]) {
                    //每次进行出栈，实现倒着遍历
                    while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
                        curRoot = roots.peek();
                        roots.pop();
                        in++;
                    }
                    //设为当前的右孩子
                    curRoot.right = new TreeNode(preorder[pre]);
                    //更新 curRoot
                    curRoot = curRoot.right;
                    roots.push(curRoot);
                    pre++;
                } else {
                    //否则的话就一直作为左子树
                    curRoot.left = new TreeNode(preorder[pre]);
                    curRoot = curRoot.left;
                    roots.push(curRoot);
                    pre++;
                }
            }

            return root;
        }
    }

}
