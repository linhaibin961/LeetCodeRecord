package com.lhb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: LeetCodeRecord
 * @description: 386. 字典序排数（中等）
 * @author: linhaibin
 * @create: 2022-4-18 11:05:21
 **/
public class _386LexicographicalNumbers {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.lexicalOrder2(130), SerializerFeature.WriteMapNullValue));
    }

    /**
     * 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
     * <p>
     * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 13
     * 输出：[1,10,11,12,13,2,3,4,5,6,7,8,9]
     * 示例 2：
     * <p>
     * 输入：n = 2
     * 输出：[1,2]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 5 * 104
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lexicographical-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * https://leetcode-cn.com/problems/lexicographical-numbers/solution/a-fei-suan-fa-by-a-fei-8-gsh1/
     */

    static class Solution {
        public List<Integer> lexicalOrder(int n) {
            List<Integer> ret = new ArrayList<Integer>();
            int number = 1;
            for (int i = 0; i < n; i++) {
                ret.add(number);
                if (number * 10 <= n) {
                    number *= 10;
                } else {
                    while (number % 10 == 9 || number + 1 > n) {
                        number /= 10;
                    }
                    number++;
                }
            }
            return ret;
        }

        public List<Integer> lexicalOrder2(int n) {
            List<Integer> res = new ArrayList<>();
            int x = 1;
            //list的大小=n
            while (res.size() < n) {
                //每次将当前层 *10进入下一层
                while (x <= n) {
                    res.add(x);
                    x *= 10;
                }
                //如果当前层的元素已经从9跃升到10这个阶梯或者当前层元素比n大，返回上一层
                while (x % 10 == 9 || x > n) {
                    x /= 10;
                }
                //当前层遍历完，递进1
                x++;
            }
            return res;
        }

        /**
         * DFS
         *
         * @param n
         * @return
         */
        public List<Integer> lexicalOrder3(int n) {
            List<Integer> res = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                dfs(i, n, res);
            }
            return res;
        }

        private void dfs(int i, int n, List<Integer> res) {
            if (i > n) return;
            res.add(i);
            for (int j = 0; j <= 9; j++) {
                dfs(i * 10 + j, n, res);
            }
        }
    }

    /**
     * 自己的想法，没实现出来
     */
    static class Solution2 {
        public List<Integer> lexicalOrder(int n) {
            PriorityQueue<String> queue = new PriorityQueue<String>(n, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int minLen = Math.min(o1.length(), o2.length());
                    int res = 0;
                    for (int i = 0; i < minLen; i++) {
                        if (o1.charAt(i) != o2.charAt(i)) {
                            res = o1.charAt(i) - o2.charAt(i);
                            break;
                        }

                    }
                    //按照大到小排序
                    return res;
                }
            });
            for (int i = 1; i <= n; i++) {
                queue.add(String.valueOf(i));
            }
            List<Integer> collect = queue.stream().map(s -> {
                return Integer.valueOf(s);
            }).collect(Collectors.toList());
            return collect;
        }
    }

}
