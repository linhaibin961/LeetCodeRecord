package com.lhb.doublePointHandleListNode;

import com.lhb.common.ListNode;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 21. 合并两个有序链表
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 **/
public class _21MergeTwoSortedLists {

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     * <p>
     *  21. 合并两个有序链表.png
     * 1->2->4
     * 1->3->4
     * 合并成 1->1->2->3->4->4
     * 示例 1：
     * <p>
     * <p>
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * 示例 2：
     * <p>
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     *  
     * <p>
     * 提示：
     * <p>
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 4};
        int[] ints2 = new int[]{1, 2, 3};
        ListNode list1 = getHeader(ints);
        System.out.println(list1);

        ListNode list2 = getHeader(ints2);
        System.out.println(list2);


        Solution solution = new Solution();
        ListNode listNode = solution.mergeTwoLists(list1, list2);
        System.out.println(listNode);
    }

    private static ListNode getHeader(int[] ints) {
        ListNode listNode = new ListNode(ints[0]), header = listNode;
        for (int i = 1; i < ints.length; i++) {
            listNode.next = new ListNode(ints[i]);
            listNode = listNode.next;
        }
        return header;
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode dummy = new ListNode(-1) ,p = dummy;
            ListNode p1 = list1, p2 = list2;
            while (p1 != null && p2 != null) {
                if (p1.val > p2.val) {
                    p.next = p2;
                    p2 = p2.next;
                } else {
                    p.next = p1;
                    p1 = p1.next;
                }
                p  = p.next;
            }
            if (p1 == null) {
                p.next = p2;
            }
            if (p2 == null) {
                p.next = p1;
            }
            return dummy.next;
        }
    }


}
