package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 652. 寻找重复的⼦树（中等）
 * @author: linhaibin
 * @create: 2022-04-16 23:03
 **/
public class _652FindDuplicateSubtrees {
    /**
     * 给定一棵二叉树 root，返回所有重复的子树。
     * <p>
     * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     * <p>
     * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
     * <p>
     *  
     * <p>
     * 示例 1：
     * //                   1
     * //       ┌───────────┴───────────┐
     * //       2                       3
     * // ┌─────┘                 ┌─────┴─────┐
     * // 4                       2           4
     * //                      ┌──┘
     * //                      4
     * <p>
     * 输入：root = [1,2,3,4,null,2,4,null,null,4]
     * 输出：[[2,4],[4]]
     * 示例 2：
     * <p>
     * <p>
     * <p>
     * 输入：root = [2,1,1]
     * 输出：[[1]]
     * 示例 3：
     * <p>
     * <p>
     * <p>
     * 输入：root = [2,2,2,3,null,3,null]
     * 输出：[[2,3],[3]]
     *  
     * <p>
     * 提示：
     * <p>
     * 树中的结点数在[1,10^4]范围内。
     * -200 <= Node.val <= 200
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-duplicate-subtrees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, -1, 2, 4, -1, -1, -1, -1, 4}; // 层序
//        int[] array = new int[]{2, 2, 2, 3, -1, 3, -1}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(array);
        TreePrinter.print(treeNode);
        Solution solution = new Solution();
        List<TreeNode> duplicateSubtrees = solution.findDuplicateSubtrees(treeNode);
        for (TreeNode duplicateSubtree : duplicateSubtrees) {
            TreePrinter.print(duplicateSubtree);

        }
    }


    static class Solution {
        // 记录重复的子树根节点
        List<TreeNode> list = new ArrayList<>();
        // 记录所有子树以及出现的次数
        Map<String, Integer> map = new HashMap();


        /**
         * @param root 二叉树
         * @return
         */
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            traverse(root);
            return list;
        }

        /* 辅助函数 */
        private String traverse(TreeNode root) {
            if (root == null) {
                return "#";
            }
            String leftNode = traverse(root.left);
            String rightNode = traverse(root.right);
            String val = root.val + "";
            // 要加上逗号，要不然有测试用例不通过
            String key = val + "," + leftNode + "," + rightNode;
            int freq = map.getOrDefault(key, 0);
            // 多次重复也只会被加入结果集一次
            if (freq == 1) {
                list.add(root);
            }
            // 给子树对应的出现次数加一
            map.put(key, freq + 1);
            return key;
        }

    }

}
