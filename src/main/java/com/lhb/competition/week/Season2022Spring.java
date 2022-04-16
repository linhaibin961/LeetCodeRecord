package com.lhb.competition.week;

/**
 * @program: LeetCodeRecord
 * @description: 招商银行专场竞赛
 * @author: linhaibin
 * @create: 2022-04-11 10:18
 **/

import com.lhb.common.TreeNode;
import com.lhb.common.TreeNodeUtils;
import com.lhb.common.TreePrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 招商银行专场竞赛
 * https://leetcode-cn.com/contest/cmbchina-2022spring/
 */
public class Season2022Spring {


    public static void main(String[] args) {
        Solution solution = new Solution();
/*        // 第一题
        int[] gem = new int[]{100, 0, 50, 100};
        int[][] operations = new int[][]{{0, 2}, {0, 1}, {3, 0}, {3, 0}};
        System.out.println(solution.giveGem(gem, operations));

        // 第二题
        int[] materials = new int[]{100, 0, 50, 100};
        int[][] attribute = new int[][]{{0, 2}, {0, 1}, {3, 0}, {3, 0}};
        int[][] cookbooks = new int[][]{{0, 2}, {0, 1}, {3, 0}, {3, 0}};
        int limit = 5;
        System.out.println(solution.giveGem(gem, operations));*/
        // 第三题

        int[] ints = new int[]{1, 2, 3, 4, 5};
        int[][] ops = new int[][]{{1, 2, 4}, {1, 1, 3}, {0, 3, 5}};
        TreeNode treeNode = new TreeNode(ints[0]);
        TreeNode temp = treeNode;
        for (int i = 1; i < ints.length; i++) {
            TreeNode node = new TreeNode(ints[i]);
            temp.right = node;
            temp = node;
        }
        TreePrinter.print(treeNode);
        System.out.println(solution.getNumber(treeNode, ops));

    }

    static class Solution {
        /**
         * 1. 宝石补给
         * 通过的用户数3797
         * 尝试过的用户数3956
         * 用户总通过次数3884
         * 用户总提交次数5801
         * 题目难度Easy
         * 欢迎各位勇者来到力扣新手村，在开始试炼之前，请各位勇者先进行「宝石补给」。
         * <p>
         * 每位勇者初始都拥有一些能量宝石， gem[i] 表示第 i 位勇者的宝石数量。现在这些勇者们进行了一系列的赠送，operations[j] = [x, y] 表示在第 j 次的赠送中 第 x 位勇者将自己一半的宝石（按需向下取整）赠送给第 y 位勇者。
         * <p>
         * 在完成所有的赠送后，请找到拥有最多宝石的勇者和拥有最少宝石的勇者，并返回他们二者的宝石数量之差。
         * <p>
         * 注意：
         * <p>
         * 赠送将按顺序逐步进行。
         * 示例 1：
         * <p>
         * 输入：gem = [3,1,2], operations = [[0,2],[2,1],[2,0]]
         * <p>
         * 输出：2
         * <p>
         * 解释：
         * 第 1 次操作，勇者 0 将一半的宝石赠送给勇者 2， gem = [2,1,3]
         * 第 2 次操作，勇者 2 将一半的宝石赠送给勇者 1， gem = [2,2,2]
         * 第 3 次操作，勇者 2 将一半的宝石赠送给勇者 0， gem = [3,2,1]
         * 返回 3 - 1 = 2
         * <p>
         * 示例 2：
         * <p>
         * 输入：gem = [100,0,50,100], operations = [[0,2],[0,1],[3,0],[3,0]]
         * <p>
         * 输出：75
         * <p>
         * 解释：
         * 第 1 次操作，勇者 0 将一半的宝石赠送给勇者 2， gem = [50,0,100,100]
         * 第 2 次操作，勇者 0 将一半的宝石赠送给勇者 1， gem = [25,25,100,100]
         * 第 3 次操作，勇者 3 将一半的宝石赠送给勇者 0， gem = [75,25,100,50]
         * 第 4 次操作，勇者 3 将一半的宝石赠送给勇者 0， gem = [100,25,100,25]
         * 返回 100 - 25 = 75
         * <p>
         * 示例 3：
         * <p>
         * 输入：gem = [0,0,0,0], operations = [[1,2],[3,1],[1,2]]
         * <p>
         * 输出：0
         * <p>
         * 提示：
         * <p>
         * 2 <= gem.length <= 10^3
         * 0 <= gem[i] <= 10^3
         * 0 <= operations.length <= 10^4
         * operations[i].length == 2
         * 0 <= operations[i][0], operations[i][1] < gem.length
         */
        public int giveGem(int[] gem, int[][] operations) {
            for (int[] operation : operations) {
                int a = operation[0];
                int b = operation[1];
                int temp = gem[a];
                int half = (gem[a] + 1) / 2;
                gem[a] = half;
                gem[b] += temp - half;
            }
            System.out.println(Arrays.toString(gem));
            int max = 0;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < gem.length; i++) {
                max = Math.max(max, gem[i]);
                min = Math.min(min, gem[i]);
            }
            return max - min;

        }

