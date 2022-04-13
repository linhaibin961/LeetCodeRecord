package com.lhb.arrayAndList.differenceArray;

/**
 * @program: LeetCodeRecord
 * @description: 370. 区间加法（中等）
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 * @see https://leetcode-cn.com/problems/car-pooling/
 **/
public class _1094CarPooling {

    /**
     * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
     * <p>
     * 给定整数 capacity 和一个数组 trips ,  trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
     * <p>
     * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
     * 输出：false
     * 示例 2：
     * <p>
     * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
     * 输出：true
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= trips.length <= 1000
     * trips[i].length == 3
     * 1 <= numPassengersi <= 100
     * 0 <= fromi < toi <= 1000
     * 1 <= capacity <= 105
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/car-pooling
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] ints = new int[][]{{2, 1, 5}, {3, 3, 7}};
        Solution solution = new Solution();
        System.out.println(solution.carPooling(ints, 4));// 输出 false
        System.out.println(solution.carPooling(ints, 5));// 输出 true
    }

    static class Solution {
        public boolean carPooling(int[][] trips, int capacity) {
            // 最多有 1001 个⻋站 根据输入参数的限制（1 <= trips.length <= 1000）
            int[] nums = new int[1001];
            // 构造差分解法
            Difference df = new Difference(nums);

            for (int[] trip : trips) {
                // 乘客数量
                int val = trip[0];
                // 第 trip[1] 站乘客上⻋
                // 注意转成数组索引不要减一哦，为什么不减呢？
                int i = trip[1];
                // 第 trip[2] 站乘客已经下⻋
                // 即乘客在⻋上的区间是 [trip[1], trip[2] - 1]
                // 注意转成数组索引要减一哦
                int j = trip[2] - 1;
                // 对区间 nums[i..j] 增加 val
                df.increment(i, j, val);
            }
            // 客⻋⾃始⾄终都不应该超载
            int[] result = df.getResult();
            for (int i = 0; i < result.length; i++) {
                if (result[i] > capacity) {
                    return false;
                }
            }
            return true;
        }

        static class Difference {
            int[] diff;

            Difference(int[] nums) {
                assert nums.length > 0;
                diff = new int[nums.length];
                diff[0] = nums[0];
                for (int i = 1; i < nums.length; i++) {
                    diff[i] = nums[i] - nums[i - 1];
                }
            }

            void increment(int i, int j, int val) {
                diff[i] += val;
                if (j + 1 < diff.length) {
                    diff[j + 1] -= val;
                }
            }

            int[] getResult() {
                int[] res = new int[diff.length];
                res[0] = diff[0];
                for (int i = 1; i < diff.length; i++) {
                    res[i] = res[i - 1] + diff[i];
                }
                return res;
            }
        }
    }


}
