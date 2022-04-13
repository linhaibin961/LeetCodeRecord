package com.lhb.arrayAndList.doublePointHandleListNode;

import com.lhb.common.ListNode;

/**
 * @program: LeetCodeRecord
 * @description: 19. 删除链表的倒数第 N 个结点（中等）
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 **/
public class _19RemoveNthNodeFromEndOfList {

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：head = [1,2,3,4,5], n = 2
     * 输出：[1,2,3,5]
     * 示例 2：
     * <p>
     * 输入：head = [1], n = 1
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：head = [1,2], n = 1
     * 输出：[1]
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        ListNode list1 = getHeader(ints);
        System.out.println(list1);
        Solution solution = new Solution();
        ListNode listNode = solution.removeNthFromEnd(list1, 2);
        // 输出 [1,2,3,5]
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
        public ListNode removeNthFromEnd(ListNode head, int n) {
            // 要注意slow 加一个哑弹，因为用例中有一个 head = [1] , n = 1 的情况，会导致slow.next.next  报错
            ListNode fast = head, slow = new ListNode(-1, head), dummy = slow;
            for (int i = 0; i < n; i++) {
                fast = fast.next;
            }
            while (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
            slow.next = slow.next.next;
            return dummy.next;
        }
    }


}
