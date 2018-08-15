package com.itheima.lottery.controller;

import com.alibaba.fastjson.JSONArray;
import com.itheima.lottery.bean.Cart;
import com.itheima.lottery.bean.CartItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 购物车模块相关内容
/*
    购物车对象:
        保存所有的商品
        向购物车中添加商品
        删除指定商品
        清空购物车
        购物车总金额

    购物项对象: 购物车的一箱内容
        红球
        蓝球
        购买了几注
        单注的金额
        小计  : 几注 * 单注的金额

*/
@Controller
@RequestMapping("/cart")
public class CartController {

    // 跳转去到选号的页面
    @GetMapping("/selectUI")
    public String selectUI(String randomOneFlag,Model model){
        model.addAttribute("randomOneFlag",randomOneFlag);
        return "select";
    }


    @PostMapping("/addToCart")
    @ResponseBody
    public Map<String,Integer> add(String balls,HttpSession session){
        //1.接收参数

        //2.将数据保存到购物车中 thank you , no thank you  , you are welcome
        //[{"red":"02,12,14,17,25,28","blue":"14"},{"red":"03,08,16,24,30,32","blue":"08"}]
        List<CartItem> cartItems = JSONArray.parseArray(balls, CartItem.class);

        //将每一个购物项都保存到购物车中
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        for (CartItem cartItem : cartItems) {
            cartItem.setCount(1);
            cart.add(cartItem);
        }

        //3.将购物车中的数量响应给页面
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("cartSize",cart.getCartItems().size());
        return map; // {cartSize:12}
    }


    @GetMapping("/cartUI")
    public String cartUI(HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null){
            cart = new Cart();
            session.setAttribute("cart",cart);
        }

        return "cart";
    }
    ///cart/remove?key=02,05,06,12,20,27-02
    @GetMapping("/remove")
    public String remove(String key,HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");

        cart.remove(key);
        //重定向去到购物车页面
        return "redirect:/cart/cartUI";
    }
     @GetMapping("/clear")
    public String clear(String key,HttpSession session){
        Cart cart = (Cart) session.getAttribute("cart");

        cart.clear();
        //重定向去到购物车页面
        return "redirect:/cart/cartUI";
    }

}
