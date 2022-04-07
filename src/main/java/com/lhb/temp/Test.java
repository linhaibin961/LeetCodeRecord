package com.lhb.temp;

import java.util.Scanner;

/**
 * @program: LeetCodeRecord
 * @description: 训练语法
 * @author: linhaibin
 * @create: 2022-04-01 17:27
 **/
public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        float f = in.nextFloat();
        System.out.println((int) f);
        System.out.println((int) f+0.5f);
        System.out.println(Math.round(f));
    }


}
