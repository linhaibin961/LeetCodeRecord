package com.lhb.queueAndStack;

import com.lhb.common.ListNode;

import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 20. 有效的括号（简单）
 * @author: linhaibin
 * @create: 2022-04-10 20:43
 **/
public class _20ValidParentheses {

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "()"
     * 输出：true
     * 示例 2：
     * <p>
     * 输入：s = "()[]{}"
     * 输出：true
     * 示例 3：
     * <p>
     * 输入：s = "(]"
     * 输出：false
     * 示例 4：
     * <p>
     * 输入：s = "([)]"
     * 输出：false
     * 示例 5：
     * <p>
     * 输入：s = "{[]}"
     * 输出：true
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 104
     * s 仅由括号 '()[]{}' 组成
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        String s = "()[]{}";
        Solution solution = new Solution();
        System.out.println(solution.isValid(s));
    }

    static class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                } else {
                    if (stack.empty() || getLeftQuota(c) != stack.pop()) return false;
                }
            }

            return stack.isEmpty();
        }

        public char getLeftQuota(char c) {
            if (c == ')') {
                return '(';
            } else if (c == ']') {
                return '[';
            } else {
                return '{';
            }
        }
    }
}
