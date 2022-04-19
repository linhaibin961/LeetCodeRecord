package com.lhb.graph;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: LeetCodeRecord
 * @description: 785. 判断⼆分图（中等）
 * @author: linhaibin
 * @create: 2022-04-19 10:03
 **/
public class _886PossibleBipartition {
    /**
     * 给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
     * <p>
     * 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
     * 输出：true
     * 解释：group1 [1,4], group2 [2,3]
     * 示例 2：
     * <p>
     * 输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
     * 输出：false
     * 示例 3：
     * <p>
     * 输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
     * 输出：false
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 2000
     * 0 <= dislikes.length <= 104
     * dislikes[i].length == 2
     * 1 <= dislikes[i][j] <= n
     * ai < bi
     * dislikes 中每一组都 不同
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/possible-bipartition
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution2 solution2 = new Solution2();

        int[][] prerequisites = new int[][]{{1, 2}, {1, 3}, {2, 4}};
        System.out.println(JSON.toJSONString(prerequisites));
        System.out.println(JSON.toJSONString(solution.possibleBipartition(4, prerequisites)));
        System.out.println(JSON.toJSONString(solution2.possibleBipartition(4, prerequisites)));


        int[][] prerequisites2 = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        System.out.println(JSON.toJSONString(prerequisites2));
        // 输出false
        System.out.println(JSON.toJSONString(solution.possibleBipartition(4, prerequisites2)));
        System.out.println(JSON.toJSONString(solution2.possibleBipartition(4, prerequisites2)));

        int[][] prerequisites3 = new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        System.out.println(JSON.toJSONString(prerequisites3));
        System.out.println(JSON.toJSONString(solution.possibleBipartition(5, prerequisites3)));
        System.out.println(JSON.toJSONString(solution2.possibleBipartition(5, prerequisites3)));

    }

    static class Solution {

        private boolean ok = true;
        private boolean[] color;
        private boolean[] visited;

        public boolean possibleBipartition(int n, int[][] dislikes) {
            // 图节点编号从 1 开始
            color = new boolean[n + 1];
            visited = new boolean[n + 1];
            // 转化成邻接表表示图结构
            List<Integer>[] graph = buildGraph(n, dislikes);

            for (int v = 1; v <= n; v++) {
                if (!visited[v]) {
                    traverse(graph, v);
                }
            }
            return ok;
        }

        // 建图函数
        private List<Integer>[] buildGraph(int n, int[][] dislikes) {
            // 图节点编号为 1...n
            List<Integer>[] graph = new LinkedList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new LinkedList<>();
            }
            for (int[] edge : dislikes) {
                int v = edge[1];
                int w = edge[0];
                // 「无向图」相当于「双向图」
                // v -> w
                graph[v].add(w);
                // w -> v
                graph[w].add(v);
            }
            return graph;
        }

        // 和之前判定二分图的 traverse 函数完全相同
        private void traverse(List<Integer>[] graph, int v) {
            if (!ok) return;
            visited[v] = true;
            for (int w : graph[v]) {
                if (!visited[w]) {
                    color[w] = !color[v];
                    traverse(graph, w);
                } else {
                    if (color[w] == color[v]) {
                        ok = false;
                    }
                }
            }
        }

    }

    /**
     * 参照labuladong解法自己手动写的
     */
    static class Solution2 {
        boolean ok = true;
        boolean[] color;
        boolean[] visited;


        public boolean possibleBipartition(int n, int[][] dislikes) {
            color = new boolean[n + 1];
            visited = new boolean[n + 1];

            LinkedList<Integer>[] lists = buildGraph(n, dislikes);
            for (int i = 1; i < n + 1; i++) {
                traverse(lists, i);
            }
            return ok;
        }

        private void traverse(LinkedList<Integer>[] lists, int i) {
            if (visited[i] || !ok) {
                return;
            }
            visited[i] = true;
            for (Integer j : lists[i]) {
                if (visited[j]) {
                    if (color[i] == color[j]) {
                        ok = false;
                    }
                } else {
                    color[j] = !color[i];
                    traverse(lists, j);
                }
            }
        }

        private LinkedList<Integer>[] buildGraph(int n, int[][] dislikes) {
            LinkedList<Integer>[] lists = new LinkedList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                lists[i] = new LinkedList<>();
            }
            for (int i = 0; i < dislikes.length; i++) {
                int[] dislike = dislikes[i];
                int from = dislike[0];
                int to = dislike[1];
                lists[from].add(to);
                lists[to].add(from);
            }
            return lists;
        }

    }
}
