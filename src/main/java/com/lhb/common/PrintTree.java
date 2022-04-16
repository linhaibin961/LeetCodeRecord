package com.lhb.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * 通过数组打印二叉树，如果不是完整二叉树打印不了
 * author：byte(234697380@qq.com)
 * createTime：2020-10-23 23:30
 * </pre>
 */
public class PrintTree {

    public static void main(String[] args) {
        print(50, 30, 2, 35, 80, 70, 801, 6560, 75, 55, 65, 72, 78, 52, 58, 51, 53);

    }

    public static void print(int... arr) {
        MyBST myBST = new MyBST(arr);
        calcPosition(myBST.root, 0);
        printNodes(Arrays.asList(myBST.root));
        System.out.println("\n________________________________________________________________________________________\n");
    }

    public static void printNodes(List<Node> nodes) {
        List<Node> newNodes = new ArrayList<>(nodes.size() * 2);
        String xuxianRow = "", shuxianRow = "", dataRow = "";

        int upPosition = 0;
        int upDataLen = 0;
        for (int i = 0; i < nodes.size(); i++) {
            Node node = nodes.get(i);
            //数据行
            int printSpaceNum = node.position - upPosition - upDataLen;
            for (int j = 0; j < printSpaceNum; j++) {
                dataRow += " ";
            }
            dataRow += node.data;

            //虚线行和竖线行
            Node parent = node.parent;
            if (parent != null) {
                int spaceNum = 0, xuxianLimit = 0, type = 0;
                if (node == parent.left) {
                    spaceNum = node.position - xuxianRow.length() + getDataLength(node) / 2;
                    if (parent.right == null) {
                        type = 1;
                        xuxianLimit = parent.position + 1;
                    } else {
                        type = 2;
                        xuxianLimit = parent.right.position + (int) Math.ceil(getDataLength(parent.right) / 2.0);
                    }
                } else if (parent.left == null) {
                    type = 3;
                    spaceNum = parent.position + getDataLength(parent) - 1 - xuxianRow.length();
                    xuxianLimit = node.position + (int) Math.ceil(getDataLength(node) / 2.0);
                }

                if (type > 0) {
                    for (int j = 0; j < spaceNum; j++) {
                        xuxianRow += " ";
                        shuxianRow += " ";
                    }

                    int xuxianLength = xuxianLimit - xuxianRow.length();
                    for (int j = 0; j < xuxianLength; j++) {
                        xuxianRow += "-";
                        shuxianRow += (j == 0 && type != 3) || (j == xuxianLength - 1 && type != 1) ? "¦" : " ";
                    }
                }
            }

            //记录子节点为打印下一行做准备
            if (node.left != null) {
                newNodes.add(node.left);
            }
            if (node.right != null) {
                newNodes.add(node.right);
            }
            upPosition = node.position;
            upDataLen = getDataLength(node);
        }
        if (nodes.get(0).parent != null) {
            System.out.println(xuxianRow);
            System.out.println(shuxianRow);
        }
        System.out.println(dataRow);

        if (!newNodes.isEmpty()) {
            printNodes(newNodes);
        }
    }

    /**
     * 计算节点位置
     */
    public static int calcPosition(Node node, int nextPosition) {
        if (node.left != null) {
            nextPosition = calcPosition(node.left, nextPosition);
        }

        node.position = nextPosition;
        nextPosition = node.position + getDataLength(node) + 1;

        if (node.right != null) {
            nextPosition = calcPosition(node.right, nextPosition);
        }

        return nextPosition;
    }

    private static int getDataLength(Node node) {
        return String.valueOf(node.data).length();
    }

   static class Node<T> {

        public Node<T> parent, left, right;
        public T data;

        public Node() {
            this.data = data;
        }

        public Node(T data) {
            this.data = data;
        }

        public int position;

    }

    static class MyBST {

        public Node root;

        public MyBST(int... arr) {
//        for (int i = 0; i < arr.length; i++) {
//            root = addNode(root, new Node(arr[i]));
//        }
            root = addNode(null, 0, arr);
        }

        private Node addNode(Node parent, int index, int[] arr) {
            if (index >= arr.length) {
                return null;
            }
            Node root = new Node();
            root.data = arr[index];
            root.left = addNode(root, index * 2 + 1, arr);
            root.right = addNode(root, index * 2 + 2, arr);
            if (parent != null) {
                root.parent = parent;
            }
            return root;
        }

        public MyBST(List<TreeNode> list) {
            for (int i = 0; i < list.size(); i++) {
                root = addNode(root, new Node(list.get(i).val));
            }
        }

        public Node addNode(Node<Integer> parent, Node<Integer> child) {
            if (parent == null) {
                parent = child;
            } else if (parent.data > child.data) {
                parent.left = addNode(parent.left, child);
                parent.left.parent = parent;
            } else if (parent.data < child.data) {
                parent.right = addNode(parent.right, child);
                parent.right.parent = parent;
            }
            return parent;
        }
    }

}