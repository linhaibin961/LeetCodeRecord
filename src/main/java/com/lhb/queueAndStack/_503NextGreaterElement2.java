package com.lhb.queueAndStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 503. 下⼀个更⼤元素II（中等）
 * @author: linhaibin
 * @create: 2022-04-11 10:43
 **/
public class _503NextGreaterElement2 {

    /**
     * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
     * <p>
     * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     * 示例 2:
     * <p>
     * 输入: nums = [1,2,3,4,3]
     * 输出: [2,3,4,-1,4]
     *  
     * <p>
     * 提示:
     * <p>
     * 1 <= nums.length <= 104
     * -109 <= nums[i] <= 109
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 1};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.nextGreaterElements(num)));
    }

    static class Solution {

        public int[] nextGreaterElements(int[] nums) {
            int length = nums.length;
            int[] res = new int[length];
            Arrays.fill(res, -1);
            Stack<Integer> stack = new Stack();
            for (int i = 0; i <= 2 * length - 1; i++) {
                int value = nums[i % length];
                // 比较当前的值（value）比上一个的值(nums[stack.peek()(peek出来上一次的索引)])大，可以确定上一个值的下一个值（就是题目要的答案）就是当前值
                while (!stack.empty() && value > nums[stack.peek()]) {
                    res[stack.pop()] = value;
                }
                stack.push(i % length);
            }
            return res;
        }
    }
}
