package com.lhb.backtrack.island;

import com.alibaba.fastjson.JSON;

/**
 * @program: LeetCodeRecord
 * @description: 39. 组合总和（中等）
 * @author: linhaibin
 * @create: 2022-04-21 01:03
 **/
public class _1254NumberOfClosedIslands {
    /**
     * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
     * <p>
     * 请返回 封闭岛屿 的数目。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
     * 输出：2
     * 解释：
     * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
     * 示例 2：
     * <p>
     * <p>
     * <p>
     * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：grid = [[1,1,1,1,1,1,1],
     *              [1,0,0,0,0,0,1],
     *              [1,0,1,1,1,0,1],
     *              [1,0,1,0,1,0,1],
     *              [1,0,1,1,1,0,1],
     *              [1,0,0,0,0,0,1],
     * [1,1,1,1,1,1,1]]
     * 输出：2
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= grid.length, grid[0].length <= 100
     * 0 <= grid[i][j] <=1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-closed-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] grid = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };
        for (int[] ints : grid) {
            System.out.println(JSON.toJSONString(ints));
        }
        System.out.println(JSON.toJSONString(solution.closedIsland(grid)));
        int[][] grid1 = new int[][]{
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0}
        };
        for (int[] ints : grid1) {
            System.out.println(JSON.toJSONString(ints));
        }
        System.out.println(JSON.toJSONString(solution.closedIsland(grid1)));


        int[][] grid2 = new int[][]{
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };
        for (int[] ints : grid2) {
            System.out.println(JSON.toJSONString(ints));
        }
        System.out.println(JSON.toJSONString(solution.closedIsland(grid2)));


    }

    static class Solution {

        public int closedIsland(int[][] grid) {
            int res = 0;
            int m = grid.length, n = grid[0].length;
            // 先把边边的岛屿淹掉
            for (int i = 0; i < m; i++) {
                backtrack(grid, i, 0);
                backtrack(grid, i, n - 1);
            }
            for (int j = 0; j < n; j++) {
                backtrack(grid, 0, j);
                backtrack(grid, m - 1, j);
            }
            // 遍历 grid
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (grid[i][j] == 0) {
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

        private void backtrack(int[][] grid, int i, int j) {
            int m = grid.length;
            int n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (grid[i][j] == 1) {
                // 已经是海⽔了
                return;
            }
            // 将 (i, j) 变成海⽔
            grid[i][j] = 1;
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
