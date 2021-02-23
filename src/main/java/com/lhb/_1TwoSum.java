package com.lhb;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: LeetCodeRecord
 * @description:
 * @author: linhaibin
 * @create: 2021-02-21 17:21
 **/
public class _1TwoSum {
    // 两数相加

    public static int[] twoSum(int[] nums, int target) {
        /**
         *
         给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。

         你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。

         你可以按任意顺序返回答案。



         示例 1：

         输入：nums = [2,7,11,15], target = 9
         输出：[0,1]
         解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
         示例 2：

         输入：nums = [3,2,4], target = 6
         输出：[1,2]
         示例 3：

         输入：nums = [3,3], target = 6
         输出：[0,1]

         */
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSONString(_1TwoSum.twoSum(new int[]{2,7,11,15},9), SerializerFeature.WriteMapNullValue));
    }
}
