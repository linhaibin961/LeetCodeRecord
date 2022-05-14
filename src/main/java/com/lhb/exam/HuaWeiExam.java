package com.lhb.exam;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class HuaWeiExam {

    static class Question1 {
        public static void main(String[] args) {
            /**
             * 输入测试用例
             * 3
             * 2
             * 2,5,6,7,9,5,7
             * 1,7,4,3,4
             *
             * 3 表示每次从数组中拿多少个数
             * 2 表示后面有多少个数组
             * 2,5,6,7,9,5,7 数组1
             * 1,7,4,3,4 数组2
             * 循环从每个数组中获取3个数字，拼接输出。该测试用例答案是：2,5,6,1,7,4,7,9,5,3,4,7
             *
             *
             */
            Scanner in = new Scanner(System.in);
            int num = Integer.parseInt(in.nextLine());
            if (num >= 10) {
                return;
            }
            int arraySize = Integer.parseInt(in.nextLine());
            if (arraySize >= 1000) {
                return;
            }
            String[][] array = new String[arraySize][];
            for (int i = 0; i < arraySize; i++) {
                String[] split = in.nextLine().split(",");
                array[i] = split;
            }
            StringBuilder sb = new StringBuilder();
            int cycle = 0;
            int length = array.length;
            for (int i = 0; i <= length; i++) {
                if (i == length) {
                    i = i % length;
                    if (cycle * num > 100) {
                        break;
                    } else {
                        cycle++;
                    }
                }
                String[] arr = array[i];
                for (int j = cycle * num, times = num; times > 0 && j < arr.length; j++, times--) {
                    if (!arr[j].isEmpty()) {
                        sb.append(arr[j]).append(",");
                    }
                }

            }
            if (sb.length() > 0) {
                System.out.println(sb.toString().substring(0, sb.length() - 1));
            }
        }
    }

    /**
     * HUAWEI 机试题：字符串的解压缩
     * <p>
     * 题目描述：
     * <p>
     * 有一种简易压缩算法：针对全部为小写英文字母组成的字符串， 将其中连续超过两个相同字母的部分压缩为连续个数加该字母 其他部分保持原样不变. 例如字符串aaabbccccd 经过压缩变成字符串 3abb4cd
     * 请您编写解压函数,根据输入的字符串,判断其是否为合法压缩过的字符串。
     * 若输入合法则输出解压缩后的字符串，否则输出字符串"!error"来报告错误。
     * 输入描述：
     * 输入一行，为一个ASCII字符串
     * 长度不超过100字符
     * 用例保证输出的字符串长度也不会超过100字符
     * <p>
     * 输出描述：
     * 若判断输入为合法的经过压缩后的字符串
     * 则输出压缩前的字符串
     * 若输入不合法 则输出字符串"!error"
     * 示例
     * 输入：
     * 4dff
     * 输出：
     * ddddff
     * 说明：
     * 4d扩展为4个d ，故解压后的字符串为ddddff
     * <p>
     * 输入：
     * 2dff
     * 输出：
     * !error
     * 说明：
     * 2个d不需要压缩 故输入不合法
     * <p>
     * 输入：
     * 4d@A
     * 输出：
     * !error
     * 说明：
     * <p>
     * 全部由小写英文字母组成的字符串，压缩后不会出现特殊字符@和大写字母A 故输入不合法
     */
    static class Question2 {
        public static void main(String[] args) {
            /**
             * 输入测试用例
             * 4dff
             * 3dff
             * 2dff
             *
             */
            Scanner in = new Scanner(System.in);
            String s = in.nextLine();
            String errorMsg = "!error";
            if (s.isEmpty()) {
                System.out.println(errorMsg);
                return;
            }
            StringBuilder sb = new StringBuilder();
            String res = "";
            int temp = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    if (temp == 0 && '0' == c) {
                        res = errorMsg;
                        break;
                    }
                    if (temp > 0) {
                        temp = temp * 10 + c - '0';
                    } else {
                        temp = c - '0';
                    }

                } else if (c >= 'a' && c <= 'z') {
                    if (temp > 0) {
                        if (temp < 3) {
                            res = errorMsg;
                            break;
                        }
                        while (temp > 0) {
                            sb.append(c);
                            temp--;
                        }
                    } else {
                        sb.append(c);
                    }
                } else {
                    res = errorMsg;
                    break;
                }
            }
            if (errorMsg.equals(res) || sb.length() == 0) {
                System.out.println(errorMsg);
            } else {
                System.out.println(sb.toString());
            }
        }
    }

    /**
     * 第三道是服务依赖
     * 例如：
     * 第一行輸入 a1-a2,a5-a6,a2-a3，表示a1依赖a2,a5依赖a6，a2依赖a3
     * 第二行输入 a5,a2 表示a5和a2挂了，让你根据服务出现的顺序，输出哪些服务还存活
     * 如该测试用例会输出：a6和a3
     */
    static class Question3 {
        public static void main(String[] args) {
            /**
             * 输入测试用例
             * a1-a2,a5-a6,a2-a3
             * a5,a2
             */
            Scanner in = new Scanner(System.in);
            String dependency = in.nextLine();
            String errorServiceStr = in.nextLine();
            String[] dependencyArray = dependency.split(",");
            LinkedHashMap<String, LinkedList<String>> graph = new LinkedHashMap<>();
            for (String s : dependencyArray) {
                String[] sp = s.split("-");
                String from = sp[1];
                String to = sp[0];
                LinkedList<String> list = graph.getOrDefault(from, new LinkedList<>());
                list.add(to);
                graph.put(from, list);

                LinkedList<String> list2 = graph.getOrDefault(to, new LinkedList<>());
//                list2.add(to);
                graph.put(to, list2);


            }
            String[] errorServiceArray = errorServiceStr.split(",");
            for (String errorService : errorServiceArray) {
                dfs(errorService, graph);
            }
            StringBuilder sb = new StringBuilder();
            for (String s : graph.keySet()) {
                sb.append(s).append(",");
            }

            if (sb.length() > 0) {
                System.out.println(sb.toString().substring(0, sb.length() - 1));
            } else {
                System.out.println(",");
            }
        }

        public static void dfs(String errorService, LinkedHashMap<String, LinkedList<String>> graph) {
            if (!graph.containsKey(errorService)) {
                return;
            }
            LinkedList<String> list = graph.remove(errorService);
            for (String s : list) {
                dfs(s, graph);
            }
        }
    }
}