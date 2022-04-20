package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 90. ⼦集 II（中等）
 * @author: linhaibin
 * @create: 2022-04-20 22:03
 **/
public class _90Subsets2 {
    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     * <p>
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subsets-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 2};
        System.out.println(JSON.toJSONString(nums));
        System.out.println(JSON.toJSONString(solution.subsetsWithDup(nums)));


    }

    static class Solution {
        LinkedList<List<Integer>> res;
        LinkedList<Integer> track;

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            this.res = new LinkedList<>();
            // 先排序，让相同的元素靠在⼀起
            Arrays.sort(nums);
            this.track = new LinkedList<>();
            backtrack(nums, 0);
            return this.res;
        }

        private void backtrack(int[] nums, int index) {

            // 前序位置，每个节点的值都是⼀个⼦集
            res.add(new LinkedList<>(track));
            for (int i = index; i < nums.length; i++) {
                // 剪枝逻辑，值相同的相邻树枝，只遍历第⼀条
                if (i > index && nums[i] == nums[i - 1]) {
                    continue;
                }
                track.add(nums[i]);
                backtrack(nums, i + 1);
                track.removeLast();
            }
        }
    }

}
