package com.lhb.doublePointHandleArray;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 283. 移动零（简单）
 * @author: linhaibin
 * @create: 2022-04-18 10:43
 **/
public class _283MoveZeroes {

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 示例 2:
     * <p>
     * 输入: nums = [0]
     * 输出: [0]
     *  
     * <p>
     * 提示:
     * <p>
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     *  
     * <p>
     * 进阶：你能尽量减少完成的操作次数吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/move-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] ints = new int[]{0, 1, 0, 3, 12};
        int[] ints2 = new int[]{0};
        Solution solution = new Solution();
        solution.moveZeroes(ints);
        System.out.println( Arrays.toString(ints));

        solution.moveZeroes(ints2);
        System.out.println( Arrays.toString(ints2));

    }


    static class Solution {
        public void moveZeroes(int[] nums) {
            int length = nums.length;
            int fast = 0, slow = 0;
            while (fast < length) {
                if (nums[fast] != 0) {
                    nums[slow] = nums[fast];
                    slow++;
                }
                fast++;
            }
            for (int i = slow; i < length; i++) {
                nums[i] = 0;
            }
        }
    }


}
