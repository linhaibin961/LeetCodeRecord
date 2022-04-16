package com.lhb.common;

/**
 * @program: LeetCodeRecord
 * @description:
 * @author: linhaibin
 * @create: 2022-04-15 23:23
 **/
public class Node implements TreePrinter.PrintableNode {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
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
        return this.val + "";
    }

    @Override
    public String toString() {
        String left = this.left != null ? this.left.val + "" : "null";
        String right = this.right != null ? this.right.val + "" : "null";
        String next = this.next != null ? this.next.val + "" : "null";
        return "Node{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                ", next=" + next +
                '}';
    }
}