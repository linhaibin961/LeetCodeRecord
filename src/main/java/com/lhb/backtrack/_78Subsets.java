package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 78. ⼦集（中等）
 * @author: linhaibin
 * @create: 2022-04-20 22:03
 **/
public class _78Subsets {
    /**
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
     * 示例 2：
     * <p>
     * 输入：nums = [0]
     * 输出：[[],[0]]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 10
     * -10 <= nums[i] <= 10
     * nums 中的所有元素 互不相同
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 3};
        System.out.println(JSON.toJSONString(nums));
        System.out.println(JSON.toJSONString(solution.subsets(nums)));


    }

    static class Solution {
        LinkedList<List<Integer>> res;

        public List<List<Integer>> subsets(int[] nums) {
            res = new LinkedList<>();
            LinkedList<Integer> track = new LinkedList<>();

            backtrack(nums, 0, track);
            return res;
        }

        private void backtrack(int[] nums, int index, LinkedList<Integer> track) {

            res.add(new LinkedList<>(track));
            for (int i = index; i < nums.length; i++) {
                track.add(nums[i]);
                backtrack(nums, i + 1, track);
                track.removeLast();
            }
        }
    }

}
