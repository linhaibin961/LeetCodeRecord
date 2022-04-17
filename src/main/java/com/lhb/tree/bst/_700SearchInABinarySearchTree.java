package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

/**
 * @program: LeetCodeRecord
 * @description: 700. ⼆叉搜索树中的搜索（简单）
 * @author: linhaibin
 * @create: 2022-04-17 23:03
 **/
public class _700SearchInABinarySearchTree {
    /**
     * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
     * <p>
     * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。
     * <p>
     *  
     * <p>
     * 示例 1:
     * //          4
     * //    ┌─────┴─────┐
     * //    2           7            2
     * // ┌──┴──┐                  ┌──┴──┐
     * // 1     3                  1     3
     * <p>
     * 输入：root = [4,2,7,1,3], val = 2
     * 输出：[2,1,3]
     * Example 2:
     * <p>
     * <p>
     * 输入：root = [4,2,7,1,3], val = 5
     * 输出：[]
     *  
     * <p>
     * 提示：
     * <p>
     * 数中节点数在 [1, 5000] 范围内
     * 1 <= Node.val <= 107
     * root 是二叉搜索树
     * 1 <= val <= 107
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] traverseOrder = new int[]{4, 2, 7, 1, 3}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(traverseOrder);
        TreePrinter.print(treeNode);


        Solution solution = new Solution();
        TreeNode deleteNode = solution.searchBST(treeNode, 2);
        TreePrinter.print(deleteNode);
    }


    static class Solution {
        public TreeNode searchBST(TreeNode root, int val) {
            if (root == null) {
                return null;
            }
            if (root.val == val) {
                return root;
            } else if (val > root.val) {
                return searchBST(root.right, val);
            } else {
                return searchBST(root.left, val);
            }
        }
    }

    /**
     * 别人迭代解法
     */
    class Solution2 {
        public TreeNode searchBST(TreeNode root, int val) {
            while (root != null) {
                if (val == root.val) {
                    return root;
                }
                root = val < root.val ? root.left : root.right;
            }
            return null;
        }
    }
}
