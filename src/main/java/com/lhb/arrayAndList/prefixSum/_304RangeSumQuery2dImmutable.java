package com.lhb.arrayAndList.prefixSum;

import java.util.Arrays;

/**
 * @program: LeetCodeRecord
 * @description: 304. 二维区域和检索 - 矩阵不可变
 * @author: linhaibin
 * @create: 2021-02-19 17:43
 * @see https://leetcode-cn.com/problems/range-sum-query-2d-immutable/
 **/
public class _304RangeSumQuery2dImmutable {
    /**
     * 给定一个二维矩阵 matrix，以下类型的多个请求：
     * <p>
     * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1, col1) ，右下角 为 (row2, col2) 。
     * 实现 NumMatrix 类：
     * <p>
     * NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
     * int sumRegion(int row1, int col1, int row2, int col2) 返回 左上角 (row1, col1) 、右下角 (row2, col2) 所描述的子矩阵的元素 总和 。
     * <p>
     * 输入:
     * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
     * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
     * 输出:
     * [null, 8, 11, 12]
     * <p>
     * 解释:
     * NumMatrix numMatrix = new NumMatrix([[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]);
     * numMatrix.sumRegion(2, 1, 4, 3); // return 8 (红色矩形框的元素总和)
     * numMatrix.sumRegion(1, 1, 2, 2); // return 11 (绿色矩形框的元素总和)
     * numMatrix.sumRegion(1, 2, 2, 4); // return 12 (蓝色矩形框的元素总和)
     *  
     * <p>
     * 提示：
     * <p>
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 200
     * -105 <= matrix[i][j] <= 105
     * 0 <= row1 <= row2 < m
     * 0 <= col1 <= col2 < n
     * 最多调用 104 次 sumRegion 方法
     */
    public static void main(String[] args) {
        int[][] ints = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(ints);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(Arrays.toString(ints[i]));//只能打印数组，不能操作
        }
        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < numMatrix.preSum.length; i++) {
            System.out.println(Arrays.toString(numMatrix.preSum[i]));//只能打印数组，不能操作
        }

        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2));
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4));
    }

    static class NumMatrix {
        int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int n = matrix.length;
            int m = matrix[0].length;
            preSum = new int[n + 1][m + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    preSum[i + 1][j + 1] = preSum[i + 1][j] + preSum[i][j + 1] + matrix[i][j] - preSum[i][j];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
//            System.out.println(preSum[row2 + 1][col2 + 1]);
//            System.out.println(preSum[row2 + 1][col1]);
//            System.out.println(preSum[row1][col2 + 1]);
//            System.out.println(preSum[row1][col1]);
            return preSum[row2 + 1][col2 + 1] - preSum[row2 + 1][col1] - preSum[row1][col2 + 1] + preSum[row1][col1];
        }
    }
}
