package com.lhb.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 51. N 皇后（困难）
 * @author: linhaibin
 * @create: 2022-04-20 10:03
 **/
public class _51NQueens {
    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * <p>
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：n = 4
     * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
     * 解释：如上图所示，4 皇后问题存在两个不同的解法。
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：[["Q"]]
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 9
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-queens
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(JSON.toJSONString(solution.solveNQueens(4)));


    }

    static class Solution {

        List<List<String>> res = new ArrayList<>();

        /* 输入棋盘的边长n，返回所有合法的放置 */
        public List<List<String>> solveNQueens(int n) {
            // "." 表示空，"Q"表示皇后，初始化棋盘
            char[][] board = new char[n][n];
            for (char[] c : board) {
                Arrays.fill(c, '.');
            }
            backtrack(board, 0);
            return res;
        }

        public void backtrack(char[][] board, int row) {
            // 每一行都成功放置了皇后，记录结果
            if (row == board.length) {
                res.add(charToList(board));
                return;
            }

            int n = board[row].length;
            // 在当前行的每一列都可能放置皇后
            for (int col = 0; col < n; col++) {
                // 排除可以相互攻击的格子
                if (!isValid(board, row, col)) {
                    continue;
                }
                // 做选择
                board[row][col] = 'Q';
                // 进入下一行放皇后
                backtrack(board, row + 1);
                // 撤销选择
                board[row][col] = '.';
            }
        }

        /* 判断是否可以在 board[row][col] 放置皇后 */
        public boolean isValid(char[][] board, int row, int col) {
            int n = board.length;
            // 检查列是否有皇后冲突
            for (int i = 0; i < n; i++) {
                if (board[i][col] == 'Q') {
                    return false;
                }
            }

            // 检查右上方是否有皇后冲突
            for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }

            // 检查左上方是否有皇后冲突
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }

        public List charToList(char[][] board) {
            List<String> list = new ArrayList<>();

            for (char[] c : board) {
                list.add(String.copyValueOf(c));
            }
            return list;
        }

    }

}
