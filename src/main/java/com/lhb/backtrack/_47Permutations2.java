package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 47. 全排列 II（中等）
 * @author: linhaibin
 * @create: 2022-04-20 10:03
 **/
public class _47Permutations2 {
    /**
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     * [1,2,1],
     * [2,1,1]]
     * 示例 2：
     * <p>
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= nums.length <= 8
     * -10 <= nums[i] <= 10
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = new int[]{1, 1, 2};

        System.out.println(JSON.toJSONString(nums));
        System.out.println(JSON.toJSONString(solution.permuteUnique(nums)));


    }

    static class Solution {
        List<List<Integer>> res;
        LinkedList<Integer> track;

        public List<List<Integer>> permuteUnique(int[] nums) {
            res = new ArrayList<>();
            track = new LinkedList<>();
            Arrays.sort(nums);
            boolean[] used = new boolean[nums.length];
            backtrack(nums, used);
            return res;
        }

        private void backtrack(int[] nums, boolean[] used) {
            if (track.size() == nums.length) {
                res.add(new ArrayList<>(track));
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                // 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                track.add(nums[i]);
                backtrack(nums, used);
                used[i] = false;
                track.removeLast();
            }
        }
    }


}
