package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreePrinter;

/**
 * @program: LeetCodeRecord
 * @description: 889. 根据前序和后序遍历构造⼆叉树（中等）
 * @author: linhaibin
 * @create: 2022-04-16 23:03
 **/
public class _889ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    /**
     * 给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
     * <p>
     * 如果存在多个答案，您可以返回其中 任何 一个。
     * <p>
     *  
     * <p>
     * 示例 1：
     * //          1
     * //    ┌─────┴─────┐
     * //    2           3
     * // ┌──┴──┐     ┌──┴──┐
     * // 4     5     6     7
     * <p>
     * 输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
     * 输出：[1,2,3,4,5,6,7]
     * 示例 2:
     * <p>
     * 输入: preorder = [1], postorder = [1]
     * 输出: [1]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= preorder.length <= 30
     * 1 <= preorder[i] <= preorder.length
     * preorder 中所有值都 不同
     * postorder.length == preorder.length
     * 1 <= postorder[i] <= postorder.length
     * postorder 中所有值都 不同
     * 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] preOrder = new int[]{1, 2, 4, 5, 3, 6, 7}; // 前序
        int[] postOrder = new int[]{4, 5, 2, 6, 7, 3, 1}; // 后序

        Solution solution = new Solution();
        TreeNode tree = solution.constructFromPrePost(preOrder, postOrder);

        TreePrinter.print(tree);
    }


    static class Solution {
        /**
         * @param preorder  前序数组
         * @param postorder 后序数组
         * @return
         */
        public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
            TreeNode treeNode = build(preorder, 0, preorder.length - 1,
                    postorder, 0, postorder.length - 1);
            return treeNode;
        }

        private TreeNode build(int[] preorder, int preStart, int preEnd,
                               int[] postorder, int postStart, int postEnd) {

            if (preStart > preEnd) {
                return null;
            }
            // 这一步很关键
            if (preStart == preEnd) {
                return new TreeNode(preorder[preStart]);
            }
            // root 节点对应的值就是前序遍历数组的第⼀个元素
            int rootVal = preorder[preStart];
            /*
             root.left 的值是前序遍历第⼆个元素
             通过前序和后序遍历构造⼆叉树的关键在于通过左⼦树的根节点
             确定 preorder 和 postorder 中左右⼦树的元素区间

             我们假设前序遍历的第⼆个元素是左⼦树的根节点，但实际上左⼦树有可能是空指针，那么这个元素就应该是右⼦树的根节点。
             由于这⾥⽆法确切进⾏判断，所以导致了最终答案的不唯⼀。
            */
            int leftRootVal = preorder[preStart + 1];
            int index = 0;
            for (int i = postStart; i < postEnd; i++) {
                // 找出根节点的左节点在后序数组的索引位置
                if (leftRootVal == postorder[i]) {
                    index = i;
                    break;
                }

            }
            // 左⼦树的元素个数 +1 是要算上index这个节点本身
            int leftSize = (index - postStart) + 1;

            // 递归构造左右⼦树
            // 根据左⼦树的根节点索引和元素个数推导左右⼦树的索引边界
            TreeNode left = build(preorder, preStart + 1, preStart + leftSize,
                    postorder, postStart, index);
            TreeNode right = build(preorder, preStart + leftSize + 1, preEnd,
                    postorder, index + 1, postEnd - 1);
            // 先构造出当前根节点
            TreeNode root = new TreeNode(rootVal);
            root.left = left;
            root.right = right;
            return root;
        }

    }

}
