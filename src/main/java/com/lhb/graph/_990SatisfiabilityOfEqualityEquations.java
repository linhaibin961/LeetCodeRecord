package com.lhb.graph;

import com.alibaba.fastjson.JSON;

/**
 * @program: LeetCodeRecord
 * @description: 323. ⽆向图中的连通分量数⽬（中等）
 * @author: linhaibin
 * @create: 2022-04-20 10:03
 **/
public class _990SatisfiabilityOfEqualityEquations {
    /**
     * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     * <p>
     * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：["a==b","b!=a"]
     * 输出：false
     * 解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
     * 示例 2：
     * <p>
     * 输入：["b==a","a==b"]
     * 输出：true
     * 解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
     * 示例 3：
     * <p>
     * 输入：["a==b","b==c","a==c"]
     * 输出：true
     * 示例 4：
     * <p>
     * 输入：["a==b","b!=c","c==a"]
     * 输出：false
     * 示例 5：
     * <p>
     * 输入：["c==c","b==d","x!=z"]
     * 输出：true
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= equations.length <= 500
     * equations[i].length == 4
     * equations[i][0] 和 equations[i][3] 是小写字母
     * equations[i][1] 要么是 '='，要么是 '!'
     * equations[i][2] 是 '='
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] equations = new String[]{"a==b", "b!=a"};
        String[] equations2 = new String[]{"b==a", "a==b"};
        String[] equations3 = new String[]{"a==b", "b==c", "a==c"};
        String[] equations4 = new String[]{"a==b", "b!=c", "c==a"};
        String[] equations5 = new String[]{"c==c", "b==d", "x!=z"};
        String[] equations6 = new String[]{"a==b","e==c","b==c","a!=e"};
        String[] equations7 = new String[]{"b!=a","a==e","a!=f","d==f","a!=c"};
//        System.out.println(JSON.toJSONString(equations));
//        System.out.println(JSON.toJSONString(solution.equationsPossible(equations)));
//
//        System.out.println(JSON.toJSONString(equations2));
//        System.out.println(JSON.toJSONString(solution.equationsPossible(equations2)));
//
//        System.out.println(JSON.toJSONString(equations3));
//        System.out.println(JSON.toJSONString(solution.equationsPossible(equations3)));
//
//        System.out.println(JSON.toJSONString(equations4));
//        System.out.println(JSON.toJSONString(solution.equationsPossible(equations4)));
//
//        System.out.println(JSON.toJSONString(equations5));
//        System.out.println(JSON.toJSONString(solution.equationsPossible(equations5)));

        System.out.println(JSON.toJSONString(equations6));
        System.out.println(JSON.toJSONString(solution.equationsPossible(equations6)));

        System.out.println(JSON.toJSONString(equations7));
        System.out.println(JSON.toJSONString(solution.equationsPossible(equations7)));


    }

    static class Solution {
        public boolean equationsPossible(String[] equations) {
            UF uf = new UF(26);
            for (String equation : equations) {
                char c = equation.charAt(1);
                if (c == '=') {
                    uf.union(equation.charAt(0) - 'a', equation.charAt(3) - 'a');
                }
            }
            for (String equation : equations) {
                char c = equation.charAt(1);
                if (c == '!' && uf.connected(equation.charAt(0) - 'a', equation.charAt(3) - 'a')) {
                    return false;
                }
            }
            return true;
        }

        static class UF {
            // 存储每个节点的⽗节点
            int[] parent;
            // 连通分量个数
            int count;
            // 记录树的“重量”
            private int[] size;
            // n 为图中节点的个数
            public UF(int n) {
                this.count = n;
                parent = new int[n];
                size = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                    size[i] = 1;
                }
            }

            // 将节点 p 和节点 q 连通
            public void union(int p, int q) {
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) {
                    return;
                }
                // 小树接到大树下面，较平衡
                if (size[rootP] > size[rootQ]) {
                    parent[rootQ] = rootP;
                    size[rootP] += size[rootQ];
                } else {
                    parent[rootP] = rootQ;
                    size[rootQ] += size[rootP];
                }
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
