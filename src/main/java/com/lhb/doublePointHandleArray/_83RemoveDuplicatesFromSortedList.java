package com.lhb.doublePointHandleArray;

import com.lhb.common.ListNode;

/**
 * @program: LeetCodeRecord
 * @description: 83. 删除排序链表中的重复元素（简单）
 * @author: linhaibin
 * @create: 2022-04-18 10:43
 **/
public class _83RemoveDuplicatesFromSortedList {

    /**
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
     * <p>
     * 1->1->2
     * 1->2
     * 示例 1：
     * <p>
     * <p>
     * 输入：head = [1,1,2]
     * 输出：[1,2]
     * 示例 2：
     * 1->1->2->3->3
     * 1->2->3
     * 输入：head = [1,1,2,3,3]
     * 输出：[1,2,3]
     *  
     * <p>
     * 提示：
     * <p>
     * 链表中节点数目在范围 [0, 300] 内
     * -100 <= Node.val <= 100
     * 题目数据保证链表已经按升序 排列
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] ints = new int[]{1, 1, 2};
        int[] ints2 = new int[]{1, 1, 2, 3, 3};
        ListNode list1 = getHeader(ints);
        System.out.println(list1);

        ListNode list2 = getHeader(ints2);
        System.out.println(list2);


        Solution solution = new Solution();
        ListNode listNode1 = solution.deleteDuplicates(list1);
        System.out.println(listNode1);
        ListNode listNode2 = solution.deleteDuplicates(list2);
        System.out.println(listNode2);
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
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) return null;
            ListNode dummy = new ListNode(-1, head);
            ListNode slow = head, fast = head;
            while (fast != null) {
                if (slow.val != fast.val) {
                    slow.next = fast;
                    slow = slow.next;
                }
                fast = fast.next;
            }
            slow.next = null;
            return dummy.next;
        }
    }


}
