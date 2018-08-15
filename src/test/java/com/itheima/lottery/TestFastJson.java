package com.itheima.lottery;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.itheima.lottery.bean.CartItem;
import com.itheima.lottery.bean.User;
import com.itheima.lottery.utils.UUIDUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestFastJson {
// java --- json字符串   ,,  json字符串 --- java
    @Test
    public void test1(){
        //java对象转json字符串
        User user = new User();
        user.setUid(UUIDUtils.getId());
        user.setUsername("张三");

        System.out.println(JSON.toJSON(user).toString());
    }

    @Test
    public void test2(){
        List<String> list = new ArrayList<String>();
        list.add("jl");
        list.add("xmq");
        list.add("dl");

        System.out.println(JSON.toJSON(list).toString());
    }

    @Test
    public void test3(){
        String str = "[{\"red\":\"02,12,14,17,25,28\",\"blue\":\"14\"},{\"red\":\"03,08,16,24,30,32\",\"blue\":\"08\"}]";

        List<CartItem> cartItems = JSONArray.parseArray(str, CartItem.class);

        System.out.println(cartItems);
    }
}
