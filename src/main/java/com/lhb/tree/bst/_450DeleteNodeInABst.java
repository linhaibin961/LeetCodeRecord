package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

/**
 * @program: LeetCodeRecord
 * @description: 450. 删除⼆叉搜索树中的节点（中等）
 * @author: linhaibin
 * @create: 2022-04-17 23:03
 **/
public class _450DeleteNodeInABst {
    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * <p>
     * 一般来说，删除节点可分为两个步骤：
     * <p>
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     *  
     * <p>
     * 示例 1:
     * //          5                                5
     * //    ┌─────┴─────┐                    ┌─────┴─────┐
     * //    3           6                    4           6
     * // ┌──┴──┐        └──┐              ┌──┘           └──┐
     * // 2     4           7              2                 7
     * <p>
     * 输入：root = [5,3,6,2,4,null,7], key = 3
     * 输出：[5,4,6,2,null,null,7]
     * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     * <p>
     * <p>
     * 示例 2:
     * <p>
     * 输入: root = [5,3,6,2,4,null,7], key = 0
     * 输出: [5,3,6,2,4,null,7]
     * 解释: 二叉树不包含值为 0 的节点
     * 示例 3:
     * <p>
     * 输入: root = [], key = 0
     * 输出: []
     *  
     * <p>
     * 提示:
     * <p>
     * 节点数的范围 [0, 104].
     * -105 <= Node.val <= 105
     * 节点值唯一
     * root 是合法的二叉搜索树
     * -105 <= key <= 105
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] traverseOrder = new int[]{5, 3, 6, 2, 4, -1, 7}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(traverseOrder);
        TreePrinter.print(treeNode);


        Solution solution = new Solution();
        TreeNode deleteNode = solution.deleteNode(treeNode, 3);
        TreePrinter.print(deleteNode);
    }


    static class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) {
                return null;
            }
            if (root.val == key) {
                if (root.left == null) {
                    return root.right;
                }
                if (root.right == null) {
                    return root.left;
                }
                TreeNode minNode = getMinNode(root.right);
                root.right = deleteNode(root.right, minNode.val);
                minNode.left = root.left;
                minNode.right = root.right;
                root = minNode;

            } else if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            }
            return root;
        }

        TreeNode getMinNode(TreeNode node) {
            // BST 最左边的就是最⼩的
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }

    static class Solution2 {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;
            if (root.val == key) {
                // 这两个 if 把情况 1 和 2 都正确处理了
                if (root.left == null) return root.right;
                if (root.right == null) return root.left;
                // 处理情况 3
                // 获得右子树最小的节点
                TreeNode minNode = getMin(root.right);
                // 删除右子树最小的节点
                root.right = deleteNode(root.right, minNode.val);
                // 用右子树最小的节点替换 root 节点
                minNode.left = root.left;
                minNode.right = root.right;
                root = minNode;
            } else if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            }
            return root;
        }

        TreeNode getMin(TreeNode node) {
            // BST 最左边的就是最小的
            while (node.left != null) node = node.left;
            return node;
        }
    }

}
