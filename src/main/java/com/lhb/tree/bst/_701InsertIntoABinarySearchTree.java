package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

/**
 * @program: LeetCodeRecord
 * @description: 701. ⼆叉搜索树中的插⼊操作（中等）
 * @author: linhaibin
 * @create: 2022-04-18 00:03
 **/
public class _701InsertIntoABinarySearchTree {
    /**
     * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     * <p>
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * //          4                        4
     * //    ┌─────┴─────┐            ┌─────┴─────┐
     * //    2           7            2           7
     * // ┌──┴──┐                  ┌──┴──┐     ┌──┘
     * // 1     3                  1     3     5
     * <p>
     * <p>
     * 输入：root = [4,2,7,1,3], val = 5
     * 输出：[4,2,7,1,3,5]
     * 解释：另一个满足题目要求可以通过的树是：
     * <p>
     * 示例 2：
     * <p>
     * 输入：root = [40,20,60,10,30,50,70], val = 25
     * 输出：[40,20,60,10,30,50,70,null,null,25]
     * 示例 3：
     * <p>
     * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
     * 输出：[4,2,7,1,3,5]
     *  
     * <p>
     * 提示：
     * <p>
     * 树中的节点数将在 [0, 104]的范围内。
     * -108 <= Node.val <= 108
     * 所有值 Node.val 是 独一无二 的。
     * -108 <= val <= 108
     * 保证 val 在原始BST中不存在。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] traverseOrder = new int[]{4, 2, 7, 1, 3}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(traverseOrder);
        TreePrinter.print(treeNode);


        Solution solution = new Solution();
        TreeNode insert = solution.insertIntoBST(treeNode, 5);
        TreePrinter.print(insert);
    }


    static class Solution {
        /**
         * 自己的解法，不过还是要向 Solution.insertIntoBST2() 方法学习
         *
         * @param root
         * @param val
         * @return
         */
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            TreeNode newNode;
            if (val > root.val) {
                if (root.right != null) {
                    insertIntoBST(root.right, val);
                } else {
                    newNode = new TreeNode(val);
                    root.right = newNode;
                }

            } else if (val < root.val) {
                if (root.left != null) {
                    insertIntoBST(root.left, val);
                } else {
                    newNode = new TreeNode(val);
                    root.left = newNode;
                }
            }

            return root;
        }

        /**
         * 别人的解法更加简洁
         *
         * @param root
         * @param val
         * @return
         */
        public TreeNode insertIntoBST2(TreeNode root, int val) {
            if (root == null) {
                // root == null 为终止条件，此时新建结点，并返回插入
                return new TreeNode(val);
            }
            if (root.val < val) {
                // val 比root值大，对右子树结点进行递归操作
                root.right = insertIntoBST2(root.right, val);
            } else {
                // val 比root值小，对左子树结点进行递归操作
                root.left = insertIntoBST2(root.left, val);
            }
            // 向上层返回已经完成插入操作的结点
            return root;
        }
    }

    /**
     * leetcode 解答
     */
    static class Solution2 {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) {
                return new TreeNode(val);
            }
            TreeNode pos = root;
            while (pos != null) {
                if (val < pos.val) {
                    if (pos.left == null) {
                        pos.left = new TreeNode(val);
                        break;
                    } else {
                        pos = pos.left;
                    }
                } else {
                    if (pos.right == null) {
                        pos.right = new TreeNode(val);
                        break;
                    } else {
                        pos = pos.right;
                    }
                }
            }
            return root;
        }
    }
}
