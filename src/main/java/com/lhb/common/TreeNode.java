package com.lhb.common;

/**
 * @program: LeetCodeRecord
 * @description:
 * @author: linhaibin
 * @create: 2021-02-19 17:46
 **/

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
