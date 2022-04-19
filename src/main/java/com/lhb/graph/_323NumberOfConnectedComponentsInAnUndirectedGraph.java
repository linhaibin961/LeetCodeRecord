package com.lhb.graph;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 323. ⽆向图中的连通分量数⽬（中等）
 * @author: linhaibin
 * @create: 2022-04-20 10:03
 **/
public class _323NumberOfConnectedComponentsInAnUndirectedGraph {
    /**
     * 题目描述:
     * 给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。
     * <p>
     * 示例:
     * 示例 1:
     * <p>
     * 输入: n = 5 和 edges = [[0, 1], [1, 2], [3, 4]]
     * <p>
     * 0          3
     * |          |
     * 1 --- 2    4
     * <p>
     * 输出: 2
     * 示例 2:
     * <p>
     * 输入: n = 5 和 edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
     * <p>
     * 0           4
     * |           |
     * 1 --- 2 --- 3
     * <p>
     * 输出:  1
     * 注意:
     * <p>
     * 你可以假设在 edges 中不会出现重复的边。而且由于所以的边都是无向边，[0, 1] 与 [1, 0] 相同，所以它们不会同时在 edges 中出现。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] prerequisites = new int[][]{{0, 1}, {1, 2}, {3, 4}};
        System.out.println(JSON.toJSONString(prerequisites));
        System.out.println(JSON.toJSONString(solution.countComponents(5, prerequisites)));


        int[][] prerequisites2 = new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}};
        System.out.println(JSON.toJSONString(prerequisites2));
        // 输出false
        System.out.println(JSON.toJSONString(solution.countComponents(5, prerequisites2)));

    }

    static class Solution {
        public int countComponents(int n, int[][] edges) {
            UF uf = new UF(n);
            for (int[] edge : edges) {
                uf.union(edge[0], edge[1]);
            }
            return uf.count();
        }

        static class UF {
            // 存储每个节点的⽗节点
            int[] parent;
            // 连通分量个数
            int count;

            // n 为图中节点的个数
            public UF(int n) {
                this.count = n;
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            // 将节点 p 和节点 q 连通
            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) {
                    return;
                }
                parent[q] = rootP;
                // 两个连通分量合并成⼀个连通分量
                count--;
            }

            // 返回节点 x 的连通分量根节点
            private int find(int x) {
                /**
                 * 1.判断父节点是不是x本身，
                 * 1.1如果是说明x是根节点
                 * 1.2 如果不是，则把父节点赋值给x，从走1步骤
                 * 1.2.1 在把父节点赋值给x之前，顺便把当前的x节点的父节点指向爷爷节点
                 */
                while (x != parent[x]) {
                    // 进⾏路径压缩,顺便把当前的x节点的父节点指向爷爷节点，减少一层树
                    parent[x] = parent[parent[x]];
                    x = parent[x];
                }
                return x;
            }

            public boolean connected(int p, int q) {
                return find(p) == find(q);
            }

            public int count() {
                return this.count;
            }
        }
    }
}
