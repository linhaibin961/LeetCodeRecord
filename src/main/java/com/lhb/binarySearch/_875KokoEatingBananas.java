package com.lhb.binarySearch;

/**
 * @program: LeetCodeRecord
 * @description: 875. 爱吃⾹蕉的珂珂（中等）
 * @author: linhaibin
 * @create: 2022-04-10 10:43
 **/
public class _875KokoEatingBananas {

    /**
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     * <p>
     * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
     * <p>
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * <p>
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入: piles = [3,6,7,11], H = 8
     * 输出: 4
     * 示例 2：
     * <p>
     * 输入: piles = [30,11,23,4,20], H = 5
     * 输出: 30
     * 示例 3：
     * <p>
     * 输入: piles = [30,11,23,4,20], H = 6
     * 输出: 23
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= piles.length <= 10^4
     * piles.length <= H <= 10^9
     * 1 <= piles[i] <= 10^9
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public static void main(String[] args) {
        int[] piles = new int[]{1000000000};

        Solution solution = new Solution();
        System.out.println(solution.minEatingSpeed(piles, 2));

    }


    static class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            int left = 1, right = 1000000000 + 1;
            int mid = 0;
            while (left <= right) {
                mid = left + (right - left) / 2;
                // func 是基于mid单调递减，也就是说mid越大，func越小。
                // 代入情景，香蕉的速度 K约大，花费的H 小时越小。
                int func = func(piles, mid);
                if (func > h) {
                    // func比h大了，所以要让func小一点，就得让mid大一点。得让mid大一点，就得让left大一点。
                    left = mid + 1;
                } else if (func <= h) {
                    // func比h小了，所以要让func大一点，就得让mid小一点。得让mid小一点，就得让right小一点。
                    right = mid - 1;
                }
                /*else if (func == h) {
                    // 这道题是求最左边界，所以固定right，让mid往left试探，找到最左边界。
                    right = mid - 1;
                }*/
            }
            return left;
        }

        /**
         * 速度越快，吃完时间越短
         * @param piles
         * @param x
         * @return
         */
        public int func(int[] piles, int x) {
            int h = 0;
            for (int i = 0; i < piles.length; i++) {
                h += (piles[i] - 1) / x + 1;
            }
            return h;
        }
    }


    /**
     * leetcode答案的思路，也可以借鉴下
     */
    class Solution2 {

        public int minEatingSpeed(int[] piles, int h) {
            //单调性，速度越快，吃完时间越短
            //速度越慢，吃完时间越长
            int max = 0;
            //最大的速度就是最大的香蕉堆，题目说了一个小时能吃一堆香蕉
            for (int pile : piles) {
                max = Math.max(max, pile);
            }

            int l = 1;//最慢速度
            int r = max;
            while (l < r) {
                //给定一个最小速度
                int mid = l + (r - l) / 2;
                //计算以该速度吃完所需的时间
                int time = eat(piles, mid);
                if (time > h) {
                    //需要的时间长了，说明速度慢了
                    l = mid + 1;
                } else {
                    //需要的时间短了，说明速度快了
                    r = mid;
                }
            }
            return l;
        }

        int eat(int[] piles, int speed) {
            int time = 0;
            for (int pile : piles) {
                time += (pile + speed - 1) / speed;
            }
            return time;
        }

    }

}
