package com.lhb.dataStructureDesign.o1HandleArrayRandomElement;

import lombok.Data;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 380. 常数时间插⼊、删除和获取随机元素（中等）
 * @author: linhaibin
 * @create: 2022-04-12 20:43
 **/
public class _710RandomPickWithBlacklist {

    /**
     * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
     * <p>
     * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
     * <p>
     * 实现 Solution 类:
     * <p>
     * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
     * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入
     * ["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
     * [[7, [2, 3, 5]], [], [], [], [], [], [], []]
     * 输出
     * [null, 0, 4, 1, 6, 1, 0, 4]
     * <p>
     * 解释
     * Solution solution = new Solution(7, [2, 3, 5]);
     * solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
     * // 0、1、4和6的返回概率必须相等(即概率为1/4)。
     * solution.pick(); // 返回 4
     * solution.pick(); // 返回 1
     * solution.pick(); // 返回 6
     * solution.pick(); // 返回 1
     * solution.pick(); // 返回 0
     * solution.pick(); // 返回 4
     *  
     * <p>
     * 提示:
     * <p>
     * 1 <= n <= 109
     * 0 <= blacklist.length <- min(105, n - 1)
     * 0 <= blacklist[i] < n
     * blacklist 中所有值都 不同
     *  pick 最多被调用 2 * 104 次
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/random-pick-with-blacklist
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 5};
        Solution2 solution = new Solution2(7, arr);
        solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
        // 0、1、4和6的返回概率必须相等(即概率为1/4)。
        solution.pick(); // 返回 4
        solution.pick(); // 返回 1
        solution.pick(); // 返回 6
        solution.pick(); // 返回 1
        solution.pick(); // 返回 0
        solution.pick(); // 返回 4
    }


    /**
     * labuladong 解法
     */
    @Data
    static class Solution {
        // 合法索引的数量
        int whiteList;
        // 黑名单中元素映射到合法的索引
        HashMap<Integer, Integer> map;

        public Solution(int n, int[] blacklist) {
            whiteList = n - blacklist.length;
            map = new HashMap<>();
            int last = n - 1;
            // 初始化映射表
            for (int b : blacklist) {
                map.put(b, 0);
            }
            for (int b : blacklist) {
                // 本身就是无效索引
                if (b >= whiteList) {
                    continue;
                }
                // 跳过无效索引
                while (map.containsKey(last)) {
                    last--;
                }
                map.put(b, last--);
            }
        }

        public int pick() {
            int index = (int) (Math.random() * whiteList);
//            System.out.println("index:"+index);
//            System.out.println(map.getOrDefault(index, index));
            // 如果这个数是黑名单内的，就取映射值，不是黑名单内的就直接返回索引（索引和索引的值是一样的）
            return map.getOrDefault(index, index);
        }
    }

    /**
     *
     作者：LeetCode
     链接：https://leetcode-cn.com/problems/random-pick-with-blacklist/solution/hei-ming-dan-zhong-de-sui-ji-shu-by-leetcode-2/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    static class Solution2 {

        Map<Integer, Integer> m;
        Random r;
        int whiteLen;

        public Solution2(int n, int[] b) {
            // 举例： n=7， b=[2, 3, 5]
            m = new HashMap<>();
            r = new Random();
            whiteLen = n - b.length; // whiteLen=4
            Set<Integer> w = new HashSet<>();
            // 先把4及之后的数据初始化到hashSet中 4、5、6放到set中
            for (int i = whiteLen; i < n; i++) w.add(i);

            // 移除黑名单中值大于4的，样例中的5，把5从set中移除
            for (int x : b) w.remove(x);
            Iterator<Integer> wi = w.iterator();
            for (int x : b)
                // 黑名单中的数据且小于的数据映射到大于4中非名单中的数据，如2映射到6中，3映射到4中。
                if (x < whiteLen)
                    m.put(x, wi.next());
        }

        public int pick() {
            int k = r.nextInt(whiteLen);
            return m.getOrDefault(k, k);
        }
    }

}