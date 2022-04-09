package com.lhb.slidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @program: LeetCodeRecord
 * @description: 3. ⽆重复字符的最⻓⼦串（中等）
 * @author: linhaibin
 * @create: 2021-02-18 11:48
 **/


public class _3LongestSubstringWithoutRepeatingCharacters {
    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *  
     * <p>
     * 提示：
     * <p>
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        String s = "bbbbb";
        Solution solution = new Solution();
        System.out.println(solution.lengthOfLongestSubstring(s));

    }


    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            int length = s.length();
            Map<Character, Integer> window = new HashMap<>();

            int left = 0, right = 0;
            int len = 0;
            while (right < length) {
                char c = s.charAt(right);
                window.put(c, window.getOrDefault(c, 0) + 1);
                while (window.get(c) > 1) {
                    char leftC = s.charAt(left);
                    window.put(leftC, window.get(leftC) - 1);
                    left++;
                }
                right++;
                // len 可以在这里处理，无需在第二个while内部处理
                len = Math.max(len, right - left);
            }
            return len;
        }
    }


}
