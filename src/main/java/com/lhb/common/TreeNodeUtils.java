package com.lhb.common;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description:
 * @author: linhaibin
 * @create: 2022-04-13 23:32
 **/
public class TreeNodeUtils {
    /**
     * 通过数组构建平衡二叉树
     *
     * @param array
     * @return
     */
    public static TreeNode createBinTree0(int[] array) {
        List<TreeNode> nodeList = new LinkedList<TreeNode>();
        //并把数组中的值都转化为树结点的值，存储到list中
        for (int i = 0; i < array.length; i++) {
            nodeList.add(new TreeNode(array[i]));
        }
        for (int j = 0; j < array.length / 2 - 1; j++) {
            //左孩子
            nodeList.get(j).left = nodeList.get(j * 2 + 1);
            //右孩子
            nodeList.get(j).right = nodeList.get(j * 2 + 2);
        }

        //最后一个父结点，可能没有右孩子
        int lastParent = array.length / 2 - 1;
        //所以，先处理左孩子
        nodeList.get(lastParent).left = nodeList.get(lastParent * 2 + 1);
        //如果数组长度为奇数，那么就建立右孩子
        if (array.length % 2 == 1) {
            nodeList.get(lastParent).right = nodeList.get(lastParent * 2 + 2);
        }
        //第一个结点就是根结点
        return nodeList.get(0);
    }

    /**
     * @param array 层序 值等于-1为占位的空节点
     * @return
     */
    public static TreeNode createBinTree(int[] array) {
        List<TreeNode> nodeList = new LinkedList<TreeNode>();
        //并把数组中的值都转化为树结点的值，存储到list中
        for (int i = 0; i < array.length; i++) {
            nodeList.add(new TreeNode(array[i]));
        }
        for (int j = 0; j < array.length / 2 - 1; j++) {
            //左孩子
            TreeNode left = nodeList.get(j * 2 + 1);
            if (left.val != -1) {
                nodeList.get(j).left = left;
            }
            TreeNode right = nodeList.get(j * 2 + 2);
            //右孩子
            if (right.val != -1) {
                nodeList.get(j).right = right;
            }
        }

        //最后一个父结点，可能没有右孩子
        int lastParent = array.length / 2 - 1;
        //所以，先处理左孩子
        TreeNode left = nodeList.get(lastParent * 2 + 1);
        if (left.val != -1) {
            nodeList.get(lastParent).left = left;
        }
        //如果数组长度为奇数，那么就建立右孩子
        if (array.length % 2 == 1) {
            TreeNode right = nodeList.get(lastParent * 2 + 2);
            if (right.val != -1) {
                nodeList.get(lastParent).right = right;
            }
        }
        //第一个结点就是根结点
        return nodeList.get(0);
    }

    /**
     * 递归生成树
     *
     * @param index
     * @param arr
     * @return
     */
    public static TreeNode createTree2(int index, int[] arr) {
        if (index >= arr.length) {
            return null;
        }
        TreeNode root = new TreeNode();
        root.val = arr[index];
        root.left = createTree2(index * 2 + 1, arr);
        root.right = createTree2(index * 2 + 2, arr);
        return root;
    }
    /**
     * 调用以构造二叉树，BFS
     * @param vals 数组
     * @return
     */
    public static TreeNode InitTree(Integer[] vals) {
        TreeNode root = new TreeNode(vals[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        int cur = 1;
        queue.offer(root);
        while (queue != null) {
            TreeNode r = queue.poll();
            if (vals[cur] == null) {
                r.left=null;
            } else {
                r.left = new TreeNode(vals[cur]);
                queue.offer(r.left);
            }
            if (++cur >= vals.length) {
                break;
            }
            if (vals[cur] == null) {
                r.right = null;
            } else {
                r.right = new TreeNode(vals[cur]);
                queue.offer(r.right);
            }
            if (++cur >= vals.length) {
                break;
            }
        }
        return root;
    }
    /**
     * 递归前序
     *
     * @param root
     */
    public static void getPreOrder(TreeNode root, LinkedList<Integer> list) {

        if (root == null)
            return;
        list.addLast(root.val);
        getPreOrder(root.left, list);
        getPreOrder(root.right, list);
    }

    /**
     * 递归前序
     *
     * @param root
     */
    public static void preOrder(TreeNode root) {
        if (root == null)
            return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 递归前序
     *
     * @param root
     */
    public static void getInorder(TreeNode root, LinkedList<Integer> list) {

        if (root == null)
            return;
        getPreOrder(root.left, list);
        list.addLast(root.val);
        getPreOrder(root.right, list);
    }

    /**
     * 递归中序
     *
     * @param root
     */
    public static void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    /**
     * 递归后序
     *
     * @param root
     */
    public static void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    /**
     * 非递归前序
     *
     * @param root
     */
    public static void preOrder2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                System.out.print(pNode.val + " ");
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                pNode = node.right;
            }
        }
    }

