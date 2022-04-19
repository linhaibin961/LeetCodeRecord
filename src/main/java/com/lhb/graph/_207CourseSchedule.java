package com.lhb.graph;

import com.alibaba.fastjson.JSON;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: LeetCodeRecord
 * @description: 207. 课程表（中等）
 * @author: linhaibin
 * @create: 2022-04-19 10:03
 **/
public class _207CourseSchedule {
    /**
     * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * <p>
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * 示例 2：
     * <p>
     * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
     * 输出：false
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= numCourses <= 105
     * 0 <= prerequisites.length <= 5000
     * prerequisites[i].length == 2
     * 0 <= ai, bi < numCourses
     * prerequisites[i] 中的所有课程对 互不相同
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/course-schedule
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
        System.out.println(solution.canFinish(2, prerequisites));
        System.out.println(solution2.canFinish(2, prerequisites));


        int[][] prerequisites2 = new int[][]{{1, 0}, {0, 1}};
        System.out.println(JSON.toJSONString(prerequisites2));
        System.out.println(solution.canFinish(2, prerequisites2));
        System.out.println(solution2.canFinish(2, prerequisites2));




    }

    static class Graph {
        int[] visited;
        // 邻接表
        // graph[x] 存储 x 的所有邻居节点以及对应的权重
        List<Integer>[] adjacencyTable;

        public Graph(int[][] graph) {
            this.visited = new int[graph.length];
            this.adjacencyTable = new List[graph.length];
        }
    }

    static class Solution {

        /*
         * 类⽐贪吃蛇游戏，visited 记录蛇经过过的格⼦，⽽ onPath 仅仅记录蛇身。
         * onPath ⽤于判断是否成环，类⽐当贪吃蛇⾃⼰咬到⾃⼰（成环）的场景
         */
        boolean[] onPath;
        boolean[] visited;
        boolean hasCycle = false;

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            visited = new boolean[numCourses];
            onPath = new boolean[numCourses];
            for (int i = 0; i < numCourses; i++) {
                traverse(graph, i);
            }
            return !hasCycle;
        }

        private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new LinkedList[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<>();
            }
            /**
             * 边的含义是「被依赖」关系 如prerequisites={[1,0]} 第一门课依赖第0门课，也可以理解为第0门课被第一门课依赖
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
            // 节点 s 遍历完成
            onPath[s] = false;
        }

    }

    /**
     * BFS
     * BFS 算法借助 indegree 数组记录每个节点的「⼊度」
     * 总结下这段 BFS 算法的思路：
     * 1、构建邻接表，和之前⼀样，边的⽅向表示「被依赖」关系。
     * 2、构建⼀个 indegree 数组记录每个节点的⼊度，即 indegree[i] 记录节点 i 的⼊度。
     * 3、对 BFS 队列进⾏初始化，将⼊度为 0 的节点⾸先装⼊队列。
     * 4、开始执⾏ BFS 循环，不断弹出队列中的节点，减少相邻节点的⼊度，并将⼊度变为 0 的节点加⼊队列。
     * 5、如果最终所有节点都被遍历过（count 等于节点数），则说明不存在环，反之则说明存在环。
     */
    static class Solution2 {
        // ⼊度数组
        int[] indgree;
        // 主函数
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // 建图，有向边代表「被依赖」关系
            List<Integer>[] graph = buildGraph(numCourses, prerequisites);
            // 构建⼊度数组
            indgree = new int[numCourses];
            for (int[] edge : prerequisites) {
                int from = edge[1], to = edge[0];
                // 节点 to 的⼊度加⼀
                indgree[to]++;
            }
            // 根据⼊度初始化队列中的节点
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (indgree[i] == 0) {
                    // 节点 i 没有⼊度，即没有依赖的节点
                    // 可以作为拓扑排序的起点，加⼊队列
                    q.offer(i);
                }
            }
            // 记录遍历的节点个数
            int count = 0;
            // 开始执⾏ BFS 循环
            while (!q.isEmpty()) {
                // 弹出节点 cur，并将它指向的节点的⼊度减⼀
                int cur = q.poll();
                count++;
                for (int next : graph[cur]) {
                    indgree[next]--;
                    if (indgree[next] == 0) {
                        // 如果⼊度变为 0，说明 next 依赖的节点都已被遍历
                        q.offer(next);
                    }
                }
            }
            // 如果所有节点都被遍历过，说明不成环
            return count == numCourses;
        }

        // 建图函数
        private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
            List<Integer>[] graph = new LinkedList[numCourses];
            for (int i = 0; i < numCourses; i++) {
                graph[i] = new LinkedList<>();
            }
            /**
             * 边的含义是「被依赖」关系 如prerequisites={[1,0]} 第一门课依赖第0门课，也可以理解为第0门课被第一门课依赖
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