        /**
         * 欢迎各位勇者来到力扣城，城内设有烹饪锅供勇者制作料理，为自己恢复状态。
         * <p>
         * 勇者背包内共有编号为 0 ~ 4 的五种食材，其中 meterials[j] 表示第 j 种食材的数量。通过这些食材可以制作若干料理，cookbooks[i][j] 表示制作第 i 种料理需要第 j 种食材的数量，而 attribute[i] = [x,y] 表示第 i 道料理的美味度 x 和饱腹感 y。
         * <p>
         * 在饱腹感不小于 limit 的情况下，请返回勇者可获得的最大美味度。如果无法满足饱腹感要求，则返回 -1。
         * <p>
         * 注意：
         * <p>
         * 每种料理只能制作一次。
         * 示例 1：
         * <p>
         * 输入：meterials = [3,2,4,1,2]
         * cookbooks = [[1,1,0,1,2],[2,1,4,0,0],[3,2,4,1,0]]
         * attribute = [[3,2],[2,4],[7,6]]
         * limit = 5
         * <p>
         * 输出：7
         * <p>
         * 解释：
         * 食材数量可以满足以下两种方案：
         * 方案一：制作料理 0 和料理 1，可获得饱腹感 2+4、美味度 3+2
         * 方案二：仅制作料理 2， 可饱腹感为 6、美味度为 7
         * 因此在满足饱腹感的要求下，可获得最高美味度 7
         * <p>
         * 示例 2：
         * <p>
         * 输入：meterials = [10,10,10,10,10]
         * cookbooks = [[1,1,1,1,1],[3,3,3,3,3],[10,10,10,10,10]]
         * attribute = [[5,5],[6,6],[10,10]]
         * limit = 1
         * <p>
         * 输出：11
         * <p>
         * 解释：通过制作料理 0 和 1，可满足饱腹感，并获得最高美味度 11
         * <p>
         * 提示：
         * <p>
         * meterials.length == 5
         * 1 <= cookbooks.length == attribute.length <= 8
         * cookbooks[i].length == 5
         * attribute[i].length == 2
         * 0 <= meterials[i], cookbooks[i][j], attribute[i][j] <= 20
         * 1 <= limit <= 100
         *
         * @param materials
         * @param cookbooks
         * @param attribute
         * @param limit
         * @return
         */
        public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
            return 0;
        }

        /**
         * 3. 二叉搜索树染色
         * 通过的用户数595
         * 尝试过的用户数1861
         * 用户总通过次数618
         * 用户总提交次数4265
         * 题目难度Medium
         * 欢迎各位勇者来到力扣城，本次试炼主题为「二叉搜索树染色」。
         * <p>
         * 每位勇士面前设有一个二叉搜索树的模型，模型的根节点为 root，树上的各个节点值均不重复。初始时，所有节点均为蓝色。现在按顺序对这棵二叉树进行若干次操作， ops[i] = [type, x, y] 表示第 i 次操作为：
         * <p>
         * type 等于 0 时，将节点值范围在 [x, y] 的节点均染蓝
         * type 等于 1 时，将节点值范围在 [x, y] 的节点均染红
         * 请返回完成所有染色后，该二叉树中红色节点的数量。
         * <p>
         * 注意：
         * <p>
         * 题目保证对于每个操作的 x、y 值定出现在二叉搜索树节点中
         * 示例 1：
         * <p>
         * 输入：root = [1,null,2,null,3,null,4,null,5], ops = [[1,2,4],[1,1,3],[0,3,5]]
         * <p>
         * 输出：2
         * <p>
         * 解释：
         * 第 0 次操作，将值为 2、3、4 的节点染红；
         * 第 1 次操作，将值为 1、2、3 的节点染红；
         * 第 2 次操作，将值为 3、4、5 的节点染蓝；
         * 因此，最终值为 1、2 的节点为红色节点，返回数量 2
         * image.png
         * <p>
         * 示例 2：
         * <p>
         * 输入：root = [4,2,7,1,null,5,null,null,null,null,6]
         * ops = [[0,2,2],[1,1,5],[0,4,5],[1,5,7]]
         * <p>
         * 输出：5
         * <p>
         * 解释：
         * 第 0 次操作，将值为 2 的节点染蓝；
         * 第 1 次操作，将值为 1、2、4、5 的节点染红；
         * 第 2 次操作，将值为 4、5 的节点染蓝；
         * 第 3 次操作，将值为 5、6、7 的节点染红；
         * 因此，最终值为 1、2、5、6、7 的节点为红色节点，返回数量 5
         * image.png
         * <p>
         * 提示：
         * <p>
         * 1 <= 二叉树节点数量 <= 10^5
         * 1 <= ops.length <= 10^5
         * ops[i].length == 3
         * ops[i][0] 仅为 0 or 1
         * 0 <= ops[i][1] <= ops[i][2] <= 10^9
         * 0 <= 节点值 <= 10^9
         * <p>
         * <b>解不出来</b>
         *
         * @param root
         * @param ops
         * @return
         */
        public int getNumber(TreeNode root, int[][] ops) {

            List<Integer> list = new ArrayList<>();
            getInOrderList(root, list);
            int[] array = list.stream().mapToInt(Integer::intValue).toArray();
            System.out.println(Arrays.toString(array));
            HashMap<Integer, Integer> map = new HashMap<>(array.length);
            for (int i = 0; i < array.length; i++) {
                map.put(array[i], i);
            }
            int[] res = new int[array.length];
            Difference difference = new Difference(res);

            for (int[] op : ops) {
                int type = op[0];
                int x = op[1];
                int y = op[2];
                difference.increment(map.get(x), map.get(y), type == 1 ? 1 : -1);
            }
            int count = 0;
            int[] result = difference.getResult();
            for (int i = 0; i < result.length; i++) {
                if (result[i] > 0) count++;
            }
            return count;
        }

        private void getInOrderList(TreeNode root, List<Integer> list) {
            if (root == null) return;
            getInOrderList(root.left, list);
            list.add(root.val);
            getInOrderList(root.right, list);
        }
    }

    static class Difference {
        int[] diff;

        Difference(int[] nums) {
            assert nums.length > 0;
            diff = new int[nums.length];
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        void increment(int i, int j, int val) {
            if (val == -1) {
                if (diff[i] == -1) {
                }
                diff[i] = 0;
                diff[j + 1] = 1;

            } else {

            }
            diff[i] += val;
            if (j + 1 < diff.length) {
                diff[j + 1] -= val;
            }
        }

        int[] getResult() {
            int[] res = new int[diff.length];
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }
    }


}
