package com.lhb;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 1672. 最富有客户的资产总量（简单）
 * @author: linhaibin
 * @create: 2022-04-15 17:21
 **/
public class _385MiniParser {
    /**
     * 给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
     * <p>
     * 列表中的每个元素只可能是整数或整数嵌套列表
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "324",
     * 输出：324
     * 解释：你应该返回一个 NestedInteger 对象，其中只包含整数值 324。
     * 示例 2：
     * <p>
     * 输入：s = "[123,[456,[789]]]",
     * 输出：[123,[456,[789]]]
     * 解释：返回一个 NestedInteger 对象包含一个有两个元素的嵌套列表：
     * 1. 一个 integer 包含值 123
     * 2. 一个包含两个元素的嵌套列表：
     * i.  一个 integer 包含值 456
     * ii. 一个包含一个元素的嵌套列表
     * a. 一个 integer 包含值 789
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 5 * 104
     * s 由数字、方括号 "[]"、负号 '-' 、逗号 ','组成
     * 用例保证 s 是可解析的 NestedInteger
     * 输入中的所有值的范围是 [-106, 106]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/mini-parser
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 测试用例"324"、 "[123,[456,[789]]]"、 "[123,456,[788,799,833],[[]],10,[]]"、 "[-1]"
     *
     * @param args
     */


    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.deserialize("324"));
        System.out.println(solution.deserialize("[123,[456,[789]]]"));
        System.out.println(solution.deserialize("[123,456,[788,799,833],[[]],10,[]]"));
        System.out.println(solution.deserialize("[-1]"));
    }


    static class Solution {
        public NestedInteger deserialize(String s) {
            if (s.charAt(0) != '[') {
                return new NestedInteger(Integer.parseInt(s));
            }
            Deque<NestedInteger> stack = new ArrayDeque<>();
            int num = 0;
            boolean negative = false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '-') {
                    negative = true;
                } else if (Character.isDigit(c)) {
                    num = num * 10 + c - '0';
                } else if (c == '[') {
                    stack.push(new NestedInteger());
                } else if (c == ',' || c == ']') {
                    if (Character.isDigit(s.charAt(i - 1))) {
                        if (negative) {
                            num *= -1;
                        }
                        stack.peek().add(new NestedInteger(num));
                    }
                    num = 0;
                    negative = false;
                    if (c == ']' && stack.size() > 1) {
                        NestedInteger ni = stack.pop();
                        stack.peek().add(ni);
                    }
                }
            }
            return stack.pop();
        }


        static class NestedInteger {
            int value = 1000_000_0;
            List<NestedInteger> nested;

            // Constructor initializes an empty nested list.
            public NestedInteger() {
                nested = new ArrayList<>();
            }

            // Constructor initializes a single integer.
            public NestedInteger(int value) {
                this.value = value;
                nested = new ArrayList<>();
            }

            // @return true if this NestedInteger holds a single integer, rather than a nested list.
            public boolean isInteger() {
                return nested.isEmpty();
            }

            // @return the single integer that this NestedInteger holds, if it holds a single integer
            // Return null if this NestedInteger holds a nested list
            public Integer getInteger() {
                if (isInteger()) return value;
                return null;
            }

            // Set this NestedInteger to hold a single integer.
            public void setInteger(int value) {
                this.value = value;
            }

            // Set this NestedInteger to hold a nested list and adds a nested integer to it.
            public void add(NestedInteger ni) {
                nested.add(ni);
            }

            // @return the nested list that this NestedInteger holds, if it holds a nested list
            // Return empty list if this NestedInteger holds a single integer
            public List<NestedInteger> getList() {
                return nested;
            }

            @Override
            public String toString() {
                final StringJoiner joiner = new StringJoiner("", "", "");
                if (value != 1000_000_0)
                    joiner.add(String.valueOf(value));
                if (!nested.isEmpty()) {
                    joiner.add("[");
                    final int size = nested.size();
                    for (int i = 0; i < size; i++) {
                        joiner.add(nested.get(i).toString());
                        if (i != size - 1) {
                            joiner.add(",");
                        }
                    }
                    joiner.add("]");
                }
                return joiner.toString();
            }

        }
    }
}
