package com.lhb.arrayAndList.doublePointHandleArray;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 344. 反转字符串（简单）
 * @author: linhaibin
 * @create: 2022-04-18 10:43
 **/
public class _344ReverseString {

    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * <p>
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = ["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     * <p>
     * 输入：s = ["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 105
     * s[i] 都是 ASCII 码表中的可打印字符
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        char[] ints = new char[]{'h', 'e', 'l', 'l', 'o'};
        char[] ints2 = new char[]{'H', 'a', 'n', 'n', 'a', 'h'};
        Solution solution = new Solution();
        solution.reverseString(ints);
        System.out.println(Arrays.toString(ints));

        solution.reverseString(ints2);
        System.out.println(Arrays.toString(ints2));

    }


    static class Solution {
        public void reverseString(char[] s) {
            int length = s.length;
            int left = 0, right = length - 1;
            char temp;
            while (left < right) {
                temp = s[right];
                s[right] = s[left];
                s[left] = temp;
                right--;
                left++;
            }
        }
    }


}
