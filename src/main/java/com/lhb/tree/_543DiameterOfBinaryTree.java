package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

/**
 * @program: LeetCodeRecord
 * @description: 543. ⼆叉树的直径（简单）
 * @author: linhaibin
 * @create: 2022-04-17 00:03
 **/
public class _543DiameterOfBinaryTree {
    /**
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * <p>
     * 自己的理解：每⼀条⼆叉树的「直径」⻓度，就是⼀个节点的左右⼦树的最⼤深度之和。
     * <p>
     * 示例 :
     * 给定二叉树
     * <p>
     * //         1
     * //        / \
     * //       2   3
     * //      / \
     * //     4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     * <p>
     *  
     * <p>
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(array);
        TreePrinter.print(treeNode);
        Solution solution = new Solution();
        System.out.println(solution.diameterOfBinaryTree(treeNode));
    }


    static class Solution {
        // 记录最⼤直径的⻓度
        int maxDiameter = 0;

        /**
         * @param root 二叉树
         * @return
         */
        public int diameterOfBinaryTree(TreeNode root) {
            return maxDepth(root);
        }

        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftLength = maxDepth(root.left);
            int rightLength = maxDepth(root.right);
            int myDiameter = leftLength + rightLength;
            maxDiameter = Math.max(myDiameter, maxDiameter);
            return Math.max(leftLength, rightLength) + 1;
        }

    }
}
