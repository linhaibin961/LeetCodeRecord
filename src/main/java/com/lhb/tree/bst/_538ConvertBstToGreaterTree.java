package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.LinkedList;

/**
 * @program: LeetCodeRecord
 * @description: 538. ⼆叉搜索树转化累加树（中等）
 * @author: linhaibin
 * @create: 2022-04-17 23:03
 **/
public class _538ConvertBstToGreaterTree {
    /**
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     * <p>
     * 提醒一下，二叉搜索树满足下列约束条件：
     * <p>
     * 节点的左子树仅包含键 小于 节点键的节点。
     * 节点的右子树仅包含键 大于 节点键的节点。
     * 左右子树也必须是二叉搜索树。
     * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
     * <p>
     *  
     * <p>
     * 示例 1：
     * //                   4                                               30
     * //       ┌───────────┴───────────┐                        ┌───────────┴───────────┐
     * //       1                       6                       36                      21
     * // ┌─────┴─────┐           ┌─────┴─────┐            ┌─────┴─────┐           ┌─────┴─────┐
     * // 0           2           5           7           36          35          26          15
     * //             └──┐                    └──┐                     └──┐                    └──┐
     * //                3                       8                       33                       8
     * <p>
     * <p>
     * <p>
     * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
     * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
     * 示例 2：
     * <p>
     * 输入：root = [0,null,1]
     * 输出：[1,null,1]
     * 示例 3：
     * <p>
     * 输入：root = [1,0,2]
     * 输出：[3,3,2]
     * 示例 4：
     * <p>
     * 输入：root = [3,2,4,1]
     * 输出：[7,9,4,10]
     *  
     * <p>
     * 提示：
     * <p>
     * 树中的节点数介于 0 和 104 之间。
     * 每个节点的值介于 -104 和 104 之间。
     * 树中的所有值 互不相同 。
     * 给定的树为二叉搜索树。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 自己的理解：题⽬理解，⽐如图中的节点 5，转化成累加树的话，⽐ 5 ⼤的节点有 6，7，8，加上 5 本身，所以
     * 累加树上这个节点的值应该是 5+6+7+8=26。
     * <p>
     * 关键点： 递归顺序改⼀下，中序情况下，先遍历右节点再遍历左节点，导致降序
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] traverseOrder = new int[]{4, 1, 6, 0, 2, 5, 7, -1, -1, -1, 3, -1, -1, -1, 8}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(traverseOrder);
        TreePrinter.print(treeNode);


        Solution solution = new Solution();
        TreeNode convertBST = solution.convertBST(treeNode);
        TreePrinter.print(convertBST);
    }


    static class Solution {
        public TreeNode convertBST(TreeNode root) {
            inOrder(root);
            return root;
        }

        int sum = 0;

        private void inOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            inOrder(root.right);
            sum += root.val;
            root.val = sum;
            inOrder(root.left);
        }
    }

}
