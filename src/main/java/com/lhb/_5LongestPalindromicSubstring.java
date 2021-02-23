package com.lhb;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: LeetCodeRecord
 * @description:
 * @author: linhaibin
 * @create: 2021-02-18 11:48
 **/

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 */
public class _5LongestPalindromicSubstring {
    // https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/

    /**
     * 复杂度分析
     * <p>
     * 时间复杂度：O(n^2)，其中 n是字符串的长度。动态规划的状态总数为 O(n^2)
     * 对于每个状态，我们需要转移的时间为 O(1)。
     * <p>
     * 空间复杂度：O(n^2)即存储动态规划状态需要的空间。
     */
    public static String longestPalindrome(String s) {
        // 方法一：动态规划

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }

    /**
     * 复杂度分析
     * 时间复杂度：O(n^2)其中 n 是字符串的长度。长度为 1 和 2 的回文中心分别有 n 和 n−1 个，每个回文中心最多会向外扩展O(n) 次。
     * 空间复杂度：O(1)
     */
    public static String longestPalindrome2(String s) {
        // 方法二：中心扩展算法

        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    public static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }


    public static void main(String[] args) {
        System.out.println(_5LongestPalindromicSubstring.longestPalindrome("babad"));
        System.out.println(_5LongestPalindromicSubstring.longestPalindrome2("babad"));
    }
}
