package com.lhb.arrayAndList.doublePointHandleArray;

/**
 * @program: LeetCodeRecord
 * @description: 5. 最⻓回⽂⼦串（中等）
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
     * 时间复杂度：O(n^2)其中 n 是字符串的长度。长度为 1 和 2 的回文中心分别有 n 和 n−1 个，每个回文中心最多会向外扩展O(n) 次。
     * 空间复杂度：O(1)
     */
    public static String longestPalindrome(String s) {
        // 方法二：中心扩展算法

        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            // 回文串长度为奇数
            int oddLen = expandAroundCenter(s, i, i);
            // 回文串长度为偶数
            int evenLen = expandAroundCenter(s, i, i + 1);

            //最大回文子串长度
            int len = Math.max(oddLen, evenLen);

            //计算最大回文子串的起始位置
            if (len > end - start) {
                // i为字符串下标中心， i 减去 len/2 大致是这个回文串的起始坐标，考虑到奇偶数，所以先 (len-1)/2
                // len -1 是为了处理len奇偶数
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        // end+1由于Java前闭后开
        return s.substring(start, end + 1);
    }

    public static int expandAroundCenter(String s, int left, int right) {
        // left >= 0 要考虑left=0时，如s="bb"时，left不等于0的话，结果有问题。
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        // 回文串的长度是right-left+1-2(跳出循环时为回文子串两边下标，故要-2) = right - left - 1,
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println(_5LongestPalindromicSubstring.longestPalindrome("babad"));
    }
}
