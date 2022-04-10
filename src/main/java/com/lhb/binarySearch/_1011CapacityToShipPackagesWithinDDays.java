package com.lhb.binarySearch;

/**
 * @program: LeetCodeRecord
 * @description: 1011. 在D天内送达包裹的能⼒（中等）
 * @author: linhaibin
 * @create: 2022-04-10 10:43
 **/
public class _1011CapacityToShipPackagesWithinDDays {

    /**
     * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
     * <p>
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     * <p>
     * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
     * 输出：15
     * 解释：
     * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
     * 第 1 天：1, 2, 3, 4, 5
     * 第 2 天：6, 7
     * 第 3 天：8
     * 第 4 天：9
     * 第 5 天：10
     * <p>
     * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
     * 示例 2：
     * <p>
     * 输入：weights = [3,2,2,4,1,4], days = 3
     * 输出：6
     * 解释：
     * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
     * 第 1 天：3, 2
     * 第 2 天：2, 4
     * 第 3 天：1, 4
     * 示例 3：
     * <p>
     * 输入：weights = [1,2,3,1,1], days = 4
     * 输出：3
     * 解释：
     * 第 1 天：1
     * 第 2 天：2
     * 第 3 天：3
     * 第 4 天：1, 1
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= days <= weights.length <= 5 * 104
     * 1 <= weights[i] <= 500
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        int[] weights = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Solution solution = new Solution();
        System.out.println(solution.shipWithinDays(weights, 5));

    }


    static class Solution {
        public int shipWithinDays(int[] weights, int days) {
            int left = 0, right = 0;
            // 确定left和right， 因为包裹不可分割，所以left至少是最大包裹的重量。right最大是一趟把所有包裹载走，所以是包裹的总和。
            for (int weight : weights) {
                left = Math.max(left, weight);
                right += weight;
            }
            int mid = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                // func 是基于mid单调递减，也就是说mid越大，func越小。
                // 代入情景，运载力约大，花费的天数越小。
                int func = func(weights, mid);
                if (func > days) {
                    // func比h大了，所以要让func小一点，就得让mid大一点。得让mid大一点，就得让left大一点。
                    left = mid + 1;
//                } else if (func <= days) {
                } else {
                    // func比h小了，所以要让func大一点，就得让mid小一点。得让mid小一点，就得让right小一点。
                    right = mid - 1;
                }
                /*else if (func == days) {
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
         * @param weights
         * @param x
         * @return
         */
        public int func2(int[] weights, int x) {
            int sum = 0;
            int days = 0;
            for (int i = 0; i < weights.length; i++) {
                // 尝试加上当前遍历的这个数，如果加上去超过了「子数组各自的和的最大值」，就不加这个数，另起炉灶
                if (sum + weights[i] > x) {
                    days++;
                    sum = weights[i];
                } else {
                    sum += weights[i];
                }
            }
            return days + 1;
        }
    }
}
