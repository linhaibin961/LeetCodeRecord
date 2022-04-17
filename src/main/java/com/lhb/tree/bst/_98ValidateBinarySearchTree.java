package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

/**
 * @program: LeetCodeRecord
 * @description: 98. 验证⼆叉搜索树（中等）
 * @author: linhaibin
 * @create: 2022-04-17 23:03
 **/
public class _98ValidateBinarySearchTree {
    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 有效 二叉搜索树定义如下：
     * <p>
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *  
     * <p>
     * 示例 1：
     * //    2
     * // ┌──┴──┐
     * // 1     3
     * <p>
     * 输入：root = [2,1,3]
     * 输出：true
     * 示例 2：
     * //       5
     * // ┌─────┴─────┐
     * // 1           4
     * //          ┌──┴──┐
     * //          3     6
     * <p>
     * 输入：root = [5,1,4,null,null,3,6]
     * 输出：false
     * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
     *  
     * <p>
     * 提示：
     * <p>
     * 树中节点数目范围在[1, 104] 内
     * -231 <= Node.val <= 231 - 1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 特殊的测试用例：{5, 4, 6, -1, -1, 3, 7}，{2 ,2, 2}
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] traverseOrder = new int[]{5, 4, 6, -1, -1, 3, 7}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(traverseOrder);
        TreePrinter.print(treeNode);


        Solution solution = new Solution();
        System.out.println(solution.isValidBST(treeNode));
    }


    static class Solution {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            if (root == null) {
                return true;
            }
            if (min != null && root.val < min.val) {
                return false;
            }
            if (max != null && root.val > max.val) {
                return false;
            }
            return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
        }

    }

}
