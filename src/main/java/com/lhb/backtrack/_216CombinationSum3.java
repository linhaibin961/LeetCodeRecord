package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 216. 组合总和 III（中等）
 * @author: linhaibin
 * @create: 2022-04-20 22:03
 **/
public class _216CombinationSum3 {
    /**
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * <p>
     * 只使用数字1到9
     * 每个数字 最多使用一次 
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     * 示例 2:
     * <p>
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * 解释:
     * 1 + 2 + 6 = 9
     * 1 + 3 + 5 = 9
     * 2 + 3 + 4 = 9
     * 没有其他符合的组合了。
     * 示例 3:
     * <p>
     * 输入: k = 4, n = 1
     * 输出: []
     * 解释: 不存在有效的组合。
     * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
     *  
     * <p>
     * 提示:
     * <p>
     * 2 <= k <= 9
     * 1 <= n <= 60
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.combinationSum3(3, 7)));
        System.out.println(JSON.toJSONString(solution.combinationSum3(3, 9)));


    }

    static class Solution {
        LinkedList<List<Integer>> res;
        LinkedList<Integer> track;
        int sum = 0;

        public List<List<Integer>> combinationSum3(int k, int n) {
            res = new LinkedList<>();
            track = new LinkedList<>();
            sum = 0;
            backtrack(1, k, n);
            return res;
        }

        private void backtrack(int start, int k, int target) {
            if (sum == target && track.size() == k) {
                res.add(new ArrayList<>(track));
            }
            if (sum > target) {
                return;
            }
            for (int i = start; i < 10; i++) {
                int candidate = i;
                track.add(candidate);
                sum += candidate;
                backtrack(i + 1, k, target);
                sum -= candidate;
                track.removeLast();
            }
        }
    }

}
