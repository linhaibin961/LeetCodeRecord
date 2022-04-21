package com.lhb.backtrack.island;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;

/**
 * @program: LeetCodeRecord
 * @description: 39. 组合总和（中等）
 * @author: linhaibin
 * @create: 2022-04-21 01:03
 **/
public class _200NumberOfIslands {
    /**
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * <p>
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * <p>
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：grid = [
     * ["1","1","1","1","0"],
     * ["1","1","0","1","0"],
     * ["1","1","0","0","0"],
     * ["0","0","0","0","0"]
     * ]
     * 输出：1
     * 示例 2：
     * <p>
     * 输入：grid = [
     * ["1","1","0","0","0"],
     * ["1","1","0","0","0"],
     * ["0","0","1","0","0"],
     * ["0","0","0","1","1"]
     * ]
     * 输出：3
     *  
     * <p>
     * 提示：
     * <p>
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 300
     * grid[i][j] 的值为 '0' 或 '1'
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        for (char[] chars : grid) {
            System.out.println(JSON.toJSONString(chars));
        }
        System.out.println(JSON.toJSONString(solution.numIslands(grid)));


        char[][] grid2 = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        for (char[] chars : grid2) {
            System.out.println(JSON.toJSONString(chars));
        }
        System.out.println(JSON.toJSONString(solution.numIslands(grid2)));


    }

    static class Solution {

        public int numIslands(char[][] grid) {
            int res = 0;
            int m = grid.length, n = grid[0].length;
            // 遍历 grid
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        // 每发现⼀个岛屿，岛屿数量加⼀
                        res++;
                        // 然后使⽤ DFS 将岛屿淹了
                        backtrack(grid, i, j);
                    }
                }
            }
            return res;
        }

        // ⽅向数组，分别代表上、下、左、右
//        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        private void backtrack(char[][] grid, int i, int j) {
            int m = grid.length;
            int n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (grid[i][j] == '0') {
                // 已经是海⽔了
                return;
            }
            // 将 (i, j) 变成海⽔
            grid[i][j] = '0';
            backtrack(grid, i - 1, j); // 上
            backtrack(grid, i + 1, j); // 下
            backtrack(grid, i, j - 1); // 左
            backtrack(grid, i, j + 1); // 右
//            /** 方法二开始 */
//            // 进⼊节点 (i, j)
//            visited[i][j] = true;
//            // 递归遍历上下左右的节点
//            for (int[] d : dirs) {
//                int next_i = i + d[0];
//                int next_j = j + d[1];
//                backtrack(grid, next_i, next_j, visited);
//            }
//            // 离开节点 (i, j)
//            /** 方法二结束 */

        }

    }

}
