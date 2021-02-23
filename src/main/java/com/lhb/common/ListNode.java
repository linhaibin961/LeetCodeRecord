package com.lhb.common;

/**
 * @program: LeetCodeRecord
 * @description:
 * @author: linhaibin
 * @create: 2021-02-19 17:46
 **/

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
