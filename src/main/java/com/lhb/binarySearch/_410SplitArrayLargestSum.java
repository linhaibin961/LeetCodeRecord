package com.lhb.binarySearch;

/**
 * @program: LeetCodeRecord
 * @description: 410. 分割数组的最大值（困难）
 * @author: linhaibin
 * @create: 2022-04-10 10:43
 **/
public class _410SplitArrayLargestSum {

    /**
     * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
     * <p>
     * 设计一个算法使得这 m 个子数组各自和的最大值最小。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [7,2,5,10,8], m = 2
     * 输出：18
     * 解释：
     * 一共有四种方法将 nums 分割为 2 个子数组。
     * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
     * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3,4,5], m = 2
     * 输出：9
     * 示例 3：
     * <p>
     * 输入：nums = [1,4,4], m = 3
     * 输出：4
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] <= 106
     * 1 <= m <= min(50, nums.length)
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    /**
     * 这道题和1011. 在D天内送达包裹的能⼒（中等） 代码一模一样，不用改都能提交
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] weights = new int[]{7, 2, 5, 10, 8};

        Solution solution = new Solution();
        System.out.println(solution.splitArray(weights, 2));

    }


    static class Solution {
        public int splitArray(int[] nums, int m) {
            int left = 0, right = 0;
            // 确定left和right
            for (int weight : nums) {
                left = Math.max(left, weight);
                right += weight;
            }
            int mid = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                // func 是基于mid单调递减，也就是说mid越大，func越小。
                // 代入情景，运载力约大，花费的天数越小。
                int func = func2(nums, mid);
                if (func > m) {
                    // func比h大了，所以要让func小一点，就得让mid大一点。得让mid大一点，就得让left大一点。
                    left = mid + 1;
//                } else if (func <= m) {
                } else {
                    // func比h小了，所以要让func大一点，就得让mid小一点。得让mid小一点，就得让right小一点。
                    right = mid - 1;
                }
                /*else if (func == m) {
                    // 这道题是求最左边界，所以固定right，让mid往left试探，找到最左边界。
                    right = mid - 1;
                }*/
            }
            return left;
        }

        /**
         * 运载力与天数单调递减,运载力越大，天数越小
         *
         * @param weights
         * @param x
         * @return
         */
        public int func(int[] weights, int x) {
            int days = 0;
            int temp = x;
            for (int i = 0; i < weights.length; i++) {
                if (weights[i] > temp) {
                    days++;
                    temp = x;
                    i--;
                    continue;
                }
                temp -= weights[i];
            }
            return days + 1;
        }

        /**
         * LeetCode 上另外一种
         *
         * @param nums
         * @param x
         * @return
         */
        public int func2(int[] nums, int x) {
            int sum = 0;
            int count = 1;
            for (int i = 0; i < nums.length; i++) {
                // 尝试加上当前遍历的这个数，如果加上去超过了「子数组各自的和的最大值」，就不加这个数，另起炉灶
                if (sum + nums[i] > x) {
                    count++;
                    sum = nums[i];
                } else {
                    sum += nums[i];
                }
            }
            return count;
        }
    }


}
