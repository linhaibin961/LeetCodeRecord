package com.lhb.doublePointHandleListNode;

import com.lhb.common.ListNode;

/**
 * @program: LeetCodeRecord
 * @description: 876. 链表的中间结点（简单）
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 **/
public class _876MiddleOfTheLinkedList {

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * <p>
     * 如果有两个中间结点，则返回第二个中间结点。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     * 示例 2：
     * <p>
     * 输入：[1,2,3,4,5,6]
     * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
     * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
     *  
     * <p>
     * 提示：
     * <p>
     * 给定链表的结点数介于 1 和 100 之间。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/middle-of-the-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        ListNode list1 = getHeader(ints);
        System.out.println(list1);
        Solution solution = new Solution();
        ListNode listNode = solution.middleNode(list1);
        // 输出 [1,2,3,5]
        System.out.println(listNode);

        int[] ints2 = new int[]{1, 2, 3, 4, 5, 6};
        ListNode list2 = getHeader(ints2);
        System.out.println(list2);
        ListNode listNode2 = solution.middleNode(list2);
        // 输出 [1,2,3,5]
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
        public ListNode middleNode(ListNode head) {
            ListNode fast = head, slow = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }


}
