package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 46. 全排列（中等）
 * @author: linhaibin
 * @create: 2022-04-20 10:03
 **/
public class _46Permutations {
    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * 示例 2：
     * <p>
     * 输入：nums = [0,1]
     * 输出：[[0,1],[1,0]]
     * 示例 3：
     * <p>
     * 输入：nums = [1]
     * 输出：[[1]]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 6
     * -10 <= nums[i] <= 10
     * nums 中的所有整数 互不相同
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 2, 3};

        System.out.println(JSON.toJSONString(nums));
        System.out.println(JSON.toJSONString(solution.permute(nums)));


    }

    static class Solution {
        List<List<Integer>> res;

        public List<List<Integer>> permute(int[] nums) {
            boolean[] used = new boolean[nums.length];
            res = new ArrayList<>();
            LinkedList<Integer> track = new LinkedList<>();
            backtrack(nums, used, track);
            return res;
        }

        private void backtrack(int[] nums, boolean[] used, LinkedList<Integer> track) {
            if (track.size() == nums.length) {
                res.add(new ArrayList<>(track));
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                track.add(nums[i]);
                backtrack(nums, used, track);
                used[i] = false;
                track.removeLast();
            }
        }
    }


}
