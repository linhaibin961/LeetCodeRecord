package com.lhb;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 370. 区间加法（中等）
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 **/
public class _1109CorporateFlightBookings {

    /**
     * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
     * <p>
     * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
     * <p>
     * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
     * 输出：[10,55,45,25,25]
     * 解释：
     * 航班编号        1   2   3   4   5
     * 预订记录 1 ：   10  10
     * 预订记录 2 ：       20  20
     * 预订记录 3 ：       25  25  25  25
     * 总座位数：      10  55  45  25  25
     * 因此，answer = [10,55,45,25,25]
     * 示例 2：
     * <p>
     * 输入：bookings = [[1,2,10],[2,2,15]], n = 2
     * 输出：[10,25]
     * 解释：
     * 航班编号        1   2
     * 预订记录 1 ：   10  10
     * 预订记录 2 ：       15
     * 总座位数：      10  25
     * 因此，answer = [10,25]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 2 * 104
     * 1 <= bookings.length <= 2 * 104
     * bookings[i].length == 3
     * 1 <= firsti <= lasti <= n
     * 1 <= seatsi <= 104
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        int[][] ints = new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.corpFlightBookings(ints, 5)));// 输出 [10, 55, 45, 25, 25]
    }

    static class Solution {
        public int[] corpFlightBookings(int[][] bookings, int n) {
            // nums 初始化为全 0
            int[] nums = new int[n];
            // 构造差分解法
            Difference df = new Difference(nums);

            for (int[] booking : bookings) {
                // 注意转成数组索引要减一哦
                int i = booking[0] - 1;
                int j = booking[1] - 1;
                int val = booking[2];
                // 对区间 nums[i..j] 增加 val
                df.increment(i, j, val);
            }
            // 返回最终的结果数组
            return df.getResult();
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
