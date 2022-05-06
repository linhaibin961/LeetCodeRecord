package com.lhb;

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

    public static void main(String[] args) {
        System.out.println(_5LongestPalindromicSubstring.longestPalindrome("babad"));
        System.out.println(_5LongestPalindromicSubstring.longestPalindrome2("babad"));

        Solution solution = new Solution();
        System.out.println(solution.longestPalindrome("abba"));
        Solution2 solution2 = new Solution2();
        System.out.println(solution2.longestPalindrome("abba"));
    }

    /**
     * 自己写的动态规划
     */
    static class Solution {
        public String longestPalindrome(String s) {
            int n = s.length();
            if (n < 2) {
                return s;
            }
            boolean[][] dp = new boolean[n][n];
            // 初始化：所有长度为 1 的子串都是回文串，实际可以不用初始化
            for (int i = 0; i < n; i++) {
                dp[i][i] = true;
            }
            int resStart = 0;
            int resMaxLength = 0;
            /*
             * 一开始第一层循环我定义成左指针，第一层 for 循环我定义成右指针，没写出来
             *
             * 后面我把第一层定义成回文串的长度，第二层定义成左指针，
             * dp 定义成：dp[len][leftIndex],意思为从索引 leftIndex 开始，长度为 len 的字符串是否为回文字符串
             * 状态转移方程为：dp[len][leftIndex] = len % 2 == 1 ? dp[len - 1][leftIndex + 1] : dp[len - 2][leftIndex + 1] ;
             * 如果 leftIndex -1 的字符等于 rightIndex 返回结果错误，会把 leftIndex -1统计进去。如 bbbgssbgso 会返回 bbgssbg，正确答案应该是 bgssbg
             *
             * 然后我又改了 dp[][]的定义：dp[leftIndex][rightIndex]
             * 状态转移方程为：dp[leftIndex][rightIndex] = dp[leftIndex + 1][rightIndex - 1];就好了
             */
            for (int len = 2; len <= n; len++) {
                for (int leftIndex = 0; leftIndex < n; leftIndex++) {
                    int rightIndex = leftIndex + len - 1;
                    if (rightIndex >= n) {
                        break;
                    }
//                    String substring = s.substring(leftIndex, rightIndex + 1);
                    if (s.charAt(rightIndex) != s.charAt(leftIndex)) {
                        dp[leftIndex][rightIndex] = false;
                    } else {
                        if (len > 2) {
                            dp[leftIndex][rightIndex] = dp[leftIndex + 1][rightIndex - 1];
                        } else {
                            dp[leftIndex][rightIndex] = true;
                        }

                    }
                    if (dp[leftIndex][rightIndex] && len > resMaxLength) {
                        resStart = leftIndex;
                        resMaxLength = len;
                    }
                }
            }

            return s.substring(resStart, resStart + resMaxLength);
        }
    }

    /**
     * 自己写的中心扩展算法
     */
    static class Solution2 {
        public String longestPalindrome(String s) {
            if (s == null || s.length() < 1) {
                return "";
            }
            int length = s.length();
            int start = 0;
            int end = 0;
            for (int i = 0; i < length; i++) {
                int edd = getMaxPalindromic(s, i, i + 1);
                int odd = getMaxPalindromic(s, i - 1, i + 1);
                int max = Math.max(edd, odd);
                if (max > end - start) {
                    start = i - (max - 1) / 2;
                    end = i + max / 2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int getMaxPalindromic(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            return right - left - 1;
        }
    }

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

}
