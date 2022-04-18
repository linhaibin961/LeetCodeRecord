package com.lhb.tree.bst;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;
import javafx.util.Pair;

/**
 * @program: LeetCodeRecord
 * @description: 96. 不同的⼆叉搜索树（中等）
 * @author: linhaibin
 * @create: 2022-04-18 10:03
 **/
public class _1373MaximumSumBstInBinaryTree {
    /**
     * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
     * <p>
     * 二叉搜索树的定义如下：
     * <p>
     * 任意节点的左子树中的键值都 小于 此节点的键值。
     * 任意节点的右子树中的键值都 大于 此节点的键值。
     * 任意节点的左子树和右子树都是二叉搜索树。
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
     * 输出：20
     * 解释：键值为 3 的子树是和最大的二叉搜索树。
     * 示例 2：
     * <p>
     * <p>
     * <p>
     * 输入：root = [4,3,null,1,2]
     * 输出：2
     * 解释：键值为 2 的单节点子树是和最大的二叉搜索树。
     * 示例 3：
     * <p>
     * 输入：root = [-4,-2,-5]
     * 输出：0
     * 解释：所有节点键值都为负数，和最大的二叉搜索树为空。
     * 示例 4：
     * <p>
     * 输入：root = [2,1,3]
     * 输出：6
     * 示例 5：
     * <p>
     * 输入：root = [5,4,8,3,null,6,3]
     * 输出：7
     *  
     * <p>
     * 提示：
     * <p>
     * 每棵树有 1 到 40000 个节点。
     * 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
//        Integer[] traverseOrder = new Integer[]{1, 4, 3, 2, 4, 2, 5, null, null, null, null, null, null, 4, 6}; // 层序
//        Integer[] traverseOrder = new Integer[]{4, 3, null, 1, 2}; // 层序
//        Integer[] traverseOrder = new Integer[]{-4,-5,-2}; // 层序
//        Integer[] traverseOrder = new Integer[]{4, 8, null, 6, 1, 9, null, -5, 4, null, null, null, -3, null, 10}; // 层序
//        Integer[] traverseOrder = new Integer[]{8, 9, 8, null, 9, null, 1, null, null, -3, 5, null, -2, null, 6}; // 层序
        Integer[] traverseOrder = new Integer[]{1, null, 10, -5, 20}; // 层序
        TreeNode treeNode = TreeNodeUtils.InitTree(traverseOrder);
        TreePrinter.print(treeNode);
        Solution2 solution = new Solution2();
        System.out.println(solution.maxSumBST(treeNode));
    }


    /**
     * 自己写的，覆盖不了所有的测试用例
     */
    static class Solution2 {
        public Solution2() {
        }

        int maxSum = 0;

        /**
         * // 1
         * // └─────┐
         * //      10
         * //    ┌──┴──┐
         * //   -5    20
         * {1, null, 10, -5, 20} 这个测试用例过不去，跑出来是26，实际是25，不算根节点1，因为加上根节点1之后，就是不是一个二查搜索树了。1>-5
         * 待优化：所以要从下往上返回子树的最大值节点和最小值节点
         *
         * @return
         */
        public int maxSumBST(TreeNode root) {
            helper(root);
            return maxSum;
        }

        private Pair<Boolean, Integer> helper(TreeNode root) {
            if (root == null) {
                return new Pair<>(true, 0);
            }
            if (root.left == null && root.right == null) {
                maxSum = Math.max(maxSum, root.val);
            }

            Pair<Boolean, Integer> left = helper(root.left);
            Pair<Boolean, Integer> right = helper(root.right);

            if (root.left != null && root.val <= root.left.val) {
                return new Pair<>(false, 0);
            }
            if (root.right != null && root.val >= root.right.val) {
                return new Pair<>(false, 0);
            }
            int sum = left.getValue() + right.getValue() + root.val;
            Pair<Boolean, Integer> result = new Pair<>(left.getKey() && right.getKey(), sum);
            if (result.getKey()) {
                maxSum = Math.max(sum, maxSum);
            }


            return result;
        }

    }

    /**
     * labuladong 解法
     */
    static class Solution {
        // 全局变量，记录 BST 最大节点之和
        int maxSum = 0;

        /* 主函数 */
        public int maxSumBST(TreeNode root) {
            traverse(root);
            return maxSum;
        }

        int[] traverse(TreeNode root) {
            // base case
            if (root == null) {
                return new int[]{
                        1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0
                };
            }

            // 递归计算左右子树
            int[] left = traverse(root.left);
            int[] right = traverse(root.right);

            /*******后序遍历位置*******/
            int[] res = new int[4];
            // 这个 if 在判断以 root 为根的二叉树是不是 BST
            if (left[0] == 1 && right[0] == 1 &&
                    root.val > left[2] && root.val < right[1]) {
                // 以 root 为根的二叉树是 BST
                res[0] = 1;
                // 计算以 root 为根的这棵 BST 的最小值
                res[1] = Math.min(left[1], root.val);
                // 计算以 root 为根的这棵 BST 的最大值
                res[2] = Math.max(right[2], root.val);
                // 计算以 root 为根的这棵 BST 所有节点之和
                res[3] = left[3] + right[3] + root.val;
                // 更新全局变量
                maxSum = Math.max(maxSum, res[3]);
            } else {
                // 以 root 为根的二叉树不是 BST
                res[0] = 0;
                // 其他的值都没必要计算了，因为用不到
            }
            /**************************/

            return res;
        }
    }

}
