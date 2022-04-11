package com.lhb.queueAndStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 496. 下⼀个更⼤元素I（简单）
 * @author: linhaibin
 * @create: 2022-04-11 10:43
 **/
public class _493NextGreaterElement1 {

    /**
     * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
     * <p>
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
     * <p>
     * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
     * <p>
     * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出：[-1,3,-1]
     * 解释：nums1 中每个值的下一个更大元素如下所述：
     * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
     * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
     * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
     * 示例 2：
     * <p>
     * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
     * 输出：[3,-1]
     * 解释：nums1 中每个值的下一个更大元素如下所述：
     * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
     * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums1.length <= nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 104
     * nums1和nums2中所有整数 互不相同
     * nums1 中的所有整数同样出现在 nums2 中
     *  
     * <p>
     * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-i
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] num1 = new int[]{4, 1, 2};
        int[] num2 = new int[]{1, 3, 4, 2};
        Solution solution = new Solution();
        System.out.println(solution.nextGreaterElement(num1, num2));
    }

    static class Solution {

        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Map<Integer, Integer> map = new HashMap<>();
            Stack<Integer> stack = new Stack();
            for (int i = nums2.length - 1; i >= 0; i--) {
                int value = nums2[i];
                while (!stack.empty() && value > stack.peek()) {
                    stack.pop();
                }
                int nextGreaterElement = stack.empty() ? -1 : stack.peek();
                map.put(value, nextGreaterElement);
                stack.push(value);
            }
            int[] res = new int[nums1.length];
            for (int i = 0; i < nums1.length; i++) {
                res[i] = map.get(nums1[i]);
            }
            return res;
        }

    }
}
