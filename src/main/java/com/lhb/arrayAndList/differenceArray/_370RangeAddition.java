package com.lhb.arrayAndList.differenceArray;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 370. 区间加法（中等）
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 * @see https://leetcode-cn.com/problems/range-addition/
 * @see 370. 区间加法（中等）.png
 **/
public class _370RangeAddition {


    public static void main(String[] args) {
        int[][] ints = new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}};
        _370RangeAddition rangeAddition = new _370RangeAddition();
        System.out.println(Arrays.toString(rangeAddition.getModifiedArray(5, ints)));// 输出 [-2, 0, 3, 5, 3]
    }

    private int[] getModifiedArray(int length, int[][] updates) {
        // nums 初始化为全 0
        int[] nums = new int[length];
        // 构造差分解法
        Difference df = new Difference(nums);
        for (int[] update : updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }
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
