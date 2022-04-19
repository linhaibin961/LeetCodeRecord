package com.lhb.graph;

import com.alibaba.fastjson.JSON;
import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 797. 所有可能的路径（中等）
 * @author: linhaibin
 * @create: 2022-04-19 10:03
 **/
public class _797AllPathsFromSourceToTarget {
    /**
     * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
     * <p>
     *  graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：graph = [[1,2],[3],[3],[]]
     * 输出：[[0,1,3],[0,2,3]]
     * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
     * 示例 2：
     * <p>
     * <p>
     * <p>
     * 输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
     * 输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
     *  
     * <p>
     * 提示：
     * <p>
     * n == graph.length
     * 2 <= n <= 15
     * 0 <= graph[i][j] < n
     * graph[i][j] != i（即不存在自环）
     * graph[i] 中的所有元素 互不相同
     * 保证输入为 有向无环图（DAG）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] ints = new int[][]{{1, 2}, {3}, {3}, {}};
        int[][] ints2 = new int[][]{{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        System.out.println(JSON.toJSONString(ints));
        System.out.println(JSON.toJSONString(ints2));

        Solution solution = new Solution();
        List<List<Integer>> lists = solution.allPathsSourceTarget(ints);
        System.out.println(JSON.toJSONString(lists));

        System.out.println(JSON.toJSONString(new Solution2().allPathsSourceTarget(ints)));
        System.out.println(JSON.toJSONString(new Solution3().allPathsSourceTarget(ints)));
        System.out.println(JSON.toJSONString(new Solution4().allPathsSourceTarget(ints)));

    }


    static class Solution {
        // 记录所有路径
        List<List<Integer>> res = new LinkedList<>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            // 维护递归过程中经过的路径
            LinkedList<Integer> path = new LinkedList<>();
            traverse(graph, 0, path);
            return res;
        }

        /* 图的遍历框架 */
        private void traverse(int[][] graph, int s, LinkedList<Integer> path) {
            // 添加节点 s 到路径
            path.add(s);
            int length = graph.length;
            if (s + 1 == length) {
                // 到达终点
                res.add(new LinkedList<>(path));
                // 从路径移出节点 s
                path.removeLast();
                return;
            }
            // 递归每个相邻节点
            for (int v : graph[s]) {
                traverse(graph, v, path);
            }
            // 从路径移出节点 s
            path.removeLast();

        }
    }

    /**
     * leetcode 官方解答
     */
    static class Solution2 {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Deque<Integer> stack = new ArrayDeque<Integer>();

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            stack.offerLast(0);
            dfs(graph, 0, graph.length - 1);
            return ans;
        }

        public void dfs(int[][] graph, int x, int n) {
            if (x == n) {
                ans.add(new ArrayList<Integer>(stack));
                return;
            }
            for (int y : graph[x]) {
                stack.offerLast(y);
                dfs(graph, y, n);
                stack.pollLast();
            }
        }
    }

    /**
     * bfs
     */
    static class Solution3 {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

            List<List<Integer>> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            path.add(0);
            Queue<List<Integer>> queue = new LinkedList<>();
            queue.offer(path);

            while (!queue.isEmpty()) {
                List<Integer> currentList = queue.poll();
                int currentNode = currentList.get(currentList.size() - 1);
                for (int i : graph[currentNode]) {
                    currentList.add(i);
                    if (i == graph.length - 1) {
                        res.add(new ArrayList<>(currentList));
                    } else {
                        queue.offer(new ArrayList<>(currentList));
                    }
                    currentList.remove(currentList.size() - 1);    //回溯--思路与DFS回溯一致
                }
            }
            return res;
        }
    }

    static class Solution4 {
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            List<List<Integer>> res = new ArrayList<>();
            int size = graph.length;
            Queue<List<Integer>> queue = new LinkedList<>();
            queue.offer(Arrays.asList(0));
            while (!queue.isEmpty()) {
                int n = queue.size();
                for (int i = 0; i < n; i++) {
                    List<Integer> cul = queue.poll();
                    int last = cul.get(cul.size() - 1);
                    if (last == size - 1) {
                        res.add(cul);
                        continue;
                    }
                    int[] dist = graph[last];
                    for (int num : dist) {
                        List<Integer> list = new ArrayList<>(cul);
                        list.add(num);
                        queue.offer(list);
                    }
                }
            }
            return res;
        }
    }
}
