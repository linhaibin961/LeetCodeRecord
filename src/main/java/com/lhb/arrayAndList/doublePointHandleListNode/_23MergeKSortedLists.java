package com.lhb.arrayAndList.doublePointHandleListNode;

import com.lhb.common.ListNode;

import java.util.PriorityQueue;

/**
 * @program: LeetCodeRecord
 * @description: 23. 合并K个升序链表
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 **/
public class _23MergeKSortedLists {

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * <p>
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     * 1->4->5,
     * 1->3->4,
     * 2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     * 示例 2：
     * <p>
     * 输入：lists = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：lists = [[]]
     * 输出：[]
     *  
     * <p>
     * 提示：
     * <p>
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] 按 升序 排列
     * lists[i].length 的总和不超过 10^4
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] ints = new int[]{1, 4, 5};
        int[] ints2 = new int[]{1, 3, 4};
        int[] ints3 = new int[]{2, 6};
        ListNode list1 = getHeader(ints);
        System.out.println(list1);
        ListNode list2 = getHeader(ints2);
        System.out.println(list2);
        ListNode list3 = getHeader(ints3);
        System.out.println(list3);
        ListNode[] list = new ListNode[]{list1, list2, list3};


        Solution solution = new Solution();
        ListNode listNode = solution.mergeKLists(list);
        // 1->1->2->3->4->4->5->6
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
        public ListNode mergeKLists(ListNode[] list) {
            if (list.length == 0) return null;
            ListNode dummy = new ListNode(-1), p = dummy;
            PriorityQueue<ListNode> queue = new PriorityQueue<>(list.length, (a, b) -> (a.val - b.val));
            for (int i = 0; i < list.length; i++) {
                if (list[i] != null) {
                    queue.add(list[i]);
                }
            }
            while (!queue.isEmpty()) {
                ListNode poll = queue.poll();
                p.next = poll;
                if (poll.next != null) {
                    queue.add(poll.next);
                }
                p = p.next;
            }

            return dummy.next;
        }
    }


}
