package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 114. ⼆叉树展开为链表（中等）
 * @author: linhaibin
 * @create: 2022-04-15 23:03
 **/
public class _114FlattenBinaryTreeToLinkedList {
    /**
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * <p>
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *  
     * <p>
     * 示例 1：
     * //          1
     * //    ┌─────┴─────┐
     * //    2           5
     * // ┌──┴──┐        └──┐
     * // 3     4           6
     * 转成
     * //  1
     * //  └──┐
     * //     2
     * //     └─┐
     * //       3
     * //       └─┐
     * //         4
     * //         └──┐
     * //            5
     * //            └──┐
     * //               6
     * <p>
     * <p>
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     * 示例 2：
     * <p>
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：root = [0]
     * 输出：[0]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 5, 3, 4, -1, 6}; //层序
        System.out.println(Arrays.toString(ints));

        TreeNode treeNode = TreeNodeUtils.createBinTree(ints);
        TreePrinter.print(treeNode);
        Solution solution = new Solution();
        solution.flatten(treeNode);
        TreePrinter.print(treeNode);
    }

    static class Solution {
        /**
         * 分解问题思维
         *
         * @param root
         */
        public void flatten(TreeNode root) {
            if (root == null) return;

            flatten(root.left);
            flatten(root.right);
            TreeNode right = root.right;
            root.right = root.left;
            root.left = null;
            while (root.right != null) {
                root = root.right;
            }
            root.right = right;
        }
    }

    /**
     * 分解问题思维
     *
     * @param root
     */
    public void flatten2(TreeNode root) {
        if (root == null) return;

//        traverse(root);
    }

    private void traverse(TreeNode root) {
        return;
    }

}
