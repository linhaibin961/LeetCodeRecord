package com.lhb.dataStructureDesign.calculator;

import lombok.Data;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: LeetCodeRecord
 * @description: 772. 基本计算器III（困难）
 * @author: linhaibin
 * @create: 2022-04-13 16:43
 **/
public class _722BasicCalculator3 {

    /**
     * _224BasicCalculator 只有加减括号
     * _227BasicCalculator2 只有加减乘除
     * _722BasicCalculator3 有加减乘除和括号
     * Implement a basic calculator to evaluate a simple expression string.
     * <p>
     * The expression string contains only non-negative integers, +, -, *, / operators ,
     * open ( and closing parentheses ) and empty spaces .
     * The integer division should truncate toward zero.
     * <p>
     * You may assume that the given expression is always valid.
     * All intermediate results will be in the range of [-2147483648, 2147483647].
     * <p>
     * Follow up: Could you solve the problem without using built-in library functions.
     * <p>
     * Example 1:
     * <p>
     * Input: s = "1 + 1"
     * Output: 2
     * Example 2:
     * <p>
     * Input: s = " 6-4 / 2 "
     * Output: 4
     * Example 3:
     * <p>
     * Input: s = "2*(5+5*2)/3+(6/2+8)"
     * Output: 21
     * Example 4:
     * <p>
     * Input: s = "(2+6* 3+5- (3*14/7+2)*5)+3"
     * Output: -12
     * Example 5:
     * <p>
     * Input: s = "0"
     * Output: 0
     * <p>
     * Constraints:
     * <p>
     * 1 <= s <= 104
     * s consists of digits, '+', '-', '*', '/', '(', ')' and ' '.
     * s is a valid expression.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.calculate("2+3*(-2)")); // 答案为-4
        System.out.println(solution.calculate("2+3*-2")); // 答案为0 不是正确的-4，该程序无法处理-为修饰符
//        System.out.println(solution.calculate("(2+6* 3+5- (3*14/7+2)*5)+3")); // -12
//        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
//        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    }


    /**
     * labuladong 解法
     */
    @Data
    static class Solution {
        public int calculate(String s) {
            Deque<Character> deque = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                //入队的时候就把空格排除在外，省的接下来再额外判断
                if (s.charAt(i) != ' ') {
                    deque.addLast(s.charAt(i));
                }
            }
            return helper(deque);
        }

        private int helper(Deque<Character> deque) {
            Deque<Integer> stack = new LinkedList<>();
            char sign = '+';
            int num = 0;
            while (deque.size() > 0) {
                char c = deque.removeFirst();
                if (isDigital(c)) {
                    num = num * 10 + (c - '0');
                }
                if (c == '(') {
                    num = helper(deque);
                }
                if (!isDigital(c) || deque.size() == 0) {
                    if (sign == '+') {
                        stack.addLast(num);
                    } else if (sign == '-') {
                        stack.addLast(-num);
                    } else if (sign == '*') {
                        int pre = stack.removeLast();
                        stack.addLast(pre * num);
                    } else if (sign == '/') {
                        int pre = stack.removeLast();
                        stack.addLast(pre / num);
                    }
                    num = 0;
                    sign = c;
                }
                if (c == ')') {
                    break;
                }
            }
            int res = 0;
            while (stack.size() > 0) {
                int top = stack.removeLast();
                res += top;
            }
            return res;
        }

        private boolean isDigital(char c) {
            return c >= '0' && c <= '9';
        }
    }
}
