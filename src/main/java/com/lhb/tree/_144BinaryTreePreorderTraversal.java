package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 144. ⼆叉树的前序遍历（简单）
 * @author: linhaibin
 * @create: 2022-04-17 00:03
 **/
public class _144BinaryTreePreorderTraversal {
    /**
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     * <p>
     *  
     * <p>
     * 示例 1：
     * // 1
     * // └─┐
     * //   2
     * //   └─┐
     * //     3
     * <p>
     * <p>
     * 输入：root = [1,null,2,3]
     * 输出：[1,2,3]
     * 示例 2：
     * <p>
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：root = [1]
     * 输出：[1]
     * 示例 4：
     * <p>
     * <p>
     * 输入：root = [1,2]
     * 输出：[1,2]
     * 示例 5：
     * <p>
     * <p>
     * 输入：root = [1,null,2]
     * 输出：[1,2]
     *  
     * <p>
     * 提示：
     * <p>
     * 树中节点数目在范围 [0, 100] 内
     * -100 <= Node.val <= 100
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{1, -1, 2, -1, -1, -1, 3}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(array);
        TreePrinter.print(treeNode);
        Solution solution = new Solution();
        System.out.println(solution.preorderTraversal(treeNode));
    }


    static class Solution {
        List<Integer> list = new ArrayList<>();

        /**
         * @param root 二叉树
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            traverse(root);
            return list;
        }

        private void traverse(TreeNode root) {
            if (root == null) {
                return;
            }
            list.add(root.val);
            traverse(root.left);
            traverse(root.right);


        }

    }
}
