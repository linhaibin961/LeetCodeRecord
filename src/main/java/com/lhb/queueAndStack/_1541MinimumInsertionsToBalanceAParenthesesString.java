package com.lhb.queueAndStack;

import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 1541. 平衡括号串的最少插⼊（中等）
 * @author: linhaibin
 * @create: 2022-04-10 20:43
 **/
public class _1541MinimumInsertionsToBalanceAParenthesesString {

    /**
     * 给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
     * <p>
     * 任何左括号 '(' 必须对应两个连续的右括号 '))' 。
     * 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
     * 比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
     * <p>
     * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
     * <p>
     * 请你返回让 s 平衡的最少插入次数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "(()))"
     * 输出：1
     * 解释：第二个左括号有与之匹配的两个右括号，但是第一个左括号只有一个右括号。我们需要在字符串结尾额外增加一个 ')' 使字符串变成平衡字符串 "(())))" 。
     * 示例 2：
     * <p>
     * 输入：s = "())"
     * 输出：0
     * 解释：字符串已经平衡了。
     * 示例 3：
     * <p>
     * 输入：s = "))())("
     * 输出：3
     * 解释：添加 '(' 去匹配最开头的 '))' ，然后添加 '))' 去匹配最后一个 '(' 。
     * 示例 4：
     * <p>
     * 输入：s = "(((((("
     * 输出：12
     * 解释：添加 12 个 ')' 得到平衡字符串。
     * 示例 5：
     * <p>
     * 输入：s = ")))))))"
     * 输出：5
     * 解释：在字符串开头添加 4 个 '(' 并在结尾添加 1 个 ')' ，字符串变成平衡字符串 "(((())))))))" 。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 10^5
     * s 只包含 '(' 和 ')' 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-insertions-to-balance-a-parentheses-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        String s = "()())(()))()())))";
        Solution solution = new Solution();
        System.out.println(solution.minInsertions(s));
    }

    static class Solution {
        /**
         * 放弃，规则不对
         * @param s
         * @return
         */
        public int minInsertions(String s) {
            Stack<Character> stack = new Stack();
            int needRightQuota = 0;
            int insertLeftQuota = 0;
            boolean hasSeeLeft = false;
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                if (c == ')') {
                    if (hasSeeLeft && stack.size() > 1) {
                        insertLeftQuota += (stack.size() + 1) / 2;
                        if (stack.size() % 2 == 1) {
                            needRightQuota++;
                        }
                        stack.clear();
                    }
                    hasSeeLeft = false;
                    stack.push(c);

                } else {
                    if (stack.empty()) {
                        needRightQuota += 2;

                    } else {
//                        if (hasSeeLeft && stack.size() > 2 && stack.size() % 2 == 1) {
//                            hasSeeLeft = false;
//                            insertLeftQuota += 1;
//                            needRightQuota++;
//                            stack.pop();
//                        }
                        stack.pop();
                        if (stack.empty() || ')' != stack.pop()) {
                            needRightQuota++;
                        }
                        hasSeeLeft = true;
                    }
                }
            }

            int i = stack.size() % 2;
            return needRightQuota + insertLeftQuota + i + (stack.size() + 1) / 2;
        }

        /**
         * labuladong 的解法
         *
         * @param s
         * @return
         */
        int minInsertions2(String s) {
            int res = 0;
            // need 记录需右括号的需求量
            int need = 0;
            for (int i = 0; i < s.length(); i++) {
                // ⼀个左括号对应两个右括号
                if (s.charAt(i) == '(') {
                    need += 2;
                    if (need % 2 == 1) {
                        // 插⼊⼀个右括号
                        res++;
                        // 对右括号的需求减⼀
                        need--;
                    }
                }
                if (s.charAt(i) == ')') {
                    need--;
                    // -1说明右括号太多了
                    if (need == -1) {
                        // 需要插⼊⼀个左括号
                        res++;
                        // 同时，对右括号的需求变为 1
                        need = 1;
                    }
                }
            }
            return res + need;
        }
    }
}
