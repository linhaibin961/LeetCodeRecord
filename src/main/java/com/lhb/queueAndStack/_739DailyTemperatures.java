package com.lhb.queueAndStack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 739. 每⽇温度（中等）
 * @author: linhaibin
 * @create: 2022-04-11 10:43
 **/
public class _739DailyTemperatures {

    /**
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指在第 i 天之后，才会有更高的温度。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * 示例 2:
     * <p>
     * 输入: temperatures = [30,40,50,60]
     * 输出: [1,1,1,0]
     * 示例 3:
     * <p>
     * 输入: temperatures = [30,60,90]
     * 输出: [1,1,0]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= temperatures.length <= 105
     * 30 <= temperatures[i] <= 100
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/daily-temperatures
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] num = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        Solution solution = new Solution();
        // 1,1,4,2,1,1,0,0
        System.out.println(Arrays.toString(solution.dailyTemperatures(num)));
    }

    static class Solution {

        public int[] dailyTemperatures(int[] temperatures) {
            int length = temperatures.length;
            int[] res = new int[length];
            Stack<Integer> stack = new Stack();
            for (int i = 0; i <= length - 1; i++) {
                int value = temperatures[i];
                // 比较当前的值（value）比上一个的值(temperatures[stack.peek()(peek出来上一次的索引)])大，可以确定上一个值的下一个值（就是题目要的答案）就是当前值
                while (!stack.empty() && value > temperatures[stack.peek()]) {
                    Integer pop = stack.pop();
                    // 举例说明：说明74比73大，74的索引1减去73的索引0，结果集中res[0]=1-0
                    res[pop] = i - pop;
                }
                // 索引入栈，为了计算索引的间距，也就是题目要求的答案
                stack.push(i);
            }
            return res;
        }
    }

}
