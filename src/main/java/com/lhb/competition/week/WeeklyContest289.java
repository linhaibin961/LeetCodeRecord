package com.lhb.competition.week;

import com.lhb.common.TreeNode;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @program: LeetCodeRecord
 * @description:
 * @author: linhaibin
 * @create: 2022-04-17 10:41
 **/
public class WeeklyContest289 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.digitSum("11111222223", 3));
        int[] tasks = new int[]{66, 66, 63, 61, 63, 63, 64, 66, 66, 65, 66, 65, 61, 67, 68, 66, 62, 67, 61, 64, 66, 60, 69, 66, 65, 68, 63, 60, 67, 62, 68, 60, 66, 64, 60, 60, 60, 62, 66, 64, 63, 65, 60, 69, 63, 68, 68, 69, 68, 61};
        System.out.println(solution.minimumRounds(tasks));

    }

    static class Solution {

        /**
         * 第一题 6070. 计算字符串的数字和
         * <p>
         * 给你一个由若干数字（0 - 9）组成的字符串 s ，和一个整数。
         * <p>
         * 如果 s 的长度大于 k ，则可以执行一轮操作。在一轮操作中，需要完成以下工作：
         * <p>
         * 将 s 拆分 成长度为 k 的若干 连续数字组 ，使得前 k 个字符都分在第一组，接下来的 k 个字符都分在第二组，依此类推。注意，最后一个数字组的长度可以小于 k 。
         * 用表示每个数字组中所有数字之和的字符串来 替换 对应的数字组。例如，"346" 会替换为 "13" ，因为 3 + 4 + 6 = 13 。
         * 合并 所有组以形成一个新字符串。如果新字符串的长度大于 k 则重复第一步。
         * 返回在完成所有轮操作后的 s 。
         * <p>
         *  
         * <p>
         * 示例 1：
         * <p>
         * 输入：s = "11111222223", k = 3
         * 输出："135"
         * 解释：
         * - 第一轮，将 s 分成："111"、"112"、"222" 和 "23" 。
         * 接着，计算每一组的数字和：1 + 1 + 1 = 3、1 + 1 + 2 = 4、2 + 2 + 2 = 6 和 2 + 3 = 5 。
         *   这样，s 在第一轮之后变成 "3" + "4" + "6" + "5" = "3465" 。
         * - 第二轮，将 s 分成："346" 和 "5" 。
         *   接着，计算每一组的数字和：3 + 4 + 6 = 13 、5 = 5 。
         *   这样，s 在第二轮之后变成 "13" + "5" = "135" 。
         * 现在，s.length <= k ，所以返回 "135" 作为答案。
         * 示例 2：
         * <p>
         * 输入：s = "00000000", k = 3
         * 输出："000"
         * 解释：
         * 将 "000", "000", and "00".
         * 接着，计算每一组的数字和：0 + 0 + 0 = 0 、0 + 0 + 0 = 0 和 0 + 0 = 0 。
         * s 变为 "0" + "0" + "0" = "000" ，其长度等于 k ，所以返回 "000" 。
         *  
         * <p>
         * 提示：
         * <p>
         * 1 <= s.length <= 100
         * 2 <= k <= 100
         * s 仅由数字（0 - 9）组成。
         * <p>
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/calculate-digit-sum-of-a-string
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         *
         * @param s
         * @param k
         * @return
         */
        public String digitSum(String s, int k) {
            String result = s;
            while (result.length() > k) {
                StringBuilder sb = new StringBuilder();
                int sum = 0;
                for (int i = 1; i <= result.length(); i++) {
                    char c = result.charAt(i - 1);
                    sum += c - '0';
                    if (i % k == 0 || i == result.length()) {
                        sb.append(sum);
                        sum = 0;
                    }
                }
                result = sb.toString();
            }

            return result;
        }


        /**
         * 第二题 6071. 完成所有任务需要的最少轮数
         * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
         * <p>
         * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
         * <p>
         *  
         * <p>
         * 示例 1：
         * <p>
         * 输入：tasks = [2,2,3,3,2,4,4,4,4,4]
         * 输出：4
         * 解释：要想完成所有任务，一个可能的计划是：
         * - 第一轮，完成难度级别为 2 的 3 个任务。
         * - 第二轮，完成难度级别为 3 的 2 个任务。
         * - 第三轮，完成难度级别为 4 的 3 个任务。
         * - 第四轮，完成难度级别为 4 的 2 个任务。
         * 可以证明，无法在少于 4 轮的情况下完成所有任务，所以答案为 4 。
         * 示例 2：
         * <p>
         * 输入：tasks = [2,3,3]
         * 输出：-1
         * 解释：难度级别为 2 的任务只有 1 个，但每一轮执行中，只能选择完成 2 个或者 3 个相同难度级别的任务。因此，无法完成所有任务，答案为 -1 。
         *  
         * <p>
         * 提示：
         * <p>
         * 1 <= tasks.length <= 105
         * 1 <= tasks[i] <= 109
         * <p>
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/minimum-rounds-to-complete-all-tasks
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        HashMap<Integer, Integer> map = new HashMap();

        public int minimumRounds(int[] tasks) {
            for (int task : tasks) {
                Integer orDefault = map.getOrDefault(task, 0);
                map.put(task, orDefault + 1);
            }
            int count = 0;
            for (Integer value : map.values()) {
                if (value == 1) {
                    return -1;
                }
                int i = value % 3;
                int times = value / 3;
                if (i == 0) {
                    count += times;
                } else {
                    count += times + 1;
                }
            }
            return count;
        }


        /**
         * 第三题 6073. 相邻字符不同的最长路径
         * 给你一棵 树（即一个连通、无向、无环图），根节点是节点 0 ，这棵树由编号从 0 到 n - 1 的 n 个节点组成。用下标从 0 开始、长度为 n 的数组 parent 来表示这棵树，其中 parent[i] 是节点 i 的父节点，由于节点 0 是根节点，所以 parent[0] == -1 。
         * <p>
         * 另给你一个字符串 s ，长度也是 n ，其中 s[i] 表示分配给节点 i 的字符。
         * <p>
         * 请你找出路径上任意一对相邻节点都没有分配到相同字符的 最长路径 ，并返回该路径的长度。
         * <p>
         *  
         * <p>
         * 示例 1：
         * <p>
         * <p>
         * <p>
         * 输入：parent = [-1,0,0,1,1,2], s = "abacbe"
         * 输出：3
         * 解释：任意一对相邻节点字符都不同的最长路径是：0 -> 1 -> 3 。该路径的长度是 3 ，所以返回 3 。
         * 可以证明不存在满足上述条件且比 3 更长的路径。
         * 示例 2：
         * <p>
         * <p>
         * <p>
         * 输入：parent = [-1,0,0,0], s = "aabc"
         * 输出：3
         * 解释：任意一对相邻节点字符都不同的最长路径是：2 -> 0 -> 3 。该路径的长度为 3 ，所以返回 3 。
         *  
         * <p>
         * 提示：
         * <p>
         * n == parent.length == s.length
         * 1 <= n <= 105
         * 对所有 i >= 1 ，0 <= parent[i] <= n - 1 均成立
         * parent[0] == -1
         * parent 表示一棵有效的树
         * s 仅由小写英文字母组成
         * <p>
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/longest-path-with-different-adjacent-characters
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        // 记录最长路径的⻓度
        int maxDiameter = 0;


        LinkedList<Pair<Integer, Character>> list = new LinkedList();

        /**
         * 我的思路本来是构建二叉树，后序遍历每一个树的不重复最大路径，还没完成
         * 其实不是二叉树
         *
         * @param parent
         * @param s
         * @return
         */
        public int longestPath(int[] parent, String s) {
            for (int i = 0; i < parent.length - 1; i++) {
                list.add(new Pair<Integer, Character>(i, s.charAt(i)));
            }
            TreeNode treeNode = buildTree(list);
            diameterOfBinaryTree(treeNode);
            return maxDiameter;
        }

        private TreeNode buildTree(LinkedList<Pair<Integer, Character>> nodes) {
            if (nodes.isEmpty()) {
                return null;
            }

            /****** 前序遍历位置 ******/
            // 列表最左侧就是根节点
            Pair<Integer, Character> integerCharacterPair = list.removeFirst();
            TreeNode root = new TreeNode(integerCharacterPair.getValue());
            root.left = buildTree(nodes);
            root.right = buildTree(nodes);

            return root;
        }

        /**
         * @param root 二叉树
         * @return
         */
        public int diameterOfBinaryTree(TreeNode root) {
            return maxDepth(root);
        }

        private int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftLength = maxDepth(root.left);
            int rightLength = maxDepth(root.right);
            int myDiameter = leftLength + rightLength;
            maxDiameter = Math.max(myDiameter, maxDiameter);
            if (root.val == root.left.val) {
                leftLength = 0;
            }
            if (root.val == root.right.val) {
                rightLength = 0;
            }
            return Math.max(leftLength, rightLength) + 1;
        }

    }

}
