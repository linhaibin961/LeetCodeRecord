package com.lhb.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: LeetCodeRecord
 * @description: 567. 字符串的排列（中等）
 * @author: linhaibin
 * @create: 2022-04-18 10:43
 **/
public class _567PermutationInString {

    /**
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s1 = "ab" s2 = "eidbaooo"
     * 输出：true
     * 解释：s2 包含 s1 的排列之一 ("ba").
     * 示例 2：
     * <p>
     * 输入：s1= "ab" s2 = "eidboaoo"
     * 输出：false
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s1.length, s2.length <= 104
     * s1 和 s2 仅包含小写字母
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutation-in-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 = "eidbaooo";
        Solution solution = new Solution();
        System.out.println(solution.checkInclusion(s1, s2));

    }


    static class Solution {
        public boolean checkInclusion(String s1, String s2) {
            int length = s2.length();
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();
            for (int i = 0; i < s1.length(); i++) {
                need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
            }
            int left = 0, right = 0;
            int valid = 0;
            while (right < length) {
                char c = s2.charAt(right);
                if (need.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (need.get(c).equals(window.get(c))) {
                        valid++;
                    }
                }
                while (valid == need.size()) {
                    char leftC = s2.charAt(left);
                    if (need.containsKey(leftC)) {
                        if (need.get(leftC).equals(window.get(leftC))) {
                            valid--;
                            if (right - left + 1 == s1.length()) {
                                return true;
                            }
                        }
                        // 更新语句放在判断语句下面，判断可以用equals。如果更新语句放在判断前面就要改成if (need.get(leftC) > (window.get(leftC)))了
                        window.put(leftC, window.get(leftC) - 1);
                    }
                    left++;
                }
                right++;
            }

            return false;
        }
    }


}
