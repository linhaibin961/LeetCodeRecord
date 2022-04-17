package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.LinkedList;

/**
 * @program: LeetCodeRecord
 * @description: 230. ⼆叉搜索树中第 K ⼩的元素（中等）
 * @author: linhaibin
 * @create: 2022-04-17 23:03
 **/
public class _230KthSmallestElementInABst {
    /**
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     * <p>
     *  
     * <p>
     * 示例 1：
     * //       3
     * // ┌─────┴─────┐
     * // 1           4
     * // └──┐
     * //    2
     * <p>
     * <p>
     * 输入：root = [3,1,4,null,2], k = 1
     * 输出：1
     * 示例 2：
     * //                      5
     * //          ┌───────────┴───────────┐
     * //          3                       6
     * //    ┌─────┴─────┐
     * //    2           4
     * // ┌──┘
     * // 1
     * <p>
     * <p>
     * 输入：root = [5,3,6,2,4,null,null,1], k = 3
     * 输出：3
     *  
     * <p>
     *  
     * <p>
     * 提示：
     * <p>
     * 树中的节点数为 n 。
     * 1 <= k <= n <= 104
     * 0 <= Node.val <= 104
     *  
     * <p>
     * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] traverseOrder = new int[]{5, 3, 6, 2, 4, -1, -1, 1}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(traverseOrder);
        TreePrinter.print(treeNode);

        Solution solution = new Solution();
        System.out.println(solution.kthSmallest(treeNode, 3));
    }


    static class Solution {
        LinkedList<TreeNode> list = new LinkedList<>();

        public int kthSmallest(TreeNode root, int k) {
            inOrder(root);
            return list.get(k - 1).val;
        }

        private void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            list.add(root);
            inOrder(root.right);

        }
    }

}
