package com.lhb.graph;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 785. 判断⼆分图（中等）
 * @author: linhaibin
 * @create: 2022-04-19 10:03
 **/
public class _785IsGraphBipartite {
    /**
     * 存在一个 无向图 ，图中有 n 个节点。
     * 其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。
     * 给你一个二维数组 graph ，其中 graph[u] 是一个节点数组，由节点 u 的邻接节点组成。
     * 形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。
     * <p>
     * 该无向图同时具有以下属性：
     * *不存在自环（graph[u] 不包含 u）。
     * *不存在平行边（graph[u] 不包含重复值）。
     * *如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图）
     * *这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。
     * <p>
     * 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称为 二分图 。
     * <p>
     * 如果图是二分图，返回 true ；否则，返回 false 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
     * 输出：false
     * 解释：不能将节点分割成两个独立的子集，以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。
     * 示例 2：
     * <p>
     * <p>
     * 输入：graph = [[1,3],[0,2],[1,3],[0,2]]
     * 输出：true
     * 解释：可以将节点分成两组: {0, 2} 和 {1, 3} 。
     *  
     * <p>
     * 提示：
     * <p>
     * graph.length == n
     * 1 <= n <= 100
     * 0 <= graph[u].length < n
     * 0 <= graph[u][i] <= n - 1
     * graph[u] 不会包含 u
     * graph[u] 的所有值 互不相同
     * 如果 graph[u] 包含 v，那么 graph[v] 也会包含 u
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/is-graph-bipartite
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        int[][] prerequisites = new int[][]{{1, 2, 3}, {0, 2}, {0, 1, 3}, {0, 2}};
        System.out.println(JSON.toJSONString(prerequisites));
        System.out.println(JSON.toJSONString(solution.isBipartite(prerequisites)));

        solution.ok = true;
        int[][] prerequisites2 = new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println(JSON.toJSONString(prerequisites2));
        System.out.println(JSON.toJSONString(solution.isBipartite(prerequisites2)));

    }

    @Data
    /**
     * labuladong 解法
     */
    static class Solution {

        // 记录图是否符合二分图性质
        private boolean ok = true;
        // 记录图中节点的颜色，false 和 true 代表两种不同颜色
        private boolean[] color;
        // 记录图中节点是否被访问过
        private boolean[] visited;

        // 主函数，输入邻接表，判断是否是二分图
        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            color = new boolean[n];
            visited = new boolean[n];
            // 因为图不一定是联通的，可能存在多个子图
            // 所以要把每个节点都作为起点进行一次遍历
            // 如果发现任何一个子图不是二分图，整幅图都不算二分图
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    traverse(graph, v);
                }
            }
            return ok;
        }

        // DFS 遍历框架
        private void traverse(int[][] graph, int v) {
            // 如果已经确定不是二分图了，就不用浪费时间再递归遍历了
            if (!ok) return;

            visited[v] = true;
            for (int w : graph[v]) {
                if (!visited[w]) {
                    // 相邻节点 w 没有被访问过
                    // 那么应该给节点 w 涂上和节点 v 不同的颜色
                    color[w] = !color[v];
                    // 继续遍历 w
                    traverse(graph, w);
                } else {
                    // 相邻节点 w 已经被访问过
                    // 根据 v 和 w 的颜色判断是否是二分图
                    if (color[w] == color[v]) {
                        // 若相同，则此图不是二分图
                        ok = false;
                    }
                }
            }
        }

    }

    /**
     * labuladong BFS解法
     */
    static class Solution0 {

        // 记录图是否符合⼆分图性质
        private boolean ok = true;
        // 记录图中节点的颜⾊，false 和 true 代表两种不同颜⾊
        private boolean[] color;
        // 记录图中节点是否被访问过
        private boolean[] visited;

        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            color = new boolean[n];
            visited = new boolean[n];
            for (int v = 0; v < n; v++) {
                if (!visited[v]) {
                    // 改为使⽤ BFS 函数
                    bfs(graph, v);
                }
            }
            return ok;
        }

        // 从 start 节点开始进⾏ BFS 遍历
        private void bfs(int[][] graph, int start) {
            Queue<Integer> q = new LinkedList<>();
            visited[start] = true;
            q.offer(start);
            while (!q.isEmpty() && ok) {
                int v = q.poll();
                // 从节点 v 向所有相邻节点扩散
                for (int w : graph[v]) {
                    if (!visited[w]) {
                        // 相邻节点 w 没有被访问过
                        // 那么应该给节点 w 涂上和节点 v 不同的颜⾊
                        color[w] = !color[v];// 标记 w 节点，并放⼊队列
                        visited[w] = true;
                        q.offer(w);
                    } else {
                        // 相邻节点 w 已经被访问过
                        // 根据 v 和 w 的颜⾊判断是否是⼆分图
                        if (color[w] == color[v]) {
                            // 若相同，则此图不是⼆分图
                            ok = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * 自己根据labuladong解法，手动重写的
     */
    static class Solution2 {
        boolean ok = true;
        boolean[] color;
        boolean[] visited;

        public boolean isBipartite(int[][] graph) {
            int length = graph.length;
            this.color = new boolean[length];
            this.visited = new boolean[length];

            for (int i = 0; i < length; i++) {
                traverse(graph, i);
            }
            return ok;
        }

        private void traverse(int[][] graph, int s) {
            if (visited[s] || !ok) {
                return;
            }
            visited[s] = true;
            int[] edges = graph[s];
            for (int edge : edges) {
                if (visited[edge] && color[s] == color[edge]) {
                    ok = false;
                } else {
                    color[edge] = !color[s];
                    traverse(graph, edge);

                }
            }
        }
    }

    /**
     * bfs
     */
    static class Solution3 {
        // bfs
        private boolean ok = true;

        public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            // visited[i] = 0 : 未染色 ; 1 : red ; -1 : blue
            int[] visited = new int[n];
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0) {
                    bfs(graph, visited, i);
                }
            }
            return ok;
        }

        private void bfs(int[][] graph, int[] visited, int start) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = 1;
            while (!queue.isEmpty() && ok) {
                int v = queue.poll();
                for (int i : graph[v]) {
                    if (visited[i] == 0) {
                        visited[i] = visited[v] * (-1);
                        queue.offer(i);
                    } else {
                        if (visited[i] == visited[v]) {
                            ok = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/is-graph-bipartite/solution/bfs-dfs-bing-cha-ji-san-chong-fang-fa-pan-duan-er-/
     * 并查集解法
     */
    static class Solution4 {
        public boolean isBipartite(int[][] graph) {
            // 初始化并查集
            UnionFind uf = new UnionFind(graph.length);
            // 遍历每个顶点，将当前顶点的所有邻接点进行合并
            for (int i = 0; i < graph.length; i++) {
                int[] adjs = graph[i];
                for (int w : adjs) {
                    // 若某个邻接点与当前顶点已经在一个集合中了，说明不是二分图，返回 false。
                    if (uf.isConnected(i, w)) {
                        return false;
                    }
                    uf.union(adjs[0], w);
                }
            }
            return true;
        }
    }

    // 并查集
    static class UnionFind {
        int[] roots;

        public UnionFind(int n) {
            roots = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
            }
        }

        public int find(int i) {
            if (roots[i] == i) {
                return i;
            }
            return roots[i] = find(roots[i]);
        }

        // 判断 p 和 q 是否在同一个集合中
        public boolean isConnected(int p, int q) {
            return find(q) == find(p);
        }

        // 合并 p 和 q 到一个集合中
        public void union(int p, int q) {
            roots[find(p)] = find(q);
        }
    }
}
