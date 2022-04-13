package com.lhb.arrayAndList.listNodeRecursion;

import com.lhb.common.ListNode;

/**
 * @program: LeetCodeRecord
 * @description: 206. 反转链表（简单）
 * @author: linhaibin
 * @create: 2022-04-10 17:43
 **/
public class _206ReverseLinkedList {

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *  
     * <p>
     * 示例 1：
     * 1->2->3->4->5
     * 5->4->3->2->1
     * 输入：head = [1,2,3,4,5]
     * 输出：[5,4,3,2,1]
     * 示例 2：
     * 1->2
     * 2->1
     * 输入：head = [1,2]
     * 输出：[2,1]
     * 示例 3：
     * <p>
     * 输入：head = []
     * 输出：[]
     *  
     * <p>
     * 提示：
     * <p>
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     *  
     * <p>
     * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 5};
        ListNode list1 = getHeader(ints);
        System.out.println(list1);
        Solution solution = new Solution();
        ListNode listNode = solution.reverseList2(list1);
        // 输出 [5,4,3,2,1]
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
        /**
         * 方法一：迭代
         *
         * @param head
         * @return
         */
        public ListNode reverseList(ListNode head) {
            //将当前节点的 next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。
            // 在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }

        /**
         * 方法二：递归
         *
         * @param head
         * @return
         */
        public ListNode reverseList2(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 以 head.next 为起点
            ListNode last = reverseList2(head.next);
            head.next.next = head;
            // 让反转之后的 head 节点和后⾯的节点连起来
            // 这一步只有原head的第一个才指向null，其他的即时执行了，也会被下一次的head.next.next = head 覆盖
            head.next = null;
            return last;
        }

         /*
         https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode-solution-d1k2/1103881
            ListNode last = reverseList2(head.next);
            第一轮出栈，head为5，head.next为空，返回5
            第二轮出栈，head为4，head.next为5，执行head.next.next=head也就是5.next=4，
                      把当前节点的子节点的子节点指向当前节点
                      此时链表为1->2->3->4<->5，由于4与5互相指向，所以此处要断开4.next=null
                      此时链表为1->2->3->4<-5
                      返回节点5
            第三轮出栈，head为3，head.next为4，执行head.next.next=head也就是4.next=3，
                      此时链表为1->2->3<->4<-5，由于3与4互相指向，所以此处要断开3.next=null
                      此时链表为1->2->3<-4<-5
                      返回节点5
            第四轮出栈，head为2，head.next为3，执行head.next.next=head也就是3.next=2，
                      此时链表为1->2<->3<-4<-5，由于2与3互相指向，所以此处要断开2.next=null
                      此时链表为1->2<-3<-4<-5
                      返回节点5
            第五轮出栈，head为1，head.next为2，执行head.next.next=head也就是2.next=1，
                      此时链表为1<->2<-3<-4<-5，由于1与2互相指向，所以此处要断开1.next=null
                      此时链表为1<-2<-3<-4<-5
                      返回节点5
            出栈完成，最终头节点5->4->3->2->1
         */
    }
}
