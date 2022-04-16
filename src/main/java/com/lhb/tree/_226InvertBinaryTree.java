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
 * @description: 226. 翻转⼆叉树（简单）
 * @author: linhaibin
 * @create: 2022-04-15 20:03
 **/
public class _226InvertBinaryTree {
    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * 示例 1：
     * //           4                        4
     * //        /     \                  /     \
     * //      2        7      -->       7        2
     * //    /  \      /  \            /  \      /  \
     * //  1     3   6     9          9    6   3     1
     * //
     * 输入：root = [4,2,7,1,3,6,9]
     * 输出：[4,7,2,9,6,3,1]
     * 示例 2：
     * //                  2                   2
     * //               /     \     -->     /     \
     * //             1        3          3        1
     * 输入：root = [2,1,3]
     * 输出：[2,3,1]
     * 示例 3：
     * <p>
     * 输入：root = []
     * 输出：[]
     *  
     * <p>
     * 提示：
     * <p>
     * 树中节点数目范围在 [0, 100] 内
     * -100 <= Node.val <= 100
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/invert-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] ints = new int[]{4, 2, 7, 1, 3, 6, 9, 22, 44, 55, 72, 12}; //层序
        System.out.println(Arrays.toString(ints));

        TreeNode treeNode = TreeNodeUtils.createBinTree(ints);
        TreePrinter.print(treeNode);

/*        LinkedList<Integer> levelTraverse = new LinkedList<>();
        TreeNodeUtils.getLevelTraverse(treeNode, levelTraverse);
        PrintTree.print(levelTraverse.stream().mapToInt(Integer::intValue).toArray());*/
        Solution solution = new Solution();
        TreeNode invertTree = solution.invertTreeByUnRecursiveUseStack(treeNode);
        TreePrinter.print(invertTree);

