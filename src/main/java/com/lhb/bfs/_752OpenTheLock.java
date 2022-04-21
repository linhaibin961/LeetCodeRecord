package com.lhb.bfs;

import com.alibaba.fastjson.JSON;
import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 752. 打开转盘锁（中等）
 * @author: linhaibin
 * @create: 2022-04-21 10:03
 **/
public class _752OpenTheLock {
    /**
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * <p>
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * <p>
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * <p>
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     * 示例 2:
     * <p>
     * 输入: deadends = ["8888"], target = "0009"
     * 输出：1
     * 解释：把最后一位反向旋转一次即可 "0000" -> "0009"。
     * 示例 3:
     * <p>
     * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * 输出：-1
     * 解释：无法旋转到目标数字且不被锁定。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/open-the-lock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        String[] array = new String[]{"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";
        Solution solution = new Solution();
        System.out.println(solution.openLock(array, target));


    }

    static class Solution {

        public int openLock(String[] deadends, String target) {
            Queue<String> queue = new LinkedList();
            Set<String> visited = new HashSet<>();
            int res = 0;
            String start = "0000";
            queue.add(start);
            for (String deadend : deadends) {
                visited.add(deadend);
            }
            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();
                    if (visited.contains(cur)) {
                        continue;
                    }
                    if (cur.equals(target)) {
                        return res;
                    }
                    for (int j = 0; j < 4; j++) {
                        String plusOne = plusOne(cur, j);
                        if (!visited.contains(plusOne)) {
                            queue.offer(plusOne);
                        }
                        String minutumOne = minutumOne(cur, j);
                        if (!visited.contains(minutumOne)) {
                            queue.offer(minutumOne);
                        }
                    }
                    visited.add(cur);

                }
                res++;
            }
            return -1;
        }

        public String plusOne(String s, int index) {
            char[] chars = s.toCharArray();
            char c = chars[index];
            if (c == '9') {
                chars[index] = '0';
            } else {
                chars[index] += 1;
            }
            return new String(chars);
        }

        public String minutumOne(String s, int index) {
            char[] chars = s.toCharArray();
            char c = chars[index];
            if (c == '0') {
                chars[index] = '9';
            } else {
                chars[index] -= 1;
            }
            return new String(chars);
        }
    }
}
