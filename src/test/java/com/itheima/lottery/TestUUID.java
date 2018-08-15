package com.itheima.lottery;

import com.itheima.lottery.utils.MD5Utils;
import org.junit.Test;

import java.util.UUID;

public class TestUUID {

    @Test
    public void test1(){

        for (int i=0;i<10;i++){
            System.out.println(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
        }
    }
    @Test
    public void test2(){
        // 123456 e10adc3949ba59abbe56e057f20f883e
        String password="123456";  // e10adc3949ba59abbe56e057f20f883e 00c66aaf5f2c3f49946f15c1ad2ea0d3
        System.out.println(MD5Utils.encode(password));

        //加盐处理
        String salt = "#$%%#%%^";
        String newPassword = password + salt;
        String encode = MD5Utils.encode(newPassword);
        System.out.println(encode);
    }
}
