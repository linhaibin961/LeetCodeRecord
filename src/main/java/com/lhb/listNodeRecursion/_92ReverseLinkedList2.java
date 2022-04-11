package com.lhb.listNodeRecursion;

import com.lhb.common.ListNode;

/**
 * @program: LeetCodeRecord
 * @description: 92. 反转链表 II（中等）
 * @author: linhaibin
 * @create: 2022-04-10 17:43
 **/
public class _92ReverseLinkedList2 {

    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     *  
     * <p>
     * 示例 1：
     * 1->2->3->4->5
     * 1->4->3->2->5
     * 输入：head = [1,2,3,4,5], left = 2, right = 4
     * 输出：[1,4,3,2,5]
     * 示例 2：
     * <p>
     * 输入：head = [5], left = 1, right = 1
     * 输出：[5]
     *  
     * <p>
     * 提示：
     * <p>
     * 链表中节点数目为 n
     * 1 <= n <= 500
     * -500 <= Node.val <= 500
     * 1 <= left <= right <= n
     *  
     * <p>
     * 进阶： 你可以使用一趟扫描完成反转吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        ListNode list1 = getHeader(ints);
        System.out.println(list1);
        Solution solution = new Solution();
        ListNode reverseListN = solution.reverseListN(list1, 3);
        // 输出 [3,2,1,4,5]
        System.out.println(reverseListN);
        ListNode list2 = getHeader(ints);

        ListNode reverseBetween = solution.reverseBetween(list2, 2, 4);
        // 输出 [1,4,3,2,5]
        System.out.println(reverseBetween);
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
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (left == 1) {
                ListNode last = reverseListN(head, right);
                return last;
            }
            head.next = reverseBetween(head.next, left - 1, right - 1);
            return head;
        }

        // 后驱节点
        ListNode successor = null;

        /**
         * 反转以 head 为起点的 n 个节点，返回新的头结点
         *
         * @param head
         * @param n
         * @return
         */
        public ListNode reverseListN(ListNode head, int n) {
            if (n == 1) {
                // 记录第 n + 1 个节点
                successor = head.next;
                return head;
            }
            // 以 head.next 为起点，需要反转前 n - 1 个节点
            ListNode last = reverseListN(head.next, n - 1);
            head.next.next = head;
            // 让反转之后的 head 节点和后⾯的节点连起来
            // 这一步只有原head的第一个才指向后驱节点，其他的即时执行了，也会被下一次的head.next.next = head 覆盖
            head.next = successor;
            return last;
        }
    }


}
