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