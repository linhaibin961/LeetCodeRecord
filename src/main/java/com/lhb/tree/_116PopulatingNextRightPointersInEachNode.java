package com.lhb.tree;

import com.lhb.common.Node;
import com.lhb.common.TreePrinter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: LeetCodeRecord
 * @description: 114. ⼆叉树展开为链表（中等）
 * @author: linhaibin
 * @create: 2022-04-15 23:03
 **/
public class _116PopulatingNextRightPointersInEachNode {
    /**
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     * <p>
     * struct Node {
     * int val;
     * Node *left;
     * Node *right;
     * Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * <p>
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * <p>
     *  
     * <p>
     * 示例 1：
     * //                   1                                              1----->null
     * //       ┌───────────┴───────────┐                      ┌───────────┴───────────┐
     * //       2                       3                      2---------------------->3----->null
     * // ┌─────┴─────┐           ┌─────┴─────┐          ┌─────┴─────┐           ┌─────┴─────┐
     * // 4           5           6           7          4---------->5---------->6---------->7----->null
     * <p>
     * <p>
     * <p>
     * 输入：root = [1,2,3,4,5,6,7]
     * 输出：[1,#,2,3,#,4,5,6,7,#]
     * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
     * 示例 2:
     * <p>
     * 输入：root = []
     * 输出：[]
     *  
     * <p>
     * 提示：
     * <p>
     * 树中节点的数量在 [0, 212 - 1] 范围内
     * -1000 <= node.val <= 1000
     *  
     * <p>
     * 进阶：
     * <p>
     * 你只能使用常量级额外空间。
     * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Integer[] ints = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}; //层序
//        Integer[] ints = new Integer[]{1, 2, 3, 4, 5, null, 7, null, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}; //层序
        System.out.println(Arrays.toString(ints));

        Node treeNode = createTree2(0, ints);
        TreePrinter.print(treeNode);
        Solution solution = new Solution();
        Node connect = solution.connect(treeNode);
        TreePrinter.print(connect);
    }

    public static Node createTree2(int index, Integer[] arr) {
        if (index >= arr.length || arr[index] == null) {
            return null;
        }
        Node root = new Node();
        root.val = arr[index];
        root.left = createTree2(index * 2 + 1, arr);
        root.right = createTree2(index * 2 + 2, arr);
        return root;
    }

    static class Solution {
        /**
         * 遍历思维
         *
         * @param root
         */
        public Node connect(Node root) {
//            traverse(root.left, root.right);
//            traverse1(root, root.left, root.right);
//            traverse2(root);
            traverse3(root);
            return root;
        }

        /**
         * labuladong里面的解法
         * 遍历思维
         * 2、这题能不能⽤「分解问题」的思维模式解决？
         * 嗯，好像没有什么特别好的思路，所以这道题⽆法使⽤「分解问题」的思维来解决。
         *
         * @param left
         * @param right
         */
        private void traverse(Node left, Node right) {
            if (left == null || right == null) return;
            left.next = right;

            traverse(left.left, left.right);
            traverse(left.right, right.left);
            traverse(right.left, right.right);
        }

        /**
         * 这种自己写的，解不了
         *
         * @param root
         * @param left
         * @param right
         */
        private void traverse1(Node root, Node left, Node right) {
            if (root == null || left == null || right == null) return;
            root.left.next = root.right;
            if (left.left == null || right.left == null) return;
            left.left.next = left.right;
            left.right.next = right.left;
            right.left.next = right.right;
            traverse1(root.left, root.left.left, root.left.right);
            traverse1(root.left, root.left.right, root.right.left);
            traverse1(root.right, root.left.right, root.right.left);
            traverse1(root.right, root.right.left, root.right.right);
        }

        /**
         * 迭代处理，层序遍历
         *
         * @param root
         */
        private void traverse2(Node root) {
            Queue<Node> queue = new LinkedList();
            queue.offer(root);
            while (!queue.isEmpty()) {

                for (int i = 0; i < queue.size(); i++) {
                    Node node = queue.poll();
                    if (i < queue.size()) {
                        node.next = queue.peek();
                    }
                    Node left = node.left;
                    Node right = node.right;
                    if (left != null) {
                        queue.offer(left);
                    }
                    if (right != null) {
                        queue.offer(right);
                    }
                }
            }
        }

        /**
         * 这种也能解，但是没有框架思维去解，不要过多沉迷
         *
         * @param root
         * @return
         */
        public Node traverse3(Node root) {
            if (root == null)
                return null;
            Node pre = root;
            Node cur = null;
            while (pre.left != null) {
                //遍历当前这一层的结点，然后把他们的下一层连接起来
                cur = pre;
                //cur不为空，就表示这一层还没遍历完，就继续循环
                while (cur != null) {
                    //让下一层的左子节点指向右子节点
                    cur.left.next = cur.right;
                    //如果cur.next不为空，就表示还没遍历到这一层
                    //最后的那个节点的右子节点，就让前一个结点的右
                    //子节点指向下一个节点的左子节点
                    if (cur.next != null)
                        cur.right.next = cur.next.left;
                    //然后继续连接下一个节点的 子节点
                    cur = cur.next;
                }
                //继续下一层
                pre = pre.left;
            }
            return root;
        }
    }
}
