package com.lhb.common;

/**
 * @program: LeetCodeRecord
 * @description:
 * @author: linhaibin
 * @create: 2021-02-19 17:46
 **/

public class TreeNode implements TreePrinter.PrintableNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public TreePrinter.PrintableNode getLeft() {
        return this.left;
    }

    @Override
    public TreePrinter.PrintableNode getRight() {
        return this.right;
    }

    @Override
    public String getText() {
        return String.valueOf(val);
    }

    /**
     * │       ┌── 1
     * │   ┌── 2
     * │   │   └── 3
     * └── 4
     * │   ┌── 6
     * └── 7
     * └── 9
     */
    /*

    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (right != null) {
            right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(val).append("\n");
        if (left != null) {
            left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }
*/
    @Override
    public String toString() {
        String left = this.left != null ? this.left.val + "" : "null";
        String right = this.right != null ? this.right.val + "" : "null";
        String s = "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
        return s;
    }
}
