package com.lhb.graph;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @program: LeetCodeRecord
 * @description: 210. 课程表 II（中等）
 * @author: linhaibin
 * @create: 2022-04-19 10:03
 **/
public class _210CourseSchedule2 {
    /**
     * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
     * <p>
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
     * <p>
     * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：[0,1]
     * 解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
     * 示例 2：
     * <p>
     * 输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
     * 输出：[0,2,1,3]
     * 解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
     * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
     * 示例 3：
     * <p>
     * 输入：numCourses = 1, prerequisites = []
     * 输出：[0]
     *  
     * <p>
     * 提示：
     * 1 <= numCourses <= 2000
     * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * ai != bi
     * 所有[ai, bi] 互不相同
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/course-schedule-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 解题：
     * 看到依赖问题，⾸先想到的就是把问题转化成「有向图」这种数据结构，只要图中存在环，那就说明存在循环依赖。
     * <p>
     * 如果发现这幅有向图中存在环，那就说明课程之间存在循环依赖，肯定没办法全部上完；反之，如果没有环，那么肯定能上完全部课程。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution2 solution2 = new Solution2();

        int[][] prerequisites = new int[][]{{1, 0}};
        System.out.println(JSON.toJSONString(prerequisites));
        System.out.println(JSON.toJSONString(solution.findOrder(2, prerequisites)));
        System.out.println(JSON.toJSONString(solution2.findOrder(2, prerequisites)));

        int[][] prerequisites2 = new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(JSON.toJSONString(prerequisites2));
        System.out.println(JSON.toJSONString(solution.findOrder(4, prerequisites2)));
        System.out.println(JSON.toJSONString(solution2.findOrder(4, prerequisites2)));

        int[][] prerequisites3 = new int[][]{{1, 0}, {2, 1}, {3, 2}, {1, 3}, {0, 4}};
        System.out.println(JSON.toJSONString(prerequisites3));
        System.out.println(JSON.toJSONString(solution.findOrder(5, prerequisites3)));
        System.out.println(JSON.toJSONString(solution2.findOrder(5, prerequisites3)));

    }

        /**
         * DFS
         */
        static class Solution {

            List<Integer> postOrder = new ArrayList<>();
            /*
             * 类⽐贪吃蛇游戏，visited 记录蛇经过过的格⼦，⽽ onPath 仅仅记录蛇身。
             * onPath ⽤于判断是否成环，类⽐当贪吃蛇⾃⼰咬到⾃⼰（成环）的场景
             */
            boolean[] onPath;
            boolean[] visited;
            boolean hasCycle = false;

            public int[] findOrder(int numCourses, int[][] prerequisites) {
                List<Integer>[] graph = buildGraph(numCourses, prerequisites);
                visited = new boolean[numCourses];
                onPath = new boolean[numCourses];
                for (int i = 0; i < numCourses; i++) {
                    traverse(graph, i);
                }
                if (hasCycle) {
                    return new int[]{};
                }
                // 逆后序遍历结果即为拓扑排序结果
                Collections.reverse(postOrder);
                int[] res = new int[numCourses];

                for (int i = 0; i < numCourses; i++) {
                    res[i] = postOrder.get(i);
                }
                return res;
            }


            private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
                List<Integer>[] graph = new LinkedList[numCourses];
                for (int i = 0; i < numCourses; i++) {
                    graph[i] = new LinkedList<>();
                }
                /**
                 * 边的含义是「被依赖」关系 如prerequisites={[1,0]} 第一门课依赖第0门课，也可以理解为第0门课被第一门课依赖
                 * 在后续后序遍历加入列表时，是1比0先加入列表，所以返回结果是要是要postOrder反转一下。
                 */
                for (int[] edge : prerequisites) {
                    int from = edge[1];
                    int to = edge[0];
                    graph[from].add(to);
                }
                return graph;
            }

            private void traverse(List<Integer>[] graph, int s) {
                if (onPath[s]) {
                    // 发现环！！
                    hasCycle = true;
                }
                if (visited[s] || hasCycle) {
                    return;
                }
                // 将节点 s 标记为已遍历
                visited[s] = true;
                // 开始遍历节点 s
                onPath[s] = true;
                for (int v : graph[s]) {
                    traverse(graph, v);
                }
                /**
                 * 为什么在这里加？
                 * 后序遍历的理解，左右根顺序，在加入根的时候已经把左右节点加到列表了。
                 * 边(箭头方向)的含义是「被依赖」关系
                 *  0
                 *  ┴──┐
                 *     ↓
                 *     1
                 *
                 * 代入情景，就是第0门课被第一门课依赖，只有遍历过第一门课(第一门课加入过列表)，才会把第0门课加入列表 列表结果：[1,0]
                 * 返回结果的时候会把postOrder反转一下[0,1]
                 */
                // 后序遍历位置
                postOrder.add(s);
                // 节点 s 遍历完成
                onPath[s] = false;
            }

        }

        /**
         * BFS
         * BFS 算法借助 indegree 数组记录每个节点的「⼊度」
         */
        static class Solution2 {
            // 主函数
            public int[] findOrder(int numCourses, int[][] prerequisites) {
                // 建图，和环检测算法相同
                List<Integer>[] graph = buildGraph(numCourses, prerequisites);
                // 计算⼊度，和环检测算法相同
                int[] indgree = new int[numCourses];
                for (int[] edge : prerequisites) {
                    int from = edge[1], to = edge[0];
                    indgree[to]++;
                }
                // 根据⼊度初始化队列中的节点，和环检测算法相同
                Queue<Integer> q = new LinkedList<>();
                for (int i = 0; i < numCourses; i++) {
                    if (indgree[i] == 0) {
                        q.offer(i);
                    }
                }
                // 记录拓扑排序结果
                int[] res = new int[numCourses];
                // 记录遍历节点的顺序（索引）
                int count = 0;
                // 开始执⾏ BFS 算法
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    // 弹出节点的顺序即为拓扑排序结果
                    res[count] = cur;
                    count++;
                    for (int next : graph[cur]) {
                        indgree[next]--;
                        if (indgree[next] == 0) {
                            q.offer(next);
                        }
                    }
                }
                if (count != numCourses) {
                    // 存在环，拓扑排序不存在
                    return new int[]{};
                }
                return res;
            }

            // 建图函数
            private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
                List<Integer>[] graph = new LinkedList[numCourses];
                for (int i = 0; i < numCourses; i++) {
                    graph[i] = new LinkedList<>();
                }
                /**
                 * 边的含义是「被依赖」关系 如prerequisites={[1,0]} 第一门课依赖第0门课，也可以理解为第0门课被第一门课依赖
                 * 在后续后序遍历加入列表时，是1比0先加入列表，所以返回结果是要是要postOrder反转一下。
                 */
                for (int[] edge : prerequisites) {
                    int from = edge[1];
                    int to = edge[0];
                    graph[from].add(to);
                }
                return graph;
            }
        }
    }
