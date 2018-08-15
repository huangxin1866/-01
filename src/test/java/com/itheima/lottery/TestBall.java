package com.itheima.lottery;

import org.junit.Test;

import java.util.*;

public class TestBall {

    @Test
    public void test1(){
        // 04,05,07,11,12,25  33个红球中随机产生 6个红球1个蓝球
        // set 集合 : 不能重复  并且有序
        TreeSet<Integer> sets = new TreeSet();

        while(sets.size() < 6){
            int i = new Random().nextInt(33) + 1; // 1-33
            sets.add(i);
        }

        List<String> balls = new ArrayList<>();
        for (Integer i : sets){
            System.out.print(String.format("%02d", i));
            balls.add(String.format("%02d", i));
        }

        int j = new Random().nextInt(16) + 1;
        String blue = String.format("%02d", j);

        System.out.println("蓝球:"+blue);
    }
}