/*        LinkedList<Integer> levelTraverse2 = new LinkedList<>();
        TreeNodeUtils.getLevelTraverse(invertTree, levelTraverse2);
        PrintTree.print(levelTraverse2.stream().mapToInt(Integer::intValue).toArray());*/
    }

    static class Solution {
        /**
         * ⼆叉树解题的思维模式分两类：
         * 	1、是否可以通过遍历一遍二叉树得到答案？如果可以，用一个 traverse 函数配合外部变量来实现，这叫「遍历」的思维模式。
         * 	2、是否可以定义一个递归函数，通过子问题（子树）的答案推导出原问题的答案？
         * 	如果可以，写出这个递归函数的定义，并充分利用这个函数的返回值，这叫「分解问题」的思维模式。
         * 	无论使用哪种思维模式，你都需要思考：
         * 		如果单独抽出一个二叉树节点，它需要做什么事情？需要在什么时候（前/中/后序位置）做？
         * 		其他的节点不用你操心，递归函数会帮你在所有节点上执⾏相同的操作。
         */


        /**
         * 遍历的思想，虽然方法用了递归
         *
         * @param root
         * @return
         */
        public TreeNode invertTreeByTraverse(TreeNode root) {
            if (root != null) {
                traverseByPreOrderOrPostOrder(root);
            }

            return root;
        }

        /**
         * ⼆叉树遍历函数
         * 前序和后序处理，前序和后序可以共用同一个模板，中序稍微要改下代码
         *
         * @param root
         */
        void traverseByPreOrderOrPostOrder(TreeNode root) {
            if (root == null) {
                return;
            }
            /* 前序位置开始 */
            /*TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;*/
            /* 前序位置结束 */

            // 遍历框架，去遍历左右⼦树的节点
            traverseByPreOrderOrPostOrder(root.left);

            /* 中序位置开始 */

            /* 中序位置结束 */

            traverseByPreOrderOrPostOrder(root.right);

            /* 后序位置开始 */
            // 每⼀个节点需要做的事就是交换它的左右⼦节点
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            /* 后序位置结束 */

        }

        /**
         * ⼆叉树遍历函数
         * 中序处理
         *
         * @param root
         */
        void traverseByInOrder(TreeNode root) {
            if (root == null) {
                return;
            }

            // 遍历框架，去遍历左右⼦树的节点
            traverseByInOrder(root.left);

            /* 中序位置开始 */
            // 每⼀个节点需要做的事就是交换它的左右⼦节点
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            /* 中序位置结束 */

            // 这里的traverse要传跟上面的一样，因为上面的root左右交换过一次了。
            traverseByInOrder(root.left);

        }

        /**
         * 分解问题的思维方式
         *
         * @param root
         * @return
         */
        public TreeNode invertTreeBySplitProblem(TreeNode root) {
            if (root == null) return null;
            // 利⽤函数定义，先翻转左右⼦树
            TreeNode left = invertTreeBySplitProblem(root.left);
            TreeNode right = invertTreeBySplitProblem(root.right);
            // 然后交换左右⼦节点
            root.left = right;
            root.right = left;
            // 和定义逻辑⾃恰：以 root 为根的这棵⼆叉树已经被翻转，返回 root
            return root;
        }

        /**
         * 迭代方式 用Queue
         * 递归实现也就是深度优先遍历的方式，那么迭代对应的就是广度优先遍历。
         * 广度优先遍历需要额外的数据结构--队列，来存放临时遍历到的元素。
         * 深度优先遍历的特点是一竿子插到底，不行了再退回来继续；而广度优先遍历的特点是层层扫荡。
         * 所以，我们需要先将根节点放入到队列中，然后不断的迭代队列中的元素。
         * 对当前元素调换其左右子树的位置，然后：
         * <p>
         * 判断其左子树是否为空，不为空就放入队列中
         * 判断其右子树是否为空，不为空就放入队列中
         *
         * @param root
         * @return
         */
        public TreeNode invertTreeByUnRecursiveUseQueue(TreeNode root) {
            if (root == null) {
                return null;
            }
            //将二叉树中的节点逐层放入队列中，再迭代处理队列中的元素
            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
            queue.add(root);
            while (!queue.isEmpty()) {
                //每次都从队列中拿一个节点，并交换这个节点的左右子树
                TreeNode tmp = queue.poll();
                /* 这三行交换代码可以放在前序后序，如果要放在中序，需要改下queue.add(tmp.left)，因为中序时，左右交换过了 */
                TreeNode left = tmp.left;
                tmp.left = tmp.right;
                tmp.right = left;
                //如果当前节点的左子树不为空，则放入队列等待后续处理
                if (tmp.left != null) {
                    queue.add(tmp.left);
                }
                //如果当前节点的右子树不为空，则放入队列等待后续处理
                if (tmp.right != null) {
                    queue.add(tmp.right);
                }

            }
            //返回处理完的根节点
            return root;
        }

        /**
         * 迭代方式 用Stack
         * 对于本题的顺序是：先交换左右子树，再遍历左子树，最后遍历右子树。
         * 因为栈“先入后出”的特点，结合上述的顺序，迭代的过程也就出来了：
         * 每次都是先将根节点放入栈，然后右子树，最后左子树。
         * <p>
         * 具体步骤如下所示：
         * 初始化维护一个栈，将根节点入栈。
         * 当栈不为空时
         * 弹出栈顶元素 node，将栈顶元素 node 的左右子树交换。
         * 若 node 的右子树不为空，右子树入栈。
         * 若 node 的左子树不为空，左子树入栈。
         *
         * @param root
         * @return
         */
        public TreeNode invertTreeByUnRecursiveUseStack(TreeNode root) {
            //递归终止条件
            if (root == null) {
                return null;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            ArrayList<Integer> res = new ArrayList<>();

            while (!stack.isEmpty()) {
                //当前节点出栈
                TreeNode node = stack.pop();
                /* 这三行交换代码可以放在前序后序，如果要放在中序，需要改下node.right != null 还有 stack.push(node.right);，因为中序时，左右交换过了 */
                //将当前节点的左右子树交换
                TreeNode tmp = node.right;
                node.right = node.left;
                node.left = tmp;
                //右子树入栈
                if (node.right != null) {
                    stack.push(node.right);
                }
                //左子树入栈
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            return root;
        }

    }
}
