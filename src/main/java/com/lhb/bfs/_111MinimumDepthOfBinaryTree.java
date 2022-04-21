package com.lhb.bfs;

import com.alibaba.fastjson.JSON;
import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: LeetCodeRecord
 * @description: 111. ⼆叉树的最⼩深度（简单）
 * @author: linhaibin
 * @create: 2022-04-21 10:03
 **/
public class _111MinimumDepthOfBinaryTree {
    /**
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明：叶子节点是指没有子节点的节点。
     * <p>
     *  
     * <p>
     * 示例 1：
     * //       3
     * // ┌─────┴─────┐
     * // 9          20
     * //          ┌──┴──┐
     * //         15     7
     * <p>
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：root = [2,null,3,null,4,null,5,null,6]
     * 输出：5
     *  
     * <p>
     * 提示：
     * <p>
     * 树中节点数的范围在 [0, 105] 内
     * -1000 <= Node.val <= 1000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] array = new Integer[]{3, 9, 20, null, null, 15, 7}; // 层序
        TreeNode treeNode = TreeNodeUtils.InitTree(array);
        TreePrinter.print(treeNode);
        Solution solution = new Solution();
        System.out.println(solution.minDepth(treeNode));


    }

    static class Solution {

        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int res = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode poll = queue.poll();
                    if (poll.left == null && poll.right == null) {
                        return res;
                    }
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }
                }
                res++;
            }
            return 0;
        }

    }

}
