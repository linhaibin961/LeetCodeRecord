package com.lhb.dataStructureDesign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 295. 数据流的中位数（困难）
 * @author: linhaibin
 * @create: 2022-04-12 14:43
 **/
public class _295FindMedianFromDataStream {

    /**
     * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
     * <p>
     * 例如，
     * <p>
     * [2,3,4] 的中位数是 3
     * <p>
     * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
     * <p>
     * 设计一个支持以下两种操作的数据结构：
     * <p>
     * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
     * double findMedian() - 返回目前所有元素的中位数。
     * 示例：
     * <p>
     * addNum(1)
     * addNum(2)
     * findMedian() -> 1.5
     * addNum(3)
     * findMedian() -> 2
     * 进阶:
     * <p>
     * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
     * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        System.out.println(JSON.toJSONString(medianFinder, SerializerFeature.WriteMapNullValue));
    }


    /**
     * labuladong 解法
     */
    @Data
    static class MedianFinder {
        private PriorityQueue<Integer> large;
        private PriorityQueue<Integer> small;

        public MedianFinder() {
            // 小顶堆 小的在顶 且里面每一个值都比 small 里面的值大，所以才取名 large
            large = new PriorityQueue<>();
            // 大顶堆 大的在顶 且里面每一个值都比 large 里面的值大，所以才取名 small
            small = new PriorityQueue<>((a, b) -> (b - a));
        }

        public void addNum(int num) {
            // 小顶堆最小的都要比大顶堆最大的大
            // 这里是为了保证小顶堆最小的都要比大顶堆最大的大且，两个二叉堆的 size 差一
            if (small.size() >= large.size()) {
                small.offer(num);
                large.offer(small.poll());
            } else {
                large.offer(num);
                small.offer(large.poll());
            }
        }

        public double findMedian() {
            if (large.size() > small.size()) {
                return large.peek();
            } /*else if (large.size() < small.size()) {
                // 实际情况不会到这里， large.size() >= small.size()
                return small.peek();
            } */ else {
                return (large.peek() + small.peek()) / 2.0;
            }
        }
    }

}
