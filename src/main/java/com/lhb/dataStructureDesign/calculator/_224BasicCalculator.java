package com.lhb.dataStructureDesign.calculator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 224. 基本计算器（困难）
 * @author: linhaibin
 * @create: 2022-04-13 16:43
 **/
public class _224BasicCalculator {

    /**
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * <p>
     * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "1 + 1"
     * 输出：2
     * 示例 2：
     * <p>
     * 输入：s = " 2-1 + 2 "
     * 输出：3
     * 示例 3：
     * <p>
     * 输入：s = "(1+(4+5+2)-3)+(6+8)"
     * 输出：23
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 3 * 105
     * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
     * s 表示一个有效的表达式
     * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
     * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
     * 输入中不存在两个连续的操作符
     * 每个数字和运行的计算将适合于一个有符号的 32位 整数
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate("2147483647"));
//        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
//        System.out.println(solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    }


    /**
     * labuladong 解法
     */
    @Data
    static class Solution {
        public int calculate(String s) {
            int num = 0;
            int sign = 1;
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isDigital(c)) {
                    int cur = c - '0';
                    while (i + 1 < s.length() && isDigital(s.charAt(i + 1))) {
                        cur = cur * 10 + (s.charAt(++i) - '0');
                    }
                    // 追加到res中(别漏了符号)
                    num += cur * sign;
                } else if ((!isDigital(c) && c != ' ') || i == s.length() - 1) {
                    switch (c) {
                        case '+':
                            sign = 1;
                            break;
                        case '-':
                            sign = -1;
                            break;
                        case '(':
                            stack.push(num);
                            stack.push(sign);
                            num = 0;
                            sign = 1;
                            break;
                        case ')':
                            num = stack.pop() * num + stack.pop();
                            break;
                        default:
                    }

                }

            }
            return num;
        }

        public boolean isDigital(char c) {
            return c >= '0' && c <= '9';
        }
    }

}
