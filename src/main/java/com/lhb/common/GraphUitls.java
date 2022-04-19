package com.lhb.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 图物理结构
 * @author: linhaibin
 * @create: 2022-04-19 10:32
 **/
public class GraphUitls {
    /**
     * 概念：
     * Vertex 顶点
     * Edge 边
     * degree 度
     * outDegree 出度
     * inDegree 入度
     * graph 图
     * matrix 矩阵
     * boolean[] visited; 记录被遍历过的节点
     * boolean[] onPath  记录从起点到当前节点的路径
     * 邻接表
     * 邻接矩阵
     * 邻接 adjacency
     * <p>
     * 有向无环图,Directed Acyclic Graph
     * 有向加权图（directed weighted graph）
     * 无向加权图（undirected weighted graph）
     */
    /* 图节点的逻辑结构 */
    static class Vertex {
        int id;
        Vertex[] neighbors;
    }

    /* 基本的 N 叉树节点 */
    static class TreeNode {
        int val;
        TreeNode[] children;
    }

    /**
     * 有向⽆权图
     * ⽆向图怎么实现？也很简单，所谓的「⽆向」，是不是等同于「双向」？
     * 如果连接⽆向图中的节点 x 和 y，把 matrix[x][y] 和 matrix[y][x] 都变成 true 不就⾏了；
     * 邻接表也是类似的操作，在 x 的邻居列表⾥添加 y，同时在 y 的邻居列表⾥添加 x。
     */
    static class Graph {
        // 邻接表
        // graph[x] 存储 x 的所有邻居节点
        List<Integer>[] neighbors;
        // 邻接矩阵
        // matrix[x][y] 记录 x 是否有⼀条指向 y 的边
        boolean[][] matrix;
        /*
         * 类⽐贪吃蛇游戏，visited 记录蛇经过过的格⼦，⽽ onPath 仅仅记录蛇身。
         * onPath ⽤于判断是否成环，类⽐当贪吃蛇⾃⼰咬到⾃⼰（成环）的场景
         */
        // 记录被遍历过的节点
        boolean[] visited;
        // 记录从起点到当前节点的路径
        boolean[] onPath;

        /* 图遍历框架 */
        void traverse(Graph graph, int s) {
            if (visited[s]) {
                return;
            }
            this.neighbors = new List[10];
/*            //insertion
            for (int i = 0; i < 10; i++) {
                neighbors[i] = new ArrayList<Integer>();
                neighbors[i].add(i);
                neighbors[i].add(i + 1);
            }

            //printing
            for (List<Integer> list : neighbors) {
                System.out.println(list.size());
            }*/

            // 经过节点 s，标记为已遍历
            visited[s] = true;
            // 做选择：标记节点 s 在路径上
            onPath[s] = true;
            for (int neighbor : graph.neighbors[s]) {
                traverse(graph, neighbor);
            }
            // 撤销选择：节点 s 离开路径
            onPath[s] = false;
        }

        /**
         * 有向加权图（directed weighted graph）
         * 无向加权图（undirected weighted graph）
         * ⽆向图怎么实现？也很简单，所谓的「⽆向」，是不是等同于「双向」？
         * 如果连接⽆向图中的节点 x 和 y，把 matrix[x][y] 和 matrix[y][x] 都变成 true 不就⾏了；
         * 邻接表也是类似的操作，在 x 的邻居列表⾥添加 y，同时在 y 的邻居列表⾥添加 x。
         */
        static class DGraph {

            // 邻接表
            // graph[x] 存储 x 的所有邻居节点以及对应的权重
            List<int[]>[] graph;
            // 邻接矩阵
            // matrix[x][y] 记录 x 指向 y 的边的权重，0 表示不相邻
            int[][] matrix;
        }
    }

//    邻接矩阵 ->邻接表

//    对于无权图来说 edges[i]=(u_i,v_i)edges[i]=(u i,v i)

    private List<Integer>[] buildGraph(int[][] edges, int n) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<Integer>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            graph[from].add(to);
        }
        return graph;
    }

//    对于有权图来说 edges[i]=(u_i,v_i,w_i)edges[i]=(u i, vi, wi    )

    private List<int[]>[] buildHeightGraph(int[][] edges, int n) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<int[]>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph[from].add(new int[]{to, weight});
        }
        return graph;
    }

}
