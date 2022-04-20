package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 78. ⼦集（中等）
 * @author: linhaibin
 * @create: 2022-04-20 22:03
 **/
public class _77Combinations {
    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * <p>
     * 你可以按 任何顺序 返回答案。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 4, k = 2
     * 输出：
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     * 示例 2：
     * <p>
     * 输入：n = 1, k = 1
     * 输出：[[1]]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 20
     * 1 <= k <= n
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combinations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.combine(4, 2)));


    }

    static class Solution {
        LinkedList<List<Integer>> res;

        public List<List<Integer>> combine(int n, int k) {
            res = new LinkedList<>();
            LinkedList<Integer> track = new LinkedList<>();

            backtrack(1, n, k, track);
            return res;
        }

        private void backtrack(int start, int n, int k, LinkedList<Integer> track) {
            if (track.size() == k) {
                res.add(new ArrayList<>(track));
            }
            for (int i = start; i <= n; i++) {
                track.add(i);
                backtrack(i + 1, n, k, track);
                track.removeLast();
            }

        }

    }

}
