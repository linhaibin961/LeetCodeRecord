package com.lhb.slidingWindow;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 438. 找到字符串中所有字⺟异位词（中等）
 * @author: linhaibin
 * @create: 2022-04-18 10:43
 **/
public class _483FindAllAnagramsInAString {

    /**
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * <p>
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *  示例 2:
     * <p>
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     *  
     * <p>
     * 提示:
     * <p>
     * 1 <= s.length, p.length <= 3 * 104
     * s 和 p 仅包含小写字母
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        Solution solution = new Solution();
        List<Integer> anagrams = solution.findAnagrams(s, p);
        System.out.println(Arrays.toString(anagrams.toArray()));

    }


    static class Solution {
        public List<Integer> findAnagrams(String s, String p) {
            int length = s.length();
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();
            List list = new ArrayList();
            for (int i = 0; i < p.length(); i++) {
                need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
            }
            int left = 0, right = 0;
            int valid = 0;
            while (right < length) {
                char c = s.charAt(right);
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (need.get(c).equals(window.get(c))) {
                        valid++;
                    }
                }
                while (valid == need.size()) {
                    char leftC = s.charAt(left);
                    if (need.containsKey(leftC)) {
                        if (need.get(leftC).equals(window.get(leftC))) {
                            valid--;
                            if (right - left + 1 == p.length()) {
                                list.add(left);
                            }
                        }
                        // 更新语句放在判断语句下面，判断可以用equals。如果更新语句放在判断前面就要改成if (need.get(leftC) > (window.get(leftC)))了
                        window.put(leftC, window.get(leftC) - 1);
                    }
                    left++;
                }
                right++;
            }

            return list;
        }
    }


}
