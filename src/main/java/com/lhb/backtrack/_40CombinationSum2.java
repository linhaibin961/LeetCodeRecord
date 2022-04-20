package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 40. 组合总和 II（中等）
 * @author: linhaibin
 * @create: 2022-04-20 22:03
 **/
public class _40CombinationSum2 {
    /**
     * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的每个数字在每个组合中只能使用 一次 。
     * <p>
     * 注意：解集不能包含重复的组合。 
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     * 示例 2:
     * <p>
     * 输入: candidates = [2,5,2,1,2], target = 5,
     * 输出:
     * [
     * [1,2,2],
     * [5]
     * ]
     *  
     * <p>
     * 提示:
     * <p>
     * 1 <= candidates.length <= 100
     * 1 <= candidates[i] <= 50
     * 1 <= target <= 30
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        System.out.println(JSON.toJSONString(candidates));
        System.out.println(JSON.toJSONString(solution.combinationSum2(candidates, 8)));
        int[] candidates2 = new int[]{2, 5, 2, 1, 2};
        System.out.println(JSON.toJSONString(candidates2));
        System.out.println(JSON.toJSONString(solution.combinationSum2(candidates2, 5)));


    }

    static class Solution {
        LinkedList<List<Integer>> res;
        LinkedList<Integer> track;
        int sum = 0;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            res = new LinkedList<>();
            track = new LinkedList<>();
            sum = 0;
            Arrays.sort(candidates);
            backtrack(candidates, 0, target);
            return res;
        }

        private void backtrack(int[] nums, int start, int target) {
            if (sum == target) {
                res.add(new ArrayList<>(track));
            }
            if (sum > target) {
                return;
            }
            for (int i = start; i < nums.length; i++) {
                int candidate = nums[i];
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                track.add(candidate);
                sum += candidate;
                backtrack(nums, i + 1, target);
                sum -= candidate;
                track.removeLast();
            }

        }

    }

}
