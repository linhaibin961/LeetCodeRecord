package com.lhb.queueAndStack;

import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 921. 使括号有效的最⼩添加（中等）
 * @author: linhaibin
 * @create: 2022-04-10 20:43
 **/
public class _921MinimumAddToMakeParenthesesValid {

    /**
     * 只有满足下面几点之一，括号字符串才是有效的：
     * <p>
     * 它是一个空字符串，或者
     * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
     * 它可以被写作 (A)，其中 A 是有效字符串。
     * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
     * <p>
     * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
     * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "())"
     * 输出：1
     * 示例 2：
     * <p>
     * 输入：s = "((("
     * 输出：3
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 1000
     * s 只包含 '(' 和 ')' 字符。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        String s = "(((";
        Solution solution = new Solution();
        System.out.println(solution.minAddToMakeValid(s));
    }

    static class Solution {
        public int minAddToMakeValid(String s) {
            Stack<Character> stack = new Stack();
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    stack.push(c);
                } else {
                    if (stack.empty() || '(' != stack.pop()) {
                        count++;
                    }
                }
            }

            return count + stack.size();
        }

        /**
         * labuladong 的解法
         * @param s
         * @return
         */
        int minAddToMakeValid2(String s) {
            // res 记录插⼊次数
            int res = 0;
            // need 变量记录右括号的需求量
            int need = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    // 对右括号的需求 + 1
                    need++;
                }
                if (s.charAt(i) == ')') {
                    // 对右括号的需求 - 1
                    need--;
                    if (need == -1) {
                        need = 0;
                        // 需插⼊⼀个左括号
                        res++;
                    }
                }
            }
            return res + need;
        }
    }
}
