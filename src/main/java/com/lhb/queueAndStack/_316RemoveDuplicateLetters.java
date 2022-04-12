package com.lhb.queueAndStack;

import java.util.Stack;

/**
 * @program: LeetCodeRecord
 * @description: 316. 去除重复字⺟（中等）
 * @author: linhaibin
 * @create: 2022-04-11 20:43
 **/
public class _316RemoveDuplicateLetters {

    /**
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "bcabc"
     * 输出："abc"
     * 示例 2：
     * <p>
     * 输入：s = "cbacdcbc"
     * 输出："acdb"
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 104
     * s 由小写英文字母组成
     *  
     * <p>
     * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicate-letters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        String s = "bbcaac";
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicateLetters(s));
    }

    static class Solution {
        public String removeDuplicateLetters(String s) {
            Stack<Character> stack = new Stack();
            int[] count = new int['z'];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i)]++;
            }
            boolean[] inStack = new boolean['z'];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                count[c]--;
                if (inStack[c]) continue;
                while (!stack.empty() && stack.peek() > c) {
                    if (count[stack.peek()] == 0) break;
                    inStack[stack.pop()] = false;
                }
                stack.push(c);
                inStack[c] = true;
            }
            StringBuilder sb = new StringBuilder();
            while (!stack.empty()) {
                sb.append(stack.pop());
            }
            return sb.reverse().toString();
        }
    }

}
