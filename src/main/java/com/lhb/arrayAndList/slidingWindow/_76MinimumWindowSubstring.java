package com.lhb.arrayAndList.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: LeetCodeRecord
 * @description: 76. 最⼩覆盖⼦串（困难）
 * @author: linhaibin
 * @create: 2022-04-18 10:43
 **/
public class _76MinimumWindowSubstring {

    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * <p>
     *  
     * <p>
     * 注意：
     * <p>
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 示例 2：
     * <p>
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 示例 3:
     * <p>
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length, t.length <= 105
     * s 和 t 由英文字母组成
     *  
     * <p>
     * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


//    public static void main(String[] args) {
//        String s = "aaaaaaaaaaaabbbbbcdd";
//        String target = "abcdd";
//        Solution solution = new Solution();
//        System.out.println(solution.minWindow(s, target));
//
//    }
    public static void main(String[] args) {
        String s = "aaaaaaaaaaaabbbbbcdd";
        String target = "abcdd";
        Solution solution = new Solution();
        System.out.println(solution.minWindow(s, target));

    }


    static class Solution {
        public String minWindow(String s, String t) {
            int length = s.length();
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();
            for (int i = 0; i < t.length(); i++) {
                need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
            }
            int left = 0, right = 0;
            int valid = 0, start = 0, len = Integer.MAX_VALUE;
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
                            if (right - left < len) {
                                len = right - left;
                                start = left;
                            }
                        }
                        // 更新语句放在判断语句下面，判断可以用equals。如果更新语句放在判断前面就要改成if (need.get(leftC) > (window.get(leftC)))了
                        window.put(leftC, window.get(leftC) - 1);
                    }
                    left++;
                }
                right++;
            }
            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len + 1);
        }
    }


}