    /**
     * 非递归前序 方法二
     *
     * @param root
     */
    public static void preOrder3(TreeNode root)// 非递归前序 栈 先左后右  t一般为root
    {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root == null)
            return;
        if (root != null) {
            stack.push(root);
        }
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            System.out.print(node.val + " ");
        }
    }

    /**
     * 非递归中序
     *
     * @param root
     */
    public static void inOrder2(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                TreeNode node = stack.pop();
                System.out.print(node.val + " ");
                pNode = node.right;
            }
        }
    }

    /**
     * 非递归中序 方法二
     *
     * @param root
     */
    public void inOrder3(TreeNode root)// 先储藏所有左侧点，抛出一个点，访问该点右节点，对右节点在储存所有子左节点
    {
        Stack<TreeNode> stack = new Stack();
        if (root == null)
            return;
        if (root != null) {
            stack.push(root);
        }
        TreeNode t1 = stack.peek();// 不能抛出，要先存最左侧
        while (t1.left != null) {
            t1 = t1.left;
            stack.push(t1);
        }
        while (!stack.isEmpty()) {
            TreeNode t2 = stack.pop();
            System.out.print(t2.val + " ");
            if (t2.right != null) {
                t2 = t2.right;
                stack.push(t2);
                while (t2.left != null) {
                    t2 = t2.left;
                    stack.push(t2);
                }
            }
        }
    }


    /**
     * 非递归后序遍历
     *
     * @param root
     */
    public static void postOrder2(TreeNode root) {
        TreeNode prev = null;
        Stack<TreeNode> s = new Stack<>();
        while (root != null || !s.empty()) {
            while (root != null) {
                s.add(root);
                root = root.left;
            }
            root = s.pop();
            if (root.right == null || root.right == prev) {
                System.out.print(root.val + " ");
                prev = root;
                root = null;
            } else {
                s.add(root);
                root = root.right;
            }
        }
    }

    /**
     * 层次遍历
     * 只需要一个队列即可，先在队列中加入根结点。
     * 之后对于任意一个结点来说，在其出队列的时候，访问之。
     * 同时如果左孩子和右孩子有不为空的，入队列。
     *
     * @param root
     */
    public static void levelTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    public static void getLevelTraverse(TreeNode root, LinkedList<Integer> list) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.addLast(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 深度优先遍历
     * 深度遍历就是上面的前序、中序和后序。
     * 但是为了保证与广度优先遍历相照应，也写在这。
     * 代码也比较好理解，其实就是前序遍历
     *
     * @param root
     */
    public static void depthOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * //    4
     * //  2   7
     * // 1 3 6 9
     */
/*
    public static void drawTree(TreeNode root) {
        List<List<String>> lists = printTree(root);
        for (int i = 0; i < lists.size(); i++) {
            List<String> list = lists.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.printf(list.get(j));
            }
            System.out.println("");

        }
    }

    private static String getSpaces(int i) {
        StringBuilder sb = new StringBuilder();
        while (i - 1 >= 0) {
            sb.append(" ");
            i--;
        }
        return sb.toString();
    }

    public static List<List<String>> printTree(TreeNode root) {
        int height = getHeight(root);
        String[][] res = new String[height][(1 << height) - 1];
        for (String[] arr : res)
            Arrays.fill(arr, " ");
        List<List<String>> ans = new ArrayList<>();
        fill(res, root, 0, 0, res[0].length);
        for (String[] arr : res)
            ans.add(Arrays.asList(arr));
        return ans;
    }

    public static void fill(String[][] res, TreeNode root, int i, int l, int r) {
        if (root == null)
            return;
        res[i][(l + r) / 2] = "" + root.val;
        fill(res, root.left, i + 1, l, (l + r) / 2);
        fill(res, root.right, i + 1, (l + r + 1) / 2, r);
    }

    public static int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }*/
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        TreeNode binTree = createBinTree(array);
        System.out.println("前序:");
        preOrder(binTree);

        System.out.println("");
        System.out.println("中序:");
        inorder(binTree);

        System.out.println("");
        System.out.println("后序:");
        postOrder(binTree);

        System.out.println("");
        System.out.println("非递归前序:");
        preOrder2(binTree);

        System.out.println("");
        System.out.println("非递归中序:");
        inOrder2(binTree);

        System.out.println("");
        System.out.println("非递归后序:");
        postOrder2(binTree);

        System.out.println("");
        System.out.println("层序遍历:");
        levelTraverse(binTree);

        System.out.println("");
        System.out.println("深度优先遍历(前序）:");
        depthOrderTraverse(binTree);

    }
}
