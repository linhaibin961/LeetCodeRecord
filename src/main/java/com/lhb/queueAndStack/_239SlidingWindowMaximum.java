package com.lhb.queueAndStack;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 739. 每⽇温度（中等）
 * @author: linhaibin
 * @create: 2022-04-11 10:43
 **/
public class _239SlidingWindowMaximum {

    /**
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * <p>
     * 返回 滑动窗口中的最大值 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7      3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * 示例 2：
     * <p>
     * 输入：nums = [1], k = 1
     * 输出：[1]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 105
     * -104 <= nums[i] <= 104
     * 1 <= k <= nums.length
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] num = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(num, 3)));
    }

    static class Solution {

        public int[] maxSlidingWindow(int[] nums, int k) {
            int length = nums.length;
            List<Integer> list = new ArrayList();
            MonotonicQueue monotonicQueue = new MonotonicQueue();
            for (int i = 0; i < length; i++) {
                int num = nums[i];
                if (i < k - 1) {
                    monotonicQueue.push(num);
                } else {
                    monotonicQueue.push(num);
                    list.add(monotonicQueue.max());
                    monotonicQueue.pop(nums[i - k + 1]);
                }
            }
            int[] res = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
        }

        class MonotonicQueue {
            LinkedList<Integer> list = new LinkedList<>();

            public int max() {
                return list.peekFirst();
            }

            public boolean push(int n) {
                while (!list.isEmpty() && n > list.peekLast()) {
                    list.pollLast();
                }
                list.addLast(n);
                return true;
            }

            public boolean pop(int n) {
                if (n == list.peekFirst()) {
                    list.removeFirst();
                }
                return true;
            }
        }

    }

}
