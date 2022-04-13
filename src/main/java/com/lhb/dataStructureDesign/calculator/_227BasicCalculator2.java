package com.lhb.dataStructureDesign.calculator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 227. 基本计算器II（中等）
 * @author: linhaibin
 * @create: 2022-04-13 16:43
 **/
public class _227BasicCalculator2 {

    /**
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * <p>
     * 整数除法仅保留整数部分。
     * <p>
     * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
     * <p>
     * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "3+2*2"
     * 输出：7
     * 示例 2：
     * <p>
     * 输入：s = " 3/2 "
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：s = " 3+5 / 2 "
     * 输出：5
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 3 * 105
     * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
     * s 表示一个 有效表达式
     * 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内
     * 题目数据保证答案是一个 32-bit 整数
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.calculate("3+5 / 2"));
        System.out.println(solution.calculate("2 - 3 * 4 + 5"));
    }


    /**
     * labuladong 解法
     */
    @Data
    static class Solution {
        public int calculate(String s) {
            int num = 0;
            char sign = '+';
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isDigital(c)) {
                    num = num * 10 + (c - '0');
                }
                // 如果不是数字，就是遇到了下⼀个符号，
                // 之前的数字和符号就要存进栈中
                if ((!isDigital(c) && c != ' ') || i == s.length() - 1) {
                    switch (sign) {
                        case '+':
                            stack.push(num);
                            break;
                        case '-':
                            stack.push(-num);
                            break;
                        case '*':
                            num *= stack.pop();
                            stack.push(num);
                            break;
                        case '/':
                            num = stack.pop() / num;
                            stack.push(num);
                            break;
                        default:
                    }
                    // 更新符号为当前符号，数字清零
                    sign = c;
                    num = 0;
                }

            }
            while (!stack.isEmpty()) {
                num += stack.pop();
            }

            return num;
        }

        public boolean isDigital(char c) {
            return c >= '0' && c <= '9';
        }
    }

}
