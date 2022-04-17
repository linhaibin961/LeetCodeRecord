package com.lhb.tree;

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: LeetCodeRecord
 * @description: 543. ⼆叉树的直径（简单）
 * @author: linhaibin
 * @create: 2022-04-17 00:03
 **/
public class _297SerializeAndDeserializeBinaryTree {
    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
     * <p>
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     * <p>
     * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
     * <p>
     *  
     * <p>
     * 示例 1：
     * //       1
     * // ┌─────┴─────┐
     * // 2           3
     * //          ┌──┴──┐
     * //          4     5
     * <p>
     * <p>
     * 输入：root = [1,2,3,null,null,4,5]
     * 输出：[1,2,3,null,null,4,5]
     * 示例 2：
     * <p>
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：root = [1]
     * 输出：[1]
     * 示例 4：
     * <p>
     * 输入：root = [1,2]
     * 输出：[1,2]
     *  
     * <p>
     * 提示：
     * <p>
     * 树中结点数在范围 [0, 104] 内
     * -1000 <= Node.val <= 1000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, -1, -1, 4, 5}; // 层序
        TreeNode treeNode = TreeNodeUtils.createBinTree(array);
        TreePrinter.print(treeNode);
        CodecTraverse codec = new CodecTraverse();
        String serialize = codec.serialize(treeNode);
        System.out.println(serialize);
        TreeNode deserialize = codec.deserialize(serialize);
        TreePrinter.print(deserialize);

    }


    /**
     * 前序
     */
    static class Codec {


        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            traverse(root, sb);
            return sb.toString();
        }

        private void traverse(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,");
                return;
            }
            // 前序
            sb.append(root.val + ",");
            traverse(root.left, sb);
            traverse(root.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] split = data.split(",");
            LinkedList<String> list = new LinkedList();
            for (String s : split) {
                list.add(s);
            }
            TreeNode treeNode = buildTree(list);
            return treeNode;
        }

        private TreeNode buildTree(LinkedList<String> list) {
            if (list.isEmpty()) {
                return null;
            }
            String s = list.removeFirst();
            if ("#".equals(s)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(s));
            TreeNode left = buildTree(list);
            TreeNode right = buildTree(list);
            root.left = left;
            root.right = right;
            return root;
        }
    }

    /**
     * 后序
     */
    static class CodecPostOrder {


        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            traverse(root, sb);
            return sb.toString();
        }

        private void traverse(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append("#,");
                return;
            }
            // 前序
            traverse(root.left, sb);
            traverse(root.right, sb);
            sb.append(root.val + ",");
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] split = data.split(",");
            LinkedList<String> list = new LinkedList();
            for (String s : split) {
                list.add(s);
            }
            TreeNode treeNode = buildTree(list);
            return treeNode;
        }

        private TreeNode buildTree(LinkedList<String> list) {
            if (list.isEmpty()) {
                return null;
            }
            String s = list.removeLast();
            if ("#".equals(s)) {
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(s));
            TreeNode right = buildTree(list);
            TreeNode left = buildTree(list);
            root.left = left;
            root.right = right;
            return root;
        }
    }

    /**
     * 层序
     */
    static class CodecTraverse {


        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            // 初始化队列，将 root 加入队列
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                TreeNode cur = q.poll();

                /* 层级遍历代码位置 */
                if (cur == null) {
                    sb.append("#").append(",");
                    continue;
                }
                sb.append(cur.val).append(",");
                /*****************/

                q.offer(cur.left);
                q.offer(cur.right);
            }

            return sb.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null) {
                return null;
            }
            String[] split = data.split(",");
            LinkedList<TreeNode> list = new LinkedList();
            TreeNode root = new TreeNode(Integer.valueOf(split[0]));
            list.offer(root);
            for (int i = 1; i <= split.length - 1; ) {
                TreeNode parent = list.poll();
                String left = split[i++];
                if (!"#".equals(left)) {
                    parent.left = new TreeNode(Integer.valueOf(left));
                    list.offer(parent.left);
                }

                String right = split[i++];
                if (!"#".equals(right)) {
                    parent.right = new TreeNode(Integer.valueOf(right));
                    list.offer(parent.right);
                }
            }
            return root;
        }

    }
}
